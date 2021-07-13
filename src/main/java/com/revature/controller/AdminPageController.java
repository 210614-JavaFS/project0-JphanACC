package com.revature.controller;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.AccountAdmin;
import com.revature.models.AccountEmployee;
import com.revature.services.AccountService;

public class AdminPageController {
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(MenuController.class);
	
	private static EmployeePageController employeePageController = new EmployeePageController();
	private static AdminPageController adminPageController = new AdminPageController();
	private static MenuController menuController = new MenuController();
	
	public void showAdminPage(AccountAdmin account) {
		System.out.println("-___Your Account Detail Page____-");
		System.out.println("Your First Name: " + account.getFirstName() + ". Your Last Name: " + account.getLastName());
		
		//Checking Section
		System.out.println("----You are super Admin: Please look at what you can do below ----");
		System.out.println("~~~Admin Action Menu~~~");
		System.out.println("1. Approve Accounts \n"
				+ "2. Deny/Cancel Accounts \n"
				+ "3. Transfer funds between accounts \n"
				+ "4. Log out.");
		
		String choice = scan.nextLine();
		
		switch(choice) {
			case "1":
				AccountService.approveAccount(account);
				break;
			case "2":
				AccountService.cancelAccount(account);
				break;
			case "3":
				AccountService.transferFund(account);
				break;
			case "4":
				System.out.println("Logging you out...");
				menuController.validateUser();
			default:
				System.out.println("You've entered invalid value, please try again");
				adminPageController.showAdminPage(account);
		}
	}
}
