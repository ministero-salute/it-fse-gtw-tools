 /*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.validator;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import it.finanze.sanita.gva.enums.LDOErrorEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

 /*
 * @author CPIERASC
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LDOValidator extends DocumentTypeValidator {
	
	
	public static List<ErrorUCTDTO> validateUC17(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();

		ValidationXML.equalIC(errors, LDOErrorEnum.T000, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > code", "code", "46241-6");
		ValidationXML.equalIC(errors, LDOErrorEnum.T001, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T002, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T003, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > code", "displayName", "Diagnosi di Accettazione");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T004, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > title", "Motivo del Ricovero");
		ValidationXML.equalIC(errors, LDOErrorEnum.T005, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.T006, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > entry > observation > code", "code", "8646-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.T007, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T008, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > entry > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T009, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > entry > observation > code", "displayName", "Diagnosi di Accettazione Ospedaliera");
		ValidationXML.equalIC(errors, LDOErrorEnum.T010, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > entry > observation > value", "xsi:type", "CD");
		ValidationXML.equalIC(errors, LDOErrorEnum.T011, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > entry > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.equalIC(errors, LDOErrorEnum.T012, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Motivo_Ricovero] > entry > observation > value", "codeSystemName", "ICD9CM");
		ValidationXML.equalIC(errors, LDOErrorEnum.T013, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > code", "code", "47039-3");
		ValidationXML.equalIC(errors, LDOErrorEnum.T014, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T015, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T016, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > code", "displayName", "Ricovero Ospedaliero, anamnesi ed esame obiettivo");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T017, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > title", "Inquadramento Clinico Iniziale");
		ValidationXML.equalIC(errors, LDOErrorEnum.T018, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > code", "code", "11329-0");
		ValidationXML.equalIC(errors, LDOErrorEnum.T019, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T020, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T021, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > code", "displayName", "Anamnesi Generale");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T022, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > title", "Anamnesi");
		ValidationXML.equalIC(errors, LDOErrorEnum.T023, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.T024, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > entry > observation > code", "code", "75326-9");
		ValidationXML.equalIC(errors, LDOErrorEnum.T025, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T026, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > entry > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T027, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > entry > observation > code", "displayName", "Problem");
		ValidationXML.equalIC(errors, LDOErrorEnum.T028, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > entry > observation > statusCode", "code", "Completed");
		ValidationXML.equalIC(errors, LDOErrorEnum.T029, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Esame_Obiettivo] > code", "code", "29545-1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T030, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Esame_Obiettivo] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T031, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Esame_Obiettivo] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T032, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Esame_Obiettivo] > code", "displayName", "Esame Obiettivo");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T033, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Esame_Obiettivo] > title", "Esame Obiettivo");
		ValidationXML.equalIC(errors, LDOErrorEnum.T034, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Terapia_Farmacologica_Ingresso] > code", "code", "42346-7");
		ValidationXML.equalIC(errors, LDOErrorEnum.T035, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Terapia_Farmacologica_Ingresso] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T036, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Terapia_Farmacologica_Ingresso] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T037, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Terapia_Farmacologica_Ingresso] > code", "displayName", "Terapia Farmacologica all'ingresso");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T038, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Terapia_Farmacologica_Ingresso] > title", "Terapia Farmacologica all'ingresso");
		ValidationXML.equalIC(errors, LDOErrorEnum.T039, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Decorso_ospedaliero] > code", "code", "8648-8");
		ValidationXML.equalIC(errors, LDOErrorEnum.T040, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Decorso_ospedaliero] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T041, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Decorso_ospedaliero] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T042, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Decorso_ospedaliero] > code", "displayName", "Decorso_ospedaliero");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T043, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Decorso_ospedaliero] > title", "Decorso ospedaliero");
		ValidationXML.equalIC(errors, LDOErrorEnum.T044, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "code", "55109-3");
		ValidationXML.equalIC(errors, LDOErrorEnum.T045, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T046, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T047, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > code", "displayName", "Complicazioni");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T048, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > title", "Complicanze");
		ValidationXML.equalIC(errors, LDOErrorEnum.T049, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.T050, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > code", "code", "75326-9");
		ValidationXML.equalIC(errors, LDOErrorEnum.T051, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Complicanze] > entry > observation > code", "displayName", "Problem");
		ValidationXML.equalIC(errors, LDOErrorEnum.T050, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Riscontri_accertamenti_significativi] > code", "code", "11493-4");
		ValidationXML.equalIC(errors, LDOErrorEnum.T053, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Riscontri_accertamenti_significativi] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T054, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Riscontri_accertamenti_significativi] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T055, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Riscontri_accertamenti_significativi] > code", "displayName", "Hospital discharge studies summary");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T056, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Riscontri_accertamenti_significativi] > title", "Riscontri e accertamenti significativi");
		ValidationXML.equalIC(errors, LDOErrorEnum.T057, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esami_eseguiti_durante_il_ricovero] > code", "code", "30954-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.T058, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esami_eseguiti_durante_il_ricovero] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T059, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esami_eseguiti_durante_il_ricovero] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T060, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esami_eseguiti_durante_il_ricovero] > code", "displayName", "Esami diagnostici e/o di laboratorio significativi");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T061, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esami_eseguiti_durante_il_ricovero] > title", "Esami eseguiti durante il ricovero");
		ValidationXML.equalIC(errors, LDOErrorEnum.T062, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Esami_eseguiti_durante_il_ricovero] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.T063, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Procedure_eseguite_durante_ricovero] > code", "code", "47519-4");
		ValidationXML.equalIC(errors, LDOErrorEnum.T064, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Procedure_eseguite_durante_ricovero] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T065, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Procedure_eseguite_durante_ricovero] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T066, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Procedure_eseguite_durante_ricovero] > code", "displayName", "History of Procedures Document");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T067, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Procedure_eseguite_durante_ricovero] > title", "Procedure eseguite durante il ricovero");
		ValidationXML.equalIC(errors, LDOErrorEnum.T068, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Procedure_eseguite_durante_ricovero] > entry > procedure > entryRelationship", "typeCode", "RSON");
		ValidationXML.equalIC(errors, LDOErrorEnum.T069, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Procedure_eseguite_durante_ricovero] > entry > procedure > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.equalIC(errors, LDOErrorEnum.T070, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Allergie] > code", "code", "48765-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.T071, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Allergie] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T072, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Allergie] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T073, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Allergie] > code", "displayName", "ALLERGIE E/O REAZIONI AVVERSE");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T074, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Allergie] > title","ALLERGIE E/O REAZIONI AVVERSE");
		ValidationXML.equalIC(errors, LDOErrorEnum.T075, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Allergie] > entry > act > statusCode", "code", "completed", "aborted", "suspended", "active");
		ValidationXML.equalIC(errors, LDOErrorEnum.T076, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Allergie] > entry > act > entryRelationship > observation > code", "code", "52473-6");
		ValidationXML.equalIC(errors, LDOErrorEnum.T077, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Allergie] > entry > act > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T078, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Allergie] > entry > act > entryRelationship > observation > code", "displayName", "Allergia o causa della reazione");
		ValidationXML.equalIC(errors, LDOErrorEnum.T079, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Allergie] > entry > act > entryRelationship > observation > statusCode", "code", "completed");
		ValidationXML.equalIC(errors, LDOErrorEnum.T090, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_effettuata_durante_ricovero] > code", "code", "10160-0");
		ValidationXML.equalIC(errors, LDOErrorEnum.T091, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_effettuata_durante_ricovero] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T092, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_effettuata_durante_ricovero] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T093, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_effettuata_durante_ricovero] > code", "displayName", "Terapie Farmacologiche");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T094, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_effettuata_durante_ricovero] > title", "Terapia farmacologica effettuata durante il ricovero");
		ValidationXML.equalIC(errors, LDOErrorEnum.T095, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_effettuata_durante_ricovero] > entry > substanceAdministration", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.T096, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_effettuata_durante_ricovero] > entry > substanceAdministration", "classCode", "SBADM");
		ValidationXML.equalIC(errors, LDOErrorEnum.T097, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_effettuata_durante_ricovero] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5");
		ValidationXML.equalIC(errors, LDOErrorEnum.T098, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_effettuata_durante_ricovero] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystemName", "AIC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T099, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > code", "code", "11535-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.T100, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T101, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T102, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > code", "displayName", "Diagnosi di Dimissione");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T103, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > title", "Condizioni del paziente e diagnosi alla dimissione");
		ValidationXML.equalIC(errors, LDOErrorEnum.T104, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.T105, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > code", "code", "8651-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.T106, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T107, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T108, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > code", "displayName", "Diagnosi di Dimissione Ospedaliera");
		ValidationXML.equalIC(errors, LDOErrorEnum.T109, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.equalIC(errors, LDOErrorEnum.T110, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > value", "codeSystemName", "ICD9CM");
		ValidationXML.equalIC(errors, LDOErrorEnum.T111, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_dimissione] > code", "code", "10183-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.T112, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_dimissione] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T113, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_dimissione] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T114, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_dimissione] > code", "displayName", "Terapia farmacologica alla dimissione");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T115, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_dimissione] > title", "Terapia farmacologica alla dimissione");
		ValidationXML.equalIC(errors, LDOErrorEnum.T116, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_dimissione] > entry > substanceAdministration", "moodCode", "INT");
		ValidationXML.equalIC(errors, LDOErrorEnum.T117, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_dimissione] > entry > substanceAdministration", "classCode", "SBADM");
		ValidationXML.equalIC(errors, LDOErrorEnum.T118, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_dimissione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5", "2.16.840.1.113883.6.73", "2.16.840.1.113883.2.9.6.1.51");
		ValidationXML.equalIC(errors, LDOErrorEnum.T119, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Terapia_farmacologica_dimissione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystem", "2.16.840.1.113883.6.73");
		ValidationXML.equalIC(errors, LDOErrorEnum.T120, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Istruzioni_followup] > code", "code", "18776-5");
		ValidationXML.equalIC(errors, LDOErrorEnum.T121, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Istruzioni_followup] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T122, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Istruzioni_followup] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T123, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Istruzioni_followup] > code", "displayName", "Piano di Cura");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T124, xml, "ClinicalDocument > component > structuredBody > component > section[ID=Istruzioni_followup] > title", "Istruzioni di follow-up");

		return errors;
	}

	public static List<ErrorUCTDTO> validateUC18(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		ValidationXML.equalIC(errors, LDOErrorEnum.T125, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > code", "code", "46241-6");
		ValidationXML.equalIC(errors, LDOErrorEnum.T126, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T127, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T128, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > code", "displayName", "Diagnosi di Accettazione");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T129, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > title", "Motivo del Ricovero");
		ValidationXML.equalIC(errors, LDOErrorEnum.T130, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.T131, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > entry > observation > code", "code", "8646-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.T132, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T133, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > entry > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T134, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > entry > observation > code", "displayName", "Diagnosi di Accettazione Ospedaliera");
		ValidationXML.equalIC(errors, LDOErrorEnum.T135, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > entry > observation > value", "xsi:type", "CD");
		ValidationXML.equalIC(errors, LDOErrorEnum.T136, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > entry > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.equalIC(errors, LDOErrorEnum.T137, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Motivo_Ricovero] > entry > observation > value", "codeSystemName", "ICD9CM");
		ValidationXML.equalIC(errors, LDOErrorEnum.T138, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Inquadramento_clinico_iniziale] > code", "code", "47039-3");
		ValidationXML.equalIC(errors, LDOErrorEnum.T139, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Inquadramento_clinico_iniziale] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T140, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Inquadramento_clinico_iniziale] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T141, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Inquadramento_clinico_iniziale] > code", "displayName", "Ricovero Ospedaliero, anamnesi ed esame obiettivo");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T142, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Inquadramento_clinico_iniziale] > title", "Inquadramento Clinico Iniziale");
		ValidationXML.equalIC(errors, LDOErrorEnum.T143, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > code", "code", "11329-0");
		ValidationXML.equalIC(errors, LDOErrorEnum.T144, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T145, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T146, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > code", "displayName", "Anamnesi Generale");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T147, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Inquadramento_clinico_iniziale] > component > section[ID=Anamnesi] > title", "Anamnesi");
		ValidationXML.equalIC(errors, LDOErrorEnum.T148, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > code", "code", "8651-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.T149, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T150, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T151, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > code", "displayName", "Diagnosi di Dimissione Ospedaliera");
		ValidationXML.equalIC(errors, LDOErrorEnum.T152, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.equalIC(errors, LDOErrorEnum.T153, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Condizioni_paziente_diagnosi_dimissione] > entry > observation > value", "codeSystemName", "ICD9CM");
		ValidationXML.equalIC(errors, LDOErrorEnum.T154, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Terapia_farmacologica_dimissione] > code", "code", "10183-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.T155, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Terapia_farmacologica_dimissione] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T156, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Terapia_farmacologica_dimissione] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T157, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Terapia_farmacologica_dimissione] > code", "displayName", "Terapia farmacologica alla dimissione");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T158, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Terapia_farmacologica_dimissione] > title", "Terapia farmacologica alla dimissione");
		ValidationXML.equalIC(errors, LDOErrorEnum.T159, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Terapia_farmacologica_dimissione] > entry > substanceAdministration", "moodCode", "INT");
		ValidationXML.equalIC(errors, LDOErrorEnum.T160, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Terapia_farmacologica_dimissione] > entry > substanceAdministration", "classCode", "SBADM");
		ValidationXML.equalIC(errors, LDOErrorEnum.T161, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Terapia_farmacologica_dimissione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5", "2.16.840.1.113883.6.73", "2.16.840.1.113883.2.9.6.1.51");
		ValidationXML.equalIC(errors, LDOErrorEnum.T162, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Terapia_farmacologica_dimissione] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystem", "2.16.840.1.113883.6.73");
		ValidationXML.equalIC(errors, LDOErrorEnum.T163, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Istruzioni_followup] > code", "code", "18776-5");
		ValidationXML.equalIC(errors, LDOErrorEnum.T164, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Istruzioni_followup] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.T165, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Istruzioni_followup] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.T166, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Istruzioni_followup] > code", "displayName", "Piano di Cura");
		ValidationXML.equalTAG(errors, LDOErrorEnum.T167, xml, "ClinicalDocument > component > structuredBody > component  > section[ID=Istruzioni_followup] > title", "Istruzioni di follow-up");

		return errors;
	}
	
	public static List<ErrorUCTDTO> validateUC1(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		motivo(errors, xml);
		decorso(errors, xml);
		condizioni(errors, xml);
		return errors;
	}

	public static List<ErrorUCTDTO> validateUC2(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		motivo(errors, xml);
		inquadramento(errors, xml);
		decorso(errors, xml);
		riscontri(errors, xml);
		condizioni(errors, xml);
		terapia(errors, xml);
		followup(errors, xml);
		return errors;
	}


	public static List<ErrorUCTDTO> validateUC3(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		motivo(errors, xml);
		inquadramento(errors, xml);
		decorso(errors, xml);
		complicanze(errors, xml);
		riscontri(errors, xml);
		esamiRicoveroV2(errors, xml);
		terapiaRicovero(errors, xml);
		condizioni(errors, xml);
		terapia(errors, xml);
		followup(errors, xml);
		return errors;
	}

	public static List<ErrorUCTDTO> validateUC4(String xml) {
		List<ErrorUCTDTO> errors = new ArrayList<>();
		motivo(errors, xml);
		inquadramento(errors, xml);
		decorso(errors, xml);
		complicanze(errors, xml);
		riscontri(errors, xml);
		consulenza(errors, xml);
		esamiRicoveroV1(errors, xml);
		allergie(errors, xml);
		storiaClinica(errors, xml);
		terapiaRicovero(errors, xml);
		condizioni(errors, xml);
		terapia(errors, xml);
		followup(errors, xml);
		return errors;
	}

	private static void terapiaRicovero(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > code", "code", "10160-0");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > code", "displayName", "Terapie Farmacologiche");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > entry > substanceAdministration", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > entry > substanceAdministration", "classCode", "SBADM");
		ValidationXML.hasDateFormat(errors, LDOErrorEnum.TERAPIA_TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > entry > substanceAdministration > effectiveTime > low", "value");
		ValidationXML.hasDateFormat(errors, LDOErrorEnum.TERAPIA_TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > entry > substanceAdministration > effectiveTime > high", "value");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_CONS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_CONS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystemName", "AIC");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_CONS_TRANS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_effettuata_durante_ricovero'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystemName", "WHO ATC", "GE");
	}

	private static void storiaClinica(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.containsIC(errors, LDOErrorEnum.STORIA_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > statusCode", "code", "completed");
		ValidationXML.containsIC(errors, LDOErrorEnum.STORIA_STATUS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > act > statusCode", "code", "completed");
	}

	private static void allergie(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.ALLERGIA_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > code", "code", "48765-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.ALLERGIA_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.ALLERGIA_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.ALLERGIA_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > code", "displayName", "ALLERGIE E/O REAZIONI AVVERSE");
		ValidationXML.hasDateFormat(errors, LDOErrorEnum.ALLERGIA_TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > effectiveTime > low", "value");
		ValidationXML.hasDateFormat(errors, LDOErrorEnum.ALLERGIA_TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > effectiveTime > high", "value");
		ValidationXML.containsIC(errors, LDOErrorEnum.ALLERGIA_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > code", "code", "52473-6");
		ValidationXML.containsIC(errors, LDOErrorEnum.ALLERGIA_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.containsIC(errors, LDOErrorEnum.ALLERGIA_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > code", "displayName", "Allergia o causa della reazione");
		ValidationXML.containsIC(errors, LDOErrorEnum.ALLERGIA_OBS_STATUS, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > statusCode", "code", "completed");
		ValidationXML.containsIC(errors, LDOErrorEnum.ALLERGIA_OBS_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship", "typeCode", "MFST", "SUBJ", "REFR");
		ValidationXML.containsIC(errors, LDOErrorEnum.ALLERGIA_OBS_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "code", "75321-0", "5321-0", "SEV", "33999-4");
		ValidationXML.containsIC(errors, LDOErrorEnum.ALLERGIA_OBS_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1", "Criticit� dell'Allergia o Intolleranza", "2.16.840.1.113883.5.4");
		ValidationXML.containsIC(errors, LDOErrorEnum.ALLERGIA_OBS_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "codeSystemName", "LOINC", "ActCode");
		ValidationXML.containsIC(errors, LDOErrorEnum.ALLERGIA_OBS_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Allergie'] > entry > act > entryRelationship > observation > entryRelationship > observation > code", "displayName", "Obiettività Clinica", "Clinical Finding", "Criticality", "Stato");
	}

	private static void esamiRicoveroV1(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Esami_eseguiti_durante_il_ricovero'] > code", "code", "30954-2", "47519-4");
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Esami_eseguiti_durante_il_ricovero'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Esami_eseguiti_durante_il_ricovero'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Esami_eseguiti_durante_il_ricovero'] > code", "displayName", "Esami diagnostici e/o di laboratorio significativi.");
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Esami_eseguiti_durante_il_ricovero'] > entry > observation", "classCode", "OBS");
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Esami_eseguiti_durante_il_ricovero'] > entry > observation", "moodCode", "EVN");
		
		
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_CODE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='PROCEDURE_ESEGUITE_DURANTE_RICOVERO'] > code", "displayName", "History of Procedures Document");
		ValidationXML.containsIC(errors, LDOErrorEnum.ESAMI_ER_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='PROCEDURE_ESEGUITE_DURANTE_RICOVERO'] > entry > procedure > entryRelationship", "typeCode", "RSON");
	}


	private static void esamiRicoveroV2(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='PROCEDURE_ESEGUITE_DURANTE_RICOVERO'] > code", "code", "47519-4");
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='PROCEDURE_ESEGUITE_DURANTE_RICOVERO'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='PROCEDURE_ESEGUITE_DURANTE_RICOVERO'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.ESAMI_CODE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='PROCEDURE_ESEGUITE_DURANTE_RICOVERO'] > code", "displayName", "History of Procedures Document");
		ValidationXML.hasDateFormat(errors, LDOErrorEnum.ESAMI_TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='PROCEDURE_ESEGUITE_DURANTE_RICOVERO'] > entry > procedure > effectiveTime > low", "value");
		ValidationXML.hasDateFormat(errors, LDOErrorEnum.ESAMI_TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='PROCEDURE_ESEGUITE_DURANTE_RICOVERO'] > entry > procedure > effectiveTime > high", "value");
		ValidationXML.containsIC(errors, LDOErrorEnum.ESAMI_ER_TYPE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='PROCEDURE_ESEGUITE_DURANTE_RICOVERO'] > entry > procedure > entryRelationship", "typeCode", "RSON");
	}

	private static void consulenza(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.CONSULENZA_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Consulenza'] > code", "code", "34104-0");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONSULENZA_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Consulenza'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONSULENZA_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Consulenza'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONSULENZA_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Consulenza'] > code", "displayName", "Hospital Consult note");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONSULENZA_OBS_CLSS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Consulenza'] > entry > observation", "classCode", "OBS");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONSULENZA_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Consulenza'] > entry > observation", "moodCode", "EVN");
	}

	private static void complicanze(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.COMPLICANZE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Complicanze'] > code", "code", "55109-3");
		ValidationXML.equalIC(errors, LDOErrorEnum.COMPLICANZE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Complicanze'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.COMPLICANZE_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Complicanze'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.COMPLICANZE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Complicanze'] > code", "displayName", "Complicazioni");
		ValidationXML.equalIC(errors, LDOErrorEnum.COMPLICANZE_OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Complicanze'] > entry > observation", "classCode", "OBS");
		ValidationXML.equalIC(errors, LDOErrorEnum.COMPLICANZE_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Complicanze'] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.COMPLICANZE_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Complicanze'] > entry > observation > code", "code", "75326-9");
		ValidationXML.equalIC(errors, LDOErrorEnum.COMPLICANZE_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Complicanze'] > entry > observation > code", "displayName", "Problem");
		ValidationXML.hasDateFormat(errors, LDOErrorEnum.COMPLICANZE_TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Complicanze'] > entry > observation > effectiveTime > low", "value");
		ValidationXML.hasDateFormat(errors, LDOErrorEnum.COMPLICANZE_TIME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Complicanze'] > entry > observation > effectiveTime > high", "value");
	}

	private static void condizioni(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > code", "code", "11535-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > code", "displayName", "Diagnosi di Dimissione");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > entry > observation", "classCode", "OBS");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > entry > observation > code", "code", "8651-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_OBS_CODE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_OBS_VALUE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > entry > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_OBS_CODE_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > entry > observation > value", "codeSystemName", "ICD9CM");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_OBS_VALUE_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > entry > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.CONDIZIONI_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Condizioni_paziente_diagnosi_dimissione'] > entry > observation > code", "displayName", "Diagnosi di Dimissione Ospedaliera");
	}

	private static void decorso(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.DECORSO_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Decorso_ospedaliero'] > code", "code", "8648-8");
		ValidationXML.equalIC(errors, LDOErrorEnum.DECORSO_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Decorso_ospedaliero'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.DECORSO_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Decorso_ospedaliero'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.DECORSO_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Decorso_ospedaliero'] > code", "displayName", "Decorso_ospedaliero", "Decorso ospedaliero");
	}


	private static void motivo(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > code", "code", "46241-6");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > code", "displayName", "Diagnosi di Accettazione");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > entry > observation", "classCode", "OBS");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_OBS_CODE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > entry > observation > code", "code", "8646-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_OBS_CODE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_OBS_VALUE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > entry > observation > value", "codeSystem", "2.16.840.1.113883.6.103");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > entry > observation > value", "codeSystemName", "ICD9CM");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > entry > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.MOTIVO_OBS_DIPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Motivo_Ricovero'] > entry > observation > code", "displayName", "Diagnosi di Accettazione Ospedaliera");
	}

	private static void riscontri(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.RISCONTRI_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Riscontri_accertamenti_significativi'] > code", "code", "11493-4");
		ValidationXML.equalIC(errors, LDOErrorEnum.RISCONTRI_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Riscontri_accertamenti_significativi'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.RISCONTRI_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Riscontri_accertamenti_significativi'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.RISCONTRI_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Riscontri_accertamenti_significativi'] > code", "displayName", "Hospital discharge studies summary");
	}

	private static void followup(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.FOLLOWUP_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Istruzioni_followup'] > code", "code", "18776-5");
		ValidationXML.equalIC(errors, LDOErrorEnum.FOLLOWUP_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Istruzioni_followup'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.FOLLOWUP_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Istruzioni_followup'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.FOLLOWUP_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Istruzioni_followup'] > code", "displayName", "Piano di Cura");
	}

	private static void terapia(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_DIMISSIONE_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_dimissione'] > code", "code", "10183-2");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_DIMISSIONE_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_dimissione'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_DIMISSIONE_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_dimissione'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_DIMISSIONE_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_dimissione'] > code", "displayName", "Terapia farmacologica alla dimissione");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_DIMISSIONE_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_dimissione'] > entry > substanceAdministration", "moodCode", "INT");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_DIMISSIONE_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_dimissione'] > entry > substanceAdministration", "classCode", "SBADM");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_DIMISSIONE_MAN_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_dimissione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code", "codeSystem", "2.16.840.1.113883.2.9.6.1.5", "2.16.840.1.113883.6.73", "2.16.840.1.113883.2.9.6.1.51");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_DIMISSIONE_MAN_TRANS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_dimissione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystem", "2.16.840.1.113883.6.73");
		ValidationXML.equalIC(errors, LDOErrorEnum.TERAPIA_DIMISSIONE_MAN_TRANS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Terapia_farmacologica_dimissione'] > entry > substanceAdministration > consumable > manufacturedProduct > manufacturedMaterial > code > translation", "codeSystemName", "WHO ATC", "Gruppi di Equivalenza");
	}

	private static void inquadramento(List<ErrorUCTDTO> errors, String xml) {
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > code", "code", "47039-3");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > code", "displayName", "Ricovero Ospedaliero, anamnesi ed esame obiettivo");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > code", "code", "11329-0");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > code", "displayName", "Anamnesi Generale");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_CLASS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation", "classCode", "OBS");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_MOOD_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation", "moodCode", "EVN");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation > code", "code", "75326-9");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation > code", "displayName", "Problem");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_STATUS, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation > statusCode", "code", "Completed");
		ValidationXML.containsIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_OBS_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation > entryRelationship > observation > code", "code", "89261-2", "33999-4");
		ValidationXML.containsIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_OBS_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation > entryRelationship > observation > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.containsIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_OBS_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation > entryRelationship > observation > code", "codeSystemName", "LOINC");
		ValidationXML.containsIC(errors, LDOErrorEnum.INQUADRAMENTO_ANAMNESI_OBS_OBS_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Anamnesi'] > entry > observation > entryRelationship > observation > code", "displayName", "Decorso clinico", "Stato");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ESAME_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Esame_Obiettivo'] > code", "code", "29545-1");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_TERAPIA_CODE, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Terapia_Farmacologica_Ingresso'] > code", "code", "42346-7");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ESAME_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Esame_Obiettivo'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ESAME_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Esame_Obiettivo'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_ESAME_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Esame_Obiettivo'] > code", "displayName", "Esame Obiettivo");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_TERAPIA_CODE_SYSTEM, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Terapia_Farmacologica_Ingresso'] > code", "codeSystem", "2.16.840.1.113883.6.1");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_TERAPIA_CODE_SYSTEM_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Terapia_Farmacologica_Ingresso'] > code", "codeSystemName", "LOINC");
		ValidationXML.equalIC(errors, LDOErrorEnum.INQUADRAMENTO_TERAPIA_DISPLAY_NAME, xml, "ClinicalDocument > component > structuredBody > component > section[ID='Inquadramento_clinico_iniziale'] > component > section[ID='Terapia_Farmacologica_Ingresso'] > code", "displayName", "Terapia Farmacologica all'ingresso","Terapia Farmacologica all’ingresso");
	}

}
