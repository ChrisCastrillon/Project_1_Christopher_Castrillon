package com.revature.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseUtil {
	private static ObjectMapper om = new ObjectMapper();
	//use this to write to the body of a response
	public static void writeJSON(HttpServletResponse response, Object body) throws IOException {
		
		PrintWriter writer = response.getWriter();
		/*
		 * Notice how body is of type object? Arraylists are object types
		 * so this will work with printing the array of employees for example.
		 */
		//wraps the response to be able to be sent in an httpresponse
		//specifically as JSON
		writer.println(om.writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");
	}

}
