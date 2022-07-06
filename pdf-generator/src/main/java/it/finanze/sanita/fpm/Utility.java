package it.finanze.sanita.fpm;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 
 * @author CPIERASC
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
	 * Check if a string is null or empty.
	 * 
	 * @param str	string to check
	 * @return		flag
	 */
	public static Boolean nullOrEmpty(String str) {
		return (str == null) || str.length() == 0;
	}
	
	public static Logger getLogger(String className) {
		final Logger LOGGER = Logger.getLogger(className);
		final ConsoleHandler consoleHandler = new ConsoleHandler();
	
		final Formatter f = new Formatter() {
			@Override
			public String format(LogRecord record) {
				return record.getMessage() + "\n"; // Printing only message
			}
		};
	
		consoleHandler.setFormatter(f);
		LOGGER.addHandler(consoleHandler);
		LOGGER.setUseParentHandlers(false);
		return LOGGER;
	}

	public static byte[] getByteFromInputStream(final InputStream is) throws Exception {
		byte[] b;
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[CHUNK_SIZE];

		while ((nRead = is.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		buffer.flush();
		b = buffer.toByteArray();
		return b;
	}

	public static byte[] getFileFromInternalResource(String filename) throws Exception {
		byte[] b = null;
		InputStream is = null;
		is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		b = getByteFromInputStream(is);
		is.close();
		return b;
	}
 
	public static void saveToFile(final byte[] content, final String fileName) {
		try (BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(new File(fileName)))){
		    bs.write(content);
		} catch (Exception e) {
			throw new RuntimeException(e); 
		} 
	}

}
