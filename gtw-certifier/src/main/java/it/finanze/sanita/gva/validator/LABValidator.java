/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.validator;

import java.util.ArrayList;
import java.util.List;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import it.finanze.sanita.gva.enums.LABErrorEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LABValidator extends DocumentTypeValidator {
	
	
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
