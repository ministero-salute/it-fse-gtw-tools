 /*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.validator;

 import static it.finanze.sanita.gva.dto.XPathAndValuesDTO.buildPathValue;

import java.util.ArrayList;
import java.util.List;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import it.finanze.sanita.gva.enums.CVACErrorEnum;
import it.finanze.sanita.gva.enums.SVACErrorEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

 
 @NoArgsConstructor(access = AccessLevel.PRIVATE)
 public final class SVACValidator extends DocumentTypeValidator {

 	 
 	public static List<ErrorUCTDTO> validateUC4New(String xml) {
 		List<ErrorUCTDTO> errors = new ArrayList<>();

 		ValidationXML.equalIC(errors, SVACErrorEnum.T000, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.3.1");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T001, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > code", "code", "11369-6");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T002, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > code", "codeSystem", "2.16.840.1.113883.6.1");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T003, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > code", "codeSystemName", "LOINC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T004, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > code", "displayName", "Storia delle immunizzazioni");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T005, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration", "classCode", "SBADM");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T007, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration", "moodCode", "EVN");
 		ValidationXML.equalTAG(errors, SVACErrorEnum.T008, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > title", "Scheda della singola vaccinazione");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T009, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > statusCode", "code", "completed");
 		ValidationXML.checkSectionByTemplateId(errors, SVACErrorEnum.T010, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration", "" , "2.16.840.1.113883.2.9.10.1.11.4.1", false, buildPathValue("routeCode", "codeSystem", "2.16.840.1.113883.5.112"), buildPathValue("approachSiteCode", "codeSystem", "2.16.840.1.113883.5.1052"), buildPathValue("approachSiteCode", "codeSystemName", "ActSite"));
 		ValidationXML.equalIC(errors, SVACErrorEnum.T011, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > statusCode", "code", "completed");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T012, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T013, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystemName", "AIC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T014, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystem", "2.16.840.1.113883.6.73");
 		ValidationXML.checkSectionByTemplateId(errors, SVACErrorEnum.T015, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship", " > observation", "2.16.840.1.113883.2.9.10.1.11.4.4", false, buildPathValue("code", "code", "59778-1", "30981-5", "30980-7", "59777-3", "59778-1"), buildPathValue("code", "codeSystem", "2.16.840.1.113883.4.642.3.308"),  buildPathValue("code", "displayName", "Earliest date to give", "Date vaccine due", "Latest date to give immunization", "Date when overdue for immunization"), buildPathValue(null, "typeCode", "REFR"), buildPathValue("code", "codeSystemName", "ImmunizationRecommendationDateCriterionCodes"));
 		ValidationXML.checkSectionByTemplateId(errors, SVACErrorEnum.T016, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship", " > observation", "2.16.840.1.113883.2.9.10.1.11.4.3", false, buildPathValue("code", "code","30973-2"), buildPathValue("code", "codeSystem", "2.16.840.1.113883.6.1"), buildPathValue("code", "displayName", "Numero di dose"), buildPathValue(null, "typeCode","SUBJ"), buildPathValue("code", "codeSystemName", "LOINC"));
 		ValidationXML.checkSectionByTemplateId(errors, SVACErrorEnum.T017, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship", " > observation", "2.16.840.1.113883.2.9.10.1.11.4.5", false, buildPathValue("code", "code","95715-9"), buildPathValue("code", "codeSystem", "2.16.840.1.113883.6.1"), buildPathValue("code", "displayName", "Categorie a rischio"), buildPathValue(null, "typeCode","RSON"), buildPathValue("code", "codeSystemName", "LOINC"));
 		ValidationXML.checkSectionByTemplateId(errors, SVACErrorEnum.T018, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship", " > observation", "2.16.840.1.113883.2.9.10.1.11.4.6", false, buildPathValue("code", "code","59785-6"), buildPathValue("code", "codeSystem", "2.16.840.1.113883.6.1"), buildPathValue("code", "displayName", "Indicazioni per la vaccinazione"), buildPathValue(null, "typeCode","RSON"), buildPathValue("code", "codeSystemName", "LOINC"));
 		ValidationXML.checkSectionByTemplateId(errors, SVACErrorEnum.T019, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship", " > observation", "2.16.840.1.113883.2.9.10.1.11.4.8", false, buildPathValue("code", "code","31044-1"), buildPathValue("code", "codeSystem", "2.16.840.1.113883.6.1"), buildPathValue("code", "displayName", "Reazione alla Vaccinazione"), buildPathValue(null, "typeCode","CAUS"), buildPathValue("code", "codeSystemName", "LOINC"));
 		ValidationXML.checkSectionByTemplateId(errors, SVACErrorEnum.T020, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship", " > observation", "2.16.840.1.113883.2.9.10.1.11.4.9", false, buildPathValue("code", "code","75323-6"), buildPathValue("code", "codeSystem", "2.16.840.1.113883.6.1"), buildPathValue("code", "displayName", "Condizione"), buildPathValue(null, "typeCode","RSON"), buildPathValue("code", "codeSystemName", "LOINC"));
 		ValidationXML.equalIC(errors, SVACErrorEnum.T021, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship > observation", "moodCode", "EVN");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T022, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship > observation > code", "codeSystemName", "LOINC", "ImmunizationRecommendationDateCriterionCodes");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T023, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship > observation > statusCode", "code", "completed");

 		return errors;
 	}

 	public static List<ErrorUCTDTO> validateUC20(String xml) {
 		List<ErrorUCTDTO> errors = new ArrayList<>();
 		
 		ValidationXML.equalIC(errors, SVACErrorEnum.T024, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.3.1");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T025, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > code", "code", "11369-6");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T026, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > code", "codeSystem", "2.16.840.1.113883.6.1");
 		ValidationXML.equalTAG(errors, SVACErrorEnum.T027, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > title", "Scheda della singola vaccinazione");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T028, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration", "classCode", "SBADM");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T029, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration", "moodCode", "EVN");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T030, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.4.2");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T031, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > statusCode", "code", "cancelled");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T032, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > consumable", "typeCode", "CSM");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T033, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T034, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystemName", "AIC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T035, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystem", "2.16.840.1.113883.6.73");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T036, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystemName", "ATC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T037, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > participant", "typeCode", "LOC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T038, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > participant > participantRole", "classCode", "ROL");
 		ValidationXML.containsIC(errors, SVACErrorEnum.T039, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship", "typeCode","RSON");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T040, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship > observation", "classCode", "OBS");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T041, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship > observation", "moodCode", "EVN");
 		ValidationXML.checkSectionByTemplateId(errors, CVACErrorEnum.T042, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship", " > observation", "2.16.840.1.113883.2.9.10.1.11.4.10", false, buildPathValue("code", "code", "85714-4"), buildPathValue(null, "typeCode", "RSON"));
 		ValidationXML.checkSectionByTemplateId(errors, CVACErrorEnum.T043, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship", " > observation", "2.16.840.1.113883.2.9.10.1.11.4.7", false, buildPathValue("code", "code", "59784-9"), buildPathValue(null, "typeCode", "RSON"), buildPathValue("code", "displayName", "Malattia con immunità presunta"));
 		ValidationXML.equalIC(errors, SVACErrorEnum.T044, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T045, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > code", "codeSystemName", "LOINC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T046, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > code", "displayName", "Storia delle immunizzazioni");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T047, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship > observation > code", "codeSystemName", "LOINC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.T048, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Singola_Vaccinazione] > entry > substanceAdministration > entryRelationship > observation > statusCode", "code", "completed");

 		return errors;
 	}
 	
 	public static List<ErrorUCTDTO> validateUC1(String xml) {
 		List<ErrorUCTDTO> errors = new ArrayList<>();
 		init(xml, errors);

 		ValidationXML.equalIC(errors, SVACErrorEnum.STATUS, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > statusCode", "code", "completed");
 		ValidationXML.hasDateFormat(errors, SVACErrorEnum.TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > effectiveTime", "value");
 		ValidationXML.equalIC(errors, SVACErrorEnum.MATERIAL_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5");
 		ValidationXML.equalIC(errors, SVACErrorEnum.MATERIAL_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystemName", "AIC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.MATERIAL_TRANS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystem", "2.16.840.1.113883.6.73");
 		ValidationXML.equalIC(errors, SVACErrorEnum.MATERIAL_TRANS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystemName", "ATC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration", "classCode", "SBADM");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration", "moodCode", "EVN");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_ROOT, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.4.1");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > statusCode", "code", "completed");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CONS_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable", "typeCode", "CSM");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CONS_MAN_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CONS_MAN_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystemName", "AIC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CONS_MAN_TRANS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystem", "2.16.840.1.113883.6.73");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CONS_MAN_TRANS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystemName", "ATC");

 		return errors;
 	}

 	public static List<ErrorUCTDTO> validateUC2(String xml) {
 		List<ErrorUCTDTO> errors = new ArrayList<>();
 		init(xml, errors);
 		ValidationXML.equalIC(errors, SVACErrorEnum.STATUS, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > statusCode", "code", "completed");
 		ValidationXML.equalIC(errors, SVACErrorEnum.TEMPLATE_ID, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > templateID", "root", "2.16.840.1.113883.2.9.10.1.11.4.1");
 		ValidationXML.hasDateFormat(errors, SVACErrorEnum.TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > effectiveTime", "value");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > routeCode", "codeSystem", "2.16.840.1.113883.5.112");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_APPROACH_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > approachSiteCode", "codeSystem", "2.16.840.1.113883.5.1052");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_APPROACH_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > approachSiteCode", "codeSystemName", "ActSite");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CONS_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable", "typeCode", "CSM");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CONS_MAN_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CONS_MAN_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystemName", "AIC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CONS_MAN_TRANS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystem", "2.16.840.1.113883.6.73");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_CONS_MAN_TRANS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystemName", "ATC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.PART_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > participant", "typeCode", "LOC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.PART_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > participant > participantRole", "classCode", "ROL");
 		ValidationXML.equalIC(errors, SVACErrorEnum.ER_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship", "typeCode", "SUBJ", "REFR", "RSON", "CAUS");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation", "classCode", "OBS");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation", "moodCode", "EVN");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_TEMPLATE_ID, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.4.3", "2.16.840.1.113883.2.9.10.1.11.4.4", "2.16.840.1.113883.2.9.10.1.11.4.5", "2.16.840.1.113883.2.9.10.1.11.4.6", "2.16.840.1.113883.2.9.10.1.11.4.8", "2.16.840.1.113883.2.9.10.1.11.4.9");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > statusCode", "code", "completed");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUBSTANCE_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship", "typeCode", "SUBJ", "REFR", "RSON", "CAUS");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation", "classCode", "OBS");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation", "moodCode", "EVN");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_TEMPLATE_ID, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.4.3", "2.16.840.1.113883.2.9.10.1.11.4.4", "2.16.840.1.113883.2.9.10.1.11.4.5", "2.16.840.1.113883.2.9.10.1.11.4.6", "2.16.840.1.113883.2.9.10.1.11.4.8", "2.16.840.1.113883.2.9.10.1.11.4.9");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > statusCode", "code", "completed");
 		ValidationXML.equalIC(errors, SVACErrorEnum.ER_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship", "typeCode", "SUBJ", "REFR", "RSON", "CAUS");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation", "classCode", "OBS");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation", "moodCode", "EVN");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_TEMPLATE_ID, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.4.3", "2.16.840.1.113883.2.9.10.1.11.4.4", "2.16.840.1.113883.2.9.10.1.11.4.5", "2.16.840.1.113883.2.9.10.1.11.4.6", "2.16.840.1.113883.2.9.10.1.11.4.8", "2.16.840.1.113883.2.9.10.1.11.4.9");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > statusCode", "code", "completed");
 		return errors;

 	}

 	public static List<ErrorUCTDTO> validateUC3(String xml) {
 		List<ErrorUCTDTO> errors = new ArrayList<>();
 		init(xml, errors);
 		ValidationXML.equalIC(errors, SVACErrorEnum.TEMPLATE_ROOT, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.4.2");
 		ValidationXML.equalIC(errors, SVACErrorEnum.STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > statusCode", "code", "cancelled");
 		ValidationXML.equalIC(errors, SVACErrorEnum.TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable", "typeCode", "CSM");
 		ValidationXML.equalIC(errors, SVACErrorEnum.MAN_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5");
 		ValidationXML.equalIC(errors, SVACErrorEnum.MAN_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystemName", "AIC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.MAN_TRANS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystem", "2.16.840.1.113883.6.73");
 		ValidationXML.equalIC(errors, SVACErrorEnum.MAN_TRANS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystemName", "ATC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.PART_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > participant", "typeCode", "LOC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.PART_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > participant > participantRole", "classCode", "ROL");
 		ValidationXML.containsIC(errors, SVACErrorEnum.ER_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship", "typeCode", "SUBJ", "RSON");
 		ValidationXML.containsIC(errors, SVACErrorEnum.OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation", "classCode", "OBS");
 		ValidationXML.containsIC(errors, SVACErrorEnum.OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation", "moodCode", "EVN");
 		ValidationXML.containsIC(errors, SVACErrorEnum.OBS_TEMPLATE_ID, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.4.10", "2.16.840.1.113883.2.9.10.1.11.4.7");
 		ValidationXML.containsIC(errors, SVACErrorEnum.OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > code", "code", "85714-4", "59784-9");
 		ValidationXML.containsIC(errors, SVACErrorEnum.OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
 		ValidationXML.containsIC(errors, SVACErrorEnum.OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > code", "codeSystemName", "LOINC");
 		ValidationXML.containsIC(errors, SVACErrorEnum.OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > statusCode", "code", "completed");
 		return errors;
 	}

 	public static List<ErrorUCTDTO> validateUC4(String xml) {
 		List<ErrorUCTDTO> errors = new ArrayList<>();
 		init(xml, errors);
 		ValidationXML.equalIC(errors, SVACErrorEnum.STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > statusCode", "code", "completed");
 		ValidationXML.hasDateFormat(errors, SVACErrorEnum.TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > effectiveTime", "value");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > routeCode", "codeSystem", "2.16.840.1.113883.5.112");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_SITE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > approachSiteCode", "codeSystem", "2.16.840.1.113883.5.1052");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_SITE_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > approachSiteCode", "codeSystemName", "ActSite");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_CONS_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable", "typeCode", "CSM");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_CONS_MATERIAL_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_CONS_MATERIAL_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystemName","AIC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_CONS_MATERIAL_TRANS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystem", "2.16.840.1.113883.6.73");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_CONS_MATERIAL_TRANS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystemName", "ATC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_PART_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > participant", "typeCode", "LOC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_PART_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > participant > participantRole", "classCode", "ROL");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_ER_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship", "typeCode", "SUBJ", "REFR", "RSON", "CAUS");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUB_ER_INV_ID, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship", "inversionInd", "true", "false");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_TEMPLATE_ID, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.4.3", "2.16.840.1.113883.2.9.10.1.11.4.4", "2.16.840.1.113883.2.9.10.1.11.4.5", "2.16.840.1.113883.2.9.10.1.11.4.6", "2.16.840.1.113883.2.9.10.1.11.4.8", "2.16.840.1.113883.2.9.10.1.11.4.9");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > statusCode", "code", "completed");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation", "classCode", "OBS");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation", "moodCode", "EVN");
 		ValidationXML.equalIC(errors, SVACErrorEnum.OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship > observation > statusCode", "code", "completed");
 		ValidationXML.equalIC(errors, SVACErrorEnum.SUV_INV_ID, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > entryRelationship", "inversionInd", "true", "false");
 		return errors;
 	}

 	private static void init(String xml, List<ErrorUCTDTO> errors) {
 		ValidationXML.equalIC(errors, SVACErrorEnum.TYPE_CODE, xml, "ClinicalDocument > component", "typeCode", "COMP");
 		ValidationXML.equalIC(errors, SVACErrorEnum.CONTEXT_ID, xml, "ClinicalDocument > component", "contextConductionInd", "true");
 		ValidationXML.equalIC(errors, SVACErrorEnum.CLASS_CODE, xml, "ClinicalDocument > component > structuredBody", "classCode", "DOCBODY");
 		ValidationXML.equalIC(errors, SVACErrorEnum.MOOD_CODE, xml, "ClinicalDocument > component > structuredBody", "moodCode", "EVN");
 		ValidationXML.equalIC(errors, SVACErrorEnum.COMP_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component", "typeCode", "COMP");
 		ValidationXML.equalIC(errors, SVACErrorEnum.COMP_CONTEXT_ID, xml, "ClinicalDocument > component > structuredBody > component", "contextConductionInd", "true");
 		ValidationXML.equalIC(errors, SVACErrorEnum.COMP_TEMPLATE_ID, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.3.1");
 		ValidationXML.equalIC(errors, SVACErrorEnum.COMP_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > code", "code", "11369-6");
 		ValidationXML.equalIC(errors, SVACErrorEnum.COMP_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > code", "codeSystem", "2.16.840.1.113883.6.1");
 		ValidationXML.equalIC(errors, SVACErrorEnum.COMP_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > code", "codeSystemName", "LOINC");
 		ValidationXML.equalIC(errors, SVACErrorEnum.COMP_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > code", "displayName", "Storia delle immunizzazioni");
 		ValidationXML.containsIC(errors, SVACErrorEnum.COMP_TEMPLATE_ID, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration > templateId", "root", "2.16.840.1.113883.2.9.10.1.11.4.1", "2.16.840.1.113883.2.9.10.1.11.4.2");
 		ValidationXML.equalIC(errors, SVACErrorEnum.COMP_SUBSTANCE_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration", "classCode", "SBADM");
 		ValidationXML.equalIC(errors, SVACErrorEnum.COMP_SUBSTANCE_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Singola_Vaccinazione'] > entry > substanceAdministration", "moodCode", "EVN");
 	}

 }
