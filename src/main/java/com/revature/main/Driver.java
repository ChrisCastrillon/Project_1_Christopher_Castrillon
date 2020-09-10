package com.revature.main;

import java.util.List;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;
import com.revature.services.EmployeeService;

public class Driver {

	public static void main(String[] args) {
		findAllEmployees();
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
	}

}
