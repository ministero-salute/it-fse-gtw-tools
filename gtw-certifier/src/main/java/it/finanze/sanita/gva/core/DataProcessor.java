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
package it.finanze.sanita.gva.core;

import com.fasterxml.jackson.databind.JsonMappingException;
import it.finanze.sanita.gva.dto.DataJSONDTO;
import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import it.finanze.sanita.gva.dto.ProcessResDTO;
import it.finanze.sanita.gva.dto.ResultJSONDTO;
import it.finanze.sanita.gva.enums.EsameEnum;
import it.finanze.sanita.gva.utility.LogUtility;
import it.finanze.sanita.gva.utility.PDFUtility;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static it.finanze.sanita.gva.utility.FilesUtility.*;

@RequiredArgsConstructor
public class DataProcessor {

	private static final Logger logger = LogUtility.getLogger(DataProcessor.class);

	private static final String EXPECTED_DIR = "FILES";
	private static final String EXPECTED_FILE = "data.json";

	// Scanning directory
	private final File dir;

	// Target directory
	private File files;

	// Data file (.json)
	private File dataJson;

	public void checkAndprocess() {
		try {
			if(check()) process();
		} catch (IOException e) {
			logger.info(String.format("Unable to process due to I/O error: %s", e.getMessage()));
		}
	}

	private boolean check() throws IOException {
		// Start looking for FILES dir
		List<Path> dirs = walkAndMatch(matchDir(EXPECTED_DIR), dir.toPath());
		// Start looking for .data.json
		List<Path> json = walkAndMatch(matchFile(EXPECTED_FILE), dir.toPath());
		// Check independently (so we can output a full logs)
		boolean dirsCheck = isUniqueMatch(dirs, String.format("%s dir", EXPECTED_DIR), logger);
		boolean dataCheck = isUniqueMatch(json, String.format("%s file", EXPECTED_FILE), logger);
		boolean out = dirsCheck && dataCheck;
		// Assign
		if(out) {
			files = dirs.get(0).toFile();
			dataJson = json.get(0).toFile();
		}
		return out;
	}

	public void process() {
		try {
			DataJSONDTO dto = readJsonFromFile(dataJson, DataJSONDTO.class);
			processResults(dto.getResults());
		} catch (JsonMappingException e) {
			logger.info(String.format("ERR: Unable to parse %s: %s", EXPECTED_FILE, e.getMessage()));
		} catch (IOException e) {
			logger.info(String.format("ERR: %s", e.getMessage()));
		}

	}

	private void processResults(List<ResultJSONDTO> results) {
		ProcessResDTO out = new ProcessResDTO();

		if (results == null) {
			logger.info("Not found result set in data.json");
			return;
		}

		File[] pdfFiles = files.listFiles((directory, name) -> name.endsWith(".pdf"));
		if (pdfFiles == null || pdfFiles.length == 0) {
			logger.info("No PDF files found in the directory.");
			return;
		}

		for (ResultJSONDTO result : results) {
			EsameEnum esame = EsameEnum.getCode(result.getId());
			if (esame != null) {
				checkValidation(esame, result,pdfFiles, out);
			} else {
				out.unverifiable(result.getId());
			}
		}
		// Print res
		out.print(logger);
	}

	private void checkValidation(EsameEnum esame,ResultJSONDTO result,File[] pdfFiles, ProcessResDTO out) {
		try {
			for (String nameFile : result.getFiles()) {
				Optional<File> matchingFile = Arrays.stream(pdfFiles)
						.filter(file -> file.getName().equalsIgnoreCase(nameFile))
						.findFirst();
				if (matchingFile.isPresent()) {
					File file = matchingFile.get();
					byte[] bytes = FileUtils.readFileToByteArray(file);
					String cda = PDFUtility.extractCDAFromAttachments(bytes);
					if(isNullOrEmpty(cda)) {
						cda = PDFUtility.unenvelopeA2(bytes);
					}

					if(!isNullOrEmpty(cda)) {
						checkValidationUAT(cda, esame, out);
					} else {
						logger.info(String.format("cda not found in %s", nameFile));
					}

				} else {
					logger.info(nameFile + " not found in the directory.");
				}
			}
		} catch (Exception e) {
			logger.info("Error while perform check validation");
		} 
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private void checkValidationUAT(String cda, EsameEnum esameEnum, ProcessResDTO out) {
		try {
			Method met = esameEnum.getValidator().getMethod(esameEnum.getMethodName(), String.class);
			List<ErrorUCTDTO> errors = (List)met.invoke(null, cda);
			if(!errors.isEmpty()) {
				out.failure(esameEnum.getIdTest(), getErrors(errors));
			} else {
				out.success(esameEnum.getIdTest());
			}
		} catch(Exception e) {
			logger.info("Error while perform check validation uat");
		}
	}

	private static String getErrors(List<ErrorUCTDTO> errors) {
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
