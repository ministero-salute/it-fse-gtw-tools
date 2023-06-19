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
package it.finanze.sanita.fjm;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(Constants.Profile.TEST)
public class LauncherTest {
	
	//chiedere un corretto esempio di Request
	
	@Test
	@DisplayName("TokenResp TokenReq tests")
	public void tokenTest() throws Exception {
		TokenRequestDTO req = new TokenRequestDTO();
		byte[] bytes = "s1".getBytes();

		req.setAliasP12("s1");
		req.setConfig("{some:stringForGsonTest}");
		req.setDurationHours(2);
		req.setP12(bytes);
		req.setPasswordP12("password");
		req.setPem(bytes);
		req.setFileToHash(bytes);
		assertThrows(IllegalArgumentException.class, () -> Launcher.getTokens(req));
	}
}
