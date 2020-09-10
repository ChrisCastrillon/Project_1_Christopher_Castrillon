package com.revature.controllers;
//this will contain helper methods for processing requests in regards to Employees

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;
import com.revature.services.EmployeeService;
import com.revature.utils.ResponseUtil;

public class EmployeeController {
	private IEmployeeDAO employeeDAO = new EmployeeDAO();
	private ObjectMapper om = new ObjectMapper();
	private EmployeeService employeeService = new EmployeeService();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//because the process emthod doesn't regard teh HTTP verb, you should check the VERB to figure
			//out what to do
			//or you could have different methods for differeny vers like a processGET or processPUT
			//if a length 1, then it has to be a get request, and update request, or a put request
			String method = request.getMethod();
			
			//Following RESTful URI patterns, with only 1 portion, the request can only be GET, POST, or PUT
			//DELETE generally must include the ID in the URI
			if(method.equals("GET")) { //finding all employees
				System.out.println(method);
				List<Employee> all = employeeService.findAllEmployees();
				ResponseUtil.writeJSON(response, all);
				//sends back json of all employees --> you then need to extract this info to
				//dynamically place this under the submission form
				return;
			}
		}
	}
	
}
