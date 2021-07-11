package com.revature.services;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.AccountPageController;
import com.revature.controller.CustomerController;
import com.revature.controller.MenuController;
import com.revature.models.AccountCustomer;

public class AccountService {
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(MenuController.class);
	
	private static AccountPageController accountPageController = new AccountPageController();
	private static CustomerController customerController = new CustomerController();
	private static MenuController menuController = new MenuController();
	
	public static void createNewAccount(AccountCustomer customer) {
		System.out.println("--- 1. Account Creation Menu ----");
		System.out.println("1. Open New Checking Account \n2. Open New Savings Account \n3. Go back to previous menu");
		String choice = scan.nextLine();
		switch (choice) {
		case "1":
			customerController.createCheckingAccount(customer);
			accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
		case "2":
			customerController.createSavingsAccount(customer);
			accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
		case "3":
			System.out.println("Going back to Account User menu...");
			accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
		default:
			System.out.println("You've entered invalid value, please try again");
			accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
		}
	}
	
	public static void editBalance(AccountCustomer customer) {
		System.out.println("--- 2. Withdraw/Deposit Menu ----");
		System.out.println("1. Checkings \n2. Savings");
		String choice = scan.nextLine();
		switch (choice) {
	
			//Checking
			case "1":
				System.out.println("----Please Select Your Checking Account--------");
				System.out.println("----Please pick which account--------");
				if (customer.getCheckingAccounts().size() == 0) {
					System.out.println("You haven't opened any Checking account yet.");
				} else {
					int numberOfAccounts = 0;
					for (int i=0; i< customer.getCheckingAccounts().size(); i++) {
						if (customer.getCheckingAccounts().get(i).isCheckingAccountStatus() == false) {
							System.out.println("Pick ++> " + i + " <++ Your checking account ID " + customer.getCheckingAccounts().get(i).getCheckingAccountID() + " is not approved yet");
						} else {
							System.out.println("Pick ++> " + i + " <++ Your checking account ID "
									+ customer.getCheckingAccounts().get(i).getCheckingAccountID() 
									+ " has total balance: $"
									+ customer.getCheckingAccounts().get(i).getCheckingBalance());
						}
						numberOfAccounts++;
					}
					
					System.out.println("Numbers of account found: " + numberOfAccounts);
					int selectCheckingAccount = Integer.parseInt(scan.nextLine());
					
					
					if (selectCheckingAccount < numberOfAccounts) {

						if (customer.getCheckingAccounts().get(selectCheckingAccount).isCheckingAccountStatus() == false) {
							System.out.println("This account is not approved yet. Please come back later...");
						} else {
							System.out.println("You Selected Checking Account " + customer.getCheckingAccounts().get(selectCheckingAccount).getCheckingAccountID());
							System.out.println("1. Withdraw \n2. Deposit");
							String optionSelect = scan.nextLine();
							switch (optionSelect) {
								case "1":
									System.out.println("Please enter how much you want to withdraw: ");
									double withdrawAmount = Double.parseDouble(scan.nextLine());
									
									if (withdrawAmount <= 0 ) {
										System.out.println("You can't input negative values or 0");
										editBalance(customer);
									} else if (withdrawAmount < customer.getCheckingAccounts().get(selectCheckingAccount).getCheckingBalance()) {
										
										double totalWithdrawAmount = customer.getCheckingAccounts().get(selectCheckingAccount).getCheckingBalance() - withdrawAmount;
										customerController.editChecking(customer.getCheckingAccounts().get(selectCheckingAccount), totalWithdrawAmount);
										accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
									
									} else {
										System.out.println("You can't overwithdraw the balance. Your Checking balance isn't enough.");
										
									}
								case "2":
									System.out.println("Please enter how much you want to deposit: ");
									double depositAmount = Double.parseDouble(scan.nextLine());
									
									if (depositAmount <= 0 ) {
										System.out.println("You can't input negative values or 0");
										editBalance(customer);
									} else {
										double totalDepositAmount = customer.getCheckingAccounts().get(selectCheckingAccount).getCheckingBalance() + depositAmount;
										customerController.editChecking(customer.getCheckingAccounts().get(selectCheckingAccount), totalDepositAmount);
										accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
									}
								default:
									System.out.println("Invalid Option menu. Try again.");
									editBalance(customer);
							}
						}
						
					} else {
						System.out.println("This account does not exist, please try again");
						editBalance(customer);
					}
				}
				
				
			case "2":
				System.out.println("----Please Select Your Savings Account--------");
				System.out.println("----Please pick which account--------");
				if (customer.getSavingsAccounts().size() == 0) {
					System.out.println("You haven't opened any Savings account yet.");
				} else {
					int numberOfAccounts = 0;
					for (int i=0; i< customer.getSavingsAccounts().size(); i++) {
						if (customer.getSavingsAccounts().get(i).isSavingsAccountStatus() == false) {
							System.out.println("Pick ++> " + i + " <++ Your Savings account ID " + customer.getSavingsAccounts().get(i).getSavingsAccountID() + " is not approved yet");
						} else {
							System.out.println("Pick ++> " + i + " <++ Your Savings account ID "
									+ customer.getSavingsAccounts().get(i).getSavingsAccountID() 
									+ " has total balance: $"
									+ customer.getSavingsAccounts().get(i).getSavingsBalance());
						}
						numberOfAccounts++;
					}
					
					System.out.println("Numbers of account found: " + numberOfAccounts);
					int selectSavingsAccount = Integer.parseInt(scan.nextLine());
					
					
					if (selectSavingsAccount < numberOfAccounts) {

						if (customer.getSavingsAccounts().get(selectSavingsAccount).isSavingsAccountStatus() == false) {
							System.out.println("This account is not approved yet. Please come back later...");
						} else {
							System.out.println("You Selected Savings Account " + customer.getSavingsAccounts().get(selectSavingsAccount).getSavingsAccountID());
							System.out.println("1. Withdraw \n2. Deposit");
							String optionSelect = scan.nextLine();
							switch (optionSelect) {
								case "1":
									System.out.println("Please enter how much you want to withdraw: ");
									double withdrawAmount = Double.parseDouble(scan.nextLine());
									
									if (withdrawAmount <= 0 ) {
										System.out.println("You can't input negative values or 0");
										editBalance(customer);
									} else if (withdrawAmount < customer.getSavingsAccounts().get(selectSavingsAccount).getSavingsBalance()) {
										
										double totalWithdrawAmount = customer.getSavingsAccounts().get(selectSavingsAccount).getSavingsBalance() - withdrawAmount;
										customerController.editSavings(customer.getSavingsAccounts().get(selectSavingsAccount), totalWithdrawAmount);
										accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
									
									} else {
										System.out.println("You can't overwithdraw the balance. Your Savings balance isn't enough.");
										
									}
								case "2":
									System.out.println("Please enter how much you want to deposit: ");
									double depositAmount = Double.parseDouble(scan.nextLine());
									
									if (depositAmount <= 0 ) {
										System.out.println("You can't input negative values or 0");
										editBalance(customer);
									} else {
										double totalDepositAmount = customer.getSavingsAccounts().get(selectSavingsAccount).getSavingsBalance() + depositAmount;
										customerController.editSavings(customer.getSavingsAccounts().get(selectSavingsAccount), totalDepositAmount);
										accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
									}
								default:
									System.out.println("Invalid Option menu. Try again.");
									editBalance(customer);
							}
						}
						
					} else {
						System.out.println("This account does not exist, please try again");
						editBalance(customer);
					}
				}
			default:
				System.out.println("You've entered invalid value, please try again");
				accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
		}
	}

