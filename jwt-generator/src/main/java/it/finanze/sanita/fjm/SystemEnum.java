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
package it.finanze.sanita.fjm;

/**
 * Copyright (c) 2022, Ministero della Salute
 */
enum SystemEnum {

	/**
	 * Show help page (optional mode).
	 */
	GATEWAY("gateway"),
	
	/**
	 * Show help page (optional mode).
	 */
	PROVISIONING("provisioning"),
	
	/**
	 * specify jwt application target.
	 */
	MONITORING("monitoring");
	
	/**
	 * Key argument.
	 */
	private String key;
	
	
	/**
	 * Constructor.
	 * 
	 * @param inKey				key
	 * @param inFlagHasValue	flag mode or argument
	 * @param inDescription		description
	 */
	private SystemEnum(String inKey) {
		key = inKey;
	}

	/**
	 * Getter key.
	 * 
	 * @return	key
	 */
	public String getKey() {
		return key;
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
