package it.finanze.sanita.fjm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author CPIERASC
 *
 *         Launcher class.
 */
public class Launcher {

	static final Logger LOGGER = Utility.getLogger(Launcher.class.getName());

	/**
	 * Main method.
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {

		LOGGER.info(" _____  _____  ___       __  _ _ _  _____    _____       _             ");
		LOGGER.info("|   __||   __||_  |   __|  || | | ||_   _|  |     | ___ | |_  ___  ___ ");
		LOGGER.info("|   __||__   ||  _|  |  |  || | | |  | |    | | | || .'|| '_|| -_||  _|");
		LOGGER.info("|__|   |_____||___|  |_____||_____|  |_|    |_|_|_||__,||_,_||___||_|  \n");

		try {
			String jsonData = null;
			String pathFileToPublish = null;
			String aliasP12 = null;
			char[] pwdP12 = null;
			Integer nHour = 24;
			Boolean flagMalformedInput = false;
			Boolean flagNeedHelp = false;
			Boolean flagVerbose = false;
			Boolean flagValidation = false;

			for (int i = 0; i < args.length;) {
				String key = args[i].toLowerCase();
				ArgumentEnum arg = ArgumentEnum.getByKey(key);

				if (arg == null) {
					flagMalformedInput = true;
					break;
				} else {
					if (arg.getFlagHasValue()) {
						String value = null;
						if (i + 1 < args.length) {
							value = args[i + 1];
						} else {
							flagMalformedInput = true;
							break;
						}

						if (ArgumentEnum.JSON_DATA.equals(arg)) {
							jsonData = value;
						} else if (ArgumentEnum.DURATION_JWT.equals(arg)) {
							nHour = Integer.valueOf(value);
						} else if (ArgumentEnum.FILE_TO_PUBLISH.equals(arg)) {
							pathFileToPublish = value;
						} else if (ArgumentEnum.P12_ALIAS.equals(arg)) {
							aliasP12 = value;
						} else if (ArgumentEnum.P12_PWD.equals(arg)) {
							pwdP12 = value.toCharArray();
						}

						i += 2;
					} else {
						i++;
						if (ArgumentEnum.HELP_MODE.equals(arg)) {
							flagNeedHelp = true;
							break;
						} else if (ArgumentEnum.VERBOSE_MODE.equals(arg)) {
							flagVerbose = true;
						} else if (ArgumentEnum.VALIDATION_MODE.equals(arg)) {
							flagValidation = true;
						}
					}
				}
			}

			if (flagNeedHelp) {
				showHelp(LOGGER);
			} else if (flagMalformedInput || Utility.nullOrEmpty(jsonData) || Utility.nullOrEmpty(aliasP12)
					|| pwdP12 == null) {
				LOGGER.info(
						"Please check for malformed input; please remember that alias p12, password p12 and json data are mandatory.");
			} else {

				Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

				Map mapJD = new Gson().fromJson(new String(Utility.getFileFromFS(jsonData)), Map.class);

				byte[] privateKeyP12 = Utility.getFileFromFS(get(mapJD, JWTKeyEnum.P12_PATH));
				Key privateKey = Utility.extractKeyByAliasFromP12(pwdP12, aliasP12, privateKeyP12);

				byte[] pem = Utility.getFileFromFS(get(mapJD, JWTKeyEnum.PEM_PATH));

				String cleanedPEM = new String(pem)
						.replace("-----BEGIN PUBLIC KEY-----", "")
						.replaceAll(System.lineSeparator(), "")
						.replace("-----END PUBLIC KEY-----", "")
						.replace("-----BEGIN CERTIFICATE-----", "")
						.replaceAll(System.lineSeparator(), "")
						.replace("-----END CERTIFICATE-----", "")
						.replace("\n", "");

				String publicKey = cleanedPEM;

				dumpVerboseMsg(flagVerbose, "Analyzing data\n");
				Date iat = new Date();
				Date exp = Utility.addHoursToJavaUtilDate(iat, nHour);

				dumpVerboseMsg(flagVerbose, "Issued At Time: " + iat);
				dumpVerboseMsg(flagVerbose, "EXPiration time: " + exp);
				if (privateKey != null) {
					dumpVerboseMsg(flagVerbose, "Private key founded.");
				} else {
					dumpVerboseMsg(flagVerbose, "Private key NOT FOUNDED!");
				}
				if (!Utility.nullOrEmpty(publicKey)) {
					dumpVerboseMsg(flagVerbose, "Public key founded.");
				} else {
					dumpVerboseMsg(flagVerbose, "Public key NOT FOUNDED!");
				}
				Integer nDataItems = 0;
				if (mapJD != null && mapJD.size() > 0) {
					nDataItems = mapJD.size();
				}
				dumpVerboseMsg(flagVerbose, "Json data items founded: " + nDataItems + ".\n");

				String jwt = generateJWT(mapJD, privateKey, publicKey, iat, exp, pathFileToPublish);

				dumpVerboseMsg(flagVerbose, "Generating token\n");
				dumpVerboseMsg(flagVerbose, "JWT START HERE");
				LOGGER.info(jwt);
				dumpVerboseMsg(flagVerbose, "JWT END HERE\n");

				if (flagValidation) {
					LOGGER.info("Validating Token\n");
					Jwt token = Jwts.parser().setSigningKey(privateKey).parse(jwt);
					LOGGER.info("HEADER: " + token.getHeader());
					LOGGER.info("BODY: " + token.getBody());
					Boolean outValidation = validate(jwt, pem);
					String signatureStatus = "VALID";
					if (!outValidation) {
						signatureStatus = "NOT VALID";
					}
					LOGGER.info("SIGNATURE: " + signatureStatus);

				}
			}
		} catch (Exception e) {
			LOGGER.info("An error occur while trying to generate JWT, hope this can help:");
			LOGGER.info(String.format("EXCEPTION: ", ExceptionUtils.getStackTrace(e)));
		}
	}

	/**
	 * Show help info.
	 */
	private static void showHelp(final Logger LOGGER) {

		LOGGER.info("NAME");
		LOGGER.info("\t\tFS2 JWT Maker (fjm) - JWT generator for FS2 Gateway\n");
		LOGGER.info("SYNOPSIS");
		LOGGER.info(
				"\t\tjava -jar fjm -d JSON_DATA_FILE -a ALIAS_P12 -p PASSWORD_P12 [-f PDF_FILE_TO_PUBLISH] [-t TOKEN_DURATION] [-v] [-x] [-h]");
		LOGGER.info("");
		LOGGER.info("DESCRIPTION");
		LOGGER.info("\t\tGenerate JWT for FS2 Gateway");
		LOGGER.info("");
		LOGGER.info("\t\tArguments:");
		for (ArgumentEnum ae : ArgumentEnum.values()) {
			LOGGER.info("");
			LOGGER.info("\t\t" + ae.getKey() + "\t" + ae.getDescription());
		}
	}

