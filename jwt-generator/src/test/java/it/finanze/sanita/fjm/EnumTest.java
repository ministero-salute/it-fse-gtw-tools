package it.finanze.sanita.fjm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(Constants.Profile.TEST)
public class EnumTest {
	
	
	@Test
	@DisplayName("argumentEnum test")
	public void argumentEnum() {
		String key1 = "-h";
		boolean flag1 = false;
		String desc1 = "show this help page";
        assertEquals(key1, ArgumentEnum.HELP_MODE.getKey());
        assertEquals(flag1, ArgumentEnum.HELP_MODE.getFlagHasValue());
        assertEquals(desc1, ArgumentEnum.HELP_MODE.getDescription());

		String key2 = "-d";
		boolean flag2 = true;
		String desc2 = "specify json data file path (mandatory)";
        assertEquals(key2, ArgumentEnum.JSON_DATA.getKey());
        assertEquals(flag2, ArgumentEnum.JSON_DATA.getFlagHasValue());
        assertEquals(desc2, ArgumentEnum.JSON_DATA.getDescription());

		String key3 = "-f";
		boolean flag3 = true;
		String desc3 = "specify PDF file path to publish (optional, if given the tool will calculate hash)";
        assertEquals(key3, ArgumentEnum.FILE_TO_PUBLISH.getKey());
        assertEquals(flag3, ArgumentEnum.FILE_TO_PUBLISH.getFlagHasValue());
        assertEquals(desc3, ArgumentEnum.FILE_TO_PUBLISH.getDescription());

		String key4 = "-a";
		boolean flag4 = true;
		String desc4 = "specify alias of p12 file (mandatory)";
        assertEquals(key4, ArgumentEnum.P12_ALIAS.getKey());
        assertEquals(flag4, ArgumentEnum.P12_ALIAS.getFlagHasValue());
        assertEquals(desc4, ArgumentEnum.P12_ALIAS.getDescription());

		String key5 = "-p";
		boolean flag5 = true;
		String desc5 = "specify password of p12 file (mandatory)";
        assertEquals(key5, ArgumentEnum.P12_PWD.getKey());
        assertEquals(flag5, ArgumentEnum.P12_PWD.getFlagHasValue());
        assertEquals(desc5, ArgumentEnum.P12_PWD.getDescription());

		String key6 = "-t";
		boolean flag6 = true;
		String desc6 = "specify token duration (optional, default is 24h)";
        assertEquals(key6, ArgumentEnum.DURATION_JWT.getKey());
        assertEquals(flag6, ArgumentEnum.DURATION_JWT.getFlagHasValue());
        assertEquals(desc6, ArgumentEnum.DURATION_JWT.getDescription());

		String key7 = "-x";
		boolean flag7 = false;
		String desc7 = "enable verbose mode (optional, default is false)";
        assertEquals(key7, ArgumentEnum.VERBOSE_MODE.getKey());
        assertEquals(flag7, ArgumentEnum.VERBOSE_MODE.getFlagHasValue());
        assertEquals(desc7, ArgumentEnum.VERBOSE_MODE.getDescription());

		String key8 = "-v";
		boolean flag8 = false;
		String desc8 = "enable validation mode (optional, default is false)";
        assertEquals(key8, ArgumentEnum.VALIDATION_MODE.getKey());
        assertEquals(flag8, ArgumentEnum.VALIDATION_MODE.getFlagHasValue());
        assertEquals(desc8, ArgumentEnum.VALIDATION_MODE.getDescription());

	}
	
