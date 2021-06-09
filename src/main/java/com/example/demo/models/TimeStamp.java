package com.example.demo.models;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TimeStamp {

    private static final String TIME_STAMP_PATTERN = "yyyy-MM-dd hh:mm:ss:SSS";

    public static String currentUTCTime(){
        ZoneId UTC_TIME_ZONE =ZoneId.from(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(TIME_STAMP_PATTERN)
                .withZone(UTC_TIME_ZONE);
        String date = formatter.format(Instant.now());
        return convertToISO(date);
    }

    private static String convertToISO(String date) {
        return date.replace(" ", "T") + "Z";
    }
}
