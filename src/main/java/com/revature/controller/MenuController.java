package com.revature.controller;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuController {
	
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(MenuController.class);
	
	private static CustomerController customerController = new CustomerController();
	private static EmployeeController employeeController = new EmployeeController();
	private static AdminController adminController = new AdminController();
	
	private static AccountPageController accountPageController = new AccountPageController();
	private static EmployeePageController employeePageController = new EmployeePageController();
	private static AdminPageController adminPageController = new AdminPageController();
//	private UserService userService = new UserService();
	
	public void validateUser() {
		//SHOWS ALL CUSTOMERS
		
		System.out.println("== Starter Menu ==");
		System.out.println("Are you a returning user?");
		System.out.println("Please answer yes or no");
		String choice = scan.nextLine().toLowerCase();
		switch(choice) {
		case "yes":
			this.login();
			break;
		case "no":
			this.registerAccount();
			break;
		default:
			System.out.println("invalid characters, please try again");
			this.validateUser();
		}
	}
	
	public void login() {
		System.out.println("=== Login Menu ===");
		String tableName = this.selectUserType();
		while (tableName == "error") {
			tableName = this.selectUserType();
		}
		
		System.out.println("Please enter your user name: ");
		String usernameInput = scan.nextLine();
		
		System.out.println("Please enter your password:");
		String passwordInput = scan.nextLine();
		
		if (tableName.equals("customers")) {
			if(customerController.validateCustomer(usernameInput, passwordInput)) {
				System.out.println("Login is successful!");
				accountPageController.showAccountCustomerPage(customerController.findCustomer(usernameInput, passwordInput));
			} else {
				this.login();
			}
		} else if (tableName.equals("employees")) {
			if(employeeController.validateEmployee(usernameInput, passwordInput)) {
				System.out.println("Employee Login is successful!");
				employeePageController.showEmployeePage(employeeController.findEmployee(usernameInput, passwordInput));
			} else {
				this.login();
			}
		} else if (tableName.equals("admin")) {
			if(adminController.validateAdmin(usernameInput, passwordInput)) {
				System.out.println("Admin Login is successful!");
				adminPageController.showAdminPage(adminController.findAdmin(usernameInput, passwordInput));
			} else {
				this.login();
			}
		}
	}
	
	public String selectUserType() {
		System.out.println("Please select what type of account: ");
		System.out.println("1. Customer \n2. Employee \n3. Admin \n4. Go back to previous menu.");
	
		String choice = scan.nextLine();
		
		switch(choice) {
			case "1":
				//Create table
				return "customers";
				
			case "2":
				return "employees";
				
			case "3":
				return "admin";

			case "4":
				this.validateUser();
				return "go back";
				
			default:
				System.out.println("You've entered invalid value, please try again");
				return "error";
		}
		//Java say I need this line but I don't want anything that isn't 'customers' or 'employees' to be spit back		
	}
	
	
	
	public void registerAccount() {
		System.out.println("----- Register Menu -----");
		String tableName = this.selectUserType();
		while (tableName == "error") {
			tableName = this.selectUserType();
			this.registerAccount();
		}
		if (tableName.equals("admin")) {
			System.out.println("Error: Admin registration is not available at this moment. Please try again.");
			this.registerAccount();
		}
		
		
		System.out.println("Please enter your user name:");
		String usernameInput = scan.nextLine();
		
		System.out.println("Please enter your password:");
		String passwordInput = scan.nextLine();
		
		System.out.println("Please enter your first name:");
		String firstNameInput = scan.nextLine();
		
		System.out.println("Please enter your last name:");
		String lastNameInput = scan.nextLine();
		
		if (tableName.equals("customers")) {
			customerController.addCustomer(usernameInput, passwordInput, firstNameInput, lastNameInput);
		} else if (tableName.equals("employees")) {
			employeeController.addEmployee(usernameInput, passwordInput, firstNameInput, lastNameInput);
		}
		
		//back to login screen
		this.login();
	}
	
}
