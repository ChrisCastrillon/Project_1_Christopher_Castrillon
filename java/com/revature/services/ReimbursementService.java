package com.revature.services;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementFormHelper;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;
import com.revature.repositories.IReimbursementDAO;
import com.revature.repositories.ReimbursementDAO;


public class ReimbursementService {
	private IReimbursementDAO reimbursementDAO = new ReimbursementDAO();
	private ObjectMapper om = new ObjectMapper();
	private IEmployeeDAO employeeDAO = new EmployeeDAO();

	public List<Reimbursement> findAllRembursements() {
		List<Reimbursement> allReimbursements = reimbursementDAO.findAll();
		return allReimbursements;
	}
	public Reimbursement reimbursementFormToReimbursement(ReimbursementFormHelper rfh) {
		int subType = Integer.valueOf(rfh.getSubType());
		int eid = Integer.valueOf(rfh.getEid());
		double amount = Double.valueOf(rfh.getAmount());
		String description = rfh.getDescription();
		Employee emp = employeeDAO.findById(eid);
		Reimbursement r = new Reimbursement(0, amount, new Timestamp(System.currentTimeMillis()), null, description, null, eid, 0, 1, subType);
		r = submitReimbursement(r);
//		r = returnUpdatedReimbursement(r);
		return r;
	}
	public Reimbursement submitReimbursement(Reimbursement reimb) {
		Reimbursement newReimb = reimbursementDAO.insert(reimb);
		System.out.println("The new reimbursement is: " + newReimb.toString());
		return newReimb;
	}
	public Reimbursement returnUpdatedReimbursement(Reimbursement r) {
		r = reimbursementDAO.findByEID(r.getAuthor());
		return r;
	}
	public Reimbursement updateReimbursement(Reimbursement reimb) {
		Reimbursement newReimb = reimbursementDAO.update(reimb);
		return newReimb;
	}
}
