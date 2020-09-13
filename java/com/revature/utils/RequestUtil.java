package com.revature.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	public static String readBody(HttpServletRequest request) throws IOException, ServletException {
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		/**
		 * String line;
		 * while((line = reader.readLine()) != null) {
		 * 	sb.append(line);
		 * }
		 * String body = sb.toString();
		 */
		String body = reader.lines().collect(Collectors.joining());
		return body;
	}
}
