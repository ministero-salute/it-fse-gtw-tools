package it.finanze.sanita.gva.dto;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class SectionCriteriaDTO {

    private String templateId;
    private XPathAndValuesDTO[] values;

    public SectionCriteriaDTO(String templateId, XPathAndValuesDTO... values){
        this.templateId = templateId;
        this.values = values;
    }

    public static SectionCriteriaDTO buildSection(String templateId, XPathAndValuesDTO... values){
        return new SectionCriteriaDTO(templateId, values);
    }

    @Override
    public String toString() {
        return "SectionCriteriaDTO{" +
                "templateId='" + templateId + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}