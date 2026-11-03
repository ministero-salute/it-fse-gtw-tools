package it.finanze.sanita.gva;

import static org.junit.jupiter.api.Assertions.*;

import it.finanze.sanita.gva.dto.ErrorUCTDTO;
import it.finanze.sanita.gva.enums.EsameEnum;
import it.finanze.sanita.gva.enums.LABErrorEnum;
import it.finanze.sanita.gva.validator.ValidationXML;
import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;

@Tag("ISOLATION")
class IsolationTest {
    @TestFactory
    Stream<DynamicTest> everyAccreditationCaseReportsMissingCdaFields() {
        return Arrays.stream(EsameEnum.values()).map(exam -> DynamicTest.dynamicTest(exam.name(), () -> {
            assertSame(exam, EsameEnum.getCode(exam.getIdTest()));
            assertNotNull(exam.getTipoDocumento());
            @SuppressWarnings("unchecked")
            List<ErrorUCTDTO> errors = (List<ErrorUCTDTO>) exam.getValidator().getMethod(exam.getMethodName(), String.class)
                    .invoke(null, "<ClinicalDocument/>");
            assertFalse(errors.isEmpty(), "Missing mandatory CDA sections must be reported");
            for (ErrorUCTDTO error : errors) {
                assertNotNull(error.getErroEnum());
                assertNotNull(error.getErroEnum().getCode());
                assertNotNull(error.getXPath());
            }
        }));
    }

    @Test
    void validationAcceptsCaseInsensitiveMatchesAndRejectsInvalidValues() {
        List<ErrorUCTDTO> errors = new ArrayList<>();
        it.finanze.sanita.gva.validator.IUC code = LABErrorEnum.values()[0];
        String xml = "<ClinicalDocument><code code='VALID'/><title>Title</title><time value='20260101123000'/></ClinicalDocument>";
        ValidationXML.equalIC(errors, code, xml, "code", "code", "valid");
        ValidationXML.containsIC(errors, code, xml, "code", "code", "valid");
        ValidationXML.equalTAG(errors, code, xml, "title", "title");
        ValidationXML.hasDateFormat(errors, code, xml, "time", "value");
        assertTrue(errors.isEmpty());
        ValidationXML.equalIC(errors, code, xml, "code", "code", "wrong");
        ValidationXML.containsIC(errors, code, xml, "code", "code", "wrong");
        ValidationXML.equalTAG(errors, code, xml, "title", "wrong");
        ValidationXML.hasDateFormat(errors, code, xml, "missing", "value");
        assertEquals(4, errors.size());
        assertNull(EsameEnum.getCode(-1));
    }
}
