package com.pos.inventory.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	public static LocalDateTime convertStringDateTimeToLocalDateTime(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return LocalDateTime.parse(dateTime, formatter);
	}

	public static LocalDate convertStringDateTimeToLocalDate(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return LocalDate.parse(dateTime, formatter);
	}

	public static Date convertStringDateTimeToDate(String dateTime) {
		try {
			SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = sdfNew.parse(dateTime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static LocalDate convertStringDateToLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}
}
