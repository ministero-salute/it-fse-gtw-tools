/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 * 
 * Copyright (C) 2023 Ministero della Salute
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package it.finanze.sanita.fjm;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.Key;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * Copyright (c) 2022, Ministero della Salute
 *
 * Launcher class.
 */
public class Launcher {

	static final Logger LOGGER = Utility.getLogger(Launcher.class.getName());

	static String jsonData = null;
	static String pathFileOrDir = null;
	static String outputFileNamePrefix = null;
	static String aliasP12 = null;
	static char[] pwdP12 = null;
	static Integer nHour = 24;
	static boolean flagMalformedInput = false;
	static boolean flagNeedHelp = false;
	static boolean flagVerbose = false;
	static boolean flagValidation = false;
	static SystemEnum system = SystemEnum.GATEWAY;

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

			checkArgs(args);

			if (flagNeedHelp) {
				showHelp(LOGGER);
			} else if (flagMalformedInput || Utility.nullOrEmpty(jsonData) || pwdP12 == null) {
				LOGGER.info("Please check for malformed input; please remember that password p12 and json data are mandatory.");
			} else {
				
				switch (system) {
				case PROVISIONING:
					buildTokensProvisioning();
					break;
				default:
					buildTokens(system);
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.info("An error occur while trying to generate JWT, hope this can help:");
			LOGGER.info(String.format("EXCEPTION: %s", e.getMessage()));
			LOGGER.info(ExceptionUtils.getStackTrace(e));
		}
	}

	public static TokenResponseDTO getTokens(TokenRequestDTO request) throws Exception {
		aliasP12 = request.getAliasP12();
		pwdP12 = request.getPasswordP12().toCharArray();
		nHour = request.getDurationHours();
		Map<String, String> mapJD = getJsonData(request.getConfig());
		return getTokens(mapJD, request.getP12(), request.getPem(), request.getFileToHash(), SystemEnum.GATEWAY);
	}

