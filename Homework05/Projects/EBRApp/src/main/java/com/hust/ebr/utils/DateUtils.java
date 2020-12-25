package com.hust.ebr.utils;

import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(TimeZone.getTimeZone("UTC").toZoneId())
                .toLocalDate()
                .plusDays(1L);
    }

    public static Date convertToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(TimeZone.getTimeZone("UTC").toZoneId())
                .toInstant());
    }

    public static Date newDateToSave(Date date) {
        LocalDate localDate = convertToLocalDate(date);
        return convertToDate(localDate);
    }
}
