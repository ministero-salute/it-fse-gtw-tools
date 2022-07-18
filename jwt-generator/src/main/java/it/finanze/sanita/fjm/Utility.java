package it.finanze.sanita.fjm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Copyright (c) 2022, Ministero della Salute
 *
 * Utility class.
 * 
 */
public class Utility {

	static final Logger LOGGER = Utility.getLogger(Utility.class.getName());

	/**
	 * Chunk size file.
	 */
    private static final int CHUNK_SIZE = 16384;

    /**
     * Constructor.
     */
	private Utility() {
		
	}
	
	/**
	 * Encode file into base64.
	 * 
	 * @param input	content
	 * @return		content encoded in base64
	 */
	public static String encodeBase64(final byte[] input) {
	    return Base64.getEncoder().encodeToString(input);
	}
	
	/**
	 * Get key from P12 from alias and password.
	 * 
	 * @param password		p12 password
	 * @param alias			p12 alias
	 * @param p12			certificate
	 * @return				key
	 * @throws Exception
	 */
	public static Key extractKeyByAliasFromP12(char[] password, String alias, byte[] p12) throws Exception {
	    try (ByteArrayInputStream bais = new ByteArrayInputStream(p12)) {
	        java.security.KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
	        keyStore.load(bais, password);
	        return keyStore.getKey(alias, password);
	    } catch (Exception e) {
			LOGGER.info(String.format("Error while extracting key by alais from p12: %s", 
					ExceptionUtils.getStackTrace(e)));
	    }
	    return null;
	}
	
	/**
	 * Get file from file system.
	 * 
	 * @param filename		path file
	 * @return				content of the file
	 * @throws Exception
	 */
	public static byte[] getFileFromFS(final String filename) throws Exception {
	    byte[] b = null;
	    try (InputStream is = new FileInputStream(new File(filename))) {
	        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();) {
	            int nRead;
	            byte[] data = new byte[CHUNK_SIZE];
	            while ((nRead = is.read(data, 0, data.length)) != -1) {
	                buffer.write(data, 0, nRead);
	            }
	            buffer.flush();
	            b = buffer.toByteArray();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    } catch (Exception e) {
			LOGGER.info(String.format("Error while retrieving file from fs: %s", e));
	    }
	    return b;
	}
	
	/**
	 * Add hours to a date.
	 * 
	 * @param date	date
	 * @param hours	hours to add
	 * @return		updated date
	 */
	public static Date addHoursToJavaUtilDate(Date date, int hours) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, hours);
	    return calendar.getTime();
	}
	
	/**
	 * Check if the magic number of the file is "%PDF".
	 * 
	 * @param pdf	file to check
	 * @return		flag
	 */
	public static boolean isPdf(byte[] pdf) {
		boolean out = false;
		if (pdf!=null && pdf.length >4) {
			byte[] magicNumber = Arrays.copyOf(pdf, 4);
			String strMagicNumber = new String(magicNumber);
			out = strMagicNumber.equals("%PDF");
		}
		return out;
	}

	/**
	 * Encode object in sha 256 and then in hexadecimal.
	 * 
	 * @param objectToEncode	object to encode
	 * @return					encoded object
	 * @throws NoSuchAlgorithmException
	 */
	public static String encodeSHA256(byte[] objectToEncode) throws NoSuchAlgorithmException {
	    final MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    return Hex.encodeHexString(digest.digest(objectToEncode));
	}

	/**
	 * Check if a string is null or empty.
	 * 
	 * @param str	string to check
	 * @return		flag
	 */
	public static boolean nullOrEmpty(String str) {
		return (str == null) || str.length() == 0;
	}
	
	public static Logger getLogger(String className) {
		final Logger logger = Logger.getLogger(className);
		final ConsoleHandler consoleHandler = new ConsoleHandler();
	
		final Formatter f = new Formatter() {
			@Override
			public String format(LogRecord record) {
				return record.getMessage() + "\n"; // Printing only message
			}
		};
	
		consoleHandler.setFormatter(f);
		logger.addHandler(consoleHandler);
		logger.setUseParentHandlers(false);
		return logger;
	}
}