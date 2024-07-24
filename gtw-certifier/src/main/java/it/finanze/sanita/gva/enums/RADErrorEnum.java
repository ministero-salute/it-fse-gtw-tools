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
public enum RADErrorEnum implements IUC{

	T001("001"), T002("002"), T003("003"), T004("004"), T005("005"), T006("006"), T007("007"), T008("008"), T009("009"), T010("010"), T011("011"), T012("012"), T013("013"), T014("014"), T015("015"), T016("016"), T017("017"), T018("018"), T019("019"), T020("020"), T021("021"), T022("022"), T023("023"), T024("024"), T025("025"),
	T026("026"), T027("027"), T028("028"), T029("029"), T030("030"), T031("031"), T032("032"), T033("033"), T034("034"), T035("035"), T036("036"), T037("037"), T038("038"), T039("039"), T040("040"), T041("041");

	private final String code;

}