    @Test
    @DisplayName("jwtClaimsEnum test")
    public void jwtClaimsEnum() {
        String code1 = "alg";
        boolean flag1 = false;
        assertEquals(code1, JWTClaimsEnum.ALG.getKey());
        assertEquals(flag1, JWTClaimsEnum.ALG.getAutoFlagPayloadClaim());
        
        String code2 = "typ";
        boolean flag2 = false;
        assertEquals(code2, JWTClaimsEnum.TYP.getKey());
        assertEquals(flag2, JWTClaimsEnum.TYP.getAutoFlagPayloadClaim());
        
        String code3 = "x5c";
        boolean flag3 = false;
        assertEquals(code3, JWTClaimsEnum.X5C.getKey());
        assertEquals(flag3, JWTClaimsEnum.X5C.getAutoFlagPayloadClaim());

        
        String code4 = "sub";
        boolean flag4 = true;
        assertEquals(code4, JWTClaimsEnum.SUB.getKey());
        assertEquals(flag4, JWTClaimsEnum.SUB.getAutoFlagPayloadClaim());

        String code5 = "purpose_of_use";
        boolean flag5 = true;
        assertEquals(code5, JWTClaimsEnum.PURPOSE_OF_USE.getKey());
        assertEquals(flag5, JWTClaimsEnum.PURPOSE_OF_USE.getAutoFlagPayloadClaim());

        String code6 = "iss";
        boolean flag6 = true;
        assertEquals(code6, JWTClaimsEnum.ISS.getKey());
        assertEquals(flag6, JWTClaimsEnum.ISS.getAutoFlagPayloadClaim());

        String code7 = "subject_application_id";
        boolean flag7 = true;
        assertEquals(code7, JWTClaimsEnum.SUBJECT_APPLICATION_ID.getKey());
        assertEquals(flag7, JWTClaimsEnum.SUBJECT_APPLICATION_ID.getAutoFlagPayloadClaim());

        String code8 = "subject_application_vendor";
        boolean flag8 = true;
        assertEquals(code8, JWTClaimsEnum.SUBJECT_APPLICATION_VENDOR.getKey());
        assertEquals(flag8, JWTClaimsEnum.SUBJECT_APPLICATION_VENDOR.getAutoFlagPayloadClaim());

        String code10 = "subject_application_version";
        boolean flag10 = true;
        assertEquals(code10, JWTClaimsEnum.SUBJECT_APPLICATION_VERSION.getKey());
        assertEquals(flag10, JWTClaimsEnum.SUBJECT_APPLICATION_VERSION.getAutoFlagPayloadClaim());

        String code11 = "locality";
        boolean flag11 = true;
        assertEquals(code11, JWTClaimsEnum.LOCALITY.getKey());
        assertEquals(flag11, JWTClaimsEnum.LOCALITY.getAutoFlagPayloadClaim());

        String code12 = "subject_organization";
        boolean flag12 = true;
        assertEquals(code12, JWTClaimsEnum.SUBJECT_ORGANIZATION.getKey());
        assertEquals(flag12, JWTClaimsEnum.SUBJECT_ORGANIZATION.getAutoFlagPayloadClaim());

        String code13 = "aud";
        boolean flag13 = true;
        assertEquals(code13, JWTClaimsEnum.AUD.getKey());
        assertEquals(flag13, JWTClaimsEnum.AUD.getAutoFlagPayloadClaim());

        String code14 = "subject_organization_id";
        boolean flag14 = true;
        assertEquals(code14, JWTClaimsEnum.SUBJECT_ORGANIZATION_ID.getKey());
        assertEquals(flag14, JWTClaimsEnum.SUBJECT_ORGANIZATION_ID.getAutoFlagPayloadClaim());

        String code15 = "patient_consent";
        boolean flag15 = true;
        assertEquals(code15, JWTClaimsEnum.PATIENT_CONSENT.getKey());
        assertEquals(flag15, JWTClaimsEnum.PATIENT_CONSENT.getAutoFlagPayloadClaim());

        String code16 = "action_id";
        boolean flag16 = true;
        assertEquals(code16, JWTClaimsEnum.ACTION_ID.getKey());
        assertEquals(flag16, JWTClaimsEnum.ACTION_ID.getAutoFlagPayloadClaim());

        String code17 = "resource_hl7_type";
        boolean flag17 = true;
        assertEquals(code17, JWTClaimsEnum.RESOURCE_HL7_TYPE.getKey());
        assertEquals(flag17, JWTClaimsEnum.RESOURCE_HL7_TYPE.getAutoFlagPayloadClaim());

        String code18 = "jti";
        boolean flag18 = true;
        assertEquals(code18, JWTClaimsEnum.JTI.getKey());
        assertEquals(flag18, JWTClaimsEnum.JTI.getAutoFlagPayloadClaim());

        String code19 = "iat";
        boolean flag19 = true;
        assertEquals(code19, JWTClaimsEnum.IAT.getKey());
        assertEquals(flag19, JWTClaimsEnum.IAT.getAutoFlagPayloadClaim());

        String code20 = "exp";
        boolean flag20 = true;
        assertEquals(code20, JWTClaimsEnum.EXP.getKey());
        assertEquals(flag20, JWTClaimsEnum.EXP.getAutoFlagPayloadClaim());

        String code21 = "JWT";
        boolean flag21 = false;
        assertEquals(code21, JWTClaimsEnum.JWT.getKey());
        assertEquals(flag21, JWTClaimsEnum.JWT.getAutoFlagPayloadClaim());

        String code22 = "p12_path";
        boolean flag22 = false;
        assertEquals(code22, JWTClaimsEnum.P12_PATH.getKey());
        assertEquals(flag22, JWTClaimsEnum.P12_PATH.getAutoFlagPayloadClaim());

        String code23 = "attachment_hash";
        boolean flag23 = true;
        assertEquals(code23, JWTClaimsEnum.ATTACHMENT_HASH.getKey());
        assertEquals(flag23, JWTClaimsEnum.ATTACHMENT_HASH.getAutoFlagPayloadClaim());

        String code24 = "pem_path";
        boolean flag24 = false;
        assertEquals(code24, JWTClaimsEnum.PEM_PATH.getKey());
        assertEquals(flag24, JWTClaimsEnum.PEM_PATH.getAutoFlagPayloadClaim());

    }
    
