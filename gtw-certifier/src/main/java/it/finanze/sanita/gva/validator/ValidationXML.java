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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationXML {

	public static void equalIC(List<ErrorUCTDTO> errors, IUC error, String xml, String xPath, String attribute, String ... values) {
		equalIC(errors, error, xml, xPath, attribute, false, values);
	}

	public static void equalIC(List<ErrorUCTDTO> errors, IUC error, String xml, String xPath, String attribute, Boolean canBeNull, String ... values) {
		IUC out = error;
		String xPathValue = "";
		try {
			xPathValue = getXPath(xml, xPath, attribute);
			for (String value:values) {
				if (xPathValue.equalsIgnoreCase(value)) {
					out = null;
					break;
				}
			}
		} catch (Exception e) {
			//TODO - Log
		}
		if (out!=null) {
			errors.add(new ErrorUCTDTO(xPath, attribute, out, xPathValue, values,false));
		}
	}
	
	private static String getXPath(String xml, String xPath, String attribute) {
		Document document = Jsoup.parse(xml);
		return document.select(xPath).attr(attribute);
	}
	
	public static void containsIC(List<ErrorUCTDTO> errors, IUC error, String xml, String xPath, String attribute, String ... values) {
		IUC out = error;
		List<String> xPathValue = new ArrayList<>();
		try {
			xPathValue = getXPaths(xml, xPath, attribute);
			for (String value:values) {
				for(String x : xPathValue) {
					if (x.equalsIgnoreCase(value)) {
						out = null;
						break;
					}	
				}
			}
		} catch (Exception e) {
		}
		if (out!=null) {
			errors.add(new ErrorUCTDTO(xPath, attribute, out, String.join(",", xPathValue), values,false));
		}
	}
	
	private static List<String> getXPaths(String xml, String xPath, String attribute) {
		Document document = Jsoup.parse(xml);
		return document.select(xPath).eachAttr(attribute);
	}
	
	public static void equalTAG(List<ErrorUCTDTO> errors, IUC error, String xml, String xPath, String ... values) {
		equalTAG(errors, error, xml, xPath, false, values);
	}
	public static void equalTAG(List<ErrorUCTDTO> errors, IUC error, String xml, String xPath, Boolean canBeNull, String ... values) {
		IUC out = error;
		String xPathValue = "";
		try {
			xPathValue = getXPathTAG(xml, xPath);
			for (String value:values) {
				if (xPathValue.equalsIgnoreCase(value)) {
					out = null;
					break;
				}
			}
		} catch (Exception e) {
		}
		if (out!=null) {
			errors.add(new ErrorUCTDTO(xPath, "", out, xPathValue, values,false));
		}
	}
	
	private static String getXPathTAG(String xml, String xPath) {
		Document document = Jsoup.parse(xml);
		return document.select(xPath).text();
	}

	public static void hasDateFormat(List<ErrorUCTDTO> errors, IUC error, String xml, String xPath, String attribute) {
		IUC out = error;
		try {
			String xPathValue = getXPath(xml, xPath, attribute);
			if (hasDateFormat(xPathValue)) {
				out = null;
			}
		} catch (Exception e) {
			//TODO - gesitre Errore
		}
		if (out!=null) {
			errors.add(new ErrorUCTDTO(xPath, attribute, error,true));
		}
	}

	private static boolean hasDateFormat(String xPathValue) throws ParseException {
		String upToNCharacters = xPathValue.substring(0, Math.min(xPathValue.length(), 14));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		sdf.parse(upToNCharacters);
		return true;
	}

}
