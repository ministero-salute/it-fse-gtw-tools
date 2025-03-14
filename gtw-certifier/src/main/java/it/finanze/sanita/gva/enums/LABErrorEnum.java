/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.enums;

import it.finanze.sanita.gva.validator.IUC;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LABErrorEnum implements IUC {

	T000("000"),T001("001"), T002("002"), T003("003"), T004("004"), T005("005"), T006("006"), T007("007"), T008("008"), T009("009"), T010("010"), T011("011"), T012("012"), T013("013"), T014("014"), T015("015"), T016("016"), T017("017"), T018("018"), T019("019"), T020("020"), T021("021"), T022("022"), T023("023"), T024("024"), T025("025"),
	T026("026"), T027("027"), T028("028"), T029("029"), T030("030"), T031("031"), T032("032"), T033("033"), T034("034"),
	SEC_CODE_SYSTEM("001"),
	SEC_CODE_SYSTEM_NAME("002"),
	SEC_ACT_STATUS("003"),
	SEC_ACT_CODE("004"),
	SEC_ACT_TYPE_CODE("005"),
	SEC_ACT_CLUSTER_STATUS("006"),
	SEC_ACT_CLUSTER_TIME("007"),
	SEC_ACT_CLUSTER_SPECIMEN_CLASS_CODE("008"),
	SEC_ACT_CLUSTER_CLASS_CODE("009"),
	SEC_ACT_CLUSTER_OBS_CODE_SYSTEM("010"),
	SEC_ACT_CLUSTER_OBS_CODE_SYSTEM_NAME("011"),
	SEC_ACT_CLUSTER_OBS_STATUS("012"),
	SEC_ACT_CLUSTER_OBS_METHOD("013"),
	CODE_SYSTEM("014"),
	CODE_SYSTEM_NAME("015"),
	STATUS("016");

	private final String code;

}
