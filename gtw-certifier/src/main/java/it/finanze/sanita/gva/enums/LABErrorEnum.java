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
	
	private String code;

}
