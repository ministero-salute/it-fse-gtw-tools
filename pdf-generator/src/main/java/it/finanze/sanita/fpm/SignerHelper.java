package it.finanze.sanita.fpm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.signature.CreateSignaturePades;


public class SignerHelper {
 
   
	/******************************************************
	*	SIGNATURE PADES
	*******************************************************/
	public static CreateSignaturePades getSP(byte[] keyStore, char[] keystorePwd) {
		try {
			KeyStore keystore = KeyStore.getInstance("PKCS12");
			InputStream isKeyStore = new ByteArrayInputStream(keyStore);
	        keystore.load(isKeyStore, keystorePwd);

	        CreateSignaturePades signing = new CreateSignaturePades(keystore, keystorePwd);
	        signing.setExternalSigning(false);
	        return signing;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/******************************************************
	*	GESTIONE PADES ON THE FLY
	*******************************************************/
	public static byte[] pades(CreateSignaturePades signing, String fileName, byte[] docToSign) {
		byte[] out = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	 PDDocument document = PDDocument.load(docToSign);) {

			signing.signDetached(document, baos);
			out = baos.toByteArray();
		} catch (Exception e) {
		}
        return out;
	}

	public static byte[] pades(byte[] keyStore, char[] keystorePwd, String fileName, byte[] docToSign) {
		CreateSignaturePades signing = getSP(keyStore, keystorePwd);
		return pades(signing, fileName, docToSign);
	}

	/******************************************************
	*	GESTIONE PADES FILESYSTEM
	*******************************************************/
	private static boolean padesFS(CreateSignaturePades signing, String inFileName, String outFileName) {
		boolean out = false;
        try {
			signing.signDetached(new File(inFileName), new File(outFileName));
			out = true;
		} catch (IOException e) {
			out = false;
		}
        return out;
	}

	public static Map<String, Boolean> padesFS(byte[] keyStore, char[] keystorePwd, Map<String, String> docsToSign) {
		Map<String, Boolean> out = new HashMap<>();
		CreateSignaturePades signing = getSP(keyStore, keystorePwd);
		for (Entry<String, String> docPair:docsToSign.entrySet()) {
			Boolean result = padesFS(signing, docPair.getKey(), docPair.getValue());
			out.put(docPair.getKey(), result);
		}
		return out;
	}

	public static boolean padesFS(byte[] keyStore, char[] keystorePwd, String inFileName, String outFileName) {
		CreateSignaturePades signing = getSP(keyStore, keystorePwd);
		return padesFS(signing, inFileName, outFileName);
	}
 
}
