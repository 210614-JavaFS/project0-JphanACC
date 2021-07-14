package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.AccountCustomer;

public class CustomerServiceTest {
	public static CustomerService customerService;
	public static AccountCustomer accountCustomer;
	
	public static String username;
	public static String password;
	public static String firstName;
	public static String lastName;
	public static boolean result;
	
	@BeforeEach
	public void setCustomerServices() {
		customerService = new CustomerService();
		accountCustomer = new AccountCustomer();
	}
	
	@BeforeEach
	public void setLoginDetails() {
		System.out.println("Set Test Data for Login");
		username = "customer1";
		password = "password1";

		//login
		
	}
	
	//test Login
	@Test
	public void testLogin() {
		System.out.println("TC1: Employee Login");
		
		assertTrue(customerService.findCustomer(username, password) != null);
	}
}
