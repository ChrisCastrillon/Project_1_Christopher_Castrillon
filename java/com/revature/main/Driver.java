package com.revature.main;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.ReimbursementFormHelper;
import com.revature.models.ReimbursementUpdateHelper;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;
import com.revature.services.EmployeeService;
import com.revature.services.ReimbursementService;

public class Driver {

	public static void main(String[] args) {
		testRUH();
		//		testRFH();
//		findAllEmployees();
		//		IEmployeeDAO employeeDAO = new EmployeeDAO();
//		EmployeeService es = new EmployeeService();
////		List<Employee> employeeList = employeeDAO.findAll();
////		for (Employee e : employeeList) {
////			System.out.println(e.toString());
////		}
//		String username = "ccastrillon";
//		String password = "password";
//		if (es.checkLogin(username, password)) {
//			System.out.println("It's a match!");
//		}
//		else {
//			System.out.println("it no work :C");
//		}
//		
//		System.out.println(es.checkLogin(username, password));
	}
	public static void findAllEmployees() {
		EmployeeService es = new EmployeeService();
		IEmployeeDAO eDAO = new EmployeeDAO();
		List<Employee> allEmps = eDAO.findAll();
		for(Employee e : allEmps) {
			System.out.println(e.toString());
		}
		Employee emp = eDAO.findById(2);
		System.out.println("calling the findByUserID " + emp.toString());
	}
	public static void testRFH() {
		ReimbursementFormHelper rfh = new ReimbursementFormHelper("1","Christopher","Castrillon","1","chriscastrillon@ers.com","Travel","10");
		ReimbursementService rs = new ReimbursementService();
		rs.reimbursementFormToReimbursement(rfh);
	}
	public static void testRUH() {
		ReimbursementUpdateHelper ruh = new ReimbursementUpdateHelper("1", "1", "2");
		ReimbursementService rs = new ReimbursementService();
		rs.reimburesmentUpdateFormToReimbursement(ruh);
	}

}