	private static void checkArgs(String[] args) {

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

					checkValueArg(arg, value);

					i += 2;
				} else {
					i++;
					checkNoValueArg(arg);
				}
			}
		}

	}

	private static void checkValueArg(ArgumentEnum arg, String value) {

		if (ArgumentEnum.JSON_DATA.equals(arg)) {
			jsonData = value;
		} else if (ArgumentEnum.DURATION_JWT.equals(arg)) {
			nHour = Integer.valueOf(value);
		} else if (ArgumentEnum.FILE_OR_DIR_PATH.equals(arg)) {
			pathFileOrDir = value;
		} else if (ArgumentEnum.P12_ALIAS.equals(arg)) {
			aliasP12 = value;
		} else if (ArgumentEnum.P12_PWD.equals(arg)) {
			pwdP12 = value.toCharArray();
		} else if (ArgumentEnum.SYSTEM.equals(arg)) {
			system = SystemEnum.getByKey(value);
		} else if (ArgumentEnum.OUTPUT_FILE_PREFIX.equals(arg)) {
			outputFileNamePrefix = value;
		}

	}


	private static void checkNoValueArg(ArgumentEnum arg) {
		if (ArgumentEnum.HELP_MODE.equals(arg)) {
			flagNeedHelp = true;
		} else if (ArgumentEnum.VERBOSE_MODE.equals(arg)) {
			flagVerbose = true;
		} else if (ArgumentEnum.VALIDATION_MODE.equals(arg)) {
			flagValidation = true;
		}
	}
 
	
	private static void buildTokensProvisioning() throws Exception {
		Map<String, String> mapJD = getJsonData(new String(Utility.getFileFromFS(jsonData)));
		byte[] privateKeyP12 = Utility.getFileFromFS(get(mapJD, JWTAuthEnum.P12_PATH));
		byte[] pem = Utility.getFileFromFS(get(mapJD, JWTAuthEnum.PEM_PATH));
		getTokensProvisioning(mapJD, privateKeyP12, pem);
	}
	
	private static void buildTokens(SystemEnum system) throws Exception {
		Map<String, String> mapJD = getJsonData(new String(Utility.getFileFromFS(jsonData)));
		byte[] privateKeyP12 = Utility.getFileFromFS(get(mapJD, JWTAuthEnum.P12_PATH));
		byte[] pem = Utility.getFileFromFS(get(mapJD, JWTAuthEnum.PEM_PATH));
		byte[] fileToHash = null;
		if (!Utility.nullOrEmpty(pathFileOrDir)) {
			fileToHash = Utility.getFileFromFS(pathFileOrDir);
		}
		getTokens(mapJD, privateKeyP12, pem, fileToHash, system);
	}

	/**
	 * Saves tokens to files.
	 * 
	 * @param authToken
	 * @param signToken
	 * 
	 */
	private static void saveTokensToFiles(String authToken, String signToken) throws IOException{
		String authTokenFileName=outputFileNamePrefix+".auth.txt";
		String signTokenFileName=outputFileNamePrefix+".sign.txt";
		Files.writeString(Path.of(authTokenFileName),authToken,StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.CREATE,StandardOpenOption.WRITE);
		Files.writeString(Path.of(signTokenFileName),signToken,StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.CREATE,StandardOpenOption.WRITE);
	}


	private static TokenResponseDTO getTokens(Map<String, String> mapJD, byte[] privateKeyP12, byte[] pem, byte[] fileToHash, SystemEnum system) throws Exception {

		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		Key privateKey = Utility.extractKeyByAliasFromP12(pwdP12, aliasP12, privateKeyP12);
		String publicKey = cleanPem(pem);

		dumpVerboseMsg(flagVerbose, "Analyzing data\n");

		String iss = get(mapJD, JWTAuthEnum.ISS);
		Date iat = new Date();
		Date exp = Utility.addHoursToJavaUtilDate(iat, nHour);

		dumpVerboseMsg(flagVerbose, "Issued At Time: " + iat);
		dumpVerboseMsg(flagVerbose, "EXPiration time: " + exp);
		dumpVerboseMsg(flagVerbose, privateKey != null ? "Private key found." : "Private key NOT FOUND!");
		dumpVerboseMsg(flagVerbose, !Utility.nullOrEmpty(publicKey) ? "Public key found." : "Public key NOT FOUND!");
		dumpVerboseMsg(flagVerbose, "Json data items found: " + mapJD.size() + ".\n");

		String jwt = generateAuthJWT(mapJD, privateKey, publicKey, iat, exp, iss);
		String claimsJwt = generateClaimsJWT(mapJD, privateKey, publicKey, iat, exp, iss, fileToHash, system); 

		if (outputFileNamePrefix!=null){
			try{
				saveTokensToFiles(jwt,claimsJwt);
			}
			catch (Exception e){
				LOGGER.info("Error saving files");
				LOGGER.info(String.format("EXCEPTION: %s", e.getMessage()));
			}
		}

		dumpVerboseMsg(flagVerbose, "Generating Authorization Bearer Token\n");
		dumpVerboseMsg(flagVerbose, "AUTHORIZATION BEARER TOKEN START HERE");
		LOGGER.info("------------- Authorization Bearer Token ---------------\n" + jwt);
		dumpVerboseMsg(flagVerbose, "AUTHORIZATION BEARER TOKEN END HERE\n"); 

		dumpVerboseMsg(flagVerbose, String.format("Generating Token for System: %s\n", system.name()));
		dumpVerboseMsg(flagVerbose, "TOKEN START HERE");
		LOGGER.info(SystemEnum.MONITORING.equals(system) ? "\n------------- FSE-JWT-Monitoring ---------------\n" + claimsJwt + "\n" : "\n------------- FSE-JWT-Signature ---------------\n" + claimsJwt + "\n");
		dumpVerboseMsg(flagVerbose, "TOKEN END HERE\n");

		if (flagValidation) {
			// Authorization Token Validation
			LOGGER.info("Validating Authorization Token\n");
			Jws<Claims> token = parse(jwt, privateKey);
			LOGGER.info("HEADER: " + token.getHeader());
			LOGGER.info("BODY: " + token.getBody());
			boolean outValidation = validate(jwt, pem);
			String signatureStatus = "VALID";
			if (!outValidation) {
				signatureStatus = "NOT VALID";
			}
			LOGGER.info("SIGNATURE: " + signatureStatus + "\n"); 

			// Claims Token Validation 
			LOGGER.info("Validating Claims Token\n");
			Jws<Claims> claimsToken = parse(claimsJwt, privateKey);
			LOGGER.info("HEADER: " + claimsToken.getHeader());
			LOGGER.info("BODY: " + claimsToken.getBody());
			boolean outValidationClaimsToken = validate(claimsJwt, pem);
			String signatureStatusClaimsToken = "VALID";
			if (!outValidationClaimsToken) {
				signatureStatusClaimsToken = "NOT VALID";
			}
			LOGGER.info("SIGNATURE: " + signatureStatusClaimsToken + "\n"); 		

		} 
		return new TokenResponseDTO(jwt, claimsJwt);
	}

	private static String cleanPem(byte[] pem) {
		return new String(pem)
				.replace("-----BEGIN PUBLIC KEY-----", "")
				.replaceAll(System.lineSeparator(), "")
				.replace("-----END PUBLIC KEY-----", "")
				.replace("-----BEGIN CERTIFICATE-----", "")
				.replaceAll(System.lineSeparator(), "")
				.replace("-----END CERTIFICATE-----", "")
				.replace("\n", "");
	}
	
	private static TokenResponseDTO getTokensProvisioning(Map<String, String> mapJD, byte[] privateKeyP12, byte[] pem) throws Exception {

		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		Key privateKey = Utility.extractKeyByAliasFromP12(pwdP12, aliasP12, privateKeyP12);
		String publicKey = cleanPem(pem);

		dumpVerboseMsg(flagVerbose, "Analyzing data\n");

		String iss = get(mapJD, JWTAuthEnum.ISS);
		Date iat = new Date();
		Date exp = Utility.addHoursToJavaUtilDate(iat, nHour);

		dumpVerboseMsg(flagVerbose, "Issued At Time: " + iat);
		dumpVerboseMsg(flagVerbose, "EXPiration time: " + exp);
		dumpVerboseMsg(flagVerbose, privateKey != null ? "Private key found." : "Private key NOT FOUND!");
		dumpVerboseMsg(flagVerbose, !Utility.nullOrEmpty(publicKey) ? "Public key found." : "Public key NOT FOUND!");
		dumpVerboseMsg(flagVerbose, "Json data items found: " + mapJD.size() + ".\n");

		String jwt = generateAuthJWT(mapJD, privateKey, publicKey, iat, exp, iss); 
		String claimsJwt = generateClaimsJWTProvisioning(mapJD, privateKey, publicKey, iat, exp, iss); 

		dumpVerboseMsg(flagVerbose, "Generating Authorization Bearer Token\n");
		dumpVerboseMsg(flagVerbose, "AUTHORIZATION BEARER TOKEN START HERE");
		LOGGER.info("------------- Authorization Bearer Token ---------------\n" + jwt);
		dumpVerboseMsg(flagVerbose, "AUTHORIZATION BEARER TOKEN END HERE\n"); 

		dumpVerboseMsg(flagVerbose, "Generating FSE-JWT-Provisioning\n");
		dumpVerboseMsg(flagVerbose, "FSE-JWT-PROVISIONING START HERE");
		LOGGER.info("\n------------- FSE-JWT-Provisioning ---------------\n" + claimsJwt + "\n");
		dumpVerboseMsg(flagVerbose, "FSE-JWT-PROVISIONING END HERE\n");

		if (flagValidation) {
			// Authorization Token Validation
			LOGGER.info("Validating Authorization Token\n");
			Jws<Claims> token = parse(jwt, privateKey);
			LOGGER.info("HEADER: " + token.getHeader());
			LOGGER.info("BODY: " + token.getBody());
			boolean outValidation = validate(jwt, pem);
			String signatureStatus = "VALID";
			if (!outValidation) {
				signatureStatus = "NOT VALID";
			}
			LOGGER.info("SIGNATURE: " + signatureStatus + "\n"); 

			// Claims Token Validation 
			LOGGER.info("Validating Claims Token\n");
			Jws<Claims> claimsToken = parse(claimsJwt, privateKey);
			LOGGER.info("HEADER: " + claimsToken.getHeader());
			LOGGER.info("BODY: " + claimsToken.getBody());
			boolean outValidationClaimsToken = validate(claimsJwt, pem);
			String signatureStatusClaimsToken = "VALID";
			if (!outValidationClaimsToken) {
				signatureStatusClaimsToken = "NOT VALID";
			}
			LOGGER.info("SIGNATURE: " + signatureStatusClaimsToken + "\n"); 		

		} 
		return new TokenResponseDTO(jwt, claimsJwt);
	}

	/**
	 * Show help info.
	 */
	private static void showHelp(final Logger logger) {

		logger.info("NAME");
		logger.info("\t\tFS2 JWT Maker (fjm) - JWT generator for FS2 Gateway\n");
		logger.info("SYNOPSIS");
		logger.info(
				"\t\tjava -jar fjm -d JSON_DATA_FILE -p PASSWORD_P12 [-a ALIAS_P12] [-f PDF_FILE_TO_PUBLISH] [-t TOKEN_DURATION] [-o OUTPUT_FILE_PREFIX] [-v] [-x] [-h]");
		logger.info("");
		logger.info("DESCRIPTION");
		logger.info("\t\tGenerate JWT for FS2 Gateway");
		logger.info("");
		logger.info("\t\tArguments:");
		for (ArgumentEnum ae : ArgumentEnum.values()) {
			logger.info("");
			logger.info("\t\t" + ae.getKey() + "\t" + ae.getDescription());
		}
	}

	/**
	 * Get argument value from map.
	 * 
	 * @param mapJD map
	 * @param jdk   argument
	 * @return value
	 */
	private static String get(Map<String, String> mapJD, JWTAuthEnum jdk) {
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
	private static String generateAuthJWT(Map<String, String> mapJD, Key privateKey, String x5c, Date iat, Date exp, String iss) {
		Map<String, Object> headerParams = new HashMap<>();
		headerParams.put(JWTAuthEnum.ALG.getKey(), SignatureAlgorithm.RS256);
		headerParams.put(JWTAuthEnum.TYP.getKey(), JWTAuthEnum.JWT.getKey());
		headerParams.put(JWTAuthEnum.X5C.getKey(), Arrays.asList(x5c).toArray());

		Map<String, Object> claims = new HashMap<>();
		for (JWTAuthEnum k : JWTAuthEnum.values()) {
			if (k.getAutoFlagPayloadClaim() && mapJD.containsKey(k.getKey())) {
				claims.put(k.getKey(), mapJD.get(k.getKey()));
			}
		}
		claims.put(JWTAuthEnum.IAT.getKey(), iat.getTime()/1000);
		claims.put(JWTAuthEnum.EXP.getKey(), exp.getTime()/1000);
		claims.put(JWTAuthEnum.ISS.getKey(), "auth:" + cleanIss(iss));

		return Jwts.builder().setHeaderParams(headerParams).setClaims(claims).signWith(SignatureAlgorithm.RS256, privateKey).compact();
	} 

	/**
	 * Generate Claims JWT.
	 * 
	 * @param mapJD Arguments map.
	 * @param privateKey Private key.
	 * @param x5c Public key.
	 * @param iat Issuing time.
	 * @param exp Expiring time.
	 * @param pathFileToPublish File to hash.
	 * @param isMonitoring Monitoring flag.
	 * @return Generated JWT
	 * @throws Exception
	 */
	private static String generateClaimsJWT(Map<String, String> mapJD, Key privateKey, String x5c, Date iat, Date exp, String iss, byte[] fileToHash, SystemEnum system) throws Exception {
		Map<String, Object> headerParams = new HashMap<>();
		headerParams.put(JWTClaimsEnum.ALG.getKey(), SignatureAlgorithm.RS256);
		headerParams.put(JWTClaimsEnum.TYP.getKey(), JWTClaimsEnum.JWT.getKey());
		headerParams.put(JWTClaimsEnum.X5C.getKey(), Arrays.asList(x5c).toArray());

		Map<String, Object> claims = new HashMap<>();
		for (JWTClaimsEnum k : JWTClaimsEnum.values()) {
			if (k.getAutoFlagPayloadClaim() && mapJD.containsKey(k.getKey())) {
				claims.put(k.getKey(), mapJD.get(k.getKey()));
			}
		}

		if (SystemEnum.GATEWAY.equals(system)) {
			claims.put(JWTClaimsEnum.PATIENT_CONSENT.getKey(), true);
		}

		claims.put(JWTClaimsEnum.IAT.getKey(), iat.getTime()/1000);
		claims.put(JWTClaimsEnum.EXP.getKey(), exp.getTime()/1000);
		claims.put(JWTAuthEnum.ISS.getKey(), "integrity:" + cleanIss(iss));

		if (Utility.isPdf(fileToHash) && SystemEnum.GATEWAY.equals(system)) {
			String hash = Utility.encodeSHA256(fileToHash);
			claims.put(JWTClaimsEnum.ATTACHMENT_HASH.getKey(), hash);
		} else if (fileToHash != null && SystemEnum.MONITORING.equals(system)) {
			String hash = Utility.encodeSHA256(fileToHash);
			claims.put(JWTClaimsEnum.FILE_HASH.getKey(), hash);
		}

		return Jwts.builder().setHeaderParams(headerParams).setClaims(claims)
				.signWith(SignatureAlgorithm.RS256, privateKey).compact();
	}
	
	private static String generateClaimsJWTProvisioning(Map<String, String> mapJD, Key privateKey, String x5c, Date iat, Date exp, String iss) throws Exception {
		Map<String, Object> headerParams = new HashMap<>();
		headerParams.put(JWTClaimsEnum.ALG.getKey(), SignatureAlgorithm.RS256);
		headerParams.put(JWTClaimsEnum.TYP.getKey(), JWTClaimsEnum.JWT.getKey());
		headerParams.put(JWTClaimsEnum.X5C.getKey(), Arrays.asList(x5c).toArray());

		Map<String, Object> claims = new HashMap<>();
		for (JWTClaimsEnum k : JWTClaimsEnum.values()) {
			if (k.getAutoFlagPayloadClaim() && mapJD.containsKey(k.getKey())) {
				claims.put(k.getKey(), mapJD.get(k.getKey()));
			}
		}
		
		claims.put(JWTClaimsEnum.IAT.getKey(), iat.getTime()/1000);
		claims.put(JWTClaimsEnum.EXP.getKey(), exp.getTime()/1000);
		claims.put(JWTAuthEnum.ISS.getKey(), "integrity:" + cleanIss(iss));

		if(!Utility.nullOrEmpty(pathFileOrDir)) {
			File directory = new File(pathFileOrDir);
			if (!directory.isDirectory()) {
				LOGGER.info("Attenzione fornire il path in cui sono presenti le csr.");
				return null;
			}
			File[] files = directory.listFiles();
			List<String> hashCsr = new ArrayList<>();
			for (File file : files) {
				if (file.isFile() && file.getName().endsWith(".csr")) {
					hashCsr.add(Utility.encodeSHA256(Files.readAllBytes(file.toPath())));
				} 
			}
			claims.put(JWTClaimsEnum.VECTOR_HASH_CSR.getKey(), hashCsr);
		}
		
		return Jwts.builder().setHeaderParams(headerParams).setClaims(claims)
				.signWith(SignatureAlgorithm.RS256, privateKey).compact();
	}

	/**
	 * Clean ISS.
	 * 
	 * @param iss
	 * @return iss cleaned
	 */
	private static String cleanIss(String iss) {
		if (iss == null) return null;
		return iss
				.replaceFirst("integrity:", "")
				.replaceFirst("auth:", "");
	}

	/**
	 * Validate JWT.
	 * 
	 * @param jwt jwt to validate
	 * @param pem perm certificate
	 * @return flag
	 */
	private static boolean validate(String jwt, byte[] pem) {
		boolean out = true;
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
	public static RSAPublicKey getPublicKey(byte[] pem) throws CertificateException {
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
	private static void dumpVerboseMsg(boolean flagVerbose, String msg) {
		if (flagVerbose) {
			LOGGER.info(msg);
		}
	}

	/**
	 * Parse JWT Token.
	 * 
	 * @param token String
	 * @param private key
	 * @return token JWT
	 */
	private static Jws<Claims> parse(String token, Key privateKey) {
		return Jwts.parser().setSigningKey(privateKey).parseClaimsJws(token);
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getJsonData(String data) {
		return new Gson().fromJson(data, Map.class);
	}

} 
