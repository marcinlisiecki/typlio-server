package com.example.typlioserver.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class IntArrayToStringConverter implements AttributeConverter<List<Integer>, String> {

    @Override
    public String convertToDatabaseColumn(List<Integer> integers) {
        return integers == null ? null : StringUtils.join(integers, ",");
    }

    @Override
    public List<Integer> convertToEntityAttribute(String s) {
        if (StringUtils.isEmptyOrWhitespace(s)) {
            return Collections.emptyList();
        }

        return Arrays
                .stream(s.split(","))
                .map(Integer::parseInt)
                .toList();
    }
}
