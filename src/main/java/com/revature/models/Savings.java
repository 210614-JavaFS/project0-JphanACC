package com.revature.models;

public class Savings {
	private int savingsAccountID;
	private double savingsBalance;
	private boolean savingsAccountStatus;
	
	public Savings() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Savings(int savingsAccountID, int savingsBalance, boolean savingsAccountStatus) {
		super();
		this.savingsAccountID = savingsAccountID;
		this.savingsBalance = savingsBalance;
		this.savingsAccountStatus = savingsAccountStatus;
	}

	public int getSavingsAccountID() {
		return savingsAccountID;
	}

	public void setSavingsAccountID(int savingsAccountID) {
		this.savingsAccountID = savingsAccountID;
	}

	public double getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public boolean isSavingsAccountStatus() {
		return savingsAccountStatus;
	}

	public void setSavingsAccountStatus(boolean savingsAccountStatus) {
		this.savingsAccountStatus = savingsAccountStatus;
	}

	@Override
	public String toString() {
		return "Savings [savingsAccountID=" + savingsAccountID + ", savingsBalance=" + savingsBalance
				+ ", savingsAccountStatus=" + savingsAccountStatus + "]";
	}
	
	


	
}
