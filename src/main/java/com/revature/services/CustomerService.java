package com.revature.services;

import java.util.List;

import com.revature.models.AccountCustomer;
import com.revature.models.Checking;
import com.revature.models.Savings;
import com.revature.repos.CustomerDAO;
import com.revature.repos.CustomerDAOImpl;

public class CustomerService {
	private static CustomerDAO customerDAO = new CustomerDAOImpl();
	
	public List<AccountCustomer> showAllCustomers() {
		return customerDAO.findAll();
	}
	
	public AccountCustomer findCustomer(String username, String password) {
		return customerDAO.findCustomer(username, password);
	}
	
	public boolean addCustomer(AccountCustomer customer) {
		return customerDAO.addCustomer(customer);
	}
	
	public boolean createCheckingAccount(AccountCustomer customer) {
		return customerDAO.createCheckingAccount(customer);
	}
	
	public boolean createSavingsAccount(AccountCustomer customer) {
		return customerDAO.createSavingsAccount(customer);
	}

	public boolean editChecking(Checking checking, double amount) {
		return customerDAO.editChecking(checking, amount);
	}
	
	public boolean editSavings(Savings savings, double amount) {
		return customerDAO.editSavings(savings, amount);
	}
	
	//Checking to Savings
	public boolean transferCheckingToSavings(Checking checking, Savings savings, double amount) {
		return customerDAO.transferCheckingToSavings(checking, savings, amount);
	}
	
	//Savings to checking
	public boolean transferSavingsToChecking(Checking checking, Savings savings, double amount) {
		return customerDAO.transferSavingsToChecking(checking, savings, amount);
	}
	
	//Checking to Checking
	public boolean transferCheckingToChecking(Checking checking, Checking checking2, double amount) {
		return customerDAO.transferCheckingToChecking(checking, checking2, amount);
	}
	
	//Savings to Savings
	public boolean transferSavingsToSavings(Savings savings, Savings savings2, double amount) {
		return customerDAO.transferSavingsToSavings(savings, savings2, amount);
	}
}
