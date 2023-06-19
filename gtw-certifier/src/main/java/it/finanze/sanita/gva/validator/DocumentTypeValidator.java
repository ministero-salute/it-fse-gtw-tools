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
package it.finanze.sanita.gva.validator;

import java.util.List;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;

public abstract class DocumentTypeValidator {

	
	protected static String getErrors(List<ErrorUCTDTO> errors) {
		StringBuilder sb = new StringBuilder("<errors>\n");
		for (ErrorUCTDTO error:errors) {
			String values = "";
			if(error.getValues()!=null) {
				values = "Valorizzare con uno dei seguenti valori: " + String.join("/", error.getValues());
			}
			sb.append("\t").append("<error code=\"" + error.getErroEnum().getCode() + "\" attribute=\"" + error.getAttribute() + " expected = \"" + values + " actual = \"" + error.getXPathValue() + "\">"  + error.getXPath().replace(" > ", "/") + "</error>").append("\n");
		}
		sb.append("</errors>");
		return sb.toString();
	}



}
