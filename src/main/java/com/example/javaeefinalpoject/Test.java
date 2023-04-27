package com.example.javaeefinalpoject;

import com.example.javaeefinalpoject.util.Converter;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class Test {
    public static void main(String[] args) {
        Converter converter = new Converter();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(converter.convertToDatabaseColumn(localDateTime));
    }
}
