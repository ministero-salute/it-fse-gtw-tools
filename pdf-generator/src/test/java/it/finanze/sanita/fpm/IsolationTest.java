package it.finanze.sanita.fpm;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

@Tag("ISOLATION")
class IsolationTest {
    @TempDir Path temp;

    @AfterEach
    void reset() {
        Launcher.pathFilePDF = null; Launcher.pathFileCDA = null; Launcher.pathP12SignPDF = null;
        Launcher.pwdP12SignPDF = null; Launcher.pathOutput = null;
        Launcher.flagMalformedInput = false; Launcher.flagNeedHelp = false;
        Launcher.flagVerbose = false; Launcher.flagValidation = false;
    }

    @Test
    void generatedPdfPreservesCdaAttachment() throws Exception {
        String cda = "<ClinicalDocument>test</ClinicalDocument>";
        byte[] pdf = Launcher.getPdf(cda.getBytes());
        assertEquals(cda, Launcher.extract(pdf));
        assertEquals("replacement", Launcher.extract(Launcher.inject(pdf, "replacement".getBytes())));
        Path source = Files.write(temp.resolve("source.pdf"), pdf);
        Path xml = Files.write(temp.resolve("cda.xml"), cda.getBytes());
        for (boolean sample : new boolean[]{true, false}) {
            reset();
            Path output = temp.resolve("output" + sample + ".pdf");
            java.util.List<String> args = new java.util.ArrayList<>(java.util.Arrays.asList("-c", xml.toString(), "-o", output.toString(), "-v", "-x"));
            if (!sample) args.addAll(java.util.Arrays.asList("-p", source.toString()));
            Launcher.main(args.toArray(new String[0]));
            assertEquals(cda, Launcher.extract(Files.readAllBytes(output)));
        }
    }

    @Test
    void malformedCliAndFilesystemFailuresAreContained() throws Exception {
        for (String[] args : new String[][]{{"-h"}, {"unknown"}, {"-c"}, {}}) {
            reset(); assertDoesNotThrow(() -> Launcher.main(args));
        }
        assertTrue(Utility.nullOrEmpty(null)); assertTrue(Utility.nullOrEmpty("")); assertFalse(Utility.nullOrEmpty("x"));
        assertNull(Utility.getFileFromFS(temp.resolve("missing").toString()));
        Path output = temp.resolve("bytes");
        Utility.saveToFile("data".getBytes(), output.toString());
        assertArrayEquals("data".getBytes(), Utility.getFileFromFS(output.toString()));
        assertThrows(RuntimeException.class, () -> Utility.saveToFile(null, null));
        assertThrows(RuntimeException.class, () -> SignerHelper.getSP(new byte[0], new char[0]));
        assertThrows(RuntimeException.class, () -> SignerHelper.pades(null, null, new byte[0]));
        java.util.Set<ArgumentEnum> valueArguments = java.util.EnumSet.of(ArgumentEnum.FILE_CDA, ArgumentEnum.FILE_PDF,
                ArgumentEnum.FILE_OUTPUT, ArgumentEnum.PATH_SIGN_P12_PDF, ArgumentEnum.PWD_SIGN_P12_PDF);
        for (ArgumentEnum arg : ArgumentEnum.values()) {
            assertSame(arg, ArgumentEnum.getByKey(arg.getKey())); assertNotNull(arg.getDescription());
            assertEquals(valueArguments.contains(arg), arg.getFlagHasValue(), arg.name());
        }
        assertNull(ArgumentEnum.getByKey("missing"));
    }

    @Test
    void cliSignsGeneratedPdf() throws Exception {
        org.apache.pdfbox.signature.SignatureIsolationTest.keys();
        Path key = Files.write(temp.resolve("key.p12"), org.apache.pdfbox.signature.SignatureIsolationTest.p12);
        Path cda = Files.write(temp.resolve("cda.xml"), "<ClinicalDocument/>".getBytes());
        Path pdf = temp.resolve("signed.pdf");
        Launcher.main(new String[]{"-c", cda.toString(), "-s", key.toString(), "-pwd", "secret", "-o", pdf.toString()});
        try (org.apache.pdfbox.pdmodel.PDDocument document = org.apache.pdfbox.pdmodel.PDDocument.load(Files.readAllBytes(pdf))) {
            assertEquals(1, document.getSignatureDictionaries().size());
        }
        assertEquals("<ClinicalDocument/>", Launcher.extract(Files.readAllBytes(pdf)));
    }
}
