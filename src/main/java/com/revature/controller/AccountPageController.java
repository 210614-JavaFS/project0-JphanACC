package com.revature.controller;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.AccountCustomer;
import com.revature.services.AccountService;

public class AccountPageController {
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(MenuController.class);
	
	private static AccountPageController accountPageController = new AccountPageController();
	private static CustomerController customerController = new CustomerController();
	private static MenuController menuController = new MenuController();
	private static AccountService accountService = new AccountService();
	
	public void showAccountCustomerPage(AccountCustomer customer) {
		System.out.println("-___Your Account Detail Page____-");
		System.out.println("Your First Name: " + customer.getFirstName() + ". Your Last Name: " + customer.getLastName());

		//Checking Section
		System.out.println("----Checking Account(s) Section--------");
		if (customer.getCheckingAccounts().size() == 0) {
			System.out.println("You haven't opened any Checking account yet.");
		}
		
		for (int i=0; i< customer.getCheckingAccounts().size(); i++) {
			if (customer.getCheckingAccounts().get(i).isCheckingAccountStatus() == false) {
				System.out.println("Your checking account ID " + customer.getCheckingAccounts().get(i).getCheckingAccountID() + " is not approved yet");
			} else {
				System.out.println("Your checking account ID " 
									+ customer.getCheckingAccounts().get(i).getCheckingAccountID() 
									+ " has total balance: $"
									+ customer.getCheckingAccounts().get(i).getCheckingBalance());
			}
		}
		
		//Savings Section
		System.out.println("----Savings Account(s) Section--------");
		if (customer.getSavingsAccounts().size() == 0) {
			System.out.println("You haven't opened any Savings account yet.");
		}
		
		for (int i=0; i< customer.getSavingsAccounts().size(); i++) {
			if (customer.getSavingsAccounts().get(i).isSavingsAccountStatus() == false) {
				System.out.println("Your Savings account ID " + customer.getSavingsAccounts().get(i).getSavingsAccountID() + " is not approved yet");
			} else {
				System.out.println("Your Savings account ID " 
									+ customer.getSavingsAccounts().get(i).getSavingsAccountID() 
									+ " has total balance: $"
									+ customer.getSavingsAccounts().get(i).getSavingsBalance());
			}
		}
		
		System.out.println("~~~Customer Action Menu~~~");
		System.out.println("1. Open New Account \n2. Withdraw/Deposit Menu \n3. Transfer funds between accounts \n4. Log out.");
		
		String choice = scan.nextLine();
		
		switch(choice) {
			case "1":
				AccountService.createNewAccount(customer);
				break;
			case "2":
				AccountService.editBalance(customer);
				break;
			case "3":
				AccountService.transferFund(customer);
				break;
			case "4":
				System.out.println("Logging you out...");
				menuController.validateUser();
			default:
				System.out.println("You've entered invalid value, please try again");
				accountPageController.showAccountCustomerPage(customer);
		}
	}
	
	

}
