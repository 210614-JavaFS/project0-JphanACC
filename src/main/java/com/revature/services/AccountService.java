package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.AccountPageController;
import com.revature.controller.CustomerController;
import com.revature.controller.EmployeeController;
import com.revature.controller.EmployeePageController;
import com.revature.controller.MenuController;
import com.revature.models.AccountCustomer;
import com.revature.models.AccountEmployee;

public class AccountService {
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = LoggerFactory.getLogger(MenuController.class);
	
	private static AccountPageController accountPageController = new AccountPageController();
	private static EmployeePageController employeePageController = new EmployeePageController();
	
	private static EmployeeController employeeController = new EmployeeController();
	private static CustomerController customerController = new CustomerController();
	
	private static MenuController menuController = new MenuController();
	
	//Create New Account
	//Customer
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
	
	//Employee
	public static void createNewAccount(AccountEmployee employee) {
		System.out.println("--- 1. Account Creation Menu ----");
		System.out.println("1. Open New Checking Account \n2. Open New Savings Account \n3. Go back to previous menu");
		String choice = scan.nextLine();
		switch (choice) {
		case "1":
			employeeController.createCheckingAccount(employee);
			employeePageController.showEmployeePage(employeeController.findEmployee(employee.getUsername(), employee.getPassword()));
		case "2":
			employeeController.createSavingsAccount(employee);
			employeePageController.showEmployeePage(employeeController.findEmployee(employee.getUsername(), employee.getPassword()));
		case "3":
			System.out.println("Going back to Account User menu...");
			employeePageController.showEmployeePage(employeeController.findEmployee(employee.getUsername(), employee.getPassword()));
		default:
			System.out.println("You've entered invalid value, please try again");
			employeePageController.showEmployeePage(employeeController.findEmployee(employee.getUsername(), employee.getPassword()));
		}
	}

