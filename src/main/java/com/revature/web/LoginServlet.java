package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.EmployeeService;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static ObjectMapper om = new ObjectMapper();
	private static EmployeeService es = new EmployeeService();
    private static Logger log = Logger.getLogger(LoginServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username is: " + username + " " + username.length());
		System.out.println("password is: " + password);
//		request.getRequestDispatcher("/static/Submissions.html").forward(request, response);
		try {
			log.info("The login servlet is attempting to connnect to the database");
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (username == null || password == null || username.equals("") || password.equals("")) {
			response.setStatus(400);
			return;
		}
		if (username.equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", username);
			log.info("A sesssion has been set for user " + username);
			response.setStatus(200);
			
			PrintWriter writer = response.getWriter();
			//you could use jackson databind to send back an emloyee object --> this is what you will do to populate the submission list
			//for both the employee and the FM
			writer.println(om.writeValueAsString(username));
			System.out.println("should change page now");
			response.setContentType("application/json");
			return;
//			request.getRequestDispatcher("/static/Submissions.html").forward(request, response);
		}
		else {
			//if the user fails to log in
			response.setStatus(401);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}
