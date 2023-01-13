package it.finanze.sanita.fpm;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtility {

    /**
	 * Dimensione massima chunk dati in fase di lettura.
	 */
	private static final int CHUNK_SIZE = 16384;

	 
    /**
	 * Metodo per il recupero del contenuto di un file dalla folder interna "/src/main/resources".
	 *
	 * @param filename	nome del file
	 * @return			contenuto del file
	 */
	public static byte[] getFileFromInternalResources(final String filename) {
		byte[] b = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
			b = getByteFromInputStream(is);
			is.close();
		} catch (Exception e) {
			System.out.println("");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					System.out.println("");
				}
			}
		}
		return b;
	}

    /**
	 * Recupero contenuto file da input stream.
	 *
	 * @param is
	 *            input stream
	 * @return contenuto file
	 */
	private static byte[] getByteFromInputStream(final InputStream is) {
		byte[] b;
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[CHUNK_SIZE];

			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			b = buffer.toByteArray();
		} catch (Exception e) {
			System.out.println("");
			return null;
		}
		return b;
	}
	
	/**
	 * Save on filesystem.
	 *
	 * @param content	content
	 * @param fileName	path
	 */
	public static void saveToFile(final byte[] content, final String fileName) {
		try (BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(new File(fileName)))){
		    bs.write(content);
		} catch (Exception e) {
			System.out.println("");
		} 
	}
	  

	public static byte[] getFileFromFS(final String filename) {
		byte[] b = null;
		try {
			File f = new File(filename);
			InputStream is = new FileInputStream(f);
			b = getByteFromInputStream(is);
			is.close();
		} catch (Exception e) {
		}
		return b;
	}
}
