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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;

import org.apache.pdfbox.signature.CreateSignaturePades;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(Constants.Profile.TEST)
@RunWith(MockitoJUnitRunner.class)
class UtilityTest {
	
	//delete asap
	@Mock
	CreateSignaturePades pades;
	
	@Test
    @DisplayName("Mixed utility")
    void genericUtilityTest() throws Exception {
		
		// file path - not empty test
		File file = new File("CDA_ATTACHMENT.pdf");
		String path = file.getAbsolutePath();
		
		assertFalse(Utility.nullOrEmpty(path));
		
		//save to file - no exceptions thrown 
        byte[] fileContent = "fileName".getBytes();               
        assertDoesNotThrow(()->Utility.saveToFile(fileContent, "fileName"));  
        
        //save to file - null file
        assertThrows(RuntimeException.class, ()->Utility.saveToFile(null, null));
        
        //get file from fs test - wrong file path string
		byte[] wrongFilePath = Utility.getFileFromFS("src/test/resources/Files/CDA_ATTACHMENT.doc");		
        assertEquals(null, wrongFilePath);        
        
        //get file from fs test - correct file path string
    	byte[] correctFilePathPdf = Utility.getFileFromFS("src/test/resources/Files/CDA_ATTACHMENT.pdf");
    	assertNotNull(correctFilePathPdf);

    }

	@Test
	@DisplayName("Signer Helper test")
	void helperTest() throws Exception {
			
		char pwd[] = {'t', 'e', 's', 't', 0};
		byte[] bytes = "test".getBytes();    	
		byte[] docToSign = Utility.getFileFromFS("src/test/resources/Files/CDA_ATTACHMENT.pdf");	
		
		//get createSignaturePades obj - exception case test
		assertThrows(RuntimeException.class, ()->SignerHelper.getSP(docToSign, null));
		
		//get createSignaturePades obj - ok

		//insert here successful case test
		
		//pades - successfull pade case test		
		//assertDoesNotThrow(()->SignerHelper.pades(sigPades, "CDA_ATTACHMENT.pdf", docToSign));
		
		//pades exception - no file name
    	assertThrows(RuntimeException.class, ()->SignerHelper.pades(pades, null, docToSign));
    	
    	//pades exception - no file name and signing
    	assertThrows(RuntimeException.class, ()->SignerHelper.pades(null, null, docToSign));

    	//pades exception - all null params
    	assertThrows(RuntimeException.class, ()->SignerHelper.pades(null, null, null));

	}
	
	@Test
	@DisplayName("Argument enum test")
	void enumTest() {
		String key1 = "-h";
		boolean flag1 = false;
		String desc1 = "show this help page";
        assertEquals(ArgumentEnum.getByKey(key1).getKey(), key1);
        assertEquals(ArgumentEnum.getByKey(key1).getFlagHasValue(), flag1);
        assertEquals(ArgumentEnum.getByKey(key1).getDescription(), desc1);
		
		String key2 = "-c";
		boolean flag2 = true;
		String desc2 = "specify CDA file path to inject (mandatory)";
        assertEquals(ArgumentEnum.getByKey(key2).getKey(), key2);
        assertEquals(ArgumentEnum.getByKey(key2).getFlagHasValue(), flag2);
        assertEquals(ArgumentEnum.getByKey(key2).getDescription(), desc2);

		String key3 = "-p";
		boolean flag3 = true;
		String desc3 = "specify PDF file path (optional, if not given the tool will use a sample)";
        assertEquals(ArgumentEnum.getByKey(key3).getKey(), key3);
        assertEquals(ArgumentEnum.getByKey(key3).getFlagHasValue(), flag3);
        assertEquals(ArgumentEnum.getByKey(key3).getDescription(), desc3);

		String key4 = "-x";
		boolean flag4 = false;
		String desc4 = "enable verbose mode (optional, default is false)";
        assertEquals(ArgumentEnum.getByKey(key4).getKey(), key4);
        assertEquals(ArgumentEnum.getByKey(key4).getFlagHasValue(), flag4);
        assertEquals(ArgumentEnum.getByKey(key4).getDescription(), desc4);

		String key5 = "-o";
		boolean flag5 = true;
		String desc5 = "specify path output pdf file (optional , default is output.pdf)";
        assertEquals(ArgumentEnum.getByKey(key5).getKey(), key5);
        assertEquals(ArgumentEnum.getByKey(key5).getFlagHasValue(), flag5);
        assertEquals(ArgumentEnum.getByKey(key5).getDescription(), desc5);

		String key6 = "-v";
		boolean flag6 = false;
		String desc6 = "enable validation mode (optional, default is false)";
        assertEquals(ArgumentEnum.getByKey(key6).getKey(), key6);
        assertEquals(ArgumentEnum.getByKey(key6).getFlagHasValue(), flag6);
        assertEquals(ArgumentEnum.getByKey(key6).getDescription(), desc6);

		String key7 = "-s";
		boolean flag7 = false;
		String desc7 = "specify if pdf have to sign(optional , default is false)";
        assertEquals(ArgumentEnum.getByKey(key7).getKey(), key7);
        assertEquals(ArgumentEnum.getByKey(key7).getFlagHasValue(), flag7);
        assertEquals(ArgumentEnum.getByKey(key7).getDescription(), desc7);
        
        //null get by key
        assertNull(ArgumentEnum.getByKey(null));
	}
}
