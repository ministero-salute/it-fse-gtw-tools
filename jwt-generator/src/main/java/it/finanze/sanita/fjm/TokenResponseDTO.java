package it.finanze.sanita.fjm;

import java.util.HashMap;
import java.util.Map;

public class TokenResponseDTO {

	private String authorizationBearer;
	private String fseJwtSignature;
	
	public TokenResponseDTO(String authJWT, String signJWT) {
		this.authorizationBearer = authJWT;
		this.fseJwtSignature = signJWT;
	}
	
	public String getAuthorizationBearer() {
		return authorizationBearer;
	}
	
	public String getFseJwtSignature() {
		return fseJwtSignature;
	}
	
	public Map<String, String> toMap() {
		Map<String, String> result = new HashMap<>();
		result.put("Authorization", 	"Bearer " + getAuthorizationBearer());
		result.put("FSE-JWT-Signature", getFseJwtSignature());
		return result;
	}
	
}
