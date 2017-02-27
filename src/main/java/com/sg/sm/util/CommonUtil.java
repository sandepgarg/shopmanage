package com.sg.sm.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sg.sm.config.URLConstants;

public class CommonUtil {
	
	public static HttpServletRequest getHttpRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static HttpHeaders getErrHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(URLConstants.HEADER_CONTENT_TYPE, URLConstants.GENERIC_ERROR_MEDIA_TYPE);
		return headers;
	}
	
	public static String getBaseURL() {
		HttpServletRequest request = getHttpRequest();
		StringBuffer requestURL = request.getRequestURL();
		String requestURI = request.getRequestURI();
		String baseURL = requestURL.substring(0,requestURL.indexOf(requestURI));
		return baseURL + request.getContextPath();
	}
}
