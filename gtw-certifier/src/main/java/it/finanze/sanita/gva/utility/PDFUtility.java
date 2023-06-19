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
package it.finanze.sanita.gva.utility;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;

import com.lowagie.text.pdf.PRStream;
import com.lowagie.text.pdf.PdfArray;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfIndirectReference;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfString;

import it.finanze.sanita.gva.dto.AttachmentDTO;

public class PDFUtility {
	
	private PDFUtility() {}
	
	
	public static String extractCDAFromAttachments(final byte[] cda) {
		String out = null;
		final Map<String, AttachmentDTO> attachments = extractAttachments(cda);
		if (!attachments.isEmpty()) {
			if (attachments.size() == 1) {
				out = detectCharsetAndExtract(attachments.values().iterator().next().getContent());
			} else {
				final AttachmentDTO attDTO = attachments.get("cda.xml");
				if (attDTO != null) {
					out = detectCharsetAndExtract(attDTO.getContent());
				}
			}
		}
		return out;
	}

	
	public static Map<String, AttachmentDTO> extractAttachments(byte[] bytePDF) {
		Map<String, AttachmentDTO> out = new HashMap<>();
		
		try (PDDocument pd = PDDocument.load(bytePDF)) {
		    PDDocumentCatalog catalog = pd.getDocumentCatalog();
		    PDDocumentNameDictionary names = catalog.getNames();
		    PDEmbeddedFilesNameTreeNode embeddedFiles = names.getEmbeddedFiles();
		    Map<String, PDComplexFileSpecification> embeddedFileNames = embeddedFiles.getNames();
		    for (Map.Entry<String, PDComplexFileSpecification> entry : embeddedFileNames.entrySet()) {  
		    	AttachmentDTO att = AttachmentDTO.builder().
		    			fileName(entry.getKey()).
		    			mimeType(entry.getValue().getEmbeddedFile().getSubtype()).
		    			content(entry.getValue().getEmbeddedFile().toByteArray()).
		    			build();
		    	out.put(entry.getKey(), att);
		    }
		} catch (Exception e) {
//			log.warn("Errore in fase di estrazione allegati da pdf.");
		}
	    return out;
	}
	
	public static String unenvelopeA2(byte[] pdf) {
		String out = null;
		String errorMsg = "No CDA found.";
		try (PdfReader pdfReader = new PdfReader(pdf)) {
			PdfDictionary catalog = pdfReader.getCatalog();
			if (catalog != null) {
				PdfDictionary names = catalog.getAsDict(PdfName.NAMES);
				if (names != null) {
					PdfIndirectReference xfaResourcesIndirect = names.getAsIndirectObject(new PdfName("XFAResources"));
					if (xfaResourcesIndirect != null) {
						PdfDictionary xfaResourcesDictionary = (PdfDictionary) pdfReader.getPdfObject(xfaResourcesIndirect.getNumber());
						if (xfaResourcesDictionary != null) {
							PdfArray pdfArrayDataset = (PdfArray) xfaResourcesDictionary.get(PdfName.NAMES);
							if (pdfArrayDataset != null) {
								if (pdfArrayDataset.size() >= 2) {
									PdfString pdfStringDataset = (PdfString) pdfArrayDataset.getPdfObject(0);
									if (pdfStringDataset != null) {
										String datasets = new String(pdfStringDataset.getBytes());
										if ("datasets".equals(datasets)) {
											PdfIndirectReference cdaIndirect = (PdfIndirectReference) pdfArrayDataset.getPdfObject(1);
											if (cdaIndirect != null) {
												PRStream cdaPRStream = (PRStream) pdfReader.getPdfObject(cdaIndirect.getNumber());
												if (cdaPRStream != null) {
													out = new String(PdfReader.getStreamBytes(cdaPRStream));
												} else {
													errorMsg = "PRStream cdaPRStream [element 1] is null";
												}
											} else {
												errorMsg = "PdfIndirectReference cdaIndirect [element 1] is null";
											}
										} else {
											errorMsg = "PdfString pdfStringDataset [element 0] String content is not equals datasets";
										}
									} else {
										errorMsg = "PdfString pdfStringDataset [element 0] is null";
									}
								} else {
									errorMsg = "PdfArray pdfArrayDataset has less than 2 elements";
								}
							} else {
								errorMsg = "PdfArray pdfArrayDataset is null";
							}
						} else {
							errorMsg = "PdfDictionary xfaResourcesDictionary is null";
						}
					} else {
						errorMsg = "PdfIndirectReference xfaResourcesIndirect inside Names is null";
					}
				} else {
					errorMsg = "PdfDictionary names inside catalog is null";
				}
			} else {
				errorMsg = "PdfDictionary catalog is null";
			}
		} catch (Exception e) {
//			log.warn("Errore in fase di recupero CDA da risorsa.", e);
		} finally {
//			log.warn(errorMsg);
		}
		return out;
	}

	public static boolean isPdf(byte[] pdf) {
		boolean out = false;
		if (pdf!=null && pdf.length > 4) {
			byte[] magicNumber = Arrays.copyOf(pdf, 4);
			String strMagicNumber = new String(magicNumber);
			out = strMagicNumber.equals("%PDF");
		}
		return out;
	}

	public static String detectCharsetAndExtract(byte[] bytes) {
		Charset detectedCharset = StandardCharsets.UTF_8;
		try {
			
			XMLInputFactory factory = XMLInputFactory.newInstance();
			factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
			factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
			final XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(new ByteArrayInputStream(bytes)); 
			final String fileEncoding = xmlStreamReader.getEncoding(); 
			detectedCharset = Charset.forName(fileEncoding);
			
		} catch (Exception ex) {
//			log.error(String.format("Error while reading extracted CDA using detected encode: %s", detectedCharset), ex);
//			throw new BusinessException(String.format("Error while reading extracted CDA using detected encode: %s", detectedCharset), ex);
		}
		return new String(bytes, detectedCharset);
	}
}
