package com.revature.services;

import java.util.List;

import com.revature.models.AccountCustomer;
import com.revature.models.AccountEmployee;
import com.revature.models.Checking;
import com.revature.models.Savings;
import com.revature.repos.CustomerDAO;
import com.revature.repos.CustomerDAOImpl;
import com.revature.repos.EmployeeDAO;
import com.revature.repos.EmployeeDAOImpl;

public class EmployeeService {
	private static EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	
	public List<AccountEmployee> showAllEmployee() {
		return employeeDAO.findAll();
	}
	
	public AccountEmployee findEmployee(String username, String password) {
		return employeeDAO.findEmployee(username, password);
	}
	
	public boolean addEmployee(AccountEmployee employee) {
		return employeeDAO.addEmployee(employee);
	}
	
	public boolean createCheckingAccount(AccountEmployee employee) {
		return employeeDAO.createCheckingAccount(employee);
	}
	
	public boolean createSavingsAccount(AccountEmployee employee) {
		return employeeDAO.createSavingsAccount(employee);
	}

	public boolean editChecking(Checking checking, double amount) {
		return employeeDAO.editChecking(checking, amount);
	}
	
	public boolean editSavings(Savings savings, double amount) {
		return employeeDAO.editSavings(savings, amount);
	}
	
	//Approve Checking
	public boolean approveChecking(int accountID) {
		return employeeDAO.approveChecking(accountID);
	}
	
	//Approve Savings
	public boolean approveSavings(int accountID) {
		return employeeDAO.approveSavings(accountID);
	}
	
	//Delete Checking
	public boolean deleteChecking(int accountID) {
		return employeeDAO.deleteChecking(accountID);
	}
	
	//Delete Savings
	public boolean deleteSavings(int accountID) {
		return employeeDAO.deleteChecking(accountID);
	}
	
	public boolean transferCheckingToSavings(int checkingID, int savingsID, double amount) {
		return employeeDAO.transferCheckingToSavings(checkingID, savingsID, amount);
	};
	public boolean transferSavingsToChecking(int checkingID, int savingsID, double amount) {
		return employeeDAO.transferSavingsToChecking(checkingID, savingsID, amount);
	};
	
	public boolean transferCheckingToChecking(int checkingID, int checking2ID, double amount) {
		return employeeDAO.transferCheckingToChecking(checkingID, checking2ID, amount);
	};
	public boolean transferSavingsToSavings(int savingsID, int savings2ID, double amount) {
		return employeeDAO.transferSavingsToSavings(savingsID, savings2ID, amount);
	};
	
}
