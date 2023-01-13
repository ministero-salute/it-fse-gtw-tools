package it.finanze.sanita.fpm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.signature.CreateSignaturePades;

/**
 * Copyright (c) 2022, Ministero della Salute
 *
 * Launcher class.
 */
public class Launcher {

	static final Logger LOGGER = Utility.getLogger(Launcher.class.getName());

	static String pathFilePDF = null;
	static String pathFileCDA = null;
	static boolean flagMalformedInput = false;
	static boolean flagNeedHelp = false;
	static boolean flagVerbose = false;
	static boolean flagValidation = false;
	static String pathOutput = null;
	
	static boolean signPdf = false;

	/**
	 * Main method.
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		LOGGER.info(" _____ _____ ___    _____ ____  _____    _____     _           ");
		LOGGER.info("|   __|   __|_  |  |  _  |    \\|   __|  |     |___| |_ ___ ___ ");
		LOGGER.info("|   __|__   |  _|  |   __|  |  |   __|  | | | | .'| '_| -_|  _|");
		LOGGER.info("|__|  |_____|___|  |__|  |____/|__|     |_|_|_|__,|_,_|___|_|  \n");

		try {

			checkArgs(args);

			if (flagNeedHelp) {
				showHelp();
			} else if (flagMalformedInput) {
				LOGGER.info("Please check for malformed input; please remember that CDA path file is mandatory.");
			} else {
				buildPdf();
			}
		} catch (Exception e) {
			LOGGER.info("An error occur while trying to inject PDF, hope this can help:");
			LOGGER.info(String.format("EXCEPTION: %s", ExceptionUtils.getStackTrace(e)));
		}
	}

	public static byte[] getPdf(byte[] cda) throws IOException {
		byte[] filePDF = createSample();
		return inject(filePDF, cda);
	}
	
	private static void checkArgs(String[] args) {
		LOGGER.info("SIZE:" + args.length);
		for (int i = 0; i < args.length;) {
			String key = args[i].toLowerCase();
			ArgumentEnum arg = ArgumentEnum.getByKey(key);
			LOGGER.info("KEY:" + key);
			if (arg == null) {
				flagMalformedInput = true;
				break;
			} else {
				if (arg.getFlagHasValue()) {
					String value = null;
					if (i + 1 < args.length) {
						value = args[i + 1];
					} else {
						flagMalformedInput = true;
						break;
					}

					checkValueArg(arg, value);

					i += 2;
				} else {
					i++;

					checkNoValueArg(arg);
					
				}
			}
		}
	}

	private static void checkValueArg(ArgumentEnum arg, String value) {
		LOGGER.info(arg.getKey()); 
		if (ArgumentEnum.FILE_PDF.equals(arg)) {
			pathFilePDF = value;
		} else if (ArgumentEnum.FILE_CDA.equals(arg)) {
			pathFileCDA = value;
		} else if(ArgumentEnum.FILE_OUTPUT.equals(arg)) {
			pathOutput = value;
		} 

	}

	private static void checkNoValueArg(ArgumentEnum arg) {
		if (ArgumentEnum.HELP_MODE.equals(arg)) {
			flagNeedHelp = true;
		} else if (ArgumentEnum.VERBOSE_MODE.equals(arg)) {
			flagVerbose = true;
		} else if (ArgumentEnum.VALIDATION_MODE.equals(arg)) {
			flagValidation = true;
		} else if(ArgumentEnum.SIGN_PDF.equals(arg)) {
			signPdf = true;
		}
	}

	private static void buildPdf() throws Exception {
		byte[] filePDF = null;
		if (pathFilePDF == null || pathFilePDF.length() == 0) {
			filePDF = createSample();
			pathFilePDF = "USING INTERNAL RESOURCE";
			String l = filePDF != null ? "" + filePDF.length : null;
			dumpVerboseMsg(flagVerbose, "l : " + l);
		} else {
			filePDF = Utility.getFileFromFS(pathFilePDF);
		}
		byte[] fileCDA = Utility.getFileFromFS(pathFileCDA);

		dumpVerboseMsg(flagVerbose, "Analyzing data\n");
		dumpVerboseMsg(flagVerbose, "Path file CDA: " + pathFileCDA);
		dumpVerboseMsg(flagVerbose, "Path file PDF: " + pathFilePDF);

		dumpVerboseMsg(flagVerbose, "\nGenerating File\n");

		byte[] output = inject(filePDF, fileCDA);

		if (Utility.nullOrEmpty(pathOutput)) {
			pathOutput = "output.pdf";
		}

		if(signPdf) {
			byte[] keyStore = Utility.getFileFromFS("mykeystore.p12");
			char[] pwd = "fascicolo".toCharArray();
			CreateSignaturePades signaturePades = SignerHelper.getSP(keyStore, pwd);
			output = SignerHelper.pades(signaturePades, pathOutput, output);
		}
		
		Utility.saveToFile(output, pathOutput);

		dumpVerboseMsg(flagVerbose, "File generated.\n");

		if (flagValidation) {
			LOGGER.info("Extracting CDA\n");
			String cda = extract(output);
			if (Utility.nullOrEmpty(cda)) {
				cda = "EMPTY CDA!";
			}
			LOGGER.info("CDA: " + cda);
		}
	}


	/**
	 * Show help info.
	 */
	private static void showHelp() {

		LOGGER.info("NAME");
		LOGGER.info("\t\tFS2 PDF Maker (fpm) - Injected PDF generator for FS2 Gateway\n");
		LOGGER.info("SYNOPSIS");
		LOGGER.info(
				"\t\tjava -jar fpm -c CDA_FILE [-p PDF_FILE] [-v] [-x] [-h] [-o]");
		LOGGER.info("");
		LOGGER.info("DESCRIPTION");
		LOGGER.info("\t\tGenerate PDF with injected CDA (attachment mode) for FS2 Gateway");
		LOGGER.info("");
		LOGGER.info("\t\tArguments:");
		for (ArgumentEnum ae : ArgumentEnum.values()) {
			LOGGER.info("");
			LOGGER.info("\t\t" + ae.getKey() + "\t" + ae.getDescription());
		}
	}

