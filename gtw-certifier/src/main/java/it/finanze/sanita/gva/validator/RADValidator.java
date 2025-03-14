/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.validator;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import it.finanze.sanita.gva.enums.RADErrorEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RADValidator extends DocumentTypeValidator {
	
	 
	public static List<ErrorUCTDTO> validateUC1New(String xml){
		List<ErrorUCTDTO> errors = new ArrayList<>();

		ValidationXML.equalIC(errors, RADErrorEnum.T001, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "code", "121181");
		ValidationXML.equalIC(errors, RADErrorEnum.T002, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "codeSystem", "1.2.840.10008.2.16.4");
		ValidationXML.equalIC(errors, RADErrorEnum.T003, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "codeSystemName", "DCM");
		ValidationXML.equalIC(errors, RADErrorEnum.T004, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "displayName", "DICOM Object Catalog");
		ValidationXML.equalIC(errors, RADErrorEnum.T005, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "code", "18785-6");
		ValidationXML.equalIC(errors, RADErrorEnum.T006, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T007, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.T008, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "displayName", "Motivo per lo studio - Radiologia");
		ValidationXML.equalIC(errors, RADErrorEnum.T009, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.T012, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "code", "18783-1");
		ValidationXML.equalIC(errors, RADErrorEnum.T013, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T014, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.T015, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "displayName", "Raccomandazione studio - Radiologia");

		return errors;
	}

	public static List<ErrorUCTDTO> validateUC2New(String xml){
		List<ErrorUCTDTO> errors = new ArrayList<>();

		ValidationXML.equalIC(errors, RADErrorEnum.T016, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "code", "121181");
		ValidationXML.equalIC(errors, RADErrorEnum.T017, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "codeSystem", "1.2.840.10008.2.16.4");
		ValidationXML.equalIC(errors, RADErrorEnum.T018, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "codeSystemName", "DCM");
		ValidationXML.equalIC(errors, RADErrorEnum.T019, xml, "ClinicalDocument > component > structuredBody > component > section[ID=DICOM_Object_Catalog] > code", "displayName", "DICOM Object Catalog");
		ValidationXML.equalIC(errors, RADErrorEnum.T020, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "code", "18785-6");
		ValidationXML.equalIC(errors, RADErrorEnum.T021, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T022, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.T023, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > code", "displayName", "Motivo per lo studio - Radiologia");
		ValidationXML.equalIC(errors, RADErrorEnum.T024, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.T025, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > code", "code", "29308-4");
		ValidationXML.equalIC(errors, RADErrorEnum.T026, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T027, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.T028, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > code", "displayName", "Diagnosi");
		ValidationXML.equalIC(errors, RADErrorEnum.T029, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.equalIC(errors, RADErrorEnum.T030, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Quesito_diagnostico] > entry > observation > value", "codeSystemName", "ICD9CM");
		ValidationXML.equalIC(errors, RADErrorEnum.T031, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > code", "code", "11329-0");
		ValidationXML.equalIC(errors, RADErrorEnum.T032, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T033, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.T034, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > code", "displayName", "Storia Generale");
		ValidationXML.equalIC(errors, RADErrorEnum.T035, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Storia_Clinica] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, RADErrorEnum.T036, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > code", "code", "75326-9");
		ValidationXML.equalIC(errors, RADErrorEnum.T037, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T038, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "code", "18783-1");
		ValidationXML.equalIC(errors, RADErrorEnum.T039, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, RADErrorEnum.T040, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, RADErrorEnum.T041, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Suggerimenti_per_il_medico_prescrittore] > code", "displayName", "Raccomandazione studio - Radiologia");

		return errors;
	}
	 
}