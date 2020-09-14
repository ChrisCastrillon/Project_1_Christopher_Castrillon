package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.LoginFormHelper;
import com.revature.services.EmployeeService;
import com.revature.utils.ResponseUtil;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ObjectMapper om = new ObjectMapper();
	private static EmployeeService es = new EmployeeService();
    private static Logger log = Logger.getLogger(LoginServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		System.out.println("username is: " + username + " " + username.length());
//		System.out.println("password is: " + password);
////		request.getRequestDispatcher("/static/Submissions.html").forward(request, response);
//
//		if (username == null || password == null || username.equals("") || password.equals("")) {
//			response.setStatus(400);
//			return;
//		}
//		
//		Employee e = es.checkLogin(username);
//
//		if (e != null) {
//			HttpSession session = request.getSession();
//			
//			session.setAttribute("currentUser", e);
//			log.info("A session has been set for user " + username);
//			response.setStatus(200);
//			
//			PrintWriter writer = response.getWriter();
//			//you could use jackson databind to send back an emloyee object --> this is what you will do to populate the submission list
//			//for both the employee and the FM
//			writer.println(om.writeValueAsString(e));
//
//			response.setContentType("application/json");
//			return;
////			request.getRequestDispatcher("/static/Submissions.html").forward(request, response);
//		}
//		else {
//			//if the user fails to log in
//			response.setStatus(401);
//			return;
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = request.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));
		System.out.println(body);
		LoginFormHelper lfh = om.readValue(body, LoginFormHelper.class);
		Employee e = es.checkLogin(lfh.getUsername(),lfh.getPassword());
		if (e != null) {
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", e);
			log.info("A session has been set for user " + e.getUsername());
			ResponseUtil.writeJSON(response, e);
			return;
			
		}
		else {
			//if the user fails to log in
			response.setStatus(401);
			return;
		}
		
		// do the rest
	}

}
