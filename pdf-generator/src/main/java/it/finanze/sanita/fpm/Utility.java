package it.finanze.sanita.fpm;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Copyright (c) 2022, Ministero della Salute
 *
 * Utility class.
 * 
 */
public class Utility {

	private static final Logger LOGGER = Utility.getLogger(Utility.class.getName());

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
			public String format(LogRecord logRecord) {
				return logRecord.getMessage() + "\n"; // Printing only message
			}
		};
	
		consoleHandler.setFormatter(f);
		logger.addHandler(consoleHandler);
		logger.setUseParentHandlers(false);
		return logger;
	}
 
	public static void saveToFile(final byte[] content, final String fileName) {
		try (BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(new File(fileName)))){
		    bs.write(content);
		} catch (Exception e) {
			throw new RuntimeException(e); 
		} 
	}

}
