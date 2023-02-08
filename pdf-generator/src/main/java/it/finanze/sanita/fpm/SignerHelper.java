package it.finanze.sanita.fpm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyStore;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.signature.CreateSignaturePades;


public class SignerHelper {
	
	private SignerHelper() {
		//Do nothing
	}
 
   
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
			throw new RuntimeException(e);
		}
        return out;
	}
 
}
