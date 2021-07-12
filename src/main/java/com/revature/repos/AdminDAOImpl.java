package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.AccountAdmin;
import com.revature.models.AccountEmployee;
import com.revature.models.Checking;
import com.revature.models.Savings;
import com.revature.utils.ConnectionUtils;

public class AdminDAOImpl implements AdminDAO {

	//Find Admin
	@Override
	public AccountAdmin findAdmin(String username, String password) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			AccountAdmin account = new AccountAdmin();
			
			while(result.next()) {
				account.setAccountID(result.getInt("account_id"));
				account.setUsername(result.getString("username"));
				account.setPassword(result.getString("password"));
				account.setFirstName(result.getString("first_name"));
				account.setLastName(result.getString("last_name"));
			}
			
			return account;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
