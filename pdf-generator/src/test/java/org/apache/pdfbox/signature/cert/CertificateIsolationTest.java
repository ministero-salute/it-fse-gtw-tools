package org.apache.pdfbox.signature.cert;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.security.cert.*;
import java.util.*;
import org.apache.pdfbox.signature.SignatureIsolationTest;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.ocsp.*;
import org.bouncycastle.operator.jcajce.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

@Tag("ISOLATION")
class CertificateIsolationTest {
    @TempDir java.nio.file.Path temp;
    X509Certificate cert;

    @BeforeAll static void keys() throws Exception { SignatureIsolationTest.keys(); }
    @BeforeEach void setup() { cert = SignatureIsolationTest.certificate; }

    static Object invoke(Object target, String name, Class<?>[] types, Object... args) throws Exception {
        Method method = (target instanceof Class ? (Class<?>) target : target.getClass()).getDeclaredMethod(name, types);
        method.setAccessible(true);
        try { return method.invoke(target instanceof Class ? null : target, args); }
        catch (InvocationTargetException e) { if (e.getCause() instanceof Exception) throw (Exception) e.getCause(); throw e; }
    }

    @Test void validatesTrustAnchorsAndRejectsUntrustedCertificates() throws Exception {
        assertThrows(CertificateVerificationException.class, () -> CertificateVerifier.verifyCertificate(cert, Collections.singleton(cert), false, new Date()));
        assertThrows(CertificateVerificationException.class, () -> CertificateVerifier.verifyCertificate(cert, Collections.emptySet(), true, new Date()));
        assertNotNull(CertificateVerifier.verifyCertificate(cert, Collections.singleton(cert), true, new Date()));
        assertTrue(CertificateVerifier.downloadExtraCertificates(cert).isEmpty());
        CRLVerifier.verifyCertificateCRLs(cert, new Date(), Collections.singleton(cert));
        X509Certificate broken = mock(X509Certificate.class);
        when(broken.getSubjectX500Principal()).thenReturn(cert.getSubjectX500Principal());
        when(broken.getExtensionValue(org.bouncycastle.asn1.x509.Extension.authorityInfoAccess.getId())).thenReturn(new DEROctetString(new byte[]{1}).getEncoded());
        assertTrue(CertificateVerifier.downloadExtraCertificates(broken).isEmpty());
        when(broken.getExtensionValue(org.bouncycastle.asn1.x509.Extension.authorityInfoAccess.getId())).thenReturn(new DEROctetString(new ASN1Integer(1).getEncoded()).getEncoded());
        assertTrue(CertificateVerifier.downloadExtraCertificates(broken).isEmpty());
        java.nio.file.Path file = java.nio.file.Files.write(temp.resolve("cert.der"), cert.getEncoded());
        AuthorityInformationAccess aia = new AuthorityInformationAccess(AccessDescription.id_ad_caIssuers, new GeneralName(GeneralName.uniformResourceIdentifier, file.toUri().toString()));
        when(broken.getExtensionValue(org.bouncycastle.asn1.x509.Extension.authorityInfoAccess.getId())).thenReturn(new DEROctetString(aia).getEncoded());
        assertEquals(Collections.singleton(cert), CertificateVerifier.downloadExtraCertificates(broken));
        aia = new AuthorityInformationAccess(AccessDescription.id_ad_ocsp, new GeneralName(GeneralName.uniformResourceIdentifier, "http://isolation.invalid"));
        when(broken.getExtensionValue(org.bouncycastle.asn1.x509.Extension.authorityInfoAccess.getId())).thenReturn(new DEROctetString(aia).getEncoded());
        assertTrue(CertificateVerifier.downloadExtraCertificates(broken).isEmpty());
        assertEquals("http://isolation.invalid", invoke(CertificateVerifier.class, "extractOCSPURL", new Class[]{X509Certificate.class}, broken));
    }

