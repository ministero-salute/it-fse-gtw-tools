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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
