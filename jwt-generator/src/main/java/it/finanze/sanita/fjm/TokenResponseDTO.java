package it.finanze.sanita.fjm;

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
	
}
