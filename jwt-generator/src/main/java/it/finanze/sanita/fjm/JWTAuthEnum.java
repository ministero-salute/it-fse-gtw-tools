package it.finanze.sanita.fjm;

/**
 * Copyright (c) 2022, Ministero della Salute
 *
 * JWT AUTH class.
 */
enum JWTAuthEnum {
	
	/**
	 * Algorithm claim.
	 */
	ALG("alg", false),
	
	/**
	 * Type claim.
	 */
	TYP("typ", false),

	/**
	 * Public certificate claim.
	 */
	X5C("x5c", false),

	/**
	 * Subject claim.
	 */
	SUB("sub", true),

	/**
	 * Subject role claim.
	 */
	SUBJECT_ROLE("subject_role", false),

	/**
	 * Purpose of use claim.
	 */
	PURPOSE_OF_USE("purpose_of_use", false),

	/**
	 * Issuer claim.
	 */
	ISS("iss", true),

	/**
	 * Locality claim.
	 */
	LOCALITY("locality", false),

	/**
	 * Subject Organization ID claim.
	 */
	SUBJECT_ORGANIZATION_ID("subject_organization_id", false),

	/**
	 * Subject Organization claim.
	 */
	SUBJECT_ORGANIZATION("subject_organization", false),

	/**
	 * Audience claim.
	 */
	AUD("aud", true),

	/**
	 * Patient consent claim.
	 */
	PATIENT_CONSENT("patient_consent", false),

	/**
	 * Action id claim.
	 */
	ACTION_ID("action_id", false),

	/**
	 * HL7 resource type claim.
	 */
	RESOURCE_HL7_TYPE("resource_hl7_type", false),

	/**
	 * Identifier claim.
	 */
	JTI("jti", true),

	/**
	 * Person id claim.
	 */
	PERSON_ID("person_id", false),

	/**
	 * Issuing at claim.
	 */
	IAT("iat", true),

	/**
	 * Expiration time claim.
	 */
	EXP("exp", true),

	/**
	 * JWT token type.
	 */
	JWT("JWT", false),

	 
	/**
	 * Hash claim.
	 */
	ATTACHMENT_HASH("attachment_hash", false),

	/**
	 * PEM path.
	 */
	PEM_PATH("pem_path", false),

	/**
	 * P12 path.
	 */
	P12_PATH("p12_path", false);

	/**
	 * Key.
	 */
	private String key;

	/**
	 * Flag claim retrive from json data.
	 */
	private boolean flagAutoPayloadClaim;

	/**
	 * Constructor.
	 * 
	 * @param inKey						key
	 * @param inFlagAutoPayloadClaim	flag claim retrive from json data
	 */
	private JWTAuthEnum(String inKey, boolean inFlagAutoPayloadClaim) {
		key = inKey;
		flagAutoPayloadClaim = inFlagAutoPayloadClaim;
	}
	
	/**
	 * Getter key.
	 * 
	 * @return	key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Getter flag claim retrive from json data
	 * @return	claim retrive from json data
	 */
	public boolean getAutoFlagPayloadClaim() {
		return flagAutoPayloadClaim;
	}

}
