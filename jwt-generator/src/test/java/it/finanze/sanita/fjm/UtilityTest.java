package it.finanze.sanita.fjm;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    	String pdfFile = "%PDFMagic";
        byte[] pdfAttachment = pdfFile.getBytes();
        
        assertTrue(Utility.isPdf(pdfAttachment));
        assertNotNull(Utility.encodeSHA256(pdfAttachment));
        
        
    }

}
