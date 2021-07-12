package com.revature.repos;

import java.util.List;

import com.revature.models.AccountCustomer;
import com.revature.models.Checking;
import com.revature.models.Savings;

public interface CustomerDAO {
	
	public List<AccountCustomer> findAll();
	
	public AccountCustomer findCustomer(String username, String password);
	
	public boolean addCustomer(AccountCustomer customer);
	
	public boolean createCheckingAccount(AccountCustomer customer);
	
	public boolean createSavingsAccount(AccountCustomer customer);
	
	public boolean editChecking(Checking checking, double amount);
	public boolean editSavings(Savings savings, double amount);
	
	public boolean transferCheckingToSavings(Checking checking, Savings savings, double amount);
	public boolean transferSavingsToChecking(Checking checking, Savings savings, double amount);
	
	public boolean transferCheckingToChecking(Checking checking, Checking checking2, double amount);
	public boolean transferSavingsToSavings(Savings savings, Savings savings2, double amount);
	
}