	/**
	 * Dump message if in verbose mode.
	 * 
	 * @param flagVerbose flag verbose mode
	 * @param msg         messagge
	 */
	private static void dumpVerboseMsg(boolean flagVerbose, String msg) {
		if (flagVerbose) {
			LOGGER.info(msg);
		}
	}

	public static byte[] inject(byte[] bytePDF, byte[] byteCDA) throws IOException {

		final String fileName = "cda.xml";
		final String mimeType = "application/xml";

		final PDDocument document = PDDocument.load(new ByteArrayInputStream(bytePDF));

		final Map<String, PDComplexFileSpecification> embeddedFileMap = new HashMap<>();

		final PDEmbeddedFile embeddedFile = new PDEmbeddedFile(document, new ByteArrayInputStream(byteCDA));
		embeddedFile.setSubtype(mimeType);
		embeddedFile.setSize(byteCDA.length);

		final PDComplexFileSpecification fileSpecification = new PDComplexFileSpecification();
		fileSpecification.setFile(fileName);
		fileSpecification.setEmbeddedFile(embeddedFile);

		embeddedFileMap.put(fileName, fileSpecification);

		final PDEmbeddedFilesNameTreeNode efTree = new PDEmbeddedFilesNameTreeNode();
		efTree.setNames(embeddedFileMap);

		final PDDocumentNameDictionary names = new PDDocumentNameDictionary(document.getDocumentCatalog());
		names.setEmbeddedFiles(efTree);
		document.getDocumentCatalog().setNames(names);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		document.save(baos);
		document.close();
		return baos.toByteArray();
	}

	public static String extract(byte[] bytePDF) throws IOException {
		String out = null;
		try (PDDocument pd = PDDocument.load(bytePDF)) {
			PDDocumentCatalog catalog = pd.getDocumentCatalog();
			PDDocumentNameDictionary names = catalog.getNames();
			PDEmbeddedFilesNameTreeNode embeddedFiles = names.getEmbeddedFiles();
			Map<String, PDComplexFileSpecification> embeddedFileNames = embeddedFiles.getNames();
			for (Map.Entry<String, PDComplexFileSpecification> entry : embeddedFileNames.entrySet()) {  
				out = new String(entry.getValue().getEmbeddedFile().toByteArray());
			}
		}
		return out;
	}

	private static byte[] createSample() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (PDDocument doc = new PDDocument()) {
			PDPage myPage = new PDPage();
			doc.addPage(myPage);

			try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

				cont.beginText();
				cont.setFont(PDType1Font.TIMES_ROMAN, 12);
				cont.setLeading(14.5f);

				cont.newLineAtOffset(25, 700);
				cont.showText("This sample file is generated by \"FS2 PDF Maker (fpm)\" on: " + new Date());
				cont.newLine();
				cont.showText("Inspect the attachments, you should found a CDA XML file injected.");
				cont.endText();
			}
			doc.save(baos);
		}
		return baos.toByteArray();
	}

}
