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
