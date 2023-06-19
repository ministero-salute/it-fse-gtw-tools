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
package it.finanze.sanita.gva.validator;
import java.util.ArrayList;
import java.util.List;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import it.finanze.sanita.gva.enums.RADErrorEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RADValidator extends DocumentTypeValidator {
	
	public static List<ErrorUCTDTO> validateUC1(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		esameEseguito(xml, errors);
		referto(xml, errors);
		return errors;
	}

	public static List<ErrorUCTDTO> validateUC2(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		esameEseguito(xml, errors);
		referto(xml, errors);
		conclusioni(xml, errors);
		return errors;
	}

	public static List<ErrorUCTDTO> validateUC3(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		esameEseguito(xml, errors);
		referto(xml, errors);
		conclusioni(xml, errors);
		dicom(xml, errors);
		quesito(xml, errors);
		storiaV1(xml, errors);
		precedentiEsami(xml, errors);
		suggerimenti(xml, errors);
		return errors;
	}

	public static List<ErrorUCTDTO> validateUC4(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		esameEseguito(xml, errors);
		conclusioni(xml, errors);
		storiaV2(xml, errors);
		precedentiEsami(xml, errors);
		referto(xml, errors);
		infoAggiuntive(xml, errors);
		complicanze(xml, errors);
		suggerimenti(xml, errors);
		return errors;
	}

	private static void storiaV2(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > code", "code", "11329-0");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_CODE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > code", "displayName", "Storia Generale");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation", "classCode", "OBS");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation > entryRelationship > observation > code", "code", "89261-2", "33999-4");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation > entryRelationship > observation > code", "codeSystemName", "LOINC");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation > entryRelationship > observation > code", "displayName", "Decorso Clinico", "Stato");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer", "classCode", "CLUSTER");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > code", "code", "10157-6");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > statusCode", "code", "completed");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_REL_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > subject > relatedSubject", "classcode", "PRS");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_OBS_VALUE_TYPE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > component > observation > value", "xsi:type", "CD");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_OBS_VALUE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > component > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ORG_OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > component > observation > entryRelationship > observation", "classCode", "OBS");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ORG_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > component > observation > entryRelationship > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ALLERGIE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > code", "code", "48765-2");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ALLERGIE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ALLERGIE_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ALLERGIE_CODE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > code", "displayName", "Allergie e/o reazioni avverse");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > code", "code", "52473-6");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > code", "displayName", "Allergia o causa della reazione");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > statusCode", "code", "completed");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_VALUE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > value", "codeSystem", true, "2.16.840.1.113883.5.4");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship", "typeCode", "MFST", "SUBJ", "REFR");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "code", "75321-0", "5321-0", "SEV", "33999-4");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1", "2.16.840.1.113883.5.4");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "codeSystemName", "LOINC", "ActCode");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "displayName" , "Obiettività Clinica", "Clinical Finding", "Criticality", "Stato");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > statusCode", "code", "completed");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_ACT_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > code", "code", "48767-8");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_ACT_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_ACT_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > code", "codeSystemName", "LOINC");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_ACT_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > code", "displayName", "Annotation comment");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_ACT_STATUS, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > statusCode","code", "completed");
	}

	private static void complicanze(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "code", "55109-3");
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "displayName", "Complicazioni");
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation", "classCode", "OBS");
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > code", "code", "75326-9");
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > effectiveTime > low", "value");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > effectiveTime > high", "value");
	}

	private static void infoAggiuntive(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Informazioni_aggiuntive'] > code", "code", "55107-7");
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Informazioni_aggiuntive'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Informazioni_aggiuntive'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.INFO_AGG_CODE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Informazioni_aggiuntive'] > code", "displayName", "Appendice");
	}



	private static void referto(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.REFERTO_CODE, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Referto'] > code", "code", "18782-3");
		ValidationXML.equalIC(errors, RADErrorEnum.REFERTO_CODE_SYSTEM, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Referto'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.REFERTO_CODE_SYSTEM_NAME, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Referto'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.REFERTO_DISPLAY_NAME, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Referto'] > code", "displayName", "Rilievo - Radiologia");
	}

	private static void esameEseguito(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.ESAME_ESEGUITO_CODE, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Esame_eseguito'] > code", "code", "55111-9");
		ValidationXML.equalIC(errors, RADErrorEnum.ESAME_ESEGUITO_CODE_SYSTEM, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Esame_eseguito'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.ESAME_ESEGUITO_CODE_SYSTEM_NAME, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Esame_eseguito'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.ESAME_ESEGUITO_DISPLAY_NAME, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Esame_eseguito'] > code", "displayName", "Descrizioni attuale procedure di imaging");
		ValidationXML.equalIC(errors, RADErrorEnum.ESAME_ESEGUITO_MOOD_CODE, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Esame_eseguito'] > entry > act", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.ESAME_ESEGUITO_CLASS_CODE, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Esame_eseguito'] > entry > act", "classCode", "ACT");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.ESAME_ESEGUITO_EFFECTIVE_TIME, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Esame_eseguito'] > entry > act > effectiveTime", "value");
	}


	private static void conclusioni(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.CONCLUSIONI_CODE, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Conclusioni'] > code", "code", "55110-1");
		ValidationXML.equalIC(errors, RADErrorEnum.CONCLUSIONI_CODE_SYSTEM, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Conclusioni'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.CONCLUSIONI_CODE_SYSTEM_NAME, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Conclusioni'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.CONCLUSIONI_DISPLAY_NAME, xml, "ClinicAlDocument > component > structuredBody > component > section[ID='Conclusioni'] > code", "displayName", "Conclusioni");
	}

	private static void storiaV1(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > code", "code", "11329-0");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_CODE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > code", "displayName", "Storia Generale");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation", "classCode", "OBS");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation > entryRelationship > observation > code", "code", "89261-2", "33999-4");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation > entryRelationship > observation > code", "codeSystemName", "LOINC");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > observation > entryRelationship > observation > code", "displayName", "Decorso Clinico", "Stato");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer", "classCode", "CLUSTER");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > code", "code", "10157-6");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > statusCode", "code", "completed");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_REL_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > subject > relatedSubject", "classcode", "PRS");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_REL_SUB_GEN, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > subject > relatedSubject > subject > administrativeGenderCode", "code","M", "F", "UN");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_OBS_VALUE_TYPE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > component > observation > value", "xsi:type", "CD");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ORG_OBS_VALUE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > component > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ORG_OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > component > observation > entryRelationship > observation", "classCode", "OBS");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ORG_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > entry > organizer > component > observation > entryRelationship > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ALLERGIE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > code", "code", "48765-2");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ALLERGIE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ALLERGIE_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ALLERGIE_CODE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > code", "displayName", "Allergie e/o reazioni avverse");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > code", "code", "52473-6");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > code", "displayName", "Allergia o causa della reazione");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > statusCode", "code", "completed");
		ValidationXML.equalIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_VALUE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > value", "codeSystem", true, "2.16.840.1.113883.5.4");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "code", "75321-0", "5321-0", "SEV", "33999-4");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1", "2.16.840.1.113883.5.4");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "codeSystemName", "LOINC", "ActCode");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "displayName" , "Obiettività Clinica", "Clinical Finding", "Criticality", "Stato");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > statusCode", "code", "completed");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_ACT_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > code", "code", "48767-8");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_ACT_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_ACT_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > code", "codeSystemName", "LOINC");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_ACT_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > code", "displayName", "Annotation comment");
		ValidationXML.containsIC(errors, RADErrorEnum.STORIA_ALLERGIE_ACT_OBS_ACT_STATUS, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Storia_Clinica'] > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > statusCode", "code" , "completed");
	}

	private static void precedentiEsami(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.ESAMI_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Precedenti_esami_eseguiti'] > code", "code", "55114-3");
		ValidationXML.equalIC(errors, RADErrorEnum.ESAMI_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Precedenti_esami_eseguiti'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.ESAMI_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Precedenti_esami_eseguiti'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.ESAMI_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Precedenti_esami_eseguiti'] > code", "displayName", "Precedente procedura di imaging, descrizioni");
		ValidationXML.equalIC(errors, RADErrorEnum.ESAMI_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Precedenti_esami_eseguiti'] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.ESAMI_OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Precedenti_esami_eseguiti'] > entry > observation", "classCode", "OBS");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.ESAMI_OBS_EFF_TIME_LOW, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Precedenti_esami_eseguiti'] > entry > observation > effectiveTime > low", "value");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.ESAMI_OBS_EFF_TIME_HIGH, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Precedenti_esami_eseguiti'] > entry > observation > effectiveTime > high", "value");
	}

	private static void quesito(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > code", "code", "18785-6");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > code", "displayName", "Motivo per lo studio - Radiologia");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > entry > observation", "classCode", "OBS");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > entry > observation > code", "code", "29308-4");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > entry > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > entry > observation > code", "displayName", "Diagnosi");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_OBS_VALUE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > entry > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.equalIC(errors, RADErrorEnum.QUESITO_OBS_VALUE_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Quesito_diagnostico'] > entry > observation > value", "codeSystemName", "ICD9CM");
	}

	private static void dicom(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.DICOM_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='DICOM_Object_Catalog'] > code", "code", "121181");
		ValidationXML.equalIC(errors, RADErrorEnum.DICOM_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='DICOM_Object_Catalog'] > code", "codeSystem", "1.2.840.10008.2.16.4");
		ValidationXML.equalIC(errors, RADErrorEnum.DICOM_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='DICOM_Object_Catalog'] > code", "codeSystemName", "DCM");
		ValidationXML.equalIC(errors, RADErrorEnum.DICOM_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='DICOM_Object_Catalog'] > code", "displayName", "DICOM Object Catalog");
	}

	private static void suggerimenti(String xml, List<ErrorUCTDTO> errors) {
		ValidationXML.equalIC(errors, RADErrorEnum.SUGGERIMENTI_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Suggerimenti_per_il_medico_prescrittore'] > code", "code", "18783-1");
		ValidationXML.equalIC(errors, RADErrorEnum.SUGGERIMENTI_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Suggerimenti_per_il_medico_prescrittore'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.SUGGERIMENTI_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Suggerimenti_per_il_medico_prescrittore'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.SUGGERIMENTI_CODE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Suggerimenti_per_il_medico_prescrittore'] > code", "displayName", "Raccomandazione studio - Radiologia");
	}

}

