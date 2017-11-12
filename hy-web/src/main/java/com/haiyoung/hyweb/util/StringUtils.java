package com.haiyoung.hyweb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static boolean isNotEmpty(String s) {
		return s != null && !s.trim().equals("");
	}

	public static boolean isEmpty(String s) {
		return s == null || s.trim().equals("");
	}

	public static boolean isNotNull(String s) {
		return s != null;
	}

	public static boolean isEqual(String s, Object o) {
		return s != null && s.equals(o);
	}

	public static String convert(String s) {
		if (s == null)
			return "";
		return s.replace("&", "&amp;").replace("'", "&apos;")
				.replace("\"", "&quot;").replace("<", "&lt;")
				.replace(">", "&gt;");
	}

	public static boolean match(String s, String p) {
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

}