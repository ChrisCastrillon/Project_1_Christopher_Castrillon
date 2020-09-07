package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static ObjectMapper om = new ObjectMapper();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username is: " + username);
		System.out.println("password is: " + password);
//		request.getRequestDispatcher("/static/Submissions.html").forward(request, response);
		
		if (username == null || password == null || username.equals("") || password.equals("")) {
			response.setStatus(400);
			return;
		}
		if (username.equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", username);
			response.setStatus(200);
			
			PrintWriter writer = response.getWriter();
			//you could use jackson databind to send back an emloyee object --> this is what you will do to populate the submission list
			//for both the employee and the FM
			writer.println(om.writeValueAsString(username));
			System.out.println("should change page now");
//			request.getRequestDispatcher("/static/Submissions.html").forward(request, response);
		}
		else {
			//if the user fails to log in
			response.setStatus(401);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}
