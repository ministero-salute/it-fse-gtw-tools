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
package it.finanze.sanita.fjm.enums;

import lombok.Getter;

/**
 * Copyright (c) 2022, Ministero della Salute
 */
@Getter
public enum SystemEnum {

	/**
	 * Show help page (optional mode).
	 */
	GATEWAY("gateway","FSE-JWT-Signature"),
	
	/**
	 * Show help page (optional mode).
	 */
	PROVISIONING("provisioning","FSE-JWT-Provisioning"),
	
	/**
	 * specify jwt application target.
	 */
	MONITORING("monitoring","FSE-JWT-Monitoring"),
	
	/**
	 * Terminology.
	 */
	TERMINOLOGY("terminology","");
	
	/**
	 * Key argument.
	 */
	private String key;
	
	/**
	 * Key argument.
	 */
	private String tokenBusinessName;
	
	
	/**
	 * Constructor.
	 * 
	 * @param inKey				key
	 * @param inFlagHasValue	flag mode or argument
	 * @param inDescription		description
	 */
	private SystemEnum(String inKey, String inTokenBusinessName) {
		key = inKey;
		tokenBusinessName = inTokenBusinessName;
		
	}


	/**
	 * Get argument by key.
	 * 	
	 * @param key	key	
	 * @return		argument
	 */
	public static SystemEnum getByKey(String key) {
		SystemEnum out = null;
		for (SystemEnum a:SystemEnum.values()) {
			if (a.getKey().equals(key)) {
				out = a;
				break;
			}
		}
		return out;
	}
	
}