	/**
	 * Get argument value from map.
	 * 
	 * @param mapJD map
	 * @param jdk   argument
	 * @return value
	 */
	private static String get(Map<String, String> mapJD, JWTKeyEnum jdk) {
		return mapJD.get(jdk.getKey());
	}

	/**
	 * Generate JWT.
	 * 
	 * @param mapJD             arguments map
	 * @param privateKey        private key
	 * @param x5c               public key
	 * @param iat               issuing time
	 * @param exp               expiring time
	 * @param pathFileToPublish file to hash
	 * @return jwt
	 * @throws Exception
	 */
	private static String generateJWT(Map<String, String> mapJD, Key privateKey, String x5c, Date iat, Date exp,
			String pathFileToPublish) throws Exception {
		Map<String, Object> headerParams = new HashMap<>();
		headerParams.put(JWTKeyEnum.ALG.getKey(), SignatureAlgorithm.RS256);
		headerParams.put(JWTKeyEnum.TYP.getKey(), JWTKeyEnum.JWT.getKey());
		headerParams.put(JWTKeyEnum.X5C.getKey(), Arrays.asList(x5c).toArray());

		Map<String, Object> claims = new HashMap<>();
		for (JWTKeyEnum k : JWTKeyEnum.values()) {
			if (k.getAutoFlagPayloadClaim()) {
				claims.put(k.getKey(), mapJD.get(k.getKey()));
			}
		}
		claims.put(JWTKeyEnum.PATIENT_CONSENT.getKey(), true);
		claims.put(JWTKeyEnum.IAT.getKey(), iat.getTime()/1000);
		claims.put(JWTKeyEnum.EXP.getKey(), exp.getTime()/1000);

		if (!Utility.nullOrEmpty(pathFileToPublish)) {
			byte[] fileToHash = Utility.getFileFromFS(pathFileToPublish);
			if (Utility.isPdf(fileToHash)) {
				String hash = Utility.encodeSHA256(fileToHash);
				claims.put(JWTKeyEnum.ATTACHMENT_HASH.getKey(), hash);
			}
		}

		return Jwts.builder().setHeaderParams(headerParams).setClaims(claims)
				.signWith(SignatureAlgorithm.RS256, privateKey).compact();
	}

	/**
	 * Validate JWT.
	 * 
	 * @param jwt jwt to validate
	 * @param pem perm certificate
	 * @return flag
	 */
	private static Boolean validate(String jwt, byte[] pem) {
		Boolean out = true;
		try {
			final RSAPublicKey key = getPublicKey(pem);
			final Algorithm algorithm = Algorithm.RSA256(key, null);
			final DecodedJWT decodedJWT = JWT.decode(jwt);
			algorithm.verify(decodedJWT);
		} catch (Exception e) {
			out = false;
		}
		return out;
	}

	/**
	 * Get public key.
	 * 
	 * @param pem pem
	 * @return public key
	 * @throws Exception
	 */
	public static RSAPublicKey getPublicKey(byte[] pem) throws Exception {
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		InputStream in = new ByteArrayInputStream(pem);
		X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(in);

		return (RSAPublicKey) certificate.getPublicKey();
	}

	/**
	 * Dump message if in verbose mode.
	 * 
	 * @param flagVerbose flag verbose mode
	 * @param msg         messagge
	 */
	private static void dumpVerboseMsg(Boolean flagVerbose, String msg) {
		if (flagVerbose) {
			LOGGER.info(msg);
		}
	}

}