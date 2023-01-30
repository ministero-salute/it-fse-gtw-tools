package it.finanze.sanita.fpm;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(Constants.Profile.TEST)
class LauncherTest {


    @Test
    @DisplayName("launcher tests")
    void launcherTests() throws Exception {

    	//pdf file
    	byte[] pdf = Utility.getFileFromFS("src/test/resources/Files/CDA_ATTACHMENT.pdf");	
    	
    	assertDoesNotThrow(()->Launcher.getPdf(pdf));
    	assertDoesNotThrow(()->Launcher.extract(pdf));
    }

    
    
    
}
