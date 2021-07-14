package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.AccountEmployee;


public class EmployeeServiceTest {
	//test Login
	public static EmployeeService employeeService;
	public static AccountEmployee accountEmployee;
	public static AccountEmployee accountEmployeeAdd;
	
	public static String username;
	public static String password;
	public static String firstName;
	public static String lastName;
	public static boolean result;
	public static int savings;
	public static int savings2;
	
	public static double amount;
	
	@BeforeAll
	public static void setEmployeeServices() {
		employeeService = new EmployeeService();
		accountEmployee = new AccountEmployee();
	}
	
	@BeforeEach
	public void setLoginDetails() {
		System.out.println("Set Test Data for Login");
		username = "employee1";
		password = "password";
		firstName = "James";
		lastName = "Bond";
		
		accountEmployeeAdd = new AccountEmployee(username,password,firstName,lastName);
	}
	
	@BeforeEach
	public void setUserToCreateAccount() {
		System.out.println("Insert Employee details");
		
	}
	
	@AfterEach
	public void clearAccount() {
		accountEmployeeAdd = null;
	}
	
	
	//test Login
	@Test
	public void testLogin() {
		System.out.println("TC1: Employee Login");
		
		assertTrue(employeeService.findEmployee(username, password) != null);
	}
	
	//test add Employee
//	@Test
//	public void testAddEmployee() {
//		System.out.println("TC2: Adding Employee");
//		
//		assertTrue(employeeService.addEmployee(accountEmployeeAdd));
//	}
	
	//test create Checking
//	@Test
//	public void testCreateChecking() {
//		System.out.println("TC3: Create a Checking account");
//		assertTrue(employeeService.createCheckingAccount(accountEmployee));
//	}
	
}
