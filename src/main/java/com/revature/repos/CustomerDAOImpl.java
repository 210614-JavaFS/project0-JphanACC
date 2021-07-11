package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.AccountCustomer;
import com.revature.models.Checking;
import com.revature.models.Savings;
import com.revature.utils.ConnectionUtils;

public class CustomerDAOImpl implements CustomerDAO {
	private static CustomerDAO customerDAO = new CustomerDAOImpl();
	
	//Find All()
	@Override
	public List<AccountCustomer> findAll() {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "SELECT * FROM customers";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<AccountCustomer> list = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				AccountCustomer customer = new AccountCustomer();
				
				customer.setAccountID(result.getInt("account_id"));
				customer.setUsername(result.getString("username"));
				customer.setFirstName(result.getString("first_name"));
				customer.setLastName(result.getString("last_name"));
//
//				customer.setCheckingBalance(result.getInt("checking_balance"));
//				customer.setCheckingAccountStatus(result.getBoolean("checking_account_status"));
//				customer.setSavingsBalance(result.getInt("savings_balance"));
//				customer.setSavingsAccountStatus(result.getBoolean("savings_account_status"));
				
				list.add(customer);
				
			}
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//Find Customer
	@Override
	public AccountCustomer findCustomer(String username, String password) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "SELECT * FROM customers WHERE username = ? AND password = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			AccountCustomer accountCustomer = new AccountCustomer();
			
			while(result.next()) {
				accountCustomer.setAccountID(result.getInt("account_id"));
				accountCustomer.setUsername(result.getString("username"));
//				accountCustomer.setPassword(result.getString("password"));
				accountCustomer.setPassword(result.getString("password"));
				accountCustomer.setFirstName(result.getString("first_name"));
				accountCustomer.setLastName(result.getString("last_name"));
			}
			
			//Retrieve Checking
			String sqlChecking = "SELECT * FROM customers INNER JOIN checking_balances ON (customers.account_id = checking_balances.customer_id) WHERE customers.account_id = ?";
			PreparedStatement statementChecking = conn.prepareStatement(sqlChecking);
			
			statementChecking.setInt(1, accountCustomer.getAccountID());
			ResultSet resultChecking = statementChecking.executeQuery();
			
			while (resultChecking.next()) {
				
//				Integer checkingAccountID = resultChecking.getInt("checking_account_id");
				
				Checking checkingAccount = new Checking();
				
				checkingAccount.setCheckingAccountID(resultChecking.getInt("checking_account_id"));
				checkingAccount.setCheckingBalance(resultChecking.getDouble("checking_balance"));
				checkingAccount.setCheckingAccountStatus(resultChecking.getBoolean("checking_account_status"));
				
				accountCustomer.addCheckingAccount(checkingAccount);
			}
			
			//Retrieve Savings
			String sqlSavings = "SELECT * FROM customers INNER JOIN savings_balances ON (customers.account_id = savings_balances.customer_id) WHERE customers.account_id = ?";
			PreparedStatement statementSavings = conn.prepareStatement(sqlSavings);
			
			statementSavings.setInt(1, accountCustomer.getAccountID());
			ResultSet resultSavings = statementSavings.executeQuery();
			
			while (resultSavings.next()) {
				
//				Integer checkingAccountID = resultChecking.getInt("checking_account_id");
				
				Savings savingsAccount = new Savings();
				
				savingsAccount.setSavingsAccountID(resultSavings.getInt("savings_account_id"));
				savingsAccount.setSavingsBalance(resultSavings.getDouble("savings_balance"));
				savingsAccount.setSavingsAccountStatus(resultSavings.getBoolean("savings_account_status"));
				
				accountCustomer.addSavingsAccount(savingsAccount);
			}
			
			return accountCustomer;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//Add Customer
	@Override
	public boolean addCustomer(AccountCustomer customer) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "INSERT INTO customers(username, password, first_name, last_name)"
				+ "VALUES (?,?,?,?)";
		
			PreparedStatement statement = conn.prepareStatement(sql);
		