	//Edit Balance
	//Customer
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
										log.warn("User attempted to put in negative values or 0.");
										editBalance(customer);
									} else if (withdrawAmount < customer.getCheckingAccounts().get(selectCheckingAccount).getCheckingBalance()) {
										
										double totalWithdrawAmount = customer.getCheckingAccounts().get(selectCheckingAccount).getCheckingBalance() - withdrawAmount;
										customerController.editChecking(customer.getCheckingAccounts().get(selectCheckingAccount), totalWithdrawAmount);
										accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
									
									} else {
										System.out.println("You can't overwithdraw the balance. Your Checking balance isn't enough.");
										log.warn("User attempted to overwithdraw the balance.");
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
										log.warn("User attempted to put in negative value or 0.");
										editBalance(customer);
									} else if (withdrawAmount < customer.getSavingsAccounts().get(selectSavingsAccount).getSavingsBalance()) {
										
										double totalWithdrawAmount = customer.getSavingsAccounts().get(selectSavingsAccount).getSavingsBalance() - withdrawAmount;
										customerController.editSavings(customer.getSavingsAccounts().get(selectSavingsAccount), totalWithdrawAmount);
										accountPageController.showAccountCustomerPage(customerController.findCustomer(customer.getUsername(), customer.getPassword()));
									
									} else {
										System.out.println("You can't overwithdraw the balance. Your Savings balance isn't enough.");
										log.warn("User attempted to overwithdraw the balance.");
										
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

	//Employee
	public static void editBalance(AccountEmployee employee) {
		System.out.println("--- 2. Withdraw/Deposit Menu ----");
		System.out.println("1. Checkings \n2. Savings");
		String choice = scan.nextLine();
		switch (choice) {
	
			//Checking
			case "1":
				System.out.println("----Please Select Your Checking Account--------");
				System.out.println("----Please pick which account--------");
				if (employee.getCheckingAccounts().size() == 0) {
					System.out.println("You haven't opened any Checking account yet.");
				} else {
					int numberOfAccounts = 0;
					for (int i=0; i< employee.getCheckingAccounts().size(); i++) {
						if (employee.getCheckingAccounts().get(i).isCheckingAccountStatus() == false) {
							System.out.println("Pick ++> " + i + " <++ Your checking account ID " + employee.getCheckingAccounts().get(i).getCheckingAccountID() + " is not approved yet");
						} else {
							System.out.println("Pick ++> " + i + " <++ Your checking account ID "
									+ employee.getCheckingAccounts().get(i).getCheckingAccountID() 
									+ " has total balance: $"
									+ employee.getCheckingAccounts().get(i).getCheckingBalance());
						}
						numberOfAccounts++;
					}
					
					System.out.println("Numbers of account found: " + numberOfAccounts);
					int selectCheckingAccount = Integer.parseInt(scan.nextLine());
					
					
					if (selectCheckingAccount < numberOfAccounts) {

						if (employee.getCheckingAccounts().get(selectCheckingAccount).isCheckingAccountStatus() == false) {
							System.out.println("This account is not approved yet. Please come back later...");
						} else {
							System.out.println("You Selected Checking Account " + employee.getCheckingAccounts().get(selectCheckingAccount).getCheckingAccountID());
							System.out.println("1. Withdraw \n2. Deposit");
							String optionSelect = scan.nextLine();
							switch (optionSelect) {
								case "1":
									System.out.println("Please enter how much you want to withdraw: ");
									double withdrawAmount = Double.parseDouble(scan.nextLine());
									
									if (withdrawAmount <= 0 ) {
										System.out.println("You can't input negative values or 0");
										editBalance(employee);
									} else if (withdrawAmount < employee.getCheckingAccounts().get(selectCheckingAccount).getCheckingBalance()) {
										
										double totalWithdrawAmount = employee.getCheckingAccounts().get(selectCheckingAccount).getCheckingBalance() - withdrawAmount;
										employeeController.editChecking(employee.getCheckingAccounts().get(selectCheckingAccount), totalWithdrawAmount);
										employeePageController.showEmployeePage(employeeController.findEmployee(employee.getUsername(), employee.getPassword()));
									
									} else {
										System.out.println("You can't overwithdraw the balance. Your Checking balance isn't enough.");
										log.warn("User attempted to overwithdraw the balance.");
									}
								case "2":
									System.out.println("Please enter how much you want to deposit: ");
									double depositAmount = Double.parseDouble(scan.nextLine());
									
									if (depositAmount <= 0 ) {
										System.out.println("You can't input negative values or 0");
										log.warn("User attempted to put in negative value or 0");
										editBalance(employee);
									} else {
										double totalDepositAmount = employee.getCheckingAccounts().get(selectCheckingAccount).getCheckingBalance() + depositAmount;
										employeeController.editChecking(employee.getCheckingAccounts().get(selectCheckingAccount), totalDepositAmount);
										employeePageController.showEmployeePage(employeeController.findEmployee(employee.getUsername(), employee.getPassword()));
									}
								default:
									System.out.println("Invalid Option menu. Try again.");
									editBalance(employee);
							}
						}
						
					} else {
						System.out.println("This account does not exist, please try again");
						editBalance(employee);
					}
				}
				
				
			case "2":
				System.out.println("----Please Select Your Savings Account--------");
				System.out.println("----Please pick which account--------");
				if (employee.getSavingsAccounts().size() == 0) {
					System.out.println("You haven't opened any Savings account yet.");
				} else {
					int numberOfAccounts = 0;
					for (int i=0; i< employee.getSavingsAccounts().size(); i++) {
						if (employee.getSavingsAccounts().get(i).isSavingsAccountStatus() == false) {
							System.out.println("Pick ++> " + i + " <++ Your Savings account ID " + employee.getSavingsAccounts().get(i).getSavingsAccountID() + " is not approved yet");
						} else {
							System.out.println("Pick ++> " + i + " <++ Your Savings account ID "
									+ employee.getSavingsAccounts().get(i).getSavingsAccountID() 
									+ " has total balance: $"
									+ employee.getSavingsAccounts().get(i).getSavingsBalance());
						}
						numberOfAccounts++;
					}
					
					System.out.println("Numbers of account found: " + numberOfAccounts);
					int selectSavingsAccount = Integer.parseInt(scan.nextLine());
					
					
					if (selectSavingsAccount < numberOfAccounts) {

						if (employee.getSavingsAccounts().get(selectSavingsAccount).isSavingsAccountStatus() == false) {
							System.out.println("This account is not approved yet. Please come back later...");
						} else {
							System.out.println("You Selected Savings Account " + employee.getSavingsAccounts().get(selectSavingsAccount).getSavingsAccountID());
							System.out.println("1. Withdraw \n2. Deposit");
							String optionSelect = scan.nextLine();
							switch (optionSelect) {
								case "1":
									System.out.println("Please enter how much you want to withdraw: ");
									double withdrawAmount = Double.parseDouble(scan.nextLine());
									
									if (withdrawAmount <= 0 ) {
										System.out.println("You can't input negative values or 0");
										log.warn("User attempted to put in negative value or 0");
										editBalance(employee);
									} else if (withdrawAmount < employee.getSavingsAccounts().get(selectSavingsAccount).getSavingsBalance()) {
										
										double totalWithdrawAmount = employee.getSavingsAccounts().get(selectSavingsAccount).getSavingsBalance() - withdrawAmount;
										employeeController.editSavings(employee.getSavingsAccounts().get(selectSavingsAccount), totalWithdrawAmount);
										employeePageController.showEmployeePage(employeeController.findEmployee(employee.getUsername(), employee.getPassword()));
									
									} else {
										System.out.println("You can't overwithdraw the balance. Your Savings balance isn't enough.");
										log.warn("User attempted to overwithdraw the balance.");
									}
								case "2":
									System.out.println("Please enter how much you want to deposit: ");
									double depositAmount = Double.parseDouble(scan.nextLine());
									
									if (depositAmount <= 0 ) {
										System.out.println("You can't input negative values or 0");
										editBalance(employee);
									} else {
										double totalDepositAmount = employee.getSavingsAccounts().get(selectSavingsAccount).getSavingsBalance() + depositAmount;
										employeeController.editSavings(employee.getSavingsAccounts().get(selectSavingsAccount), totalDepositAmount);
										employeePageController.showEmployeePage(employeeController.findEmployee(employee.getUsername(), employee.getPassword()));
									}
								default:
									System.out.println("Invalid Option menu. Try again.");
									editBalance(employee);
							}
						}
						
					} else {
						System.out.println("This account does not exist, please try again");
						editBalance(employee);
					}
				}
			default:
				System.out.println("You've entered invalid value, please try again");
				employeePageController.showEmployeePage(employeeController.findEmployee(employee.getUsername(), employee.getPassword()));
		}
	}
	
	//TransferFund
	//Customer
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

	//Employee
	public static void transferFund(AccountEmployee account) {
		System.out.println("--- 3. Transfer Funds Menu ----");
		System.out.println("Select what type of accounts to transfer");
		System.out.println("1. Checkings \n2. Savings");
		String choice = scan.nextLine();
		switch (choice) {
			//From Checking
			case "1":
				System.out.println("----Please Select Your Checking Account--------");
				System.out.println("----Please pick which account--------");
				if (account.getCheckingAccounts().size() == 0) {
					System.out.println("You haven't opened any Checking account yet.");
				} else {
					int numberOfAccounts = 0;
					//All available Checking accounts to choose:
					List<Integer> selectCheckingIDList = new ArrayList<Integer>();
					
					//Checking accounts from Employees
					for (int i=0; i< account.getCheckingAccounts().size(); i++) {
						if (account.getCheckingAccounts().get(i).isCheckingAccountStatus() != false) {
							System.out.println("Pick ++> " + numberOfAccounts + " <++ Your checking account ID "
									+ account.getCheckingAccounts().get(i).getCheckingAccountID() 
									+ " has total balance: $"
									+ account.getCheckingAccounts().get(i).getCheckingBalance());
							numberOfAccounts++;
							selectCheckingIDList.add(account.getCheckingAccounts().get(i).getCheckingAccountID());
						}
					}
					//Checking accounts from Customers
					//Get all customer
					List<AccountCustomer> allCustomerList = customerController.showAllCustomers();
					
					for (int customerListIndex = 0; customerListIndex < allCustomerList.size(); customerListIndex ++) {
						for (int customerCheckingIndex = 0; customerCheckingIndex < allCustomerList.get(customerListIndex).getCheckingAccounts().size(); customerCheckingIndex++) {
							if (allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).isCheckingAccountStatus() != false) {
								System.out.println("Pick ++> " + numberOfAccounts + " <++ Customer's username "+ allCustomerList.get(customerListIndex).getUsername() + " with Checking account ID "
										+ allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).getCheckingAccountID()
										+ " has total balance: $"
										+ allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).getCheckingBalance());
								numberOfAccounts++;
								selectCheckingIDList.add(allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).getCheckingAccountID());
							}
						}
					}
					
					int selectCheckingAccount = Integer.parseInt(scan.nextLine());
					
					
					if (selectCheckingAccount < numberOfAccounts) {

							System.out.println("System: Which type of account do you want transfer to?");
							System.out.println("1. Savings \n2. Checking");
							String accountTypeTransferTo = scan.nextLine();
							
							switch (accountTypeTransferTo) {
								//CheckingtoSavings
								case "1":
									List<Integer> transferSavingsIDList = new ArrayList<Integer>();
									int numberOfSavingsToTransfer = 0;
									
									//this employee's savings
									for (int i=0; i< account.getSavingsAccounts().size(); i++) {
										if (account.getSavingsAccounts().get(i).isSavingsAccountStatus() != false) {
											System.out.println("Pick ++> " + numberOfAccounts + " <++ Your checking account ID "
													+ account.getSavingsAccounts().get(i).getSavingsAccountID() 
													+ " has total balance: $"
													+ account.getSavingsAccounts().get(i).getSavingsBalance());
											numberOfSavingsToTransfer++;
											transferSavingsIDList.add(account.getCheckingAccounts().get(i).getCheckingAccountID());
										}
									}
									
									//all customers' savings
									for (int customerListIndex = 0; customerListIndex < allCustomerList.size(); customerListIndex ++) {
										for (int customerSavingsIndex = 0; customerSavingsIndex < allCustomerList.get(customerListIndex).getCheckingAccounts().size(); customerSavingsIndex++) {
											if (allCustomerList.get(customerListIndex).getCheckingAccount(customerSavingsIndex).isCheckingAccountStatus() != false) {
												System.out.println("Pick ++> " + numberOfSavingsToTransfer + " <++ Customer's username "+ allCustomerList.get(customerListIndex).getUsername() + " with Savings account ID "
														+ allCustomerList.get(customerListIndex).getSavingsAccount(customerSavingsIndex).getSavingsAccountID()
														+ " has total balance: $"
														+ allCustomerList.get(customerListIndex).getSavingsAccount(customerSavingsIndex).getSavingsBalance());
												numberOfSavingsToTransfer++;
												transferSavingsIDList.add(allCustomerList.get(customerListIndex).getSavingsAccount(customerSavingsIndex).getSavingsAccountID());
											}
										}
									}
									
									int CheckingToSavingsChoice = Integer.parseInt(scan.nextLine());
									
									try {
										if (CheckingToSavingsChoice < transferSavingsIDList.size() || CheckingToSavingsChoice > 0) {
											
										} else {
											System.out.println("Invalid option number. Try again");
											log.warn("User attempted to pick invalid option number");
										}
									} catch (Exception e) {
										e.printStackTrace();
										log.debug("Checking to Savings transaction failed");
									}
									
								//CheckingtoCheckings
								case "2":
									List<Integer> transferCheckingIDList = new ArrayList<Integer>();
									int numberOfCheckingToTransfer = 0;
									
									//this employee's Checking
									for (int i=0; i< account.getCheckingAccounts().size(); i++) {
										if (account.getCheckingAccounts().get(i).isCheckingAccountStatus() != false) {
											System.out.println("Pick ++> " + numberOfAccounts + " <++ Your checking account ID "
													+ account.getCheckingAccounts().get(i).getCheckingAccountID() 
													+ " has total balance: $"
													+ account.getCheckingAccounts().get(i).getCheckingBalance());
											numberOfCheckingToTransfer++;
											transferCheckingIDList.add(account.getCheckingAccounts().get(i).getCheckingAccountID());
										}
									}
									//All Customer's Checking
									
									for (int customerListIndex = 0; customerListIndex < allCustomerList.size(); customerListIndex ++) {
										for (int customerCheckingIndex = 0; customerCheckingIndex < allCustomerList.get(customerListIndex).getCheckingAccounts().size(); customerCheckingIndex++) {
											if (allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).isCheckingAccountStatus() != false) {
												System.out.println("Pick ++> " + numberOfAccounts + " <++ Customer's username "+ allCustomerList.get(customerListIndex).getUsername() + " with Checking account ID "
														+ allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).getCheckingAccountID()
														+ " has total balance: $"
														+ allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).getCheckingBalance());
												numberOfCheckingToTransfer++;
												transferCheckingIDList.add(allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).getCheckingAccountID());
											}
										}
									}
									
								default:
									System.out.println("Invalid Option menu. Try again.");
									transferFund(account);
							}
							
					}

				}
			case "2":
				
			default:
				System.out.println("You've entered invalid value, please try again");
				employeePageController.showEmployeePage(employeeController.findEmployee(employee.getUsername(), employee.getPassword()));
		} 
	}

	//Approve Accounts
	//Employee
	public static void approveAccount(AccountEmployee account) {
		System.out.println("--- 4. Approve Accounts Menu ----");
		System.out.println("Select what type of accounts to approved");
		System.out.println("1. Checkings \n2. Savings");
		
		List<AccountCustomer> allCustomerList = customerController.showAllCustomers();
		String choice = scan.nextLine();
		
		switch (choice) {
			case "1":
				int numberOfCheckingAccounts = 0;
				List<Integer> checkingAccountIDs = new ArrayList<Integer>();
				
				//List of unapproved Customer Checkings
				System.out.println("+++++++++++++ List of unapproved Customer Checkings ++++++++++++");
				for (int customerListIndex = 0; customerListIndex < allCustomerList.size(); customerListIndex ++) {
					for (int customerCheckingIndex = 0; customerCheckingIndex < allCustomerList.get(customerListIndex).getCheckingAccounts().size(); customerCheckingIndex++) {
						if (allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).isCheckingAccountStatus() == false) {
							System.out.println("Pick ++> " + numberOfCheckingAccounts + " <++ Customer's username "+ allCustomerList.get(customerListIndex).getUsername() + " with Checking account ID "
									+ allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).getCheckingAccountID());
							numberOfCheckingAccounts++;
							checkingAccountIDs.add(allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).getCheckingAccountID());
						}
					}
				}
				
				int userCheckingSelect = Integer.parseInt(scan.nextLine());
				
				if (userCheckingSelect > checkingAccountIDs.size()) {
					System.out.println("You entered invalid value. Try again.");
					log.warn("User entered a number more than Checking account's size");
					approveAccount(account);
				} else if (userCheckingSelect < 0) {
					System.out.println("You entered invalid value. Try again.");
					log.warn("User entered negative number");
					approveAccount(account);
				} else {
					int selectedID = checkingAccountIDs.get(userCheckingSelect);
					employeeController.approveChecking(selectedID);
					approveAccount(account);
				}
				
			case "2":
				int numberOfSavingsAccounts = 0;
				List<Integer> savingsAccountIDs = new ArrayList<Integer>();
				
				//List of unapproved Customer Savings
				System.out.println("+++++++++++++ List of unapproved Customer Savings ++++++++++++");
				for (int customerListIndex = 0; customerListIndex < allCustomerList.size(); customerListIndex ++) {
					for (int customerSavingsIndex = 0; customerSavingsIndex < allCustomerList.get(customerListIndex).getSavingsAccounts().size(); customerSavingsIndex++) {
						if (allCustomerList.get(customerListIndex).getSavingsAccount(customerSavingsIndex).isSavingsAccountStatus() == false) {
							System.out.println("Pick ++> " + numberOfSavingsAccounts + " <++ Customer's username "+ allCustomerList.get(customerListIndex).getUsername() + " with Savings account ID "
									+ allCustomerList.get(customerListIndex).getSavingsAccount(customerSavingsIndex).getSavingsAccountID());
							numberOfSavingsAccounts++;
							savingsAccountIDs.add(allCustomerList.get(customerListIndex).getSavingsAccount(customerSavingsIndex).getSavingsAccountID());
						}
					}
				}
				
				int userSavingsSelect = Integer.parseInt(scan.nextLine());
				
				if (userSavingsSelect > savingsAccountIDs.size()) {
					System.out.println("You entered invalid value. Try again.");
					log.warn("User entered a number more than Savings account's size");
					approveAccount(account);
				} else if (userSavingsSelect < 0) {
					System.out.println("You entered invalid value. Try again.");
					log.warn("User entered negative number");
					approveAccount(account);
				} else {
					int selectedID = savingsAccountIDs.get(userSavingsSelect);					
					employeeController.approveSavings(selectedID);
					approveAccount(account);	
				}
				
			default:
				System.out.println("You've entered invalid value, please try again");
				employeePageController.showEmployeePage(employeeController.findEmployee(account.getUsername(), account.getPassword()));					
		}

	}

	//Deny Accounts
	//Employee
	public static void cancelAccount(AccountEmployee account) {
		System.out.println("--- 4. Cancelling Accounts Menu ----");
		System.out.println("Select what type of accounts to cancel");
		System.out.println("1. Checkings \n2. Savings");
		
		List<AccountCustomer> allCustomerList = customerController.showAllCustomers();
		String choice = scan.nextLine();
		
		switch (choice) {
			case "1":
				int numberOfCheckingAccounts = 0;
				List<Integer> checkingAccountIDs = new ArrayList<Integer>();
				
				//List of Customer Checkings
				System.out.println("+++++++++++++ List of Customer Checkings ++++++++++++");
				for (int customerListIndex = 0; customerListIndex < allCustomerList.size(); customerListIndex ++) {
					for (int customerCheckingIndex = 0; customerCheckingIndex < allCustomerList.get(customerListIndex).getCheckingAccounts().size(); customerCheckingIndex++) {
						System.out.println("Pick ++> " + numberOfCheckingAccounts + " <++ Customer's username "+ allCustomerList.get(customerListIndex).getUsername() + " with Checking account ID "
								+ allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).getCheckingAccountID() 
								+ ". Status: " + allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).isCheckingAccountStatus());
						numberOfCheckingAccounts++;
						checkingAccountIDs.add(allCustomerList.get(customerListIndex).getCheckingAccount(customerCheckingIndex).getCheckingAccountID());
						
					}
				}
				
				int userCheckingSelect = Integer.parseInt(scan.nextLine());
		
				if (userCheckingSelect > checkingAccountIDs.size()) {
					System.out.println("You entered invalid value. Try again.");
					log.warn("User entered a number more than Checking account's size");
					cancelAccount(account);
				} else if (userCheckingSelect < 0) {
					System.out.println("You entered invalid value. Try again.");
					log.warn("User entered negative number");
					cancelAccount(account);
				} else {
					int selectedID = checkingAccountIDs.get(userCheckingSelect);
					employeeController.deleteChecking(selectedID);
					cancelAccount(account);
				}
				
			case "2":
				int numberOfSavingsAccounts = 0;
				List<Integer> savingsAccountIDs = new ArrayList<Integer>();
				
				//List of Customer Savings
				System.out.println("+++++++++++++ List of unapproved Customer Savings ++++++++++++");
				for (int customerListIndex = 0; customerListIndex < allCustomerList.size(); customerListIndex ++) {
					for (int customerSavingsIndex = 0; customerSavingsIndex < allCustomerList.get(customerListIndex).getSavingsAccounts().size(); customerSavingsIndex++) {
						
						System.out.println("Pick ++> " + numberOfSavingsAccounts + " <++ Customer's username "+ allCustomerList.get(customerListIndex).getUsername() + " with Savings account ID "
								+ allCustomerList.get(customerListIndex).getSavingsAccount(customerSavingsIndex).getSavingsAccountID()
								+ ". Status: " + allCustomerList.get(customerListIndex).getSavingsAccount(customerSavingsIndex).isSavingsAccountStatus());
						numberOfSavingsAccounts++;
						savingsAccountIDs.add(allCustomerList.get(customerListIndex).getSavingsAccount(customerSavingsIndex).getSavingsAccountID());
						
					}
				}
				
				int userSavingsSelect = Integer.parseInt(scan.nextLine());
				
				if (userSavingsSelect > savingsAccountIDs.size()) {
					System.out.println("You entered invalid value. Try again.");
					log.warn("User entered a number more than Savings account's size");
					cancelAccount(account);
				} else if (userSavingsSelect < 0) {
					System.out.println("You entered invalid value. Try again.");
					log.warn("User entered negative number");
					cancelAccount(account);
				} else {
					int selectedID = savingsAccountIDs.get(userSavingsSelect);					
					employeeController.deleteSavings(selectedID);
					cancelAccount(account);
				}
				
			default:
				System.out.println("You've entered invalid value, please try again");
				employeePageController.showEmployeePage(employeeController.findEmployee(account.getUsername(), account.getPassword()));					
		}

	}
}
