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
public enum ArgumentEnum {

	/**
	 * Show help page (optional mode).
	 */
	HELP_MODE("-h", false, "show this help page"),

	/**
	 * Json data input (mandatory parameter).
	 */
	JSON_DATA("-d", true, "specify json data file path (mandatory)"),

	/**
	 * File to publish (optional parameter).
	 */
	FILE_OR_DIR_PATH("-f", true, "specify path to file or directory (optional, if given will be used for custom claims)"),

	/**
	 * P12 alias (mandatory parameter).
	 */
	P12_ALIAS("-a", true, "specify alias of p12 file"),

	/**
	 * P12 password (mandatory parameter).
	 */
	P12_PWD("-p", true, "specify password of p12 file (mandatory)"),

	/**
	 * Token duration (from now till n hours, optional parameter).
	 */
	DURATION_JWT("-t", true, "specify token duration (optional, default is 24h)"),

	/**
	 * Output file prefix.
	 */
	OUTPUT_FILE_PREFIX("-o", true, "specify output file name prefix (optional, tokens will be saved as prefix.auth.txt and prefix.sign.txt)"),

	/**
	 * Show verbose message (optional mode).
	 */
	VERBOSE_MODE("-x", false, "enable verbose mode (optional, default is false)"),

	/**
	 * Show validation info (optional mode).
	 */
	VALIDATION_MODE("-v", false, "enable validation mode (optional, default is false)"),
	
	/**
	 * specify jwt application target.
	 */
	SYSTEM("-s", true, "specify jwt application target");
	
	/**
	 * Key argument.
	 */
	private String key;
	
	/**
	 * Flag mode or argument.
	 */
	private boolean flagHasValue;
	
	/**
	 * Key description (for help page).
	 */
	private String description;
	
	
	/**
	 * Constructor.
	 * 
	 * @param inKey				key
	 * @param inFlagHasValue	flag mode or argument
	 * @param inDescription		description
	 */
	private ArgumentEnum(String inKey, boolean inFlagHasValue, String inDescription) {
		key = inKey;
		flagHasValue = inFlagHasValue;
		description = inDescription;
	}

	/**
	 * Get argument by key.
	 * 	
	 * @param key	key	
	 * @return		argument
	 */
	public static ArgumentEnum getByKey(String key) {
		ArgumentEnum out = null;
		for (ArgumentEnum a:ArgumentEnum.values()) {
			if (a.getKey().equals(key)) {
				out = a;
				break;
			}
		}
		return out;
	}
	
}
