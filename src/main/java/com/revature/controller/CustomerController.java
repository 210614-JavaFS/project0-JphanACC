package com.revature.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.AccountCustomer;
import com.revature.models.Checking;
import com.revature.models.Savings;
import com.revature.services.CustomerService;

import jdk.internal.org.jline.utils.Log;

public class CustomerController {
	public static Logger log = LoggerFactory.getLogger(MenuController.class);
	public static CustomerService customerService = new CustomerService();
	
	public List<AccountCustomer> showAllCustomers() {
		List<AccountCustomer> customers = customerService.showAllCustomers();
		
//		for (AccountCustomer c:customers) {
//			System.out.println(c);
//		}
		return customers;
	}
	
	public boolean validateCustomer(String username, String password) {
		
		AccountCustomer accountCustomer = customerService.findCustomer(username, password);
		
		if (accountCustomer == null) {
			System.out.println("Error: Account not found...");
			return false;
		} 

		if (accountCustomer.getUsername() == null) {
			System.out.println("Error: Invalid username...");
			return false;
		}
		
		if (accountCustomer.getUsername().equals(username) && accountCustomer.getPassword().equals(password)) {
			System.out.println("Welcome back! " + accountCustomer.getFirstName());
			return true;
		} else {
			System.out.println("Error: Account not found...");
			return false;
		}
	}
	
	
	
	public AccountCustomer findCustomer(String username, String password) {
		AccountCustomer accountCustomer = customerService.findCustomer(username, password);
		
		return accountCustomer;
	}
	
	public void addCustomer(String username,String password , String firstName, String lastName) {
		AccountCustomer newCustomer = new AccountCustomer(username,password,firstName,lastName);
			
		if (customerService.addCustomer(newCustomer)) {
			System.out.println("Customer Registation is successful...");
		} else {
			System.out.println("Something went wrong, try again...");
		}
	}
	
	public void createCheckingAccount(AccountCustomer customer) {
		System.out.println("Sending request...");
		if (customerService.createCheckingAccount(customer)) {
			System.out.println("Sending request completed...");
		} else {
			System.out.println("Something went wrong, try again...");
		}
	}
	
	public void createSavingsAccount(AccountCustomer customer) {
		System.out.println("Sending request...");
		if (customerService.createSavingsAccount(customer)) {
			System.out.println("Sending request completed...");
		} else {
			System.out.println("Something went wrong, try again...");
		}
	}
	
	public void editChecking(Checking checking, double amount) {
		System.out.println("Sending request...");
		if (customerService.editChecking(checking, amount)) {
			System.out.println("Sending request completed...");
		} else {
			System.out.println("Something went wrong, try again...");
		}
	}
	
	public void editSavings(Savings savings, double amount) {
		System.out.println("Sending request...");
		if (customerService.editSavings(savings, amount)) {
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
}
