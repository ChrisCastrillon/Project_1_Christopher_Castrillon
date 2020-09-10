package com.revature.services;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.repositories.IReimbursementDAO;
import com.revature.repositories.ReimbursementDAO;


public class ReimbursementService {
	private IReimbursementDAO reimbursementDAO = new ReimbursementDAO();

	public List<Reimbursement> findAllRembursements() {
		// TODO Auto-generated method stub
		return null;
	}
	public Reimbursement submitReimbursement(Reimbursement reimb) {
		//why not equal to zero?
		if(reimb.getReimbId() != 0) {
			/*
			 * Throw a custom exception since new employees
			 * have to have a zero id.
			 */
		}
		Reimbursement newReimb = reimbursementDAO.insert(reimb);
		System.out.println(newReimb.toString());
		return newReimb;
	}
}
