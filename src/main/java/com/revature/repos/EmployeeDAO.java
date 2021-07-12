package com.revature.repos;

import java.util.List;

import com.revature.models.AccountEmployee;
import com.revature.models.Checking;
import com.revature.models.Savings;

public interface EmployeeDAO {
	public List<AccountEmployee> findAll();
	
	public AccountEmployee findEmployee(String username, String password);
	
	public boolean addEmployee(AccountEmployee employee);
	
	public boolean createCheckingAccount(AccountEmployee employee);
	
	public boolean createSavingsAccount(AccountEmployee employee);
	
	public boolean editChecking(Checking checking, double amount);
	public boolean editSavings(Savings savings, double amount);
	
	public boolean approveChecking(int accountID);
	public boolean approveSavings(int accountID);

	public boolean deleteChecking(int accountID);
	public boolean deleteSavings(int accountID);
}
