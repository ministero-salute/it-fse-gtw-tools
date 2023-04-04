/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
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
