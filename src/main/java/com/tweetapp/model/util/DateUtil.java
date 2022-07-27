package com.tweetapp.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date convertToDate(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");		//YYYY
		return df.parse(date);
	}

	public static String convertDateToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}
}
