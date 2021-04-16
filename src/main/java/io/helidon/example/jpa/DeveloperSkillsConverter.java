package io.helidon.example.jpa;

import io.netty.util.internal.StringUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Converter
public class DeveloperSkillsConverter implements AttributeConverter<ArrayList<String>, String> {
    @Override
    public String convertToDatabaseColumn(ArrayList<String> stringArrayList) {
        if(stringArrayList!=null && stringArrayList.size() > 0) {
            return String.join(",", stringArrayList);
        }
        else
            return null;
    }

    @Override
    public ArrayList<String> convertToEntityAttribute(String s) {
        if(!StringUtil.isNullOrEmpty(s))
        {
            return (ArrayList<String>) Arrays.stream(s.split(",")).collect(Collectors.toList());
        }
        else
            return null;
    }
}
