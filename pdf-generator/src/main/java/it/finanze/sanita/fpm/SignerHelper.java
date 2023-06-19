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
