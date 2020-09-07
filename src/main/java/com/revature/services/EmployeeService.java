package com.revature.services;

import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;

public class EmployeeService {
	
	public boolean checkLogin(String username, String password) {
		IEmployeeDAO eDAO = new EmployeeDAO();
		String[] credentials = eDAO.findByUsername(username);
		if (!credentials[1].equals(password)) {
			return false;
			
		}
		return true;
	}
}
