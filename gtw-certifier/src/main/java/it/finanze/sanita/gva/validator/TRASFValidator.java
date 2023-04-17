/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.validator;
import java.util.ArrayList;
import java.util.List;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import it.finanze.sanita.gva.enums.TRASFErrorEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TRASFValidator extends DocumentTypeValidator {
	
	public static List<ErrorUCTDTO> validateUC1(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();

		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > code", "code", "18717-9");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystem", "2.16.840.1.113883.6.1" );
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > code", "displayName", "Banca del Sangue");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > code", "code", "56836-0");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > code", "codeSystem", "2.16.840.1.113883.6.1" );
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > code", "displayName", "Blood transfusion Narrative");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry", "typeCode", "DRIV");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_MOODE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act", "moodCode", "EVN");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act", "classCode", "ACT");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > statusCode", "code","completed", "active", "aborted");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_SPECIMENROLE_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > specimen", "typeCode", "SPC");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_SPECIMENROLE_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > specimen > specimenRole", "classCode", "SPEC");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_SPECIMENROLE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > specimen > specimenRole > specimenPlayingEntity > code", "code", "BLD");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_SPECIMENROLE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > specimen > specimenRole > specimenPlayingEntity > code", "codeSystem", "2.16.840.1.113883.5.129");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_SPECIMENROLE_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > specimen > specimenRole > specimenPlayingEntity > code", "codeSystemName","SpecimenType");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_SPECIMENROLE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > specimen > specimenRole > specimenPlayingEntity > code", "displayName", "Whole Blood");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer", "classCode","BATTERY");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > statusCode", "code", "completed", "aborted");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component", "typeCode", "COMP");
		ValidationXML.containsIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component > observation > code", "displayName","Erythrocyte agglutination presence in blood","ABO group type in blood from donor","Direct antiglobulin.polyspecific reagent presence on red blood cells", "Indirect antiglobulin test.polyspecific reagent presence in serum or plasma","Cryoglobulin.IgA [Mass > volume] in Serum by 37 degree C incubation","Cryoglobulin.IgG [Mass > volume] in Serum by 37 degree C incubation","Cryoglobulin.IgM [Mass > volume] in Serum by 37 degree C incubation", "Ag inferred phenotype presence in blood or tissue from donor by molecular genetics method");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component > observation > statusCode", "code", "completed", "aborted");
		ValidationXML.containsIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component > observation > code", "code","1007-4","50670-9","14579-7","1008-2", "92551-1", "40628-0", "40582-9", "40581-1");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_COMP_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component", "typeCode", "COMP");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_COMP_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component > observation > statusCode", "code","completed", "aborted");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_COMP_OBS_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component > observation > statusCode", "code","completed", "aborted");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component > observation > code", "codeSystem", "2.16.840.1.113883.6.1" );
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ORG_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > organizer > component > observation > code", "codeSystemName", "LOINC");
		ValidationXML.containsIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ER_TYPEC_ODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship", "typeCode", "SUBJ","COMP");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ER_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act", "classCode", "ACT");
		ValidationXML.equalIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ER_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act", "moodCode", "EVN");
		ValidationXML.containsIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ER_CODE, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "code", "48767-8");
		ValidationXML.containsIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ER_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "codeSystem", "2.16.840.1.113883.6.1" );
		ValidationXML.containsIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ER_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "codeSystemName", "LOINC");
		ValidationXML.containsIC(errors, TRASFErrorEnum.SECTION_COMPONENT_ACT_ER_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section > component > section > entry > act > entryRelationship > act > code", "displayName", "Annotazioni e commenti");
		return errors;
	}

}
