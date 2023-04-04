/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.dto;

import it.finanze.sanita.gva.validator.IUC;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ErrorUCTDTO {

	private String xPath;
	private String attribute;
	private IUC erroEnum;
	private String xPathValue;
	private String[] values;
	private boolean dateFormatException;

	public ErrorUCTDTO(String xPath, String attribute, IUC erroEnum, boolean dateFormatException) {
		super();
		this.xPath = xPath;
		this.attribute = attribute;
		this.erroEnum = erroEnum;
		this.dateFormatException = dateFormatException;
	}
	 
}
