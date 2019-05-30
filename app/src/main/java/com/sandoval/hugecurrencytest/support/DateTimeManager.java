package com.sandoval.hugecurrencytest.support;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeManager {

    public static final DateTimeFormatter HNB_DATE = DateTimeFormat.forPattern("YYYY-MM-dd");
    public static final DateTimeFormatter CHART_DATE = DateTimeFormat.forPattern("dd.MM.YYYY");

    public static String parseFromDate(LocalDate date , DateTimeFormatter format){

        return  format.print(date);
    }
}
