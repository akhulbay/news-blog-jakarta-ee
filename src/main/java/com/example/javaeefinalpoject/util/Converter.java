package com.example.javaeefinalpoject.util;

import jakarta.persistence.AttributeConverter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Converter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return Timestamp.valueOf(attribute);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        if (dbData != null) {
            return dbData.toLocalDateTime();
        }
        return null;
    }
}
