package com.revature.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.AccountCustomer;
import com.revature.models.AccountEmployee;
import com.revature.models.Checking;
import com.revature.models.Savings;
import com.revature.services.CustomerService;
import com.revature.services.EmployeeService;

import jdk.internal.org.jline.utils.Log;


public class EmployeeController {
	public static Logger log = LoggerFactory.getLogger(MenuController.class);
	public static CustomerService customerService = new CustomerService();
	public static EmployeeService employeeService = new EmployeeService();
		
	public void showAllCustomers() {
		System.out.println("Show all customers");
		List<AccountEmployee> employees = employeeService.showAllEmployee();
			
		for (AccountEmployee e:employees) {
		System.out.println(e);
		}
	}
		
	public boolean validateEmployee(String username, String password) {
			
		AccountEmployee accountEmployee = employeeService.findEmployee(username, password);
			
		if (accountEmployee == null) {
			System.out.println("Error: Account not found...");
			log.warn("This employee account is null");
			return false;
		} 
	
		if (accountEmployee.getUsername() == null) {
			System.out.println("Error: Invalid username...");
			log.warn("The credentials are wrong");
			return false;
		}
			
		if (accountEmployee.getUsername().equals(username) && accountEmployee.getPassword().equals(password)) {
			System.out.println("Welcome back! " + accountEmployee.getFirstName());
			return true;
		} else {
			System.out.println("Error: Account not found...");
			return false;
		}
	}
		
		
		
	public AccountEmployee findEmployee(String username, String password) {
		AccountEmployee accountEmployee = employeeService.findEmployee(username, password);
			
		return accountEmployee;
	}
		
	public void addEmployee(String username,String password , String firstName, String lastName) {
		AccountEmployee accountEmployee = new AccountEmployee(username,password,firstName,lastName);
				
		if (employeeService.addEmployee(accountEmployee)) {
			System.out.println("Customer Registation is successful...");
		} else {
			System.out.println("Something went wrong, try again...");
			log.warn("Account creation is not successful. Try again");
		}
	}
		
	public void createCheckingAccount(AccountEmployee employee) {
		System.out.println("Sending request...");
		if (employeeService.createCheckingAccount(employee)) {
			System.out.println("Sending request completed...");
		} else {
			System.out.println("Something went wrong, try again...");
			log.warn("New request for Checking account failed...");
		}
	}
		
	public void createSavingsAccount(AccountEmployee employee) {
		System.out.println("Sending request...");
		if (employeeService.createSavingsAccount(employee)) {
			System.out.println("Sending request completed...");
		} else {
			System.out.println("Something went wrong, try again...");
			log.warn("New request for Savings account failed...");
		}
	}
	
	public void editChecking(Checking checking, double amount) {
		System.out.println("Sending request...");
		if (employeeService.editChecking(checking, amount)) {
			System.out.println("Sending request completed...");
		} else {
			System.out.println("Something went wrong, try again...");
		}
	}
	
	public void editSavings(Savings savings, double amount) {
		System.out.println("Sending request...");
		if (employeeService.editSavings(savings, amount)) {
			System.out.println("Sending request completed...");
		} else {
			System.out.println("Something went wrong, try again...");
		}
	}
	
	//Checking -> Savings
	public void transferCheckingToSavings(Checking checking, Savings savings, double amount) {
		System.out.println("Sending request...");
		if (customerService.transferCheckingToSavings(checking, savings, amount)) {
			System.out.println("Sending request completed...");
		} else {
			log.warn("Transfer Checking to Savings did not complete");
			System.out.println("Something went wrong, try again...");
		}
	}
	
	//Savings -> Checking
	public void transferSavingsToChecking(Checking checking, Savings savings, double amount) {
		System.out.println("Sending request...");
		if (customerService.transferSavingsToChecking(checking, savings, amount)) {
			System.out.println("Sending request completed...");
		} else {
			log.warn("Transfer Savings to Checking did not complete");
			System.out.println("Something went wrong, try again...");
		}
	}
	
	//Checking -> Checking
	public void transferCheckingToChecking(Checking checking, Checking checking2, double amount) {
		System.out.println("Sending request...");
		if (customerService.transferCheckingToChecking(checking, checking2, amount)) {
			System.out.println("Sending request completed...");
		} else {
			log.warn("Transfer Checking to Checking did not complete");
			System.out.println("Something went wrong, try again...");
		}
	}
	
	//Savings -> Savings
	public void transferSavingsToSavings(Savings savings, Savings savings2, double amount) {
		System.out.println("Sending request...");
		if (customerService.transferSavingsToSavings(savings, savings2, amount)) {
			System.out.println("Sending request completed...");
		} else {
			log.warn("Transfer Savings to Savings did not complete");
			System.out.println("Something went wrong, try again...");
		}
	}
	
	//Approve Checking
	public void approveChecking(int accountID) {
		System.out.println("Sending request...");
		if (employeeService.approveChecking(accountID)) {
			System.out.println("Sending request completed...");
		} else {
			log.warn("Approve Checking did not complete");
			System.out.println("Something went wrong, try again...");
		}
	}
	
	//Approve Savings
	public void approveSavings(int accountID) {
		System.out.println("Sending request...");
		if (employeeService.approveSavings(accountID)) {
			System.out.println("Sending request completed...");
		} else {
			log.warn("Approve Savings did not complete");
			System.out.println("Something went wrong, try again...");
		}
	}
	
	//Delete Checking
	public void deleteChecking(int accountID) {
		System.out.println("Sending request...");
		if (employeeService.deleteChecking(accountID)) {
			System.out.println("Sending request completed...");
			log.warn("Account with ID " + accountID + " is now removed/denied");
		} else {
			log.warn("Approve Checking did not complete");
			System.out.println("Something went wrong, try again...");
		}
	}
	
	//Delete Checking
	public void deleteSavings(int accountID) {
		System.out.println("Sending request...");
		if (employeeService.deleteSavings(accountID)) {
			System.out.println("Sending request completed...");
			log.warn("Account with ID " + accountID + " is now removed/denied");
		} else {
			log.warn("Approve Checking did not complete");
			System.out.println("Something went wrong, try again...");
		}
	}
	
}