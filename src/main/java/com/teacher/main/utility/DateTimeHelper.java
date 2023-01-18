package com.teacher.main.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeHelper {

	public static Date getCurrentDateTime() {

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		String timezone = "UTC";
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		Date dateObj = new Date();
		try {
			String sdate = sdf.format(dateObj);
			return sdf.parse(sdate);
		} catch (ParseException e) {
			return new Date();
		}

//        DateFormat sdf = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy");
//        String timezone = "Asia/Kolkata";
//
//        sdf.setTimeZone(TimeZone.getTimeZone(timezone));
//        Date dateObj = new Date();
//        try {
//            String sdate = sdf.format(dateObj);
//            String result[] = sdate.split(" ");
//            sdate = sdate.replaceAll(result[result.length - 2], "UTC");
//            DateFormat sdfUTC = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy");
//            sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
//            return sdfUTC.parse(sdate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
		// return null;
	}
}
