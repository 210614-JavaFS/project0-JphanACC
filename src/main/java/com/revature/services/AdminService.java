package com.revature.services;

import com.revature.models.AccountAdmin;
import com.revature.repos.AdminDAO;
import com.revature.repos.AdminDAOImpl;

public class AdminService {
	private static AdminDAO adminDAO = new AdminDAOImpl();
	
	public AccountAdmin findAdmin(String username, String password) {
		return adminDAO.findAdmin(username, password);
	}
}
