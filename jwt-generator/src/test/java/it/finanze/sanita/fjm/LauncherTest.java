/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
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
