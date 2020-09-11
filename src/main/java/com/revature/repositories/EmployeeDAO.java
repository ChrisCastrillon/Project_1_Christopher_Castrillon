package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

public class EmployeeDAO implements IEmployeeDAO {
	private static Logger log = Logger.getLogger(EmployeeDAO.class);
	@Override
	public List<Employee> findAll() {
		List<Employee> allEmployees = new ArrayList<>();

		// get a connection to the database
		log.info("Trying to get a connect to database");
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
				// add the user to the list of users
				allEmployees.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve all epmployees");
			System.out.println("WE FAILED TO RETRIEVE ALL Employees");
			// if it returns null then you should try again.
			return null;
		}
		log.info("returning a list fo all employees");
		return allEmployees;
	}

	@Override
	public String[] findByUsername(String username) {
		String[] credentials = new String[2];
		credentials[0] = username;
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT ers_password FROM project1.ers_users WHERE ers_username = " + "\'"+ username + "\'";
			ResultSet rs = stmt.executeQuery(sql);
			//iterate through the set of users
			while(rs.next()) {
				credentials[1] = rs.getString("ers_password");
			}
			System.out.println("credentials " + credentials[0] + ' ' + credentials[1]);
			log.info("returning a string array of user credentials");
			return credentials;
		}catch(SQLException e) {
			System.out.println("WE FAILED TO RETRIEVE USER");
			log.info("Fail to retrive the user credetials from the database ");
			//if it returns null then you should try again.
			return null;
		}
		
	}

	@Override
	public Employee findById(int eid) {
		Employee emp = new Employee();
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project1.ers_users WHERE ers_user_id = " + "\'"+ eid + "\'";
			ResultSet rs = stmt.executeQuery(sql);
			//iterate through the set of users
			while(rs.next()) {
				String username = rs.getString("ers_username");
				String password = rs.getString("ers_password");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				int roleId = rs.getInt("user_role_id");
				Role role = new Role(roleId);
				emp = new Employee(eid, username, password, firstName, lastName, role, email);
				log.info("returning the user of id " +  emp.toString());

			}		
		}catch(SQLException e) {
			System.out.println("WE FAILED TO RETRIEVE USER");
			log.info("Fail to retrive the user credetials from the database ");
			//if it returns null then you should try again.
			return null;
		}
		return emp;
		
	}

}
