package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.repositories.IReimbursementDAO;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.ReimbursementService;
import com.revature.utils.RequestUtil;
import com.revature.utils.ResponseUtil;

public class ReimbursementController {
	private IReimbursementDAO reimbursementDAO = new ReimbursementDAO();
	private ObjectMapper om = new ObjectMapper();
	private ReimbursementService reimbursementService = new ReimbursementService();
	public void process(HttpServletRequest request, HttpServletResponse response, String[] portions) throws IOException, ServletException{
		
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
			if(method.equals("GET")) {
				//this will be used to retrieve all submissions --> to populate the 
				//sbumissions section
				System.out.println(method);
				List<Reimbursement> all = reimbursementService.findAllRembursements();
				ResponseUtil.writeJSON(response, all);
				return;
			}
			if(method.equals("POST")) {
				//this will be used to create a new reimbursment and add it to the database:
				System.out.println(method);
				//extract the information from the request:
				//obtain the body:
				String body = RequestUtil.readBody(request);
				
				Reimbursement r = om.readValue(body, Reimbursement.class);
			
				//Jackson Databind object mappper uses reflection to verify that the variable 
				//names match the keys of the JSON and the types of the inputs match the types on the class
				r = reimbursementService.submitReimbursement(r);
				//this return ths reimbursement as a JSON
				ResponseUtil.writeJSON(response, r);
				return;
			}
		
			
		}
		
	}
}