	public static void transferFund(AccountCustomer customer) {
		System.out.println("--- 3. Transfer Funds Menu ----");
		System.out.println("Select what type of accounts to transfer");
		System.out.println("1. Checkings \n2. Savings");
		String choice = scan.nextLine();
		switch (choice) {
			//From Checking
			case "1":
				System.out.println("----Please Select Your Checking Account--------");
				System.out.println("----Please pick which account--------");
				if (customer.getCheckingAccounts().size() == 0) {
					System.out.println("You haven't opened any Checking account yet.");
				} else {
					int numberOfAccounts = 0;
					for (int i=0; i< customer.getCheckingAccounts().size(); i++) {
						if (customer.getCheckingAccounts().get(i).isCheckingAccountStatus() == false) {
							System.out.println("Pick ++> " + i + " <++ Your checking account ID " + customer.getCheckingAccounts().get(i).getCheckingAccountID() + " is not approved yet");
						} else {
							System.out.println("Pick ++> " + i + " <++ Your checking account ID "
									+ customer.getCheckingAccounts().get(i).getCheckingAccountID() 
									+ " has total balance: $"
									+ customer.getCheckingAccounts().get(i).getCheckingBalance());
						}
						numberOfAccounts++;
					}
					
					int selectCheckingAccount = Integer.parseInt(scan.nextLine());
					
					
					if (selectCheckingAccount < numberOfAccounts) {
	
						if (customer.getCheckingAccounts().get(selectCheckingAccount).isCheckingAccountStatus() == false) {
							System.out.println("This account is not approved yet. Please come back later...");
						} else {
							System.out.println("You Selected Checking Account " + customer.getCheckingAccounts().get(selectCheckingAccount).getCheckingAccountID());
							System.out.println("System: Which type of account do you want transfer to?");
							System.out.println("1. Savings \n2. Checking");
							String accountTypeTransferTo = scan.nextLine();


							switch (accountTypeTransferTo) {
							
								//Checking -> Savings
								case "1":
									//List all available Savings account you can transfer to
									System.out.println("This is all Savings account available to you.");
									
									int numberOfAvailableSavingsAccounts = 0;
									for (int i = 0; i < customer.getSavingsAccounts().size(); i++) {
										if (customer.getSavingsAccounts().get(i).isSavingsAccountStatus() == false) {
											System.out.println("Pick ++> " + i + " <++ This Savings account ID " + customer.getSavingsAccounts().get(i).getSavingsAccountID() + " is not approved yet");
										} else {
											System.out.println("Pick ++> " + i + " <++ This Savings account ID "
													+ customer.getSavingsAccounts().get(i).getSavingsAccountID() 
													+ " has total balance: $"
													+ customer.getSavingsAccounts().get(i).getSavingsBalance());
										}
										numberOfAvailableSavingsAccounts++;
									}
									
									//Pick specific account you transfer to
									int selectSavingsToTransfer = Integer.parseInt(scan.nextLine());
									
									if (selectSavingsToTransfer > numberOfAvailableSavingsAccounts || selectSavingsToTransfer < 0) {
										System.out.println("Invalid account selection number. Try again");
										transferFund(customer);
									} else {
										System.out.println("Enter how much you want to transfer: ");
										double amountToTransfer = Double.parseDouble(scan.nextLine());
										
										if (customer.getCheckingAccounts().get(selectCheckingAccount).getCheckingBalance() > amountToTransfer) {
											
											customerController.transferCheckingToSavings(customer.getCheckingAccounts().get(selectCheckingAccount), customer.getSavingsAccounts().get(selectSavingsToTransfer), amountToTransfer);
											
											accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
										} else {
											log.warn(customer.getUsername() + "doesn't have enough balance to transfer");
											System.out.println("Your checking account isn't enough to transfer");
											transferFund(customer);
										}
									}
								
								//Checking -> Checking
								case "2":
									//List all available Checking account you can transfer to
									System.out.println("This is all Checking account available to you.");
									
									int numberOfAvailableCheckingAccounts = 0;
									for (int i = 0; i < customer.getCheckingAccounts().size(); i++) {
										if (customer.getCheckingAccounts().get(i).isCheckingAccountStatus() == false) {
											System.out.println("Pick ++> " + i + " <++ This Checking account ID " + customer.getCheckingAccounts().get(i).getCheckingAccountID() + " is not approved yet");
										} else if (customer.getCheckingAccounts().get(i).getCheckingAccountID() != customer.getCheckingAccounts().get(selectCheckingAccount).getCheckingAccountID()) {
											System.out.println("Pick ++> " + i + " <++ This Checking account ID "
													+ customer.getCheckingAccounts().get(i).getCheckingAccountID() 
													+ " has total balance: $"
													+ customer.getCheckingAccounts().get(i).getCheckingBalance());
										}
										numberOfAvailableCheckingAccounts++;
									}
									
									//Pick specific account you transfer to
									int selectCheckingToTransfer = Integer.parseInt(scan.nextLine());
									
									if (selectCheckingToTransfer > numberOfAvailableCheckingAccounts || selectCheckingToTransfer < 0) {
										System.out.println("Invalid account selection number. Try again");
										transferFund(customer);
									} else {
										System.out.println("Enter how much you want to transfer: ");
										double amountToTransfer = Double.parseDouble(scan.nextLine());
										
										if (customer.getCheckingAccounts().get(selectCheckingAccount).getCheckingBalance() > amountToTransfer) {
											
											customerController.transferCheckingToChecking(customer.getCheckingAccounts().get(selectCheckingAccount), customer.getCheckingAccounts().get(selectCheckingToTransfer), amountToTransfer);
											
											accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
										} else {
											log.warn(customer.getUsername() + "doesn't have enough balance to transfer");
											System.out.println("Your checking account isn't enough to transfer");
											transferFund(customer);
										}
									}
									
								default:
									System.out.println("Invalid Option menu. Try again.");
									transferFund(customer);
							}
						}
						
					} else {
						System.out.println("This account does not exist, please try again");
						transferFund(customer);
					}
				}
				
			//From Savings
			case "2":
				System.out.println("----Please Select Your Savings Account--------");
				System.out.println("----Please pick which account--------");
				if (customer.getSavingsAccounts().size() == 0) {
					System.out.println("You haven't opened any Savings account yet.");
				} else {
					int numberOfAccounts = 0;
					for (int i=0; i< customer.getSavingsAccounts().size(); i++) {
						if (customer.getSavingsAccounts().get(i).isSavingsAccountStatus() == false) {
							System.out.println("Pick ++> " + i + " <++ Your Savings account ID " + customer.getSavingsAccounts().get(i).getSavingsAccountID() + " is not approved yet");
						} else {
							System.out.println("Pick ++> " + i + " <++ Your Savings account ID "
									+ customer.getSavingsAccounts().get(i).getSavingsAccountID() 
									+ " has total balance: $"
									+ customer.getSavingsAccounts().get(i).getSavingsBalance());
						}
						numberOfAccounts++;
					}
					
					int selectSavingsAccount = Integer.parseInt(scan.nextLine());
					
					
					if (selectSavingsAccount < numberOfAccounts) {
	
						if (customer.getSavingsAccounts().get(selectSavingsAccount).isSavingsAccountStatus() == false) {
							System.out.println("This account is not approved yet. Please come back later...");
						} else {
							System.out.println("You Selected Checking Account " + customer.getSavingsAccounts().get(selectSavingsAccount).getSavingsAccountID());
							System.out.println("System: Which type of account do you want transfer to?");
							System.out.println("1. Checkings \n2. Savings");
							String accountTypeTransferTo = scan.nextLine();


							switch (accountTypeTransferTo) {
							
								//Savings -> Checking
								case "1":
									//List all available Checking account you can transfer to
									System.out.println("This is all Checking account available to you.");
									
									int numberOfAvailableCheckingAccounts = 0;
									for (int i = 0; i < customer.getCheckingAccounts().size(); i++) {
										if (customer.getCheckingAccounts().get(i).isCheckingAccountStatus() == false) {
											System.out.println("Pick ++> " + i + " <++ This Checking account ID " + customer.getSavingsAccounts().get(i).getSavingsAccountID() + " is not approved yet");
										} else {
											System.out.println("Pick ++> " + i + " <++ This Checking account ID "
													+ customer.getCheckingAccounts().get(i).getCheckingAccountID() 
													+ " has total balance: $"
													+ customer.getCheckingAccounts().get(i).getCheckingBalance());
										}
										numberOfAvailableCheckingAccounts++;
									}
									
									//Pick specific Checking account you transfer to
									int selectCheckingToTransfer = Integer.parseInt(scan.nextLine());
									
									if (selectCheckingToTransfer > numberOfAvailableCheckingAccounts || selectCheckingToTransfer < 0) {
										System.out.println("Invalid account selection number. Try again");
										transferFund(customer);
									} else {
										System.out.println("Enter how much you want to transfer: ");
										double amountToTransfer = Double.parseDouble(scan.nextLine());
										
										if (customer.getSavingsAccounts().get(selectSavingsAccount).getSavingsBalance() > amountToTransfer) {
											
											customerController.transferSavingsToChecking(customer.getCheckingAccounts().get(selectCheckingToTransfer), customer.getSavingsAccounts().get(selectSavingsAccount), amountToTransfer);
										
											accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
											
										} else {
											log.warn(customer.getUsername() + "doesn't have enough balance to transfer");
											System.out.println("Your checking account isn't enough to transfer");
											transferFund(customer);
										}
									}
								
								//Savings -> Savings
								case "2":
									//List all available Checking account you can transfer to
									System.out.println("This is all Checking account available to you.");
									
									int numberOfAvailableSavingsAccounts = 0;
									for (int i = 0; i < customer.getCheckingAccounts().size(); i++) {
										if (customer.getSavingsAccounts().get(i).isSavingsAccountStatus() == false) {
											System.out.println("Pick ++> " + i + " <++ This Savings account ID " + customer.getSavingsAccounts().get(i).getSavingsAccountID() + " is not approved yet");
										} else if (customer.getSavingsAccounts().get(i).getSavingsAccountID() != customer.getSavingsAccounts().get(selectSavingsAccount).getSavingsAccountID()) {
											System.out.println("Pick ++> " + i + " <++ This Savings account ID "
													+ customer.getSavingsAccounts().get(i).getSavingsAccountID() 
													+ " has total balance: $"
													+ customer.getSavingsAccounts().get(i).getSavingsBalance());
										}
										numberOfAvailableSavingsAccounts++;
									}
									
									//Pick specific account you transfer to
									int selectSavingsToTransfer = Integer.parseInt(scan.nextLine());
									
									if (selectSavingsToTransfer > numberOfAvailableSavingsAccounts || selectSavingsToTransfer < 0) {
										System.out.println("Invalid account selection number. Try again");
										transferFund(customer);
									} else {
										System.out.println("Enter how much you want to transfer: ");
										double amountToTransfer = Double.parseDouble(scan.nextLine());
										
										if (customer.getSavingsAccounts().get(selectSavingsAccount).getSavingsBalance() > amountToTransfer) {
											
											customerController.transferSavingsToSavings(customer.getSavingsAccounts().get(selectSavingsAccount), customer.getSavingsAccounts().get(selectSavingsToTransfer), amountToTransfer);
											accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
										} else {
											log.warn(customer.getUsername() + "doesn't have enough balance to transfer");
											System.out.println("Your checking account isn't enough to transfer");
											transferFund(customer);
										}
									}
									
								default:
									System.out.println("Invalid Option menu. Try again.");
									transferFund(customer);
							}
						}
						
					} else {
						System.out.println("This account does not exist, please try again");
						transferFund(customer);
					}
				}
				
			default:
				System.out.println("You've entered invalid value, please try again");
				accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
		}
	}
}
