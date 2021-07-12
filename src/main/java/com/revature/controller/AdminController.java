package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.AccountAdmin;
import com.revature.models.AccountCustomer;
import com.revature.services.AdminService;
import com.revature.services.CustomerService;
import com.revature.services.EmployeeService;

public class AdminController {
	public static Logger log = LoggerFactory.getLogger(MenuController.class);
	public static CustomerService customerService = new CustomerService();
	public static EmployeeService employeeService = new EmployeeService();
	public static AdminService adminService = new AdminService();
	
	public boolean validateAdmin(String username, String password) {
		AccountAdmin accountAdmin = adminService.findAdmin(username, password);
		
		if (accountAdmin == null) {
			System.out.println("Error: Account not found...");
			log.warn("This admin account is null");
			return false;
		} 
	
		if (accountAdmin.getUsername() == null) {
			System.out.println("Error: Invalid username...");
			log.warn("The credentials are wrong");
			return false;
		}
			
		if (accountAdmin.getUsername().equals(username) && accountAdmin.getPassword().equals(password)) {
			System.out.println("Welcome back! " + accountAdmin.getFirstName());
			return true;
		} else {
			System.out.println("Error: Account not found...");
			return false;
		}
		
	}
	
	public AccountAdmin findAdmin(String username, String password) {
		AccountAdmin accountAdmin = adminService.findAdmin(username, password);
		
		return accountAdmin;
	}
	
}
