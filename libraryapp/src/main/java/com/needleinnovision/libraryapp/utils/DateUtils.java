package com.needleinnovision.libraryapp.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date getFutureDate(int units) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, units);
		return now.getTime();
	}
}
