package com.revature.services;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementFormHelper;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;
import com.revature.repositories.IReimbursementDAO;
import com.revature.repositories.ReimbursementDAO;
import java.sql.Timestamp;


public class ReimbursementService {
	private IReimbursementDAO reimbursementDAO = new ReimbursementDAO();
	private IEmployeeDAO employeeDAO = new EmployeeDAO();

	public List<Reimbursement> findAllRembursements() {
		// TODO Auto-generated method stub
		return null;
	}
	public Reimbursement reimbursementFormToReimbursement(ReimbursementFormHelper rfh) {
		int subType = Integer.valueOf(rfh.getSubType());
		int eid = Integer.valueOf(rfh.getEid());
		double amount = Double.valueOf(rfh.getAmount());
		String description = rfh.getDescription();
		Employee emp = employeeDAO.findById(eid);
		Reimbursement r = new Reimbursement(0, amount, new Timestamp(System.currentTimeMillis()), null, description, null, eid, 0, 1, subType);
		return r;
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
