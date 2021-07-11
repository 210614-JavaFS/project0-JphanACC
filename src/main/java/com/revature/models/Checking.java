package com.revature.models;

public class Checking {
	private int checkingAccountID;
	private double checkingBalance;
	private boolean checkingAccountStatus;
	
	public Checking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Checking(int checkingAccountID, int checkingBalance, boolean checkingAccountStatus) {
		super();
		this.checkingAccountID = checkingAccountID;
		this.checkingBalance = checkingBalance;
		this.checkingAccountStatus = checkingAccountStatus;
	}

	public int getCheckingAccountID() {
		return checkingAccountID;
	}

	public void setCheckingAccountID(int checkingAccountID) {
		this.checkingAccountID = checkingAccountID;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}

	public void setCheckingBalance(double checkingBalance) {
		this.checkingBalance = checkingBalance;
	}

	public boolean isCheckingAccountStatus() {
		return checkingAccountStatus;
	}

	public void setCheckingAccountStatus(boolean checkingAccountStatus) {
		this.checkingAccountStatus = checkingAccountStatus;
	}

	@Override
	public String toString() {
		return "Checking [checkingAccountID=" + checkingAccountID + ", checkingBalance=" + checkingBalance
				+ ", checkingAccountStatus=" + checkingAccountStatus + "]";
	}

	
	
}
