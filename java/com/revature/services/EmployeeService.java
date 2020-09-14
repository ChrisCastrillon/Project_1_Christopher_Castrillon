package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;

public class EmployeeService {
	IEmployeeDAO employeeDAO = new EmployeeDAO();
	private static Logger log = Logger.getLogger(EmployeeService.class);
	
	public Employee checkLogin(String username, String password) {
//		IEmployeeDAO eDAO = new EmployeeDAO();
		 Employee e = employeeDAO.findByUsername(username);
		 if(username.equals(e.getUsername()) & password.equals(e.getPassword())) {
			 return e;
		 }
		 return null;
		 
		 
	}
	public List<Employee> findAllEmployees() {
		List<Employee> allEmployees = employeeDAO.findAll();
		return allEmployees;
		
	}
	public String getRoleFromUsername(String username) {
		Role role = new Role();
		Employee emp = new Employee();
		String roleType;
		IEmployeeDAO eDAO = new EmployeeDAO();
		emp = eDAO.findOneEmployeeByUsername(username);
		role = emp.getRole();
		roleType = role.getName();
		return roleType;
		
	}
}
