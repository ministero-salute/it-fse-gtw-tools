/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.pdfbox.signature.cert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.pdmodel.encryption.SecurityProvider;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;

/**
 * Copied from Apache CXF 2.4.9, initial version:
 * https://svn.apache.org/repos/asf/cxf/tags/cxf-2.4.9/distribution/src/main/release/samples/sts_issue_operation/src/main/java/demo/sts/provider/cert/
 * 
 */
public final class CRLVerifier
{
    private static final Log LOG = LogFactory.getLog(CRLVerifier.class);

    private CRLVerifier()
    {
    }

     
    public static void verifyCertificateCRLs(X509Certificate cert, Date signDate, Set<X509Certificate> additionalCerts)
            throws CertificateVerificationException, RevokedCertificateException {
        try {
            Date now = Calendar.getInstance().getTime();
            Exception firstException = null;
            List<String> crlDistributionPointsURLs = getCrlDistributionPoints(cert);
            for (String crlDistributionPointsURL : crlDistributionPointsURLs)
            {
                LOG.info("Checking distribution point URL: " + crlDistributionPointsURL);
                X509CRL crl;
                try {
                    crl = downloadCRL(crlDistributionPointsURL);
                }
                catch (Exception ex)
                {
                    // e.g. LDAP behind corporate proxy
                    LOG.warn("Caught " + ex.getClass().getSimpleName() + " downloading CRL, will try next distribution point if available");
                    if (firstException == null)
                    {
                        firstException = ex;
                    }
                    continue;
                }

                Set<X509Certificate> mergedCertSet = CertificateVerifier.downloadExtraCertificates(crl);
                mergedCertSet.addAll(additionalCerts);

                // Verify CRL, see wikipedia:
                // "To validate a specific CRL prior to relying on it,
                //  the certificate of its corresponding CA is needed"
                X509Certificate crlIssuerCert = null;
                for (X509Certificate possibleCert : mergedCertSet)
                {
                    try
                    {
                        cert.verify(possibleCert.getPublicKey(), SecurityProvider.getProvider().getName());
                        crlIssuerCert = possibleCert;
                        break;
                    }
                    catch (GeneralSecurityException ex)
                    {
                        // not the issuer
                    }
                }
                if (crlIssuerCert == null)
                {
                    throw new CertificateVerificationException(
                            "Certificate for " + crl.getIssuerX500Principal() +
                            "not found in certificate chain, so the CRL at " +
                            crlDistributionPointsURL + " could not be verified");
                }
                crl.verify(crlIssuerCert.getPublicKey(), SecurityProvider.getProvider().getName());
                if (crl.getThisUpdate().after(now))
                {
                    LOG.error("CRL not yet valid, thisUpdate is " + crl.getThisUpdate());
                }
                if (crl.getNextUpdate().before(now))
                {
                    LOG.error("CRL no longer valid, nextUpdate is " + crl.getNextUpdate());
                }

                if (!crl.getIssuerX500Principal().equals(cert.getIssuerX500Principal()))
                {
                    LOG.info("CRL issuer certificate is not identical to cert issuer, check needed");
                    CertificateVerifier.verifyCertificate(crlIssuerCert, mergedCertSet, true, now);
                    LOG.info("CRL issuer certificate checked successfully");
                }
                else
                {
                    LOG.info("CRL issuer certificate is identical to cert issuer, no extra check needed");
                }

                checkRevocation(crl, cert, signDate, crlDistributionPointsURL);
 
                return;
            }
            if (firstException != null)
            {
                throw firstException;
            }
        }
        catch (CertificateVerificationException | RevokedCertificateException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
            throw new CertificateVerificationException(
                    "Cannot verify CRL for certificate: "
                    + cert.getSubjectX500Principal(), ex);

        }
    }
 