    @Test void crlChecksRevocationDatesAndDistributionPoints() throws Exception {
        X509CRL crl = mock(X509CRL.class);
        X509CRLEntry entry = mock(X509CRLEntry.class);
        Class<?>[] signature = {X509CRL.class, X509Certificate.class, Date.class, String.class};
        invoke(CRLVerifier.class, "checkRevocation", signature, crl, cert, new Date(1000), "test");
        when(crl.getRevokedCertificate(cert)).thenReturn(entry);
        when(entry.getRevocationDate()).thenReturn(new Date(2000));
        invoke(CRLVerifier.class, "checkRevocation", signature, crl, cert, new Date(1000), "test");
        assertThrows(RevokedCertificateException.class, () -> invoke(CRLVerifier.class, "checkRevocation", signature, crl, cert, new Date(3000), "test"));
        X509Certificate broken = mock(X509Certificate.class);
        when(broken.getSubjectX500Principal()).thenReturn(cert.getSubjectX500Principal());
        when(broken.getExtensionValue(org.bouncycastle.asn1.x509.Extension.cRLDistributionPoints.getId())).thenReturn(new ASN1Integer(1).getEncoded());
        assertTrue(((List<?>) invoke(CRLVerifier.class, "getCrlDistributionPoints", new Class[]{X509Certificate.class}, broken)).isEmpty());
        DistributionPoint point = new DistributionPoint(new DistributionPointName(new GeneralNames(new GeneralName(GeneralName.uniformResourceIdentifier, "invalid://crl"))), null, null);
        when(broken.getExtensionValue(org.bouncycastle.asn1.x509.Extension.cRLDistributionPoints.getId())).thenReturn(new DEROctetString(new CRLDistPoint(new DistributionPoint[]{point})).getEncoded());
        assertEquals(Collections.singletonList("invalid://crl"), invoke(CRLVerifier.class, "getCrlDistributionPoints", new Class[]{X509Certificate.class}, broken));
        assertThrows(CertificateVerificationException.class, () -> CRLVerifier.verifyCertificateCRLs(broken, new Date(), Collections.singleton(cert)));
    }

    private BasicOCSPResp response(CertificateStatus status, boolean hash, boolean includeCert, Date start, Date end) throws Exception {
        JcaDigestCalculatorProviderBuilder digest = new JcaDigestCalculatorProviderBuilder();
        BasicOCSPRespBuilder builder = hash ? new BasicOCSPRespBuilder(org.bouncycastle.asn1.x509.SubjectPublicKeyInfo.getInstance(cert.getPublicKey().getEncoded()), digest.build().get(CertificateID.HASH_SHA1))
                : new BasicOCSPRespBuilder(new RespID(new X500Name(cert.getSubjectX500Principal().getName())));
        CertificateID id = new CertificateID(digest.build().get(CertificateID.HASH_SHA1), new JcaX509CertificateHolder(cert), cert.getSerialNumber());
        builder.addResponse(id, status, start, end, null);
        return builder.build(new JcaContentSignerBuilder("SHA256withRSA").build(SignatureIsolationTest.pair.getPrivate()), includeCert ? new X509CertificateHolder[]{new JcaX509CertificateHolder(cert)} : null, new Date());
    }

