package com.nab.currencyenquiry.util;

import com.nab.currencyenquiry.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateValidator {

    private DateValidator() {
    }

    /**
     * This method converts the ISO_LOCAL_DATE formatted string (eg:- 2018-05-07 ) to LocalDate
     *
     * @param dateStr
     * @return
     * @throws BadRequestException
     */
    public static LocalDate validateAndConvertStringToDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            log.error(e.getMessage());
            throw new BadRequestException("date is not in ISO_LOCAL_DATE format");
        }
    }

}
