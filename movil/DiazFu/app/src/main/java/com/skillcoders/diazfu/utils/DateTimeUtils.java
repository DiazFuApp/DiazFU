package com.skillcoders.diazfu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by jvier on 04/09/2017.
 */

public class DateTimeUtils {

    public static Calendar getParseTimeFromSQL(String myDate) {
        Calendar myCalendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.MASK_DATE_FROM_SQL_YMD, Locale.ROOT);

        try {
            myCalendar.setTime(sdf.parse(myDate.replace("T", " ")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return myCalendar;
    }

    public static String getParseTimeToSQL(Calendar myCalendar) {
        String myFormat = Constants.MASK_DATE_TO_SQL_YMD; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ROOT);
        return sdf.format(myCalendar.getTime());
    }

}
