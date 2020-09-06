package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

public class EmployeeDAO implements IEmployeeDAO {

	@Override
	public List<Employee> findAll() {
		List<Employee> allEmployees = new ArrayList<>();

		// get a connection to the database
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project1.ers_users";
			ResultSet rs = stmt.executeQuery(sql);
			// iterate through the set of users
			while (rs.next()) {
				int id = rs.getInt("ers_user_id"); // this gets the string from the column name
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				int roleId = rs.getInt("user_role_id");
				Role role = new Role(roleId);
				
				Employee e = new Employee(id, username, password, firstName, lastName, role, email);
				// add the uer to the list of users
				allEmployees.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO RETRIEVE ALL USERS");
			// if it returns null then you should try again.
			return null;
		}
		return allEmployees;
	}

	@Override
	public Employee findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
