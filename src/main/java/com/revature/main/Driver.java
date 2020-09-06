package com.revature.main;

import java.util.List;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.IEmployeeDAO;

public class Driver {

	public static void main(String[] args) {
		IEmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employeeList = employeeDAO.findAll();
		for (Employee e : employeeList) {
			System.out.println(e.toString());
		}
	}

}
