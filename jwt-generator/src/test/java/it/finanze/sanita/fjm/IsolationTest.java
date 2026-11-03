package it.finanze.sanita.fjm;

import static org.junit.jupiter.api.Assertions.*;

import com.auth0.jwt.JWT;
import com.google.gson.Gson;
import it.finanze.sanita.fjm.dto.TokenRequestDTO;
import it.finanze.sanita.fjm.dto.TokenResponseDTO;
import it.finanze.sanita.fjm.enums.*;
import java.io.ByteArrayOutputStream;
import java.nio.file.*;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.*;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

@Tag("ISOLATION")
class IsolationTest {
    @TempDir Path temp;
    private byte[] p12;
    private byte[] pem;
    private KeyPair pair;
    private Map<String, String> config;

    @BeforeEach
    void setup() throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        pair = generator.generateKeyPair();
        X509V3CertificateGenerator cert = new X509V3CertificateGenerator();
        cert.setSerialNumber(java.math.BigInteger.ONE);
        cert.setIssuerDN(new javax.security.auth.x500.X500Principal("CN=Isolation"));
        cert.setSubjectDN(new javax.security.auth.x500.X500Principal("CN=Isolation"));
        cert.setNotBefore(new Date(System.currentTimeMillis() - 60000));
        cert.setNotAfter(new Date(System.currentTimeMillis() + 86400000));
        cert.setPublicKey(pair.getPublic());
        cert.setSignatureAlgorithm("SHA256WithRSA");
        X509Certificate certificate = cert.generate(pair.getPrivate());
        KeyStore store = KeyStore.getInstance("PKCS12", "BC");
        store.load(null, null);
        store.setKeyEntry("test", pair.getPrivate(), "secret".toCharArray(), new java.security.cert.Certificate[]{certificate});
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        store.store(out, "secret".toCharArray());
        p12 = out.toByteArray();
        pem = ("-----BEGIN CERTIFICATE-----\n" + Base64.getEncoder().encodeToString(certificate.getEncoded()) + "\n-----END CERTIFICATE-----\n").getBytes();
        Files.write(temp.resolve("key.p12"), p12);
        Files.write(temp.resolve("cert.pem"), pem);
        config = new HashMap<>();
        for (JWTAuthEnum key : JWTAuthEnum.values()) if (key.isFlagAutoPayloadClaim()) config.put(key.getKey(), "test");
        for (JWTClaimsEnum key : JWTClaimsEnum.values()) if (key.isFlagAutoPayloadClaim()) config.put(key.getKey(), "test");
        config.put(JWTAuthEnum.ISS.getKey(), "auth:test");
        config.put(JWTAuthEnum.P12_PATH.getKey(), temp.resolve("key.p12").toString());
        config.put(JWTAuthEnum.PEM_PATH.getKey(), temp.resolve("cert.pem").toString());
        Files.write(temp.resolve("config.json"), new Gson().toJson(config).getBytes());
        reset();
    }

    @AfterEach
    void reset() {
        Launcher.jsonData = null;
        Launcher.pathFileOrDir = null;
        Launcher.outputFileNamePrefix = null;
        Launcher.aliasP12 = null;
        Launcher.pwdP12 = null;
        Launcher.nHour = 24;
        Launcher.flagMalformedInput = false;
        Launcher.flagNeedHelp = false;
        Launcher.flagVerbose = false;
        Launcher.flagValidation = false;
        Launcher.system = SystemEnum.GATEWAY;
    }

    @Test
    void signsAndVerifiesGatewayTokens() throws Exception {
        TokenRequestDTO request = new TokenRequestDTO();
        request.setAliasP12("test"); request.setPasswordP12("secret"); request.setConfig(new Gson().toJson(config));
        request.setDurationHours(2); request.setP12(p12); request.setPem(pem); request.setFileToHash("%PDF-data".getBytes());
        TokenResponseDTO response = Launcher.getTokens(request);
        assertEquals("auth:test", JWT.decode(response.getAuthorizationBearer()).getIssuer());
        assertEquals("integrity:test", JWT.decode(response.getFseJwtSignature()).getIssuer());
        assertEquals(Utility.encodeSHA256(request.getFileToHash()), JWT.decode(response.getFseJwtSignature()).getClaim("attachment_hash").asString());
        com.auth0.jwt.algorithms.Algorithm.RSA256(Launcher.getPublicKey(pem), null).verify(JWT.decode(response.getAuthorizationBearer()));
        assertArrayEquals(p12, request.getP12());
        assertEquals("test", request.getAliasP12());
        assertEquals("secret", request.getPasswordP12());
        assertNotNull(request.getConfig()); assertNotNull(request.getPem()); assertEquals(2, request.getDurationHours());
    }

    @Test
    void cliGeneratesEveryTargetAndPersistsTokens() throws Exception {
        Path document = Files.write(temp.resolve("document.pdf"), "%PDF-data".getBytes());
        Path csrs = Files.createDirectory(temp.resolve("csr"));
        Files.write(csrs.resolve("request.csr"), "csr".getBytes());
        Files.write(csrs.resolve("ignored.txt"), new byte[0]);
        for (SystemEnum system : SystemEnum.values()) {
            reset();
            Path prefix = temp.resolve(system.name());
            Launcher.main(new String[]{"-d", temp.resolve("config.json").toString(), "-p", "secret", "-a", "test", "-t", "1", "-f", (system == SystemEnum.PROVISIONING ? csrs : document).toString(), "-s", system.getKey(), "-o", prefix.toString(), "-x", "-v"});
            String auth = Files.readString(Paths.get(prefix + ".auth.txt"));
            String sign = Files.readString(Paths.get(prefix + ".sign.txt"));
            assertEquals("auth:test", JWT.decode(auth).getIssuer());
            assertEquals("integrity:test", JWT.decode(sign).getIssuer());
        }
    }

    @Test
    void cliRejectsMalformedArgumentsAndPrintsHelp() {
        for (String[] args : new String[][]{{"-h"}, {"unknown"}, {"-d"}, {}, {"-t", "NaN"}}) {
            reset();
            assertDoesNotThrow(() -> Launcher.main(args));
        }
    }

    @Test
    void utilityHandlesKeysFilesAndDates() throws Exception {
        assertEquals(pair.getPrivate(), Utility.extractKeyByAliasFromP12("secret".toCharArray(), null, p12));
        assertEquals(pair.getPrivate(), Utility.extractKeyByAliasFromP12("secret".toCharArray(), "test", p12));
        assertNull(Utility.extractKeyByAliasFromP12("wrong".toCharArray(), "test", p12));
        assertNull(Utility.getFileFromFS(temp.resolve("missing").toString()));
        assertEquals(3600000, Utility.addHoursToJavaUtilDate(new Date(0), 1).getTime());
        assertTrue(Utility.nullOrEmpty(null)); assertTrue(Utility.nullOrEmpty("")); assertFalse(Utility.nullOrEmpty("x"));
        assertFalse(Utility.isPdf(null)); assertFalse(Utility.isPdf(new byte[0])); assertFalse(Utility.isPdf("other".getBytes()));
        assertNull(Utility.cleanIss(null)); assertEquals("test", Utility.cleanIss("integrity:auth:test"));
        for (ArgumentEnum a : ArgumentEnum.values()) { assertSame(a, ArgumentEnum.getByKey(a.getKey())); assertNotNull(a.getDescription()); a.isFlagHasValue(); }
        assertNull(ArgumentEnum.getByKey("unknown"));
        for (SystemEnum s : SystemEnum.values()) { assertSame(s, SystemEnum.getByKey(s.getKey())); assertNotNull(s.getTokenBusinessName()); }
    }
}
