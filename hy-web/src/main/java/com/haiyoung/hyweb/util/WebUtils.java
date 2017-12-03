package com.haiyoung.hyweb.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

public class WebUtils {

	public static boolean isFormSubmission(HttpServletRequest request) {
		return request != null && "POST".equals(request.getMethod());
	}

	public static Object getSessionAttribute(HttpServletRequest request,
			String name) {
		if (request == null || StringUtils.isEmpty(name))
			return null;
		HttpSession session = request.getSession(false);
		return (session != null ? session.getAttribute(name) : null);
	}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		if (request == null || StringUtils.isEmpty(name))
			return null;
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}

	public static String getCurrentUrl(HttpServletRequest request) {
		return getCurrentUrl(request, false);
	}

	public static String getCurrentUrl(HttpServletRequest request,
			boolean enableBase64Encode) {
		if (request == null)
			return null;
		String url = request.getRequestURL().toString();
		String query = request.getQueryString();
		if (query != null)
			url = url + "?" + query;
		if (enableBase64Encode)
			return Base64.encodeBase64URLSafeString(url.getBytes());
		return url;
	}
	
	/**
	 * 获取系统访问路径
	 *
	 * @param request
	 *            请求对象
	 * @return 路径
	 */
	public static String getAccessPath(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/");
		return sb.toString();
	}

}
