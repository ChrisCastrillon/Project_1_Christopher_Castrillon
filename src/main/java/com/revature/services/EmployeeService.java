package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;

public class EmployeeService {
	IEmployeeDAO employeeDAO = new EmployeeDAO();
	
	public boolean checkLogin(String username, String password) {
		IEmployeeDAO eDAO = new EmployeeDAO();
		String[] credentials = eDAO.findByUsername(username);
		System.out.println(credentials[0] + " " + credentials[1] + " " + password);
		
		if (!credentials[1].equals(password)) {
			System.out.println("is it false?");
			return false;
			
		}
		System.out.println("is it true?");
		return true;
	}
	public List<Employee> findAllEmployees() {
		List<Employee> allEmployees = employeeDAO.findAll();
		return allEmployees;
		
	}
}
