package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.AccountAdmin;

public class AdminServiceTest {
	public static AdminService adminService;
	public static AccountAdmin accountAdmin;
	
	public static String username;
	public static String password;
	public static String firstName;
	public static String lastName;
	public static boolean result;
	
	@BeforeAll
	public static void setAdminServices() {
		adminService = new AdminService();
		accountAdmin = new AccountAdmin();
	}
	
	@BeforeEach
	public void setLoginDetails() {
		System.out.println("Set Test Data for Login");
		username = "admin";
		password = "password";

	}
	
	//test Login
	@Test
	public void testFindAdmin() {
		System.out.println("TC1: Employee Login");
		
		assertTrue(adminService.findAdmin(username, password) != null);
	}
}