    @Test
    @DisplayName("jwtAuthEnum test")
    public void jwtAuthEnum() {
        String code1 = "alg";
        boolean flag1 = false;
        assertEquals(code1, JWTAuthEnum.ALG.getKey());
        assertEquals(flag1, JWTAuthEnum.ALG.getAutoFlagPayloadClaim());
        
        String code2 = "typ";
        boolean flag2 = false;
        assertEquals(code2, JWTAuthEnum.TYP.getKey());
        assertEquals(flag2, JWTAuthEnum.TYP.getAutoFlagPayloadClaim());
        
        String code3 = "x5c";
        boolean flag3 = false;
        assertEquals(code3, JWTAuthEnum.X5C.getKey());
        assertEquals(flag3, JWTAuthEnum.X5C.getAutoFlagPayloadClaim());

        
        String code4 = "sub";
        boolean flag4 = true;
        assertEquals(code4, JWTAuthEnum.SUB.getKey());
        assertEquals(flag4, JWTAuthEnum.SUB.getAutoFlagPayloadClaim());

        String code5 = "purpose_of_use";
        boolean flag5 = false;
        assertEquals(code5, JWTAuthEnum.PURPOSE_OF_USE.getKey());
        assertEquals(flag5, JWTAuthEnum.PURPOSE_OF_USE.getAutoFlagPayloadClaim());

        String code6 = "iss";
        boolean flag6 = true;
        assertEquals(code6, JWTAuthEnum.ISS.getKey());
        assertEquals(flag6, JWTAuthEnum.ISS.getAutoFlagPayloadClaim());

        String code11 = "locality";
        boolean flag11 = false;
        assertEquals(code11, JWTAuthEnum.LOCALITY.getKey());
        assertEquals(flag11, JWTAuthEnum.LOCALITY.getAutoFlagPayloadClaim());

        String code12 = "subject_organization";
        boolean flag12 = false;
        assertEquals(code12, JWTAuthEnum.SUBJECT_ORGANIZATION.getKey());
        assertEquals(flag12, JWTAuthEnum.SUBJECT_ORGANIZATION.getAutoFlagPayloadClaim());

        String code13 = "aud";
        boolean flag13 = true;
        assertEquals(code13, JWTAuthEnum.AUD.getKey());
        assertEquals(flag13, JWTAuthEnum.AUD.getAutoFlagPayloadClaim());

        String code14 = "subject_organization_id";
        boolean flag14 = false;
        assertEquals(code14, JWTAuthEnum.SUBJECT_ORGANIZATION_ID.getKey());
        assertEquals(flag14, JWTAuthEnum.SUBJECT_ORGANIZATION_ID.getAutoFlagPayloadClaim());

        String code15 = "patient_consent";
        boolean flag15 = false;
        assertEquals(code15, JWTAuthEnum.PATIENT_CONSENT.getKey());
        assertEquals(flag15, JWTAuthEnum.PATIENT_CONSENT.getAutoFlagPayloadClaim());

        String code16 = "action_id";
        boolean flag16 = false;
        assertEquals(code16, JWTAuthEnum.ACTION_ID.getKey());
        assertEquals(flag16, JWTAuthEnum.ACTION_ID.getAutoFlagPayloadClaim());

        String code17 = "resource_hl7_type";
        boolean flag17 = false;
        assertEquals(code17, JWTAuthEnum.RESOURCE_HL7_TYPE.getKey());
        assertEquals(flag17, JWTAuthEnum.RESOURCE_HL7_TYPE.getAutoFlagPayloadClaim());

        String code18 = "jti";
        boolean flag18 = true;
        assertEquals(code18, JWTAuthEnum.JTI.getKey());
        assertEquals(flag18, JWTAuthEnum.JTI.getAutoFlagPayloadClaim());

        String code19 = "iat";
        boolean flag19 = true;
        assertEquals(code19, JWTAuthEnum.IAT.getKey());
        assertEquals(flag19, JWTAuthEnum.IAT.getAutoFlagPayloadClaim());

        String code20 = "exp";
        boolean flag20 = true;
        assertEquals(code20, JWTAuthEnum.EXP.getKey());
        assertEquals(flag20, JWTAuthEnum.EXP.getAutoFlagPayloadClaim());

        String code21 = "JWT";
        boolean flag21 = false;
        assertEquals(code21, JWTAuthEnum.JWT.getKey());
        assertEquals(flag21, JWTAuthEnum.JWT.getAutoFlagPayloadClaim());

        String code22 = "p12_path";
        boolean flag22 = false;
        assertEquals(code22, JWTAuthEnum.P12_PATH.getKey());
        assertEquals(flag22, JWTAuthEnum.P12_PATH.getAutoFlagPayloadClaim());

        String code23 = "attachment_hash";
        boolean flag23 = false;
        assertEquals(code23, JWTAuthEnum.ATTACHMENT_HASH.getKey());
        assertEquals(flag23, JWTAuthEnum.ATTACHMENT_HASH.getAutoFlagPayloadClaim());

        String code24 = "pem_path";
        boolean flag24 = false;
        assertEquals(code24, JWTAuthEnum.PEM_PATH.getKey());
        assertEquals(flag24, JWTAuthEnum.PEM_PATH.getAutoFlagPayloadClaim());
        
        String code25 = "subject_role";
        boolean flag25 = false;
        assertEquals(code25, JWTAuthEnum.SUBJECT_ROLE.getKey());
        assertEquals(flag25, JWTAuthEnum.SUBJECT_ROLE.getAutoFlagPayloadClaim());
    }


}
