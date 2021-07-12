package com.revature.models;

import java.util.ArrayList;

public class AccountAdmin extends Account {

	private ArrayList<Checking> checkingAccounts = new ArrayList<Checking>();
	private ArrayList<Savings> savingsAccounts = new ArrayList<Savings>();
	
	public AccountAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountAdmin(int accountID, String username, String password, String firstName, String lastName) {
		super(accountID, username, password, firstName, lastName);
		// TODO Auto-generated constructor stub
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((checkingAccounts == null) ? 0 : checkingAccounts.hashCode());
		result = prime * result + ((savingsAccounts == null) ? 0 : savingsAccounts.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountAdmin other = (AccountAdmin) obj;
		if (checkingAccounts == null) {
			if (other.checkingAccounts != null)
				return false;
		} else if (!checkingAccounts.equals(other.checkingAccounts))
			return false;
		if (savingsAccounts == null) {
			if (other.savingsAccounts != null)
				return false;
		} else if (!savingsAccounts.equals(other.savingsAccounts))
			return false;
		return true;
	}
	
	//add Checking account
	public void addCheckingAccount(Checking checkingObj) {
		this.checkingAccounts.add(checkingObj);
	}
	
	//add Savings account
	public void addSavingsAccount(Savings savingsObj) {
		this.savingsAccounts.add(savingsObj);
	}
	
	//get Checking 
	public Checking getCheckingAccount(int index) {
		return checkingAccounts.get(index);
	}

	//get Savings
	public Savings getSavingsAccount(int index) {
		return savingsAccounts.get(index);
	}
}
