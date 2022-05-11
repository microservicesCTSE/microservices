package com.pos.inventory.service.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * contains utility methods for date time
 * 
 * @author Thilina Madhusanka
 *
 */
public class DateTimeUtil {

	/**
	 * return Instant time stamp for ripple date ranges
	 * 
	 * @param date
	 * @return Instant timestamp
	 */
	public Instant getInstantTimeStamp(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date newDate = sdf.parse(date);
			Instant timestamp = newDate.toInstant();
			return timestamp;
		} catch (Exception e) {
			
			return null;
		}
	}

	/**
	 * return sql time stamp
	 * 
	 * @param dateToConvert
	 * @return java.sql.Timestamp
	 */
	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		if (dateToConvert != null) {
			return new java.sql.Timestamp(dateToConvert.getTime()).toLocalDateTime();
		}
		return null;
	}

	/**
	 * @param dateToConvert
	 * @param daysToAdd
	 * @return
	 */
	public static Date convertToLocalDateTimeViaInstant(Date dateToConvert, Integer daysToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateToConvert);
		cal.add(Calendar.DATE, daysToAdd);
		return cal.getTime();
	}

	/**
	 * @param dateToConvert
	 * @param daysToAdd
	 * @return
	 */
	public static Date convertToLocalDateTimeViaInstantByWeek(Date dateToConvert, Integer daysToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateToConvert);		
		cal.add(Calendar.WEEK_OF_YEAR, daysToAdd);		
		return cal.getTime();
	}
	
	/**
	 * @param dateToConvert
	 * @param monthToAdd
	 * @return
	 */
	public static Date convertToLocalDateTimeViaInstantByMonth(Date dateToConvert, Integer monthToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateToConvert);
		cal.add(Calendar.MONTH, monthToAdd);
		return cal.getTime();
	}
	
	public static Date convertToLocalDateTimeViaInstantByYear(Date dateToConvert, Integer yearToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateToConvert);
		cal.add(Calendar.YEAR, yearToAdd);
		return cal.getTime();
	}

	/**
	 * return Util date from localdatetime
	 * @param localDateTime
	 * @return Date
	 */
	public static Date getUtilDate(LocalDateTime localDateTime) {
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return new Date().from(instant);
	}
	
	public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate convertToLocalDateFromUtilDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
