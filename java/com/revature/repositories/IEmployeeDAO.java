package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;


public interface IEmployeeDAO {
	//crud operations:
	//You really only need this for login validation, ad to retrieve reimbursements
	public List<Employee> findAll();//you dont need to retrieve all finance manager employees, so you have to filter this request by the role.
	public Employee findByUsername(String username);
	public Employee findById(int id);
	public Employee findOneEmployeeByUsername(String username);
	//you can come back and add crud operations for removing and adding employees.
}
