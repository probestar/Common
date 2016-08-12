package com.probestar.psutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class PSDate {

    public static synchronized String date2String(long date, String format) {
        return date2String(new Date(date), format);
    }

    public static synchronized String date2String(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        return formatter.format(date);
    }

    public static synchronized Date string2Date(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        return formatter.parse(date);
    }

    public static synchronized Date string2Date(String date, ArrayList<String> formats) throws ParseException {
        for (String format : formats) {
            try {
                return string2Date(date, format);
            } catch (ParseException e) {
            }
        }
        throw new ParseException("no format is fit. date: " + date + "; formats: " + formats.toString(), 0);
    }
}