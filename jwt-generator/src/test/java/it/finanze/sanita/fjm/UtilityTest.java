package it.finanze.sanita.fjm;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(Constants.Profile.TEST)
public class UtilityTest {

    @Test
    @DisplayName("Mixed utility")
    public void genericUtilityTest() throws Exception {
		
    	// file path - not empty test
		File file = new File("CDA_ATTACHMENT.pdf");
		String path = file.getAbsolutePath();
		
		assertFalse(Utility.nullOrEmpty(path));

        //get file from fs test - wrong file path string
		byte[] wrongFilePath = Utility.getFileFromFS("src/test/resources/Files/CDA_ATTACHMENT.doc");		
        assertNull(wrongFilePath);        
        
        //get file from fs test - correct file path string
    	byte[] correctFilePathPdf = Utility.getFileFromFS("src/test/resources/Files/CDA_ATTACHMENT.pdf");
    	assertNotNull(correctFilePathPdf);
    	
    	//is pdf - true case test
    	String pdfFile = "%PDFMagic";
        byte[] pdfAttachment = pdfFile.getBytes();
        
        assertTrue(Utility.isPdf(pdfAttachment));
        
        //is pdf - false case test
        String notPdfFile = "";
        byte[] notPdfAttachment = notPdfFile.getBytes();
        
        assertFalse(Utility.isPdf(notPdfAttachment));
        
        //encoded object in 256 - not null case test
        assertNotNull(Utility.encodeSHA256(pdfAttachment));

    }

}
