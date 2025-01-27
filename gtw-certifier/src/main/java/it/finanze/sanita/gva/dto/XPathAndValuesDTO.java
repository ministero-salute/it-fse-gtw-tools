package it.finanze.sanita.gva.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class XPathAndValuesDTO {

    private String specificXPath;
    private String attribute;
    private List<String> attributeValues;

    public static XPathAndValuesDTO buildPathValue(String specificXPath, String attribute, String... values){
        return new XPathAndValuesDTO(
                specificXPath,
                attribute,
                Arrays.asList(values)
        );

    }

    @Override
    public String toString() {
        return "XPathAndValuesDTO{" +
                "specificXPath='" + specificXPath + '\'' +
                ", attribute='" + attribute + '\'' +
                ", attributeValues=" + attributeValues +
                '}';
    }
}