			int index = 0;
			statement.setString(++index, customer.getUsername());
			statement.setString(++index, customer.getPassword());
			statement.setString(++index, customer.getFirstName());
			statement.setString(++index, customer.getLastName());
			statement.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}

	//Create Checking Account
	@Override
	public boolean createCheckingAccount(AccountCustomer customer) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "INSERT INTO checking_balances(customer_id) VALUES (?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
		
			statement.setInt(1, customer.getAccountID());
			statement.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//Create Savings Account
	@Override
	public boolean createSavingsAccount(AccountCustomer customer) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "INSERT INTO savings_balances(customer_id) VALUES (?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
		
			statement.setInt(1, customer.getAccountID());
			statement.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	//Edit Checking Account
	@Override
	public boolean editChecking(Checking checking, double amount ) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "UPDATE checking_balances SET checking_balance = ? WHERE checking_account_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
		
			statement.setInt(2, checking.getCheckingAccountID());
			statement.setDouble(1, amount);
			statement.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//Edit Savings Account
	@Override
	public boolean editSavings(Savings savings, double amount) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "UPDATE savings_balances SET savings_balance = ? WHERE savings_account_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
		
			statement.setInt(2, savings.getSavingsAccountID());
			statement.setDouble(1, amount);
			statement.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	//Checking to Savings
	@Override
	public boolean transferCheckingToSavings(Checking checking, Savings savings, double amount) {
		int checkingAccountID = checking.getCheckingAccountID();
		int savingsAccountID = savings.getSavingsAccountID();
		
		double checkingBalance = checking.getCheckingBalance();
		double savingsBalance = savings.getSavingsBalance();
		
		try(Connection conn = ConnectionUtils.getConnection()){
			String updateCheckingSQL = "UPDATE checking_balances SET checking_balance = ? WHERE checking_account_id = ?;";
			String updateSavingsSQL = "UPDATE savings_balances SET savings_balance = ? WHERE savings_account_id = ?;";
			
			PreparedStatement statementCheckingUpdate = conn.prepareStatement(updateCheckingSQL);
			PreparedStatement statementSavingsUpdate = conn.prepareStatement(updateSavingsSQL);
		
			statementCheckingUpdate.setInt(2, checkingAccountID);
			statementCheckingUpdate.setDouble(1, checkingBalance - amount);
			statementCheckingUpdate.execute();
			
			statementSavingsUpdate.setInt(2, savingsAccountID);
			statementSavingsUpdate.setDouble(1, savingsBalance + amount);
			statementSavingsUpdate.execute();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Savings to Checking
	@Override
	public boolean transferSavingsToChecking(Checking checking, Savings savings, double amount) {
		int checkingAccountID = checking.getCheckingAccountID();
		int savingsAccountID = savings.getSavingsAccountID();
		
		double checkingBalance = checking.getCheckingBalance();
		double savingsBalance = savings.getSavingsBalance();
		
		try(Connection conn = ConnectionUtils.getConnection()){
			String updateCheckingSQL = "UPDATE checking_balances SET checking_balance = ? WHERE checking_account_id = ?;";
			String updateSavingsSQL = "UPDATE savings_balances SET savings_balance = ? WHERE savings_account_id = ?;";
			
			PreparedStatement statementCheckingUpdate = conn.prepareStatement(updateCheckingSQL);
			PreparedStatement statementSavingsUpdate = conn.prepareStatement(updateSavingsSQL);
		
			statementCheckingUpdate.setInt(2, checkingAccountID);
			statementCheckingUpdate.setDouble(1, checkingBalance + amount);
			statementCheckingUpdate.execute();
			
			statementSavingsUpdate.setInt(2, savingsAccountID);
			statementSavingsUpdate.setDouble(1, savingsBalance - amount);
			statementSavingsUpdate.execute();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Checking to Checking
	@Override
	public boolean transferCheckingToChecking(Checking checking, Checking checking2, double amount) {
		int checkingAccountID = checking.getCheckingAccountID();
		int checking2AccountID = checking2.getCheckingAccountID();
		
		double checkingBalance = checking.getCheckingBalance();
		double checking2Balance = checking2.getCheckingBalance();
		
		try(Connection conn = ConnectionUtils.getConnection()){
			String updateCheckingSQL = "UPDATE checking_balances SET checking_balance = ? WHERE checking_account_id = ?;";
			String updateCheckingSQL2 = "UPDATE checking_balances SET checking_balance = ? WHERE checking_account_id = ?;";
			
			PreparedStatement statementCheckingUpdate = conn.prepareStatement(updateCheckingSQL);
			PreparedStatement statementChecking2Update = conn.prepareStatement(updateCheckingSQL2);
		
			statementCheckingUpdate.setInt(2, checkingAccountID);
			statementCheckingUpdate.setDouble(1, checkingBalance - amount);
			statementCheckingUpdate.execute();
			
			statementChecking2Update.setInt(2, checking2AccountID);
			statementChecking2Update.setDouble(1, checking2Balance + amount);
			statementChecking2Update.execute();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//Savings to Savings
	@Override
	public boolean transferSavingsToSavings(Savings savings, Savings savings2, double amount) {
		int savingsAccountID = savings.getSavingsAccountID();
		int savingsAccountID2 = savings2.getSavingsAccountID();
		
		double savingsBalance = savings.getSavingsBalance();
		double savingsBalance2 = savings2.getSavingsBalance();
		
		try(Connection conn = ConnectionUtils.getConnection()){
			String updateSavingsSQL = "UPDATE savings_balances SET savings_balance = ? WHERE savings_account_id = ?;";
			String updateSavingsSQL2 = "UPDATE savings_balances SET savings_balance = ? WHERE savings_account_id = ?;";
			
			PreparedStatement statementSavingsUpdate = conn.prepareStatement(updateSavingsSQL);
			PreparedStatement statementSavingsUpdate2 = conn.prepareStatement(updateSavingsSQL2);
		
			statementSavingsUpdate.setInt(2, savingsAccountID);
			statementSavingsUpdate.setDouble(1, savingsBalance - amount);
			statementSavingsUpdate.execute();
			
			statementSavingsUpdate2.setInt(2, savingsAccountID2);
			statementSavingsUpdate2.setDouble(1, savingsBalance2 + amount);
			statementSavingsUpdate2.execute();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