    @Test void ocspVerifiesSignaturesResponderIdentityAndStatuses() throws Exception {
        for (boolean hash : new boolean[]{false, true}) for (boolean include : new boolean[]{false, true}) {
            OcspHelper helper = new OcspHelper(cert, new Date(), cert, Collections.singleton(cert), "unused");
            BasicOCSPResp basic = response(CertificateStatus.GOOD, hash, include, new Date(System.currentTimeMillis() - 60000), new Date(System.currentTimeMillis() + 60000));
            OCSPResp response = new OCSPRespBuilder().build(OCSPRespBuilder.SUCCESSFUL, basic);
            invoke(helper, "verifyOcspResponse", new Class[]{OCSPResp.class}, response);
            assertEquals(cert, helper.getOcspResponderCertificate()); assertEquals(cert, helper.getCertificateToCheck());
        }
        for (CertificateStatus status : new CertificateStatus[]{new UnknownStatus(), new RevokedStatus(new Date(0), 0), new RevokedStatus(new Date(System.currentTimeMillis() + 60000), 0)}) {
            OcspHelper helper = new OcspHelper(cert, new Date(), cert, Collections.singleton(cert), "unused");
            OCSPResp response = new OCSPRespBuilder().build(0, response(status, false, true, new Date(0), new Date(System.currentTimeMillis() + 60000)));
            if (status instanceof RevokedStatus && ((RevokedStatus) status).getRevocationTime().after(new Date())) {
                invoke(helper, "verifyOcspResponse", new Class[]{OCSPResp.class}, response);
            } else assertThrows(Exception.class, () -> invoke(helper, "verifyOcspResponse", new Class[]{OCSPResp.class}, response));
        }
        OcspHelper helper = new OcspHelper(cert, new Date(), cert, Collections.emptySet(), "unused");
        for (int status : new int[]{1, 2, 3, 5, 6, 4}) {
            OCSPResp response = new OCSPRespBuilder().build(status, null);
            assertThrows(OCSPException.class, () -> invoke(helper, "verifyRespStatus", new Class[]{OCSPResp.class}, response));
        }
        assertThrows(OCSPException.class, () -> invoke(helper, "verifyRespStatus", new Class[]{OCSPResp.class}, new Object[]{null}));
        OCSPReq req = (OCSPReq) invoke(helper, "generateOCSPRequest", new Class[0]);
        assertEquals(1, req.getRequestList().length);
        for (Date[] dates : new Date[][]{{null, null}, {new Date(), null}, {new Date(System.currentTimeMillis() + 60000), new Date(System.currentTimeMillis() + 120000)}, {new Date(0), new Date(1)}}) {
            SingleResp single = mock(SingleResp.class); when(single.getThisUpdate()).thenReturn(dates[0]); when(single.getNextUpdate()).thenReturn(dates[1]);
            assertThrows(OCSPException.class, () -> invoke(helper, "checkOcspResponseFresh", new Class[]{SingleResp.class}, single));
        }
        BasicOCSPResp basic = mock(BasicOCSPResp.class);
        assertEquals(false, invoke(helper, "checkNonce", new Class[]{BasicOCSPResp.class}, basic));
        org.bouncycastle.asn1.x509.Extension nonce = req.getExtension(org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers.id_pkix_ocsp_nonce);
        when(basic.getExtension(nonce.getExtnId())).thenReturn(nonce);
        assertEquals(true, invoke(helper, "checkNonce", new Class[]{BasicOCSPResp.class}, basic));
        when(basic.getExtension(nonce.getExtnId())).thenReturn(new org.bouncycastle.asn1.x509.Extension(nonce.getExtnId(), false, new DEROctetString(new byte[0])));
        assertThrows(OCSPException.class, () -> invoke(helper, "checkNonce", new Class[]{BasicOCSPResp.class}, basic));
    }

    @Test void ocspTransportIsLocalAndChecksHttpStatus() throws Exception {
        for (int status : new int[]{200, 503}) {
            byte[] body = new OCSPRespBuilder().build(0, null).getEncoded();
            com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
            server.createContext("/", exchange -> {
                exchange.getRequestBody().readAllBytes();
                exchange.getResponseHeaders().set("Content-Type", "application/ocsp-response");
                exchange.sendResponseHeaders(status, body.length);
                try (OutputStream out = exchange.getResponseBody()) { out.write(body); }
            });
            server.start();
            try {
                OcspHelper helper = new OcspHelper(cert, new Date(), cert, Collections.singleton(cert), "http://127.0.0.1:" + server.getAddress().getPort());
                if (status == 200) assertEquals(0, helper.getResponseOcsp().getStatus());
                else assertThrows(IOException.class, helper::getResponseOcsp);
            } finally { server.stop(0); }
        }
    }

    @Test void certificateOcspValidationChecksResponderTrust() throws Exception {
        OcspHelper helper = mock(OcspHelper.class);
        X509Certificate responder = mock(X509Certificate.class);
        BasicOCSPResp basic = response(CertificateStatus.GOOD, false, true, new Date(0), new Date(System.currentTimeMillis() + 60000));
        when(helper.getResponseOcsp()).thenReturn(new OCSPRespBuilder().build(0, basic));
        when(helper.getOcspResponderCertificate()).thenReturn(responder);
        when(responder.getExtensionValue(org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers.id_pkix_ocsp_nocheck.getId())).thenReturn(new byte[0]);
        invoke(CertificateVerifier.class, "verifyOCSP", new Class[]{OcspHelper.class, Set.class}, helper, Collections.singleton(cert));
        when(responder.getExtensionValue(anyString())).thenReturn(null);
        when(helper.getCertificateToCheck()).thenReturn(responder);
        invoke(CertificateVerifier.class, "verifyOCSP", new Class[]{OcspHelper.class, Set.class}, helper, Collections.singleton(cert));
        when(helper.getOcspResponderCertificate()).thenReturn(cert);
        when(helper.getCertificateToCheck()).thenReturn(responder);
        invoke(CertificateVerifier.class, "verifyOCSP", new Class[]{OcspHelper.class, Set.class}, helper, Collections.singleton(cert));
        when(helper.getResponseOcsp()).thenReturn(new OCSPRespBuilder().build(2, null));
        assertThrows(CertificateVerificationException.class, () -> invoke(CertificateVerifier.class, "verifyOCSP", new Class[]{OcspHelper.class, Set.class}, helper, Collections.singleton(cert)));
    }

