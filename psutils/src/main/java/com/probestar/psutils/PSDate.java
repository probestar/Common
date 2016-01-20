package com.probestar.psutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class PSDate {
	private static ConcurrentHashMap<String, SimpleDateFormat> _formatters;

	static {
		_formatters = new ConcurrentHashMap<String, SimpleDateFormat>(5, 0.8f, 2);
	}

	public static String date2String(long date, String format) {
		return date2String(new Date(date), format);
	}

	public static String date2String(Date date, String format) {
		SimpleDateFormat formatter = _formatters.get(format);
		if (formatter == null)
			_formatters.put(format, formatter = new SimpleDateFormat(format, Locale.ENGLISH));
		return formatter.format(date);
	}

	public static Date string2Date(String date, String format) throws ParseException {
		SimpleDateFormat formatter = _formatters.get(format);
		if (formatter == null)
			_formatters.put(format, formatter = new SimpleDateFormat(format, Locale.ENGLISH));
		return formatter.parse(date);
	}

	public static Date string2Date(String date, ArrayList<String> formats) throws ParseException {
		for (String format : formats) {
			try {
				return string2Date(date, format);
			} catch (ParseException e) {
			}
		}
		throw new ParseException("no format is fit. date: " + date + "; formats: " + formats.toString(), 0);
	}
}