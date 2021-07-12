package com.revature.repos;

import com.revature.models.AccountAdmin;

public interface AdminDAO {
	public AccountAdmin findAdmin(String username, String password);
	
}
