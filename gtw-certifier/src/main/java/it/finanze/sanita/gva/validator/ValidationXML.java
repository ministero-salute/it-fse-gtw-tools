/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.finanze.sanita.gva.dto.SectionCriteriaDTO;
import it.finanze.sanita.gva.dto.XPathAndValuesDTO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.Nullable;

@Slf4j
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

	private static List<Element> getSectionsByKey(String xml, String xPath, String specificXPath, String key, String attribute, String value) {
		Document document = Jsoup.parse(xml);
		Elements elements = document.select(xPath);

		return filterElementsByKey(elements, specificXPath, key, attribute, value);
	}

	private static List<Element> getSectionsByKey(Element parentElement, String xPath, String specificXPath, String key, String attribute, String value) {
		Elements elements = parentElement.select(xPath);

		return filterElementsByKey(elements, specificXPath, key, attribute, value);
	}

	private static List<Element> filterElementsByKey(Elements elements, String specificXPath, String key, String attribute, String value) {
		List<Element> nodes = new ArrayList<>();

		for (Element element : elements) {
			Element keyElement;
			if (!specificXPath.isEmpty()) {
				keyElement = element.selectFirst(specificXPath + " > " + key);
			} else {
				keyElement = element.selectFirst(key);
			}

			if (keyElement != null && keyElement.attr(attribute).equalsIgnoreCase(value)) {
				nodes.add(element);
			}
		}

		return nodes;
	}

	public static void containsSection(
			List<ErrorUCTDTO> errors,
			IUC error,
			String xml,
			String sectionXPath,
			String templateIdPath,
			SectionCriteriaDTO... sections
	) {
		boolean found = false;
		for (SectionCriteriaDTO section : sections) {
			boolean sectionFound = checkSectionByTemplateId(
					errors,
					error,
					xml,
					sectionXPath,
					templateIdPath,
					section.getTemplateId(),
					true,
					section.getValues()
			);
			if(sectionFound) found = true;
		}
		if (!found) {
			errors.add(new ErrorUCTDTO(
					sectionXPath,
					"Entire section missing",
					error,
					"Not found",
					new String[]{Arrays.toString(sections)},
					false
			));
		}
	}



	public static void checkNestedSections(
			List<ErrorUCTDTO> errors,
			IUC error,
			String xml,
			String outerXPath,
			String outerTemplateId,
			String innerXPath,
			String templateIdPath,
			String innerTemplateId,
			XPathAndValuesDTO... values
	){
		String completeXPath = outerXPath + "(templateId=" + outerTemplateId + ")"+ " > " + innerXPath + " > templateId[root='" + innerTemplateId + "']";
		try {
			List<Element> outerSections = getSectionsByKey(xml, outerXPath, "", "templateId", "root", outerTemplateId);
			if(outerSections.isEmpty()){
				ErrorUCTDTO errorUCTDTO = new ErrorUCTDTO(
						outerXPath + "templateId",
						"root",
						error,
						"Not found",
						new String[]{outerTemplateId},
						false
				);
				if(!errors.contains(errorUCTDTO)) errors.add(errorUCTDTO);
				return;
			}

			for (Element outerSection : outerSections) {
				List<Element> innerSections = getSectionsByKey(outerSection, innerXPath, templateIdPath, "templateId", "root", innerTemplateId);
				if (innerSections.isEmpty()) {
					errors.add(new ErrorUCTDTO(
							completeXPath,
							"root",
							error,
							"Not found",
							new String[]{innerTemplateId},
							false
					));
					return;
				}
				for (Element innerSection : innerSections) {
					checkSection(errors, completeXPath, innerSection, error, values);
				}
			}
		}catch (Exception ex){
			errors.add(new ErrorUCTDTO(completeXPath, "exception", error, ex.getMessage(), null, false));
		}
	}

	public static boolean checkSection(List<ErrorUCTDTO> errors, IUC error, String xml, SectionCriteriaDTO section){
		return checkSectionByTemplateId(
				errors,
				error,
				xml,
				"",
				"",
				section.getTemplateId(),
				false,
				section.getValues()
		);
	}

	public static boolean checkSectionByTemplateId(
			List<ErrorUCTDTO> errors,
			IUC error,
			String xml,
			String sectionXPath,
			String templateIdPath,
			String templateId,
			XPathAndValuesDTO... values
	){
		return checkSectionByTemplateId(errors, error, xml, sectionXPath, templateIdPath, templateId, false, values);
	}

	public static boolean checkSectionByCode(
			List<ErrorUCTDTO> errors,
			IUC error,
			String xml,
			String sectionXPath,
			String templateIdPath,
			String templateId,
			XPathAndValuesDTO... values
	){
		return checkSectionByTemplateId(errors, error, xml, sectionXPath, templateIdPath, templateId, values);
	}

	public static boolean checkSectionByTemplateId(
			List<ErrorUCTDTO> errors,
			IUC error,
			String xml,
			String sectionXPath,
			String templateIdPath,
			String templateId,
			boolean isOptional,
			XPathAndValuesDTO... values
	) {
		String completeXPath = sectionXPath + templateIdPath;
		try {
			//Check if the section having the templateId as given in input exists
			String specificTemplateIdXPath = checkSectionExists(errors, error, xml, sectionXPath, templateId, completeXPath, isOptional);
			if (specificTemplateIdXPath == null) return false; //Section not found
			//Get the outer section found by the templateId
			List<Element> entrySections = getSectionsByKey(xml, sectionXPath, "observation", "templateId", "root", templateId);
			//Check for each section if it satisfies the requirements given by the values in input
			for(Element section : entrySections)
				checkSection(errors, specificTemplateIdXPath, section, error, values);
			return true; //Section found
		} catch (Exception e) {
			errors.add(new ErrorUCTDTO(sectionXPath, "exception", error, e.getMessage(), null, false));
			return false;
		}
	}

	private static void checkSection(List<ErrorUCTDTO> errors, String xPath, Element entrySection, IUC out, XPathAndValuesDTO... values) {
		for(XPathAndValuesDTO element : values) {
			try{
				Element section;
				if (element.getSpecificXPath() != null)
					section = entrySection.selectFirst(element.getSpecificXPath());
				else
					section = entrySection;
				String actualValue = section.attr(element.getAttribute());
				List<String> expectedValues = element.getAttributeValues();
				if (!expectedValues.contains(actualValue)){
					errors.add(new ErrorUCTDTO(
							xPath,
							element.getAttribute(),
							out,
							actualValue,
							expectedValues.toArray(new String[0]),
							false
					));
				}
			}catch (Exception e){
				log.error("{}", e);
			}
		}

	}

	@Nullable
	private static String checkSectionExists(List<ErrorUCTDTO> errors, IUC error, String xml, String sectionXPath, String templateId, String completeXPath, boolean isOptional) {
		String specificTemplateIdXPath = completeXPath + " > templateId[root='" + templateId + "']";
		List<String> specificTemplateIdValue = getXPaths(xml, specificTemplateIdXPath, "root");

		if (specificTemplateIdValue.isEmpty() || !specificTemplateIdValue.contains(templateId)) {
			ErrorUCTDTO errorUCTDTO = new ErrorUCTDTO(
					sectionXPath,
					"root",
					error,
					"Not found",
					new String[]{templateId},
					false
			);
			if(!errors.contains(errorUCTDTO) && !isOptional) errors.add(errorUCTDTO);
			return null;
		}
		return specificTemplateIdXPath;
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
	public static void checkXPathExists(
			List<ErrorUCTDTO> errors,
			IUC error,
			String xml,
			String xPath,
			String attribute,
			String expectedValue
	){
		String completeXPath = xPath + " > " + attribute;
		try{
			String element = getXPath(xml, xPath, attribute);
			if(element.isEmpty()){
				errors.add(
						new ErrorUCTDTO(
								xPath,
								attribute,
								error,
								"The section should be present",
								new String[]{expectedValue},
								false
						));
			}
		} catch (Exception e){
			errors.add(new ErrorUCTDTO(completeXPath, "exception", error, e.getMessage(), null, true));
		}
	}

}
