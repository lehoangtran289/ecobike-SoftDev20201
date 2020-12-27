package com.hust.ebr.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static final String DATE_FORMAT = "dd/MM/yyyy";

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

    /**
     * Check if date is both valid and exist or not
     * @param date
     * @return
     */
    public static boolean isDateValid(String date) {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);

        boolean valid = true;
        boolean isDatePassed = true;
        try {
            isDatePassed = isDatePassed(date);
            sdf.parse(date);
        } catch (ParseException e) {
            valid = false;
        }

        if (valid && isDatePassed) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Compare current date with the one that passed through
     * check if current date is larger than compared date -> true
     * else -> false
     * @param date compared date
     * @return
     * @throws ParseException
     */
    public static boolean isDatePassed(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date parse = sdf.parse(date);
        if (System.currentTimeMillis() > parse.getTime()) {
            return true;
        } else {
            return false;
        }

    }

}
