package org.apache.pdfbox.signature;
/*
 * Copyright 2015 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Enumeration;

import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

public abstract class CreateSignatureBasePades implements SignatureInterface {

    private PrivateKey privateKey;
    private Certificate[] certificateChain;
    private String tsaUrl;
    private boolean externalSigning;

  
    protected CreateSignatureBasePades(KeyStore keystore, char[] pin) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, CertificateException {

    	Enumeration<String> aliases = keystore.aliases();
        String alias;
        Certificate cert = null;
        while (cert == null && aliases.hasMoreElements()) {
            alias = aliases.nextElement();
            setPrivateKey((PrivateKey) keystore.getKey(alias, pin));
            Certificate[] certChain = keystore.getCertificateChain(alias);
            if (certChain != null) {
                setCertificateChain(certChain);
                cert = certChain[0];
                if (cert instanceof X509Certificate) {
                    // avoid expired certificate
                    ((X509Certificate) cert).checkValidity();

                    SigUtils.checkCertificateUsage((X509Certificate) cert);
                }
            }
        }

        if (cert == null) {
            throw new IOException("Could not find certificate");
        }
    }

    public final void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public final void setCertificateChain(final Certificate[] certificateChain) {
        this.certificateChain = certificateChain;
    }

    public Certificate[] getCertificateChain() {
        return certificateChain;
    }

    public void setTsaUrl(String tsaUrl) {
        this.tsaUrl = tsaUrl;
    }

    private static org.bouncycastle.asn1.cms.Attribute createSigningCertificateV2Attribute(X509Certificate[] signingCert,
            String digestAlgorithm, String digestAlgorithmOID)
            throws IOException, NoSuchAlgorithmException, CertificateEncodingException {

        org.bouncycastle.asn1.ess.ESSCertIDv2[] esscert = new org.bouncycastle.asn1.ess.ESSCertIDv2[signingCert.length];

        for (int i = 0; i < esscert.length; i++) {

            java.security.MessageDigest md = java.security.MessageDigest.getInstance(digestAlgorithm);
            byte[] dgst = md.digest(signingCert[i].getEncoded());

            org.bouncycastle.asn1.x509.AlgorithmIdentifier algoID
                    = new org.bouncycastle.asn1.x509.AlgorithmIdentifier(
                            new org.bouncycastle.asn1.ASN1ObjectIdentifier(digestAlgorithmOID));

            org.bouncycastle.asn1.ASN1Primitive obj;
            
            try(org.bouncycastle.asn1.ASN1InputStream asn1InputStream = new org.bouncycastle.asn1.ASN1InputStream(signingCert[i].getIssuerX500Principal().getEncoded())) {
            	obj = asn1InputStream.readObject();
            }

            org.bouncycastle.asn1.x500.X500Name name = org.bouncycastle.asn1.x500.X500Name.getInstance(obj);

            org.bouncycastle.asn1.x509.GeneralName gn
                    = new org.bouncycastle.asn1.x509.GeneralName(name);
            org.bouncycastle.asn1.x509.GeneralNames gns
                    = new org.bouncycastle.asn1.x509.GeneralNames(gn);

            org.bouncycastle.asn1.ASN1Integer sn
                    = new org.bouncycastle.asn1.ASN1Integer(signingCert[i].getSerialNumber());

            org.bouncycastle.asn1.x509.IssuerSerial is = new org.bouncycastle.asn1.x509.IssuerSerial(gns, sn);

            esscert[i] = new org.bouncycastle.asn1.ess.ESSCertIDv2(algoID, dgst, is);

        }

        org.bouncycastle.asn1.ess.SigningCertificateV2 signingCertV2 = new org.bouncycastle.asn1.ess.SigningCertificateV2(esscert);

        org.bouncycastle.asn1.cms.Attribute a;
        a = new org.bouncycastle.asn1.cms.Attribute(
                new org.bouncycastle.asn1.ASN1ObjectIdentifier("1.2.840.113549.1.9.16.2.47"),
                new org.bouncycastle.asn1.DERSet(signingCertV2.toASN1Primitive()));

        return a;
    }
 
    @Override
    public byte[] sign(InputStream content) throws IOException {
        try {
            CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
            X509Certificate cert = (X509Certificate) certificateChain[0];
            java.security.PublicKey pubk = cert.getPublicKey();
            String type = pubk.getAlgorithm();
            ContentSigner sha1Signer = null;
            if (type.equalsIgnoreCase("EC")) {
                sha1Signer = new JcaContentSignerBuilder("SHA256WithECDSA").build(privateKey);
            } else if (type.equalsIgnoreCase("RSA")) {
                sha1Signer = new JcaContentSignerBuilder("SHA256WithRSA").build(privateKey);
            }
            
            org.bouncycastle.asn1.ASN1EncodableVector dv = new org.bouncycastle.asn1.ASN1EncodableVector();
            String digestAlgorithm = "SHA-256";
            String digestAlgorithmOID = "2.16.840.1.101.3.4.2.1";
            org.bouncycastle.asn1.cms.Attribute a = createSigningCertificateV2Attribute(
                    new X509Certificate[]{cert}, digestAlgorithm, digestAlgorithmOID);
            dv.add(a);
            org.bouncycastle.asn1.cms.AttributeTable signedAttr = new org.bouncycastle.asn1.cms.AttributeTable(dv);
            org.bouncycastle.cms.CMSAttributeTableGenerator atgS;
            atgS = new PAdESSignedAttributeTableGenerator(signedAttr);
            
            org.bouncycastle.operator.DigestCalculatorProvider dcp
                    = new org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder().build();
            org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder sigb;
            sigb = new org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder(dcp);
            sigb.setSignedAttributeGenerator(atgS);
            sigb.setUnsignedAttributeGenerator(null);
            gen.addSignerInfoGenerator(sigb.build(sha1Signer, cert));

            
            gen.addCertificates(new JcaCertStore(Arrays.asList(certificateChain)));
            CMSProcessableInputStream msg = new CMSProcessableInputStream(content);
            CMSSignedData signedData = gen.generate(msg, false);
            if (tsaUrl != null && tsaUrl.length() > 0) {
                ValidationTimeStamp validation = new ValidationTimeStamp(tsaUrl);
                signedData = validation.addSignedTimeStamp(signedData);
            }
            return signedData.getEncoded();
        } catch (GeneralSecurityException | CMSException | OperatorCreationException e) {
            throw new IOException(e);
        }
    }

     
    public void setExternalSigning(boolean externalSigning) {
        this.externalSigning = externalSigning;
    }

    public boolean isExternalSigning() {
        return externalSigning;
    }
}
