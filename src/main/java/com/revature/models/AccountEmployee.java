package com.revature.models;

import java.util.ArrayList;

public class AccountEmployee extends Account {
	
	private ArrayList<Checking> checkingAccounts = new ArrayList<Checking>();
	private ArrayList<Savings> savingsAccounts = new ArrayList<Savings>();
	
	public AccountEmployee() {
		super();
	}
	
	//Add employee
	public AccountEmployee(String username,String password,String firstName, String lastName) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public ArrayList<Checking> getCheckingAccounts() {
		return checkingAccounts;
	}

	public void setCheckingAccounts(ArrayList<Checking> checkingAccounts) {
		this.checkingAccounts = checkingAccounts;
	}

	public ArrayList<Savings> getSavingsAccounts() {
		return savingsAccounts;
	}

	public void setSavingsAccounts(ArrayList<Savings> savingsAccounts) {
		this.savingsAccounts = savingsAccounts;
	}
	
	//add Checking account
	public void addCheckingAccount(Checking checkingObj) {
		this.checkingAccounts.add(checkingObj);
	}
	
	//add Savings account
	public void addSavingsAccount(Savings savingsObj) {
		this.savingsAccounts.add(savingsObj);
	}
}
