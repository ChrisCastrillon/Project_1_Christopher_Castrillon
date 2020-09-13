package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementFormHelper;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;
import com.revature.repositories.IReimbursementDAO;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.EmployeeService;
import com.revature.services.ReimbursementService;
import com.revature.utils.RequestUtil;
import com.revature.utils.ResponseUtil;

public class ReimbursementController {
	private IReimbursementDAO reimbursementDAO = new ReimbursementDAO();
	private IEmployeeDAO employeeDAO = new EmployeeDAO();
	private ObjectMapper om = new ObjectMapper();
	private ReimbursementService reimbursementService = new ReimbursementService();
	private EmployeeService employeeService = new EmployeeService();
	private static Logger log = Logger.getLogger(ReimbursementController.class);
	
	public void process(HttpServletRequest request, HttpServletResponse response, String[] portions) throws IOException, ServletException{
		String username = request.getParameter("username");
		System.out.println("the username for the get request is : " + username);
		
		if(!portions[0].equals("reimbursements")) {
			response.setStatus(400);
			return;
		}
		if(portions.length == 1) {
			String method = request.getMethod();
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if(!method.equals("GET") || !method.equals("POST") || !method.equals("PUT")) {
				//throw an exception
			}
			if(method.equals("GET") & (username == null)) {
				//this will be used to retrieve all submissions --> to populate the 
				//sbumissions section
				System.out.println(method);
				
				System.out.println("the get method for all reimbursements is being called");
				List<Reimbursement> all = reimbursementService.findAllRembursements();
				ResponseUtil.writeJSON(response, all);
				return;
			}
			if(method.equals("GET") && (username != null)) {
				log.info("attempting to get all reimbursements for user : " + username);
				List<Reimbursement> allForEmployee = reimbursementService.findAllReimbursementsByUsername(username);
				ResponseUtil.writeJSON(response, allForEmployee);
			}
			if(method.equals("POST")) {
				System.out.println(method);
				String body = RequestUtil.readBody(request);
				System.out.println("the body of the request is: " + body);
				ReimbursementFormHelper rfh = om.readValue(body, ReimbursementFormHelper.class);
				System.out.println("The form that is submitted it: " + rfh.toString());
				Reimbursement r = reimbursementService.reimbursementFormToReimbursement(rfh);
				
				//Jackson Databind object mappper uses reflection to verify that the variable 
				//names match the keys of the JSON and the types of the inputs match the types on the class
				r = reimbursementService.submitReimbursement(r);
				ResponseUtil.writeJSON(response, r);
				return;
			}	
		}
		if(portions.length == 2) {
			String method = request.getMethod();
			System.out.println("You are asking for the list of all the employees");
			//find all reimbursements for the user
		}
		
		
	}
}
