package org.apache.pdfbox.signature;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.util.*;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.cms.*;
import org.bouncycastle.cms.*;
import org.bouncycastle.tsp.*;
import org.junit.jupiter.api.*;

@Tag("ISOLATION")
class TimestampIsolationTest {
    @Test void tsaTransportHandlesRejectionAuthenticationAndIoFailures() throws Exception {
        for (String algorithm : new String[]{"MD2", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512"}) {
            URLConnection connection = mock(URLConnection.class);
            when(connection.getOutputStream()).thenReturn(new ByteArrayOutputStream());
            byte[] rejection = new TimeStampResponseGenerator(null, TSPAlgorithms.ALLOWED).generateFailResponse(2, 1, "Rejected").getEncoded();
            when(connection.getInputStream()).thenReturn(new ByteArrayInputStream(rejection));
            URL url = url(connection);
            TSAClient client = new TSAClient(url, null, null, MessageDigest.getInstance(algorithm));
            IOException error = assertThrows(IOException.class, () -> client.getTimeStampToken("content".getBytes()));
            assertTrue(error.getMessage().contains("time stamp token"));
            verify(connection).setRequestProperty("Content-Type", "application/timestamp-query");
        }
        URLConnection connection = mock(URLConnection.class);
        assertThrows(UnsupportedOperationException.class, () -> new TSAClient(url(connection), "user", "password", MessageDigest.getInstance("SHA-256")).getTimeStampToken(new byte[0]));
        when(connection.getOutputStream()).thenThrow(new IOException("write"));
        assertThrows(IOException.class, () -> new TSAClient(url(connection), null, null, MessageDigest.getInstance("SHA-256")).getTimeStampToken(new byte[0]));
        doReturn(new ByteArrayOutputStream()).when(connection).getOutputStream(); when(connection.getInputStream()).thenThrow(new IOException("read"));
        assertThrows(IOException.class, () -> new TSAClient(url(connection), null, null, MessageDigest.getInstance("SHA-256")).getTimeStampToken(new byte[0]));
    }

    static URL url(URLConnection connection) throws Exception {
        return new URL(null, "http://isolation.test", new URLStreamHandler() {
            @Override protected URLConnection openConnection(URL url) { return connection; }
        });
    }

    @Test void timestampAddsUnsignedAttributeWithoutChangingSignedContent() throws Exception {
        SignatureIsolationTest.keys();
        CreateSignaturePades signing = it.finanze.sanita.fpm.SignerHelper.getSP(SignatureIsolationTest.p12, "secret".toCharArray());
        CMSSignedData cms = new CMSSignedData(signing.sign(new ByteArrayInputStream("content".getBytes())));
        ValidationTimeStamp timestamp = new ValidationTimeStamp(null);
        TSAClient client = mock(TSAClient.class);
        TimeStampToken token = mock(TimeStampToken.class);
        when(token.getEncoded()).thenReturn(new DERSequence().getEncoded());
        when(client.getTimeStampToken(any())).thenReturn(token);
        java.lang.reflect.Field field = ValidationTimeStamp.class.getDeclaredField("tsaClient"); field.setAccessible(true); field.set(timestamp, client);
        CMSSignedData stamped = timestamp.addSignedTimeStamp(cms);
        SignerInformation original = cms.getSignerInfos().getSigners().iterator().next();
        SignerInformation result = stamped.getSignerInfos().getSigners().iterator().next();
        assertArrayEquals(original.getSignature(), result.getSignature());
        assertNotNull(result.getUnsignedAttributes().get(org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.id_aa_signatureTimeStampToken));
        assertNotNull(timestamp.addSignedTimeStamp(stamped));
        assertNotNull(new ValidationTimeStamp("http://isolation.test"));
    }

    @Test void tsaAcceptsAValidLocallyIssuedTimestamp() throws Exception {
        SignatureIsolationTest.keys();
        org.bouncycastle.asn1.x500.X500Name name = new org.bouncycastle.asn1.x500.X500Name("CN=Timestamp");
        org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder builder = new org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder(name, java.math.BigInteger.TEN,
                new Date(System.currentTimeMillis() - 60000), new Date(System.currentTimeMillis() + 60000), name, SignatureIsolationTest.pair.getPublic());
        builder.addExtension(org.bouncycastle.asn1.x509.Extension.extendedKeyUsage, true,
                new org.bouncycastle.asn1.x509.ExtendedKeyUsage(org.bouncycastle.asn1.x509.KeyPurposeId.id_kp_timeStamping));
        org.bouncycastle.cert.X509CertificateHolder holder = builder.build(new org.bouncycastle.operator.jcajce.JcaContentSignerBuilder("SHA256withRSA").build(SignatureIsolationTest.pair.getPrivate()));
        org.bouncycastle.operator.DigestCalculatorProvider digest = new org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder().build();
        TimeStampTokenGenerator generator = new TimeStampTokenGenerator(new org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder(digest)
                .build(new org.bouncycastle.operator.jcajce.JcaContentSignerBuilder("SHA256withRSA").build(SignatureIsolationTest.pair.getPrivate()), holder),
                digest.get(new org.bouncycastle.asn1.x509.AlgorithmIdentifier(org.bouncycastle.asn1.oiw.OIWObjectIdentifiers.idSHA1)), new ASN1ObjectIdentifier("1.2.3"));
        generator.addCertificates(new org.bouncycastle.util.CollectionStore<>(Collections.singletonList(holder)));
        ByteArrayOutputStream requestBytes = new ByteArrayOutputStream();
        URLConnection connection = mock(URLConnection.class);
        when(connection.getOutputStream()).thenReturn(requestBytes);
        when(connection.getInputStream()).thenAnswer(call -> {
            TimeStampRequest request = new TimeStampRequest(requestBytes.toByteArray());
            return new ByteArrayInputStream(new TimeStampResponseGenerator(generator, TSPAlgorithms.ALLOWED).generateGrantedResponse(request, java.math.BigInteger.ONE, new Date()).getEncoded());
        });
        TimeStampToken token = new TSAClient(url(connection), null, null, MessageDigest.getInstance("SHA-256")).getTimeStampToken("content".getBytes());
        assertArrayEquals(MessageDigest.getInstance("SHA-256").digest("content".getBytes()), token.getTimeStampInfo().getMessageImprintDigest());
    }
}
