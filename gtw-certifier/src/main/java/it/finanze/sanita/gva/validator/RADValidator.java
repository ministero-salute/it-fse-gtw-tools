/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
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

	public static List<ErrorUCTDTO> validateUC1New(String xml) { 
		List<ErrorUCTDTO> errors = new ArrayList<>();
		ValidationXML.equalIC(errors, RADErrorEnum.T001, xml,"ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code","code", "121181");
		ValidationXML.equalIC(errors, RADErrorEnum.T002, xml,"ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code","codeSystem", "1.2.840.10008.2.16.4");
		ValidationXML.equalIC(errors, RADErrorEnum.T003, xml,"ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "code", "18785-6");
		ValidationXML.equalIC(errors, RADErrorEnum.T004, xml,"ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T005, xml,"ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.T006, xml,"ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > code", "code", "29308-4");
		ValidationXML.equalIC(errors, RADErrorEnum.T007, xml,"ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T009, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.equalIC(errors, RADErrorEnum.T010, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > code", "code", "11329-0");
		ValidationXML.equalIC(errors, RADErrorEnum.T011, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T014, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.T018, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > observation > value", "xsi:type", "CD");
		ValidationXML.equalIC(errors, RADErrorEnum.T022, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.T023, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer", "classCode", "CLUSTER");
		ValidationXML.equalIC(errors, RADErrorEnum.T024, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > code", "code", "10157-6");
		ValidationXML.equalIC(errors, RADErrorEnum.T025, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T026, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > statusCode", "code", "completed");
		ValidationXML.equalIC(errors, RADErrorEnum.T027, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > subject > relatedSubject", "classcode", "PRS");
		ValidationXML.equalIC(errors, RADErrorEnum.T036, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > code", "code", "48765-2");
		ValidationXML.equalIC(errors, RADErrorEnum.T037, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T039, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > statusCode", "code", "active","suspended","completed","aborted");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.T040, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > effectiveTime > low", "value");
		ValidationXML.equalIC(errors, RADErrorEnum.T041, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > code", "code", "52473-6");
		ValidationXML.equalIC(errors, RADErrorEnum.T042, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T044, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > statusCode", "code", "completed");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.T045, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > effectiveTime > low", "value");
		ValidationXML.equalIC(errors, RADErrorEnum.T047, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > value", "xsi:type", "CD");
		ValidationXML.equalIC(errors, RADErrorEnum.T051, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship", "typeCode", "MFST","SUBJ","REFR");
		ValidationXML.equalIC(errors, RADErrorEnum.T052, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "code", "75321-0","5321-0","33999-4");
		ValidationXML.equalIC(errors, RADErrorEnum.T053, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1","2.16.840.1.113883.5.4","2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T054, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > observation > statusCode", "code", "completed");
		ValidationXML.equalIC(errors, RADErrorEnum.T060, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > code", "code", "55114-3");
		ValidationXML.equalIC(errors, RADErrorEnum.T061, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T062, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > entry > observation", "moodCode", "EVN");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.T063, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > entry > observation > effectiveTime > low", "value");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.T064, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > entry > observation > effectiveTime > high", "value");
		ValidationXML.equalIC(errors, RADErrorEnum.T065, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > code", "code", "55111-9");
		ValidationXML.equalIC(errors, RADErrorEnum.T066, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T067, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > entry > act", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.T068, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > entry > act", "classCode", "ACT");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.T069, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > entry > act > effectiveTime", "value");
		ValidationXML.equalIC(errors, RADErrorEnum.T070, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Referto] > code", "code", "18782-3");
		ValidationXML.equalIC(errors, RADErrorEnum.T071, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Referto] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T073, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Informazioni_aggiuntive] > code", "code", "55107-7");
		ValidationXML.equalIC(errors, RADErrorEnum.T074, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Informazioni_aggiuntive] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T075, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Conclusioni] > code", "code", "55110-1");
		ValidationXML.equalIC(errors, RADErrorEnum.T076, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Conclusioni] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T078, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "code", "55109-3");
		ValidationXML.equalIC(errors, RADErrorEnum.T079, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T080, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.T081, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation", "classCode", "OBS");
		ValidationXML.equalIC(errors, RADErrorEnum.T082, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > code", "code", "75326-9");
		ValidationXML.equalIC(errors, RADErrorEnum.T083, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
//		ValidationXML.hasDateFormat(errors, RADErrorEnum.T084, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > effectiveTime", "value");
		ValidationXML.equalIC(errors, RADErrorEnum.T084, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > participant > participantRole > playingEntity > code", "codeSystem","2.16.840.1.113883.6.73","2.16.840.1.113883.2.9.6.1.5","2.16.840.1.113883.2.9.77.22.11.2");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.T085, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > effectiveTime > low", "value");
		ValidationXML.hasDateFormat(errors, RADErrorEnum.T086, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > effectiveTime > high", "value");
		ValidationXML.equalIC(errors, RADErrorEnum.T089, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "code", "18783-1");
		ValidationXML.equalIC(errors, RADErrorEnum.T090, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "codeSystem", "2.16.840.1.113883.6.1");
		return errors;
	}

	public static List<ErrorUCTDTO> validateUC2New(String xml) {
	    List<ErrorUCTDTO> errors = new ArrayList<>();
	    ValidationXML.equalIC(errors, RADErrorEnum.T091, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "code","121181");
	    ValidationXML.equalIC(errors, RADErrorEnum.T092, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "codeSystem","1.2.840.10008.2.16.4");
	    ValidationXML.equalIC(errors, RADErrorEnum.T093, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "codeSystemName","DCM");
	    ValidationXML.equalIC(errors, RADErrorEnum.T094, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "displayName","DICOM Object Catalog");
	    ValidationXML.equalIC(errors, RADErrorEnum.T095, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "code","18785-6");
	    ValidationXML.equalIC(errors, RADErrorEnum.T096, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T097, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T098, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "displayName","Motivo per lo studio - Radiologia");
	    ValidationXML.equalIC(errors, RADErrorEnum.T099, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation", "moodCode","EVN");
	    ValidationXML.equalIC(errors, RADErrorEnum.T100, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > code", "code","29308-4");
	    ValidationXML.equalIC(errors, RADErrorEnum.T101, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T102, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T103, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > code", "displayName","Diagnosi");
	    ValidationXML.equalIC(errors, RADErrorEnum.T105, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > value", "codeSystem","2.16.840.1.113883.6.103");
	    ValidationXML.equalIC(errors, RADErrorEnum.T106, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > value", "codeSystemName","ICD9CM");
	    ValidationXML.equalIC(errors, RADErrorEnum.T107, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > code", "code","11329-0");
	    ValidationXML.equalIC(errors, RADErrorEnum.T108, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T109, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T110, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > code", "displayName","Storia Generale");
	    ValidationXML.equalIC(errors, RADErrorEnum.T113, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > observation", "moodCode","EVN");
	    ValidationXML.hasDateFormat(errors, RADErrorEnum.T116, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > observation > effectiveTime > low", "value");
	    ValidationXML.equalIC(errors, RADErrorEnum.T118, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > observation > entryRelationship > observation > code", "code","89261-2","33999-4");
	    ValidationXML.equalIC(errors, RADErrorEnum.T119, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > observation > entryRelationship > observation > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T120, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > observation > entryRelationship > observation > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T121, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > observation > entryRelationship > observation > code", "displayName","Decorso Clinico","Stato");
	    ValidationXML.equalIC(errors, RADErrorEnum.T123, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer", "moodCode","EVN");
	    ValidationXML.equalIC(errors, RADErrorEnum.T124, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer", "classCode","CLUSTER");
	    ValidationXML.equalIC(errors, RADErrorEnum.T125, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > code", "code","10157-6");
	    ValidationXML.equalIC(errors, RADErrorEnum.T126, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T127, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > statusCode", "code","completed");
	    ValidationXML.equalIC(errors, RADErrorEnum.T128, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > subject > relatedSubject", "classcode","PRS");
	    ValidationXML.hasDateFormat(errors, RADErrorEnum.T133, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > component > observation > effectiveTime", "value");
	    ValidationXML.equalIC(errors, RADErrorEnum.T135, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > component > observation > entryRelationship", "typeCode","SUBJ","STATIC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T136, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > organizer > component > observation > entryRelationship > observation", "moodCode","EVN"); 
	    ValidationXML.equalIC(errors, RADErrorEnum.T138, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > code", "code","48765-2");
	    ValidationXML.equalIC(errors, RADErrorEnum.T139, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T140, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T141, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > code", "displayName","Allergie e/o reazioni avverse");
	    ValidationXML.equalIC(errors, RADErrorEnum.T145, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > code", "code","52473-6");
	    ValidationXML.equalIC(errors, RADErrorEnum.T146, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T147, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T148, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > code", "displayName","Allergia o causa della reazione");
	    ValidationXML.equalIC(errors, RADErrorEnum.T150, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > statusCode", "code","completed");
	    ValidationXML.hasDateFormat(errors, RADErrorEnum.T151, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > effectiveTime > low", "value");
	    ValidationXML.equalIC(errors, RADErrorEnum.T152, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > value", "xsi:type","CD");
	    
//	    ValidationXML.equalIC(errors, RADErrorEnum.T154, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > participant > participantRole > playingEntity > code", "code","2.16.840.1.113883.6.73","2.16.840.1.113883.2.9.6.1.5");
	    ValidationXML.equalIC(errors, RADErrorEnum.T154, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > participant > participantRole > playingEntity > code", "codeSystem","2.16.840.1.113883.6.73","2.16.840.1.113883.2.9.6.1.5","2.16.840.1.113883.2.9.77.22.11.2");
	    
	    ValidationXML.equalIC(errors, RADErrorEnum.T156, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship", "typeCode","MFST","SUBJ","REFR");
	    ValidationXML.equalIC(errors, RADErrorEnum.T157, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "code","75321-0","5321-0","33999-4");
	    ValidationXML.equalIC(errors, RADErrorEnum.T158, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "codeSystem","2.16.840.1.113883.6.1","2.16.840.1.113883.5.4","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T160, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "displayName","Obiettività Clinica", "Clinical Finding","Criticality","Stato");
	    ValidationXML.equalIC(errors, RADErrorEnum.T161, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > observation > statusCode", "code","completed");
//	    ValidationXML.hasDateFormat(errors, RADErrorEnum.T162, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > observation > effectiveTime", "value");
	    ValidationXML.equalIC(errors, RADErrorEnum.T163, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > observation > value", "codeSystem","2.16.840.1.113883.6.103","2.16.840.1.113883.5.1063","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T165, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > act > code", "code","48767-8");
	    ValidationXML.equalIC(errors, RADErrorEnum.T166, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > act > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T167, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > act > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T168, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > act > code", "displayName","Annotation comment");
	    ValidationXML.equalIC(errors, RADErrorEnum.T169, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > component > section[ID=Allergie] > entry > act > entryRelationship > observation > entryRelationship > act > statusCode", "code","completed");
	    ValidationXML.equalIC(errors, RADErrorEnum.T170, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > code", "code","55114-3");
	    ValidationXML.equalIC(errors, RADErrorEnum.T171, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T172, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T173, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > code", "displayName","Precedente procedura di imaging, descrizioni");
	    ValidationXML.equalIC(errors, RADErrorEnum.T174, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > entry > observation", "moodCode","EVN");
	    ValidationXML.hasDateFormat(errors, RADErrorEnum.T175, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > entry > observation > effectiveTime > low", "value");
	    ValidationXML.hasDateFormat(errors, RADErrorEnum.T176, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Precedenti_esami_eseguiti] > entry > observation > effectiveTime > high","value");
	    ValidationXML.equalIC(errors, RADErrorEnum.T177, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > code", "code","55111-9");
	    ValidationXML.equalIC(errors, RADErrorEnum.T178, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T179, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T180, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > entry > act", "moodCode","EVN");
	    ValidationXML.equalIC(errors, RADErrorEnum.T181, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > entry > act", "classCode","ACT");
	    ValidationXML.hasDateFormat(errors, RADErrorEnum.T182, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esame_eseguito] > entry > act > effectiveTime","value");
	    ValidationXML.equalIC(errors, RADErrorEnum.T183, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Referto] > code", "code","18782-3");
	    ValidationXML.equalIC(errors, RADErrorEnum.T184, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Referto] > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T185, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Referto] > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T186, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Referto] > code", "displayName","Rilievo - Radiologia");
	    ValidationXML.equalIC(errors, RADErrorEnum.T188, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Informazioni_aggiuntive] > code", "code","55107-7");
	    ValidationXML.equalIC(errors, RADErrorEnum.T189, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Informazioni_aggiuntive] > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T190, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Informazioni_aggiuntive] > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T191, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Informazioni_aggiuntive] > code", "displayName","Appendice");
	    ValidationXML.equalIC(errors, RADErrorEnum.T192, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Conclusioni] > code", "code","55110-1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T193, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Conclusioni] > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T194, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Conclusioni] > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T195, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Conclusioni] > code", "displayName","Conclusioni");
	    ValidationXML.equalIC(errors, RADErrorEnum.T197, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "code","55109-3");
	    ValidationXML.equalIC(errors, RADErrorEnum.T198, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T199, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T200, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "displayName","Complicazioni");
	    ValidationXML.equalIC(errors, RADErrorEnum.T201, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation", "moodCode","EVN");
	    ValidationXML.equalIC(errors, RADErrorEnum.T202, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation", "classCode","OBS");
	    ValidationXML.equalIC(errors, RADErrorEnum.T203, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > code", "code","75326-9");
	    ValidationXML.equalIC(errors, RADErrorEnum.T204, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T211, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "code","18783-1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T212, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "codeSystem","2.16.840.1.113883.6.1");
	    ValidationXML.equalIC(errors, RADErrorEnum.T213, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "codeSystemName","LOINC");
	    ValidationXML.equalIC(errors, RADErrorEnum.T214, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "displayName","Raccomandazione studio - Radiologia");
	    return errors;
	}


}