package com.kuznetsov.linoleum.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

public final class LocalDateTimeFormatter {
    private static final String PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDateTime format(String date){
        return LocalDateTime.parse(date,DateTimeFormatter.ISO_DATE_TIME);
    }

    public static boolean isValid(String date){
        try {
            format(date);
            return Optional.ofNullable(date).map(LocalDateTimeFormatter::format)
                    .isPresent();
        }catch (DateTimeParseException e){
            return false;
        }
    }
}
