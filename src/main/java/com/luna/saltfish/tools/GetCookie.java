package com.luna.saltfish.tools;

import javax.servlet.http.Cookie;

/**
 * @author luna@mac
 */
public class GetCookie {
	public static String getCookie(Cookie[] cookies, String cookiename) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookiename)) {
				String cookieValue = cookie.getValue();
				return cookieValue;
			}
		}
		return null;
	}
}