    @Test void crlDownloadVerifiesLocalSignedRevocationList() throws Exception {
        org.bouncycastle.cert.X509v2CRLBuilder builder = new org.bouncycastle.cert.X509v2CRLBuilder(new X500Name(cert.getSubjectX500Principal().getName()), new Date(System.currentTimeMillis() - 60000));
        builder.setNextUpdate(new Date(System.currentTimeMillis() + 60000));
        byte[] encoded = builder.build(new JcaContentSignerBuilder("SHA256withRSA").build(SignatureIsolationTest.pair.getPrivate())).getEncoded();
        com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            exchange.sendResponseHeaders(200, encoded.length);
            try (OutputStream out = exchange.getResponseBody()) { out.write(encoded); }
        }); server.start();
        try {
            String uri = "http://127.0.0.1:" + server.getAddress().getPort();
            X509Certificate leaf = mock(X509Certificate.class);
            when(leaf.getPublicKey()).thenReturn(cert.getPublicKey());
            when(leaf.getIssuerX500Principal()).thenReturn(cert.getSubjectX500Principal());
            when(leaf.getSubjectX500Principal()).thenReturn(cert.getSubjectX500Principal());
            when(leaf.getSerialNumber()).thenReturn(java.math.BigInteger.TEN);
            DistributionPoint dp = new DistributionPoint(new DistributionPointName(new GeneralNames(new GeneralName(GeneralName.uniformResourceIdentifier, uri))), null, null);
            when(leaf.getExtensionValue(org.bouncycastle.asn1.x509.Extension.cRLDistributionPoints.getId())).thenReturn(new DEROctetString(new CRLDistPoint(new DistributionPoint[]{dp})).getEncoded());
            assertDoesNotThrow(() -> CRLVerifier.verifyCertificateCRLs(leaf, new Date(), Collections.singleton(cert)));
            assertThrows(CertificateVerificationException.class, () -> CRLVerifier.verifyCertificateCRLs(leaf, new Date(), Collections.emptySet()));
        } finally { server.stop(0); }
    }

    @Test void ldapCrlTransportHandlesEmptyAndValidResults() throws Exception {
        org.bouncycastle.cert.X509v2CRLBuilder builder = new org.bouncycastle.cert.X509v2CRLBuilder(new X500Name(cert.getSubjectX500Principal().getName()), new Date());
        builder.setNextUpdate(new Date(System.currentTimeMillis() + 60000));
        byte[] encoded = builder.build(new JcaContentSignerBuilder("SHA256withRSA").build(SignatureIsolationTest.pair.getPrivate())).getEncoded();
        for (byte[] bytes : new byte[][]{null, new byte[0], encoded}) {
            javax.naming.directory.Attributes attrs = new javax.naming.directory.BasicAttributes();
            attrs.put(new javax.naming.directory.BasicAttribute("certificateRevocationList;binary", bytes));
            try (org.mockito.MockedConstruction<javax.naming.directory.InitialDirContext> ignored = mockConstruction(javax.naming.directory.InitialDirContext.class,
                    (context, construction) -> when(context.getAttributes("")).thenReturn(attrs))) {
                if (bytes == null || bytes.length == 0) {
                    assertThrows(CertificateVerificationException.class, () -> invoke(CRLVerifier.class, "downloadCRL", new Class[]{String.class}, "ldap://local.test"));
                } else assertInstanceOf(X509CRL.class, invoke(CRLVerifier.class, "downloadCRL", new Class[]{String.class}, "ldap://local.test"));
            }
        }
    }

    @Test void issuerRevocationsFallBackToCrlWhenOcspFails() throws Exception {
        X509Certificate leaf = mock(X509Certificate.class);
        when(leaf.getPublicKey()).thenReturn(cert.getPublicKey());
        when(leaf.getSubjectX500Principal()).thenReturn(cert.getSubjectX500Principal());
        when(leaf.getSerialNumber()).thenReturn(java.math.BigInteger.TEN);
        doThrow(new java.security.SignatureException("not self signed")).when(leaf).verify(eq(cert.getPublicKey()), anyString());
        Class<?>[] signature = {X509Certificate.class, X509Certificate.class, Set.class, Date.class};
        // Without AIA, verification uses CRL directly.
        invoke(CertificateVerifier.class, "checkRevocationsWithIssuer", signature, leaf, cert, Collections.singleton(cert), new Date());
        AuthorityInformationAccess aia = new AuthorityInformationAccess(AccessDescription.id_ad_ocsp, new GeneralName(GeneralName.uniformResourceIdentifier, "http://unused.test"));
        when(leaf.getExtensionValue(org.bouncycastle.asn1.x509.Extension.authorityInfoAccess.getId())).thenReturn(new DEROctetString(aia).getEncoded());
        for (boolean io : new boolean[]{true, false}) {
            try (org.mockito.MockedConstruction<OcspHelper> ignored = mockConstruction(OcspHelper.class, (helper, context) -> {
                if (io) when(helper.getResponseOcsp()).thenThrow(new IOException("offline"));
                else when(helper.getResponseOcsp()).thenThrow(new OCSPException("offline"));
            })) {
                invoke(CertificateVerifier.class, "checkRevocationsWithIssuer", signature, leaf, cert, Collections.singleton(cert), new Date());
            }
        }
    }

    @Test void ocspRejectsMissingResponderAndBadSignature() throws Exception {
        BasicOCSPResp basic = response(CertificateStatus.GOOD, false, true, new Date(0), new Date(System.currentTimeMillis() + 60000));
        OcspHelper helper = new OcspHelper(cert, new Date(), cert, Collections.emptySet(), "unused");
        BasicOCSPResp withoutCert = response(CertificateStatus.GOOD, false, false, new Date(0), new Date(System.currentTimeMillis() + 60000));
        assertThrows(OCSPException.class, () -> invoke(helper, "verifyOcspResponse", new Class[]{OCSPResp.class}, new OCSPRespBuilder().build(0, withoutCert)));
        BasicOCSPResp withoutHashCert = response(CertificateStatus.GOOD, true, false, new Date(0), new Date(System.currentTimeMillis() + 60000));
        assertThrows(OCSPException.class, () -> invoke(helper, "verifyOcspResponse", new Class[]{OCSPResp.class}, new OCSPRespBuilder().build(0, withoutHashCert)));
        BasicOCSPResp invalid = mock(BasicOCSPResp.class);
        when(invalid.isSignatureValid(any())).thenReturn(false);
        assertThrows(OCSPException.class, () -> invoke(helper, "checkOcspSignature", new Class[]{X509Certificate.class, BasicOCSPResp.class}, cert, invalid));
        X509Certificate wrong = mock(X509Certificate.class);
        when(wrong.getSubjectX500Principal()).thenReturn(new javax.security.auth.x500.X500Principal("CN=Wrong"));
        when(wrong.getEncoded()).thenReturn(cert.getEncoded());
        OcspHelper wrongName = new OcspHelper(cert, new Date(), cert, Collections.singleton(wrong), "unused");
        assertThrows(OCSPException.class, () -> invoke(wrongName, "verifyOcspResponse", new Class[]{OCSPResp.class}, new OCSPRespBuilder().build(0, withoutCert)));
        BasicOCSPResp spy = spy(basic);
        doReturn(new SingleResp[0]).when(spy).getResponses();
        OCSPResp outer = mock(OCSPResp.class);
        when(outer.getStatus()).thenReturn(0); when(outer.getResponseObject()).thenReturn(spy);
        assertThrows(OCSPException.class, () -> invoke(helper, "verifyOcspResponse", new Class[]{OCSPResp.class}, outer));
    }
}
