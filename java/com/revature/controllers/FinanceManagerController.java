package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.utils.ResponseUtil;

public class FinanceManagerController {
	private ReimbursementService reimbursementService = new ReimbursementService();
	public void process(HttpServletRequest request, HttpServletResponse response, String[] portions) throws IOException, ServletException {
		if(!portions[0].equals("financemanager")) {
			response.setStatus(400);
			return;
		}
		if(portions.length ==1) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String method = request.getMethod();
			
			if(method.equals("GET")) {
				List<Reimbursement> all = reimbursementService.findAllRembursements();
				ResponseUtil.writeJSON(response, all);
				return;
			}
		}
	}
}
