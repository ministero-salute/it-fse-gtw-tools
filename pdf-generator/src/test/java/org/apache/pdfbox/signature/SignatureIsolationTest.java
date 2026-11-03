package org.apache.pdfbox.signature;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import it.finanze.sanita.fpm.Launcher;
import it.finanze.sanita.fpm.SignerHelper;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.*;
import org.apache.pdfbox.cos.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.*;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.junit.jupiter.api.*;

@Tag("ISOLATION")
public class SignatureIsolationTest {
    public static KeyPair pair;
    public static X509Certificate certificate;
    public static byte[] p12;

    @BeforeAll
    public static void keys() throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA"); gen.initialize(2048); pair = gen.generateKeyPair();
        X500Name name = new X500Name("CN=Isolation");
        certificate = new JcaX509CertificateConverter().getCertificate(new JcaX509v3CertificateBuilder(name, BigInteger.ONE,
                new Date(System.currentTimeMillis() - 86400000), new Date(System.currentTimeMillis() + 86400000), name, pair.getPublic())
                .build(new JcaContentSignerBuilder("SHA256withRSA").build(pair.getPrivate())));
        KeyStore store = KeyStore.getInstance("PKCS12"); store.load(null, null);
        store.setKeyEntry("test", pair.getPrivate(), "secret".toCharArray(), new java.security.cert.Certificate[]{certificate});
        ByteArrayOutputStream out = new ByteArrayOutputStream(); store.store(out, "secret".toCharArray()); p12 = out.toByteArray();
    }

    @Test
    void detachedSignaturesAreCryptographicallyValid() throws Exception {
        byte[] input = Launcher.getPdf("<ClinicalDocument/>".getBytes());
        for (boolean external : new boolean[]{false, true}) {
            CreateSignaturePades signing = SignerHelper.getSP(p12, "secret".toCharArray());
            signing.setExternalSigning(external); signing.setTsaUrl(null);
            assertEquals(external, signing.isExternalSigning());
            assertEquals(certificate, signing.getCertificateChain()[0]);
            byte[] signed = SignerHelper.pades(signing, null, input);
            try (PDDocument doc = PDDocument.load(signed)) {
                org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature sig = doc.getSignatureDictionaries().get(0);
                CMSSignedData cms = new CMSSignedData(new CMSProcessableByteArray(sig.getSignedContent(signed)), sig.getContents(signed));
                assertTrue(cms.getSignerInfos().getSigners().iterator().next().verify(new JcaSimpleSignerInfoVerifierBuilder().build(certificate)));
            }
        }
        assertEquals("<ClinicalDocument/>", Launcher.extract(Launcher.getPdf("<ClinicalDocument/>".getBytes(), p12, "secret")));
        KeyStore empty = KeyStore.getInstance("PKCS12"); empty.load(null, null);
        assertThrows(IOException.class, () -> new CreateSignaturePades(empty, new char[0]));
    }

    @Test
    void documentPermissionsAndCertificateUsageAreInspected() throws Exception {
        try (PDDocument doc = new PDDocument()) {
            assertEquals(0, SigUtils.getMDPPermission(doc));
            COSDictionary perms = new COSDictionary(), signature = new COSDictionary(), ref = new COSDictionary(), params = new COSDictionary();
            COSArray refs = new COSArray(); refs.add(ref);
            doc.getDocumentCatalog().getCOSObject().setItem(COSName.PERMS, perms);
            perms.setItem(COSName.DOCMDP, signature); signature.setItem(COSName.REFERENCE, refs);
            ref.setItem(COSName.TRANSFORM_METHOD, COSName.DOCMDP); ref.setItem(COSName.TRANSFORM_PARAMS, params);
            for (int p : new int[]{0, 1, 2, 3, 4}) {
                params.setInt(COSName.P, p); assertEquals(p < 1 || p > 3 ? 2 : p, SigUtils.getMDPPermission(doc));
            }
            params.setInt(COSName.P, 1);
            assertThrows(IllegalStateException.class, () -> SignerHelper.getSP(p12, "secret".toCharArray()).signDetached(doc, new ByteArrayOutputStream()));
        }
        X509Certificate cert = mock(X509Certificate.class);
        when(cert.getKeyUsage()).thenReturn(new boolean[9]);
        when(cert.getExtendedKeyUsage()).thenReturn(Collections.singletonList("unknown"));
        SigUtils.checkCertificateUsage(cert); SigUtils.checkResponderCertificateUsage(cert);
        SigUtils.checkCertificateUsage(certificate); SigUtils.checkResponderCertificateUsage(certificate);
    }

    @Test
    void cmsStreamPreservesContentAndSigningRejectsInvalidKeys() throws Exception {
        ByteArrayInputStream input = new ByteArrayInputStream("content".getBytes());
        CMSProcessableInputStream stream = new CMSProcessableInputStream(input);
        assertSame(input, stream.getContent()); assertNotNull(stream.getContentType());
        ByteArrayOutputStream out = new ByteArrayOutputStream(); stream.write(out);
        assertArrayEquals("content".getBytes(), out.toByteArray());
        CreateSignaturePades signing = SignerHelper.getSP(p12, "secret".toCharArray());
        signing.setPrivateKey(null);
        assertThrows(IOException.class, () -> signing.sign(new ByteArrayInputStream(new byte[0])));
    }
}
