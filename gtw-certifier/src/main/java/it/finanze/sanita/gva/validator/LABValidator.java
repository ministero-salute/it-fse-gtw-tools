package it.finanze.sanita.gva.validator;

import java.util.ArrayList;
import java.util.List;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import it.finanze.sanita.gva.enums.LABErrorEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LABValidator extends DocumentTypeValidator {
 
	public static List<ErrorUCTDTO> validateUC17(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		
		ValidationXML.equalIC(errors, LABErrorEnum.T000, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.T001, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystemName", "codeSystem", "LOINC");
		ValidationXML.equalIC(errors, LABErrorEnum.T002, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.T003, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry", "typeCode", "DRIV");
		ValidationXML.equalIC(errors, LABErrorEnum.T004, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > statusCode", "code", "completed", "active", "aborted");
		ValidationXML.equalIC(errors, LABErrorEnum.T005, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.T006, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LABErrorEnum.T007, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > observation > statusCode", "code", "completed", "aborted");
		ValidationXML.equalIC(errors, LABErrorEnum.T008, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > observation > interpretationCode", "codeSystem", "2.16.840.1.113883.5.83");
		ValidationXML.equalIC(errors, LABErrorEnum.T009, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > observation > interpretationCode", "codeSystemName", "ObservationInterpretation");
		ValidationXML.equalIC(errors, LABErrorEnum.T010, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > observation > interpretationCode", "codeSystem", "2.16.840.1.113883.5.83");
		ValidationXML.equalIC(errors, LABErrorEnum.T011, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship", "typeCode", "COMP");
		ValidationXML.equalIC(errors, LABErrorEnum.T012, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > procedure", "classCode", "PROC");
		ValidationXML.equalIC(errors, LABErrorEnum.T013, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > procedure", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LABErrorEnum.T014, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > observationMedia > value", "xsi:type", "ED");
		ValidationXML.equalIC(errors, LABErrorEnum.T015, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > observationMedia > value", "representation", "B64");
		ValidationXML.equalIC(errors, LABErrorEnum.T016, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship", "typeCode", "COMP");
		ValidationXML.equalIC(errors, LABErrorEnum.T017, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "code", "48767-8");
		ValidationXML.equalIC(errors, LABErrorEnum.T018, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.T019, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LABErrorEnum.T020, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "displayName", "Annotazioni e commenti");

		return errors;
	}

	public static List<ErrorUCTDTO> validateUC4New(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();

		ValidationXML.equalIC(errors, LABErrorEnum.T021, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.T022, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystemName", "codeSystem", "LOINC");
		ValidationXML.equalIC(errors, LABErrorEnum.T023, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.T024, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry", "typeCode", "DRIV");
		ValidationXML.equalIC(errors, LABErrorEnum.T025, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > statusCode", "code", "completed", "aborted", "active");
		ValidationXML.equalIC(errors, LABErrorEnum.T026, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > specimen > specimenRole > specimenPlayingEntity", "classCode", "MIC");
		ValidationXML.equalIC(errors, LABErrorEnum.T027, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > component > organizer", "classCode", "BATTERY");
		ValidationXML.equalIC(errors, LABErrorEnum.T028, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > component > organizer[classCode='BATTERY'] > statusCode", "code", "completed", "aborted");
		ValidationXML.equalIC(errors, LABErrorEnum.T029, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > component > organizer[classCode='BATTERY'] > component > observation > statusCode", "code", "completed", "aborted");
		ValidationXML.equalIC(errors, LABErrorEnum.T030, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > component > organizer[classCode='BATTERY'] > component > observation > methodCode", "codeSystem", "2.16.840.1.113883.5.84");
		ValidationXML.equalIC(errors, LABErrorEnum.T031, xml,"ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "code", "48767-8");
		ValidationXML.equalIC(errors, LABErrorEnum.T032, xml,"ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.T033, xml,"ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LABErrorEnum.T034, xml,"ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "displayName", "Annotazioni e commenti");

		return errors;
	}
	
	public static List<ErrorUCTDTO> validateUC1(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		ValidationXML.equalIC(errors, LABErrorEnum.SEC_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.SEC_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystemName", "LOINC");
		return errors;
	}

	public static List<ErrorUCTDTO> validateUC2(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		ValidationXML.equalIC(errors, LABErrorEnum.SEC_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.SEC_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LABErrorEnum.SEC_ACT_STATUS, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > statusCode", "code", "completed", "active", "aborted");
		return errors;
	}

	public static List<ErrorUCTDTO> validateUC3(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		ValidationXML.equalIC(errors, LABErrorEnum.SEC_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.SEC_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystemName", "LOINC");
		ValidationXML.containsIC(errors, LABErrorEnum.SEC_ACT_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "code", "33882-2");
		return errors;
	}
	
	public static List<ErrorUCTDTO> validateUC4(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		ValidationXML.equalIC(errors, LABErrorEnum.SEC_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.SEC_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LABErrorEnum.SEC_ACT_STATUS, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > statusCode", "code", "completed", "active", "aborted");
		ValidationXML.containsIC(errors, LABErrorEnum.SEC_ACT_CLUSTER_STATUS, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > statusCode", "code", "completed", "active", "aborted");
		ValidationXML.hasDateFormat(errors, LABErrorEnum.SEC_ACT_CLUSTER_TIME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > effectiveTime", "value");
		ValidationXML.containsIC(errors, LABErrorEnum.SEC_ACT_CLUSTER_SPECIMEN_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > specimen > specimenRole > specimenPlayingEntity", "classCode", "MIC");
		ValidationXML.containsIC(errors, LABErrorEnum.SEC_ACT_CLUSTER_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > component > organizer", "classCode", "BATTERY");
		ValidationXML.containsIC(errors, LABErrorEnum.SEC_ACT_CLUSTER_STATUS, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > component > organizer[classCode='BATTERY'] > statusCode", "code", "completed", "aborted");
		ValidationXML.hasDateFormat(errors, LABErrorEnum.SEC_ACT_CLUSTER_TIME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > component > organizer[classCode='BATTERY'] > effectiveTime", "value");
		ValidationXML.containsIC(errors, LABErrorEnum.SEC_ACT_CLUSTER_OBS_STATUS, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > component > organizer[classCode='BATTERY'] > component > observation > statusCode", "code", "completed", "aborted");
		ValidationXML.containsIC(errors, LABErrorEnum.SEC_ACT_CLUSTER_OBS_METHOD, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer[classCode='CLUSTER'] > component > organizer[classCode='BATTERY'] > component > observation > methodCode", "codeSystem" , "2.16.840.1.113883.5.84");
		return errors;
	}


	public static List<ErrorUCTDTO> validateUC5(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		ValidationXML.equalIC(errors, LABErrorEnum.CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LABErrorEnum.CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LABErrorEnum.STATUS, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > statusCode", "code", "completed", "active", "aborted");
		return errors;
	}


}
