package com.connected.userpost.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.springframework.core.convert.converter.Converter;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
    }
}
