package com.dali.dali.domain.community.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TimeConverter implements AttributeConverter<Time, String> {

    @Override
    public String convertToDatabaseColumn(Time attribute) {
        return attribute != null ? attribute.getTime() : null;
    }

    @Override
    public Time convertToEntityAttribute(String dbData) {
        for (Time time : Time.values()) {
            if (time.getTime().equals(dbData)) {
                return time;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