    private static void checkRevocation(
        X509CRL crl, X509Certificate cert, Date signDate, String crlDistributionPointsURL)
                throws RevokedCertificateException
    {
        X509CRLEntry revokedCRLEntry = crl.getRevokedCertificate(cert);
        if (revokedCRLEntry != null &&
                revokedCRLEntry.getRevocationDate().compareTo(signDate) <= 0)
        {
            throw new RevokedCertificateException(
                    "The certificate was revoked by CRL " +
                            crlDistributionPointsURL + " on " + revokedCRLEntry.getRevocationDate(),
                    revokedCRLEntry.getRevocationDate());
        }
        else if (revokedCRLEntry != null)
        {
            LOG.info("The certificate was revoked after signing by CRL " +
                    crlDistributionPointsURL + " on " + revokedCRLEntry.getRevocationDate());
        }
        else
        {
            LOG.info("The certificate was not revoked by CRL " + crlDistributionPointsURL);
        }
    }

    /**
     * Downloads CRL from given URL. Supports http, https, ftp and ldap based URLs.
     */
    private static X509CRL downloadCRL(String crlURL) throws IOException,
            CertificateException, CRLException,
            CertificateVerificationException, NamingException
    {
        if (crlURL.startsWith("http://") || crlURL.startsWith("https://")
                || crlURL.startsWith("ftp://"))
        {
            return downloadCRLFromWeb(crlURL);
        }
        else if (crlURL.startsWith("ldap://"))
        {
            return downloadCRLFromLDAP(crlURL);
        }
        else
        {
            throw new CertificateVerificationException(
                    "Can not download CRL from certificate "
                    + "distribution point: " + crlURL);
        }
    }

    private static X509CRL downloadCRLFromLDAP(String ldapURL) throws CertificateException,
            NamingException, CRLException,
            CertificateVerificationException
    {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapURL);

        env.put("com.sun.jndi.ldap.connect.timeout", "1000");

        DirContext ctx = new InitialDirContext(env);
        Attributes avals = ctx.getAttributes("");
        Attribute aval = avals.get("certificateRevocationList;binary");
        byte[] val = (byte[]) aval.get();
        if (val == null || val.length == 0)
        {
            throw new CertificateVerificationException("Can not download CRL from: " + ldapURL);
        }
        else
        {
            InputStream inStream = new ByteArrayInputStream(val);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return (X509CRL) cf.generateCRL(inStream);
        }
    }
 
    private static X509CRL downloadCRLFromWeb(String crlURL)
            throws IOException, CertificateException, CRLException
    {
        InputStream crlStream = new URL(crlURL).openStream();
        try
        {
            return (X509CRL) CertificateFactory.getInstance("X.509").generateCRL(crlStream);
        }
        finally
        {
            crlStream.close();
        }
    }
 
    private static List<String> getCrlDistributionPoints(X509Certificate cert)
            throws IOException
    {
        byte[] crldpExt = cert.getExtensionValue(Extension.cRLDistributionPoints.getId());
        if (crldpExt == null)
        {
            return new ArrayList<>();
        }
        ASN1Primitive derObjCrlDP;
        try(ASN1InputStream oAsnInStream = new ASN1InputStream(crldpExt)) {
        	derObjCrlDP = oAsnInStream.readObject();
        }
        if (!(derObjCrlDP instanceof ASN1OctetString))
        {
            LOG.warn("CRL distribution points for certificate subject " +
                    cert.getSubjectX500Principal().getName() +
                    " should be an octet string, but is " + derObjCrlDP);
            return new ArrayList<>();
        }
        ASN1OctetString dosCrlDP = (ASN1OctetString) derObjCrlDP;
        byte[] crldpExtOctets = dosCrlDP.getOctets();
        ASN1Primitive derObj2;
        try(ASN1InputStream oAsnInStream2 = new ASN1InputStream(crldpExtOctets)){
        	derObj2 = oAsnInStream2.readObject();
        }
        CRLDistPoint distPoint = CRLDistPoint.getInstance(derObj2);
        List<String> crlUrls = new ArrayList<>();
        for (DistributionPoint dp : distPoint.getDistributionPoints())
        {
            DistributionPointName dpn = dp.getDistributionPoint();
            // Look for URIs in fullName
            if (dpn != null && dpn.getType() == DistributionPointName.FULL_NAME)
            {
                // Look for an URI
                for (GeneralName genName : GeneralNames.getInstance(dpn.getName()).getNames())
                {
                    if (genName.getTagNo() == GeneralName.uniformResourceIdentifier)
                    {
                        String url = DERIA5String.getInstance(genName.getName()).getString();
                        crlUrls.add(url);
                    }
                }
            }
        }
        return crlUrls;
    }
}
