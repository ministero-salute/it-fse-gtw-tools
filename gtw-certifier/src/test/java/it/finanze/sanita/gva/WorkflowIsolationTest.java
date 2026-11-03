package it.finanze.sanita.gva;

import static org.junit.jupiter.api.Assertions.*;
import it.finanze.sanita.gva.core.DataProcessor;
import it.finanze.sanita.gva.dto.ProcessResDTO;
import it.finanze.sanita.gva.utility.*;
import java.nio.file.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

@Tag("ISOLATION")
class WorkflowIsolationTest {
    @TempDir Path temp;

    @Test void filesystemAndResultCollection() throws Exception {
        java.util.logging.Logger log = LogUtility.getLogger(WorkflowIsolationTest.class);
        assertFalse(FilesUtility.isUniqueMatch(Collections.emptyList(), "test", log));
        assertFalse(FilesUtility.isUniqueMatch(Arrays.asList(temp, temp), "test", log));
        assertTrue(FilesUtility.isUniqueMatch(Collections.singletonList(temp), "test", log));
        assertTrue(FilesUtility.isNullOrEmpty(null)); assertTrue(FilesUtility.isNullOrEmpty("")); assertFalse(FilesUtility.isNullOrEmpty("a"));
        ProcessResDTO result = new ProcessResDTO(); result.success(1); result.unverifiable(2); result.failure(3, "first"); result.failure(3, "second");
        assertTrue(result.getSuccess().contains(1)); assertTrue(result.getUnverifiable().contains(2)); assertEquals("first", result.getFailures().get(3)); result.print(log);
        new DataProcessor(temp.toFile()).checkAndprocess();
        Path files = Files.createDirectory(temp.resolve("FILES"));
        Path json = temp.resolve("data.json");
        for (String data : new String[]{"{}", "{\"results\":[]}", "invalid", "{\"results\":[{\"id\":999,\"files\":[\"sample.pdf\"]}]}"}) {
            Files.write(json, data.getBytes()); new DataProcessor(temp.toFile()).checkAndprocess();
        }
        Files.write(files.resolve("sample.pdf"), "invalid".getBytes());
        for (String data : new String[]{"{\"results\":[{\"id\":999,\"files\":[\"sample.pdf\"]}]}", "{\"results\":[{\"id\":1,\"files\":[\"missing.pdf\",\"sample.pdf\"]}]}"}) {
            Files.write(json, data.getBytes()); new DataProcessor(temp.toFile()).checkAndprocess();
        }
    }

    @Test void invalidPdfAndCharsetFallback() {
        assertFalse(PDFUtility.isPdf(null)); assertFalse(PDFUtility.isPdf(new byte[0])); assertFalse(PDFUtility.isPdf("other".getBytes())); assertTrue(PDFUtility.isPdf("%PDF-".getBytes()));
        assertTrue(PDFUtility.extractAttachments(new byte[0]).isEmpty()); assertNull(PDFUtility.extractCDAFromAttachments(new byte[0])); assertNull(PDFUtility.unenvelopeA2(new byte[0]));
        assertEquals("test", PDFUtility.detectCharsetAndExtract("test".getBytes()));
        String xml = "<?xml version='1.0' encoding='UTF-8'?><ClinicalDocument/>";
        assertEquals(xml, PDFUtility.detectCharsetAndExtract(xml.getBytes(java.nio.charset.StandardCharsets.UTF_8)));
    }

    @Test void cliUsageAndUnknownArguments() throws Exception {
        it.finanze.sanita.gva.cli.CliParser parser = new it.finanze.sanita.gva.cli.CliParser();
        assertFalse(parser.isInit()); assertNotNull(parser.getArgs()); parser.usage(); parser.version(); parser.parse("--unknown"); parser.unknown();
        parser = new it.finanze.sanita.gva.cli.CliParser();
        parser.parse("-dir", temp.toString()); assertTrue(parser.isInit()); assertEquals(temp.toFile(), parser.getArgs().getDir());
        assertFalse(parser.getArgs().anyUnknownParam());
        Path file = Files.write(temp.resolve("not-a-directory"), new byte[0]);
        parser = new it.finanze.sanita.gva.cli.CliParser(); parser.parse("-dir", file.toString()); assertFalse(parser.isInit());
        Launcher.main(new String[]{"--help"});
    }
}
