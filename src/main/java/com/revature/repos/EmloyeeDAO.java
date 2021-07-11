package com.revature.repos;

import java.util.List;

import com.revature.models.AccountEmployee;
import com.revature.models.Checking;
import com.revature.models.Savings;

public interface EmloyeeDAO {
	public List<AccountEmployee> findAll();
	
	public AccountEmployee findEmloyee(String username, String password);
	
	public boolean addEmployee(AccountEmployee employee);
	
	public boolean createCheckingAccount(AccountEmployee employee);
	
	public boolean createSavingsAccount(AccountEmployee employee);
	
	public boolean editChecking(Checking checking, double amount);
	public boolean editSavings(Savings savings, double amount);
	
	public boolean transferCheckingToSavings(Checking checking, Savings savings, double amount);
	public boolean transferSavingsToChecking(Checking checking, Savings savings, double amount);
	
	public boolean transferCheckingToChecking(Checking checking, Checking checking2, double amount);
	public boolean transferSavingsToSavings(Savings savings, Savings savings2, double amount);
}
