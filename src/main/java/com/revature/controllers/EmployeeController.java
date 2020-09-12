package com.revature.controllers;
//this will contain helper methods for processing requests in regards to Employees

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.LoginFormHelper;
import com.revature.models.Role;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;
import com.revature.services.EmployeeService;
import com.revature.utils.RequestUtil;
import com.revature.utils.ResponseUtil;

public class EmployeeController {
	private IEmployeeDAO employeeDAO = new EmployeeDAO();
	private ObjectMapper om = new ObjectMapper();
	private EmployeeService employeeService = new EmployeeService();
	private static Logger log = Logger.getLogger(EmployeeController.class);
	public void process(HttpServletRequest request, HttpServletResponse response, String[] portions) throws IOException, ServletException {
		if(!portions[0].equals("employees")) {
			//throw an exception
			response.setStatus(400);
			return;
		}
		if(portions.length == 1) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				log.info("Could not connect to the server");
				e.printStackTrace();
			}
			
			String method = request.getMethod();
	
			if(method.equals("GET")) { 
				System.out.println(method);
				List<Employee> all = employeeService.findAllEmployees();
				ResponseUtil.writeJSON(response, all);
				//sends back json of all employees --> you then need to extract this info to
				//dynamically place this under the submission form
				return;
				
			}
			if(method.equals("POST")) {
				log.info("a post request was submitted to the employee controller");
				
				String body = RequestUtil.readBody(request);
				LoginFormHelper lfh = om.readValue(body, LoginFormHelper.class);
				
				if(employeeService.checkLogin(lfh.getUsername())!=null) {
					Employee employee = new Employee();
					response.setStatus(200);
					employee = employeeDAO.findOneEmployeeByUsername(lfh.getUsername());
					Role role = employee.getRole();
					System.out.println(role.getName());

					String json = om.writeValueAsString(role);
					System.out.println(json);
					response.setStatus(200);
					response.getWriter().write(json);
					System.out.println(response);
					response.getWriter().flush();
					response.getWriter().close();

//					ResponseUtil.writeJSON(response, role);
				}
				response.setStatus(401);
				return;	
			}
		}
	}
	
}
