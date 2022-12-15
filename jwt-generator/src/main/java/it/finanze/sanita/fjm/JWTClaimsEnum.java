package it.finanze.sanita.fjm;


/**
 * Copyright (c) 2022, Ministero della Salute
 *
 * JWT Claims class.
 */
enum JWTClaimsEnum {
	
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
	SUBJECT_ROLE("subject_role", true),

	/**
	 * Purpose of use claim.
	 */
	PURPOSE_OF_USE("purpose_of_use", true),

	/**
	 * Issuer claim.
	 */
	ISS("iss", true),

	/**
	 * Subject Application ID claim.
	 */
	SUBJECT_APPLICATION_ID("subject_application_id", true),

	/**
	 * Subject Application Vendor claim.
	 */
	SUBJECT_APPLICATION_VENDOR("subject_application_vendor", true),

	/**
	 * Subject Application Version claim.
	 */
	SUBJECT_APPLICATION_VERSION("subject_application_version", true),

	/**
	 * Locality claim.
	 */
	LOCALITY("locality", true),

	/**
	 * Subject Organization ID claim.
	 */
	SUBJECT_ORGANIZATION_ID("subject_organization_id", true),

	/**
	 * Subject Organization claim.
	 */
	SUBJECT_ORGANIZATION("subject_organization", true),

	/**
	 * Audience claim.
	 */
	AUD("aud", true),

	/**
	 * Patient consent claim.
	 */
	PATIENT_CONSENT("patient_consent", true),

	/**
	 * Action id claim.
	 */
	ACTION_ID("action_id", true),

	/**
	 * HL7 resource type claim.
	 */
	RESOURCE_HL7_TYPE("resource_hl7_type", true),

	/**
	 * Identifier claim.
	 */
	JTI("jti", true),

	/**
	 * Person id claim.
	 */
	PERSON_ID("person_id", true),

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
	ATTACHMENT_HASH("attachment_hash", true),

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
	private JWTClaimsEnum(String inKey, boolean inFlagAutoPayloadClaim) {
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
