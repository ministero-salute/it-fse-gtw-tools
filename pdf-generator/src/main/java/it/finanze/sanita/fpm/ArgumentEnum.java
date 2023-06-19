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
package it.finanze.sanita.fpm;

/**
 * Copyright (c) 2022, Ministero della Salute
 *
 * Arguments enum.
 */
enum ArgumentEnum {

	/**
	 * Show help page (optional mode).
	 */
	HELP_MODE("-h", false, "show this help page"),

	/**
	 * File pdf to inject (optional parameter).
	 */
	FILE_CDA("-c", true, "specify CDA file path to inject (mandatory)"),

	/**
	 * File pdf to inject (optional parameter).
	 */
	FILE_PDF("-p", true, "specify PDF file path (optional, if not given the tool will use a sample)"),

	/**
	 * Show verbose message (optional mode).
	 */
	VERBOSE_MODE("-x", false, "enable verbose mode (optional, default is false)"),

	/**
	 * Show validation info (optional mode).
	 */
	VALIDATION_MODE("-v", false, "enable validation mode (optional, default is false)"),
	
	/**
	 * File pdf to inject (optional parameter).
	 */
	FILE_OUTPUT("-o", true, "specify path output pdf file (optional , default is output.pdf)"),
	
	/**
	 * Pdf sign(optional parameter).
	 */
	PATH_SIGN_P12_PDF("-s", true, "specify if pdf have to sign(optional , default is false)"),
	
	/**
	 * Pwd sign p12.
	 */
	PWD_SIGN_P12_PDF("-pwd", true, "specify pwd of p12 certificate");
	
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
	 * Getter key.
	 * 
	 * @return	key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Getter flag attribute or mode.
	 * 
	 * @return	flag attribute or mode
	 */
	public boolean getFlagHasValue() {
		return flagHasValue;
	}

	/**
	 * Getter description.
	 * 
	 * @return	description
	 */
	public String getDescription() {
		return description;
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
