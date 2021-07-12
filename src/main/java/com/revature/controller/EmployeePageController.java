package com.revature.controller;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.AccountCustomer;
import com.revature.models.AccountEmployee;
import com.revature.services.AccountService;

public class EmployeePageController {
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(MenuController.class);
	
	private static EmployeePageController employeePageController = new EmployeePageController();
	private static MenuController menuController = new MenuController();
	
	public void showEmployeePage(AccountEmployee account) {
		System.out.println("-___Your Account Detail Page____-");
		System.out.println("Your First Name: " + account.getFirstName() + ". Your Last Name: " + account.getLastName());

		//Checking Section
		System.out.println("----Checking Account(s) Section--------");
		if (account.getCheckingAccounts().size() == 0) {
			System.out.println("You haven't opened any Checking account yet.");
		}
		
		for (int i=0; i< account.getCheckingAccounts().size(); i++) {
			if (account.getCheckingAccounts().get(i).isCheckingAccountStatus() == false) {
				System.out.println("Your checking account ID " + account.getCheckingAccounts().get(i).getCheckingAccountID() + " is not approved yet");
			} else {
				System.out.println("Your checking account ID " 
									+ account.getCheckingAccounts().get(i).getCheckingAccountID() 
									+ " has total balance: $"
									+ account.getCheckingAccounts().get(i).getCheckingBalance());
			}
		}
		
		//Savings Section
		System.out.println("----Savings Account(s) Section--------");
		if (account.getSavingsAccounts().size() == 0) {
			System.out.println("You haven't opened any Savings account yet.");
		}
		
		for (int i=0; i< account.getSavingsAccounts().size(); i++) {
			if (account.getSavingsAccounts().get(i).isSavingsAccountStatus() == false) {
				System.out.println("Your Savings account ID " + account.getSavingsAccounts().get(i).getSavingsAccountID() + " is not approved yet");
			} else {
				System.out.println("Your Savings account ID " 
									+ account.getSavingsAccounts().get(i).getSavingsAccountID() 
									+ " has total balance: $"
									+ account.getSavingsAccounts().get(i).getSavingsBalance());
			}
		}
		
		System.out.println("~~~Employee Action Menu~~~");
		System.out.println("1. Open New Account \n"
				+ "2. Withdraw/Deposit Menu \n"
				+ "3. Transfer funds between accounts \n"
				+ "4. Approve accounts \n"
				+ "5. Cancel accounts \n"
				+ "6. Log out.");
		
		String choice = scan.nextLine();
		
		switch(choice) {
			case "1":
				AccountService.createNewAccount(account);
				break;
			case "2":
				AccountService.editBalance(account);
				break;
			case "3":
//				AccountService.transferFund(account);
				break;
			case "4":
				AccountService.approveAccount(account);
				break;	
			case "5":
				AccountService.cancelAccount(account);
				break;			
			case "6":
				System.out.println("Logging you out...");
				menuController.validateUser();
			default:
				System.out.println("You've entered invalid value, please try again");
				employeePageController.showEmployeePage(account);
		}
	}
}
