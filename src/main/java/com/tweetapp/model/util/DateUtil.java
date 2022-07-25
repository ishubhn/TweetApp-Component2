package com.tweetapp.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Not used as of now (trying Jackson approach)
public class DateUtil {
	public static Date convertToDate(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");		//YYYY
		return df.parse(date);
	}
}
