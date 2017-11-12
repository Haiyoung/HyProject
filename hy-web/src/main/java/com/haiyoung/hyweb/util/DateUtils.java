package com.haiyoung.hyweb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final String DatePattern = "yyyy-MM-dd";

	public static final String DateTimePattern = "yyyy-MM-dd HH:mm:ss";

	public static final int YEAR = 0;

	public static final int MONTH = 1;

	public static final int DAY = 2;

	public static Date parseDate(String source) {
		return parse(source, DatePattern);
	}

	public static Date parseDateTime(String source) {
		return parse(source, DateTimePattern);
	}

	public static Date parse(String source, String pattern) {
		if (StringUtils.isEmpty(source))
			return null;
		DateFormat format;
		try {
			format = new SimpleDateFormat(pattern);
		} catch (Exception e) {
			format = new SimpleDateFormat(DateTimePattern);
		}
		Date date;
		try {
			date = format.parse(source);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}

	public static String formatDate(Date date) {
		return formatDate(date, null);
	}

	public static String formatDate(Date date, WhiteSpaceType type) {
		return format(date, DatePattern, type);
	}

	public static String formatDateTime(Date date) {
		return formatDateTime(date, null);
	}

	public static String formatDateTime(Date date, WhiteSpaceType type) {
		return format(date, DateTimePattern, type);
	}

	public static String format(Date date, String pattern) {
		return format(date, pattern, null);
	}

	public static String format(Date date, String pattern, WhiteSpaceType type) {
		if (date == null)
			return type != null ? type.value() : WhiteSpaceType.BAR_STR.value();
		DateFormat format;
		try {
			format = new SimpleDateFormat(pattern);
		} catch (Exception e) {
			format = new SimpleDateFormat(DateTimePattern);
		}
		return format.format(date);
	}

	public static String getFormatBeforeOrAfterDate(int offset) {
		return getFormatBeforeOrAfterDay(offset, DatePattern);
	}

	public static String getFormatBeforeOrAfterDateTime(int offset) {
		return getFormatBeforeOrAfterDay(offset, DateTimePattern);
	}

	public static String getFormatBeforeOrAfterDay(int offset, String pattern) {
		return format(getBeforeOrAfterDay(offset), pattern);
	}

	public static String getFormatBeforeOrAfterDate(Date date, int offset) {
		return getFormatBeforeOrAfterDay(date, offset, DatePattern);
	}

	public static String getFormatBeforeOrAfterDateTime(Date date, int offset) {
		return getFormatBeforeOrAfterDay(date, offset, DateTimePattern);
	}

	public static String getFormatBeforeOrAfterDay(Date date, int offset, String pattern) {
		return format(getBeforeOrAfterDay(date, offset), pattern);
	}

	public static Date getBeforeOrAfterDay(int offset) {
		return getBeforeOrAfterDay(null, offset);
	}

	public static Date getBeforeOrAfterDay(Date date, int offset) {
		if (date == null)
			date = new Date();
		if (offset == 0)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, offset);
		return c.getTime();
	}

	public static int getYear() {
		return getYear(new Date());
	}

	public static int getMonth() {
		return getMonth(new Date());
	}

	public static int getDayOfWeek() {
		return getDayOfWeek(new Date());
	}

	public static int getDayOfMonth() {
		return getDayOfMonth(new Date());
	}

	public static int getDayOfYear() {
		return getDayOfYear(new Date());
	}

	public static int getYear(Date date) {
		return get(date, Calendar.YEAR);
	}

	public static int getSeason(Date date) {
		return getMonth(date) / 3 + 1;
	}

	public static int getMonth(Date date) {
		return get(date, Calendar.MONTH) + 1;
	}

	public static int getDayOfWeek(Date date) {
		int day = get(date, Calendar.DAY_OF_WEEK);
		return day == 0 ? 7 : get(date, Calendar.DAY_OF_WEEK) - 1;
	}

	public static int getDayOfMonth(Date date) {
		return get(date, Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfYear(Date date) {
		return get(date, Calendar.DAY_OF_YEAR);
	}

	private static Calendar getCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		if (date != null)
			c.setTime(date);
		return c;
	}

	private static int get(Date date, int field) {
		Calendar c = getCalendar(date);
		return c.get(field);
	}

	public static boolean compareDateTime(String s1, String s2) {
		return compare(parseDateTime(s1), parseDateTime(s2));
	}

	public static boolean compareDateTime(String s, Date date) {
		return compare(parseDateTime(s), date);
	}

	public static boolean compareDateTime(Date date, String s) {
		return compare(date, parseDateTime(s));
	}

	public static boolean compareDate(String s1, String s2) {
		return compare(parseDate(s1), parseDate(s2));
	}

	public static boolean compareDate(String s, Date date) {
		return compare(parseDate(s), date);
	}

	public static boolean compareDate(Date date, String s) {
		return compare(date, parseDate(s));
	}

	public static boolean compare(String s1, String pattern1, String s2, String pattern2) {
		return compare(parse(s1, pattern1), parse(s2, pattern2));
	}

	public static boolean compare(String s, String pattern, Date date) {
		return compare(parse(s, pattern), date);
	}

	public static boolean compare(Date date, String s, String pattern) {
		return compare(date, parse(s, pattern));
	}

	public static boolean compare(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			throw new IllegalArgumentException();
		return d1.after(d2);
	}

	public static int getIntervalDays(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / 86400000);
	}

	public static int diffDate(int type, Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		if (type == DateUtils.YEAR) {
			int i = 0;
			while (c1.compareTo(c2) <= 0) {
				c1.add(Calendar.YEAR, 1);
				i++;
			}
			return i - 1;
		} else if (type == DateUtils.MONTH) {
			int i = 0;
			while (c1.compareTo(c2) <= 0) {
				c1.add(Calendar.MONTH, 1);
				i++;
			}
			return i - 1;
		} else if (type == DateUtils.DAY) {
			return (int) ((c2.getTimeInMillis() - c1.getTimeInMillis()) / (24 * 3600 * 1000));
		} else {
			return 0;
		}
	}

	public static Date getFirstDateOfYear(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, getYear(today));
		return calendar.getTime();
	}

}
