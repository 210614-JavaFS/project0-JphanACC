package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountCustomer;
import com.revature.models.AccountEmployee;
import com.revature.models.Checking;
import com.revature.models.Savings;
import com.revature.utils.ConnectionUtils;

public class EmployeeDAOImpl implements EmployeeDAO {
	private static EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	
	//Find All()
	@Override
	public List<AccountEmployee> findAll() {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "SELECT * FROM employees";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<AccountEmployee> list = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				AccountEmployee employee = new AccountEmployee();
				
				employee.setAccountID(result.getInt("account_id"));
				employee.setUsername(result.getString("username"));
				employee.setFirstName(result.getString("first_name"));
				employee.setLastName(result.getString("last_name"));
				
				list.add(employee);
				
				//Retrieve all Checking and add to employee's list
				String sqlChecking = "SELECT * FROM employees INNER JOIN checking_balances ON (employees.account_id = checking_balances.employee_id) WHERE employees.account_id = ?";
				PreparedStatement statementChecking = conn.prepareStatement(sqlChecking);
				
				statementChecking.setInt(1, employee.getAccountID());
				ResultSet resultChecking = statementChecking.executeQuery();
				
				while (resultChecking.next()) {

					Checking checkingAccount = new Checking();
					
					checkingAccount.setCheckingAccountID(resultChecking.getInt("checking_account_id"));
					checkingAccount.setCheckingBalance(resultChecking.getDouble("checking_balance"));
					checkingAccount.setCheckingAccountStatus(resultChecking.getBoolean("checking_account_status"));
					
					employee.addCheckingAccount(checkingAccount);
				}
				
				//Retrieve all Savings and add to employyes's list
				String sqlSavings = "SELECT * FROM employees INNER JOIN savings_balances ON (employees.account_id = savings_balances.employee_id) WHERE employees.account_id = ?";
				PreparedStatement statementSavings = conn.prepareStatement(sqlSavings);
				
				statementSavings.setInt(1, employee.getAccountID());
				ResultSet resultSavings = statementSavings.executeQuery();
				
				while (resultSavings.next()) {
					
					
					Savings savingsAccount = new Savings();
					
					savingsAccount.setSavingsAccountID(resultSavings.getInt("savings_account_id"));
					savingsAccount.setSavingsBalance(resultSavings.getDouble("savings_balance"));
					savingsAccount.setSavingsAccountStatus(resultSavings.getBoolean("savings_account_status"));
					
					employee.addSavingsAccount(savingsAccount);
				}
				
			}
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//Find Employee
	@Override
	public AccountEmployee findEmployee(String username, String password) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			AccountEmployee account = new AccountEmployee();
			
			while(result.next()) {
				account.setAccountID(result.getInt("account_id"));
				account.setUsername(result.getString("username"));
				account.setPassword(result.getString("password"));
				account.setFirstName(result.getString("first_name"));
				account.setLastName(result.getString("last_name"));
			}
			
			//Retrieve Checking
			String sqlChecking = "SELECT * FROM employees INNER JOIN checking_balances ON (employees.account_id = checking_balances.employee_id) WHERE employees.account_id = ?";
			PreparedStatement statementChecking = conn.prepareStatement(sqlChecking);
			
			statementChecking.setInt(1, account.getAccountID());
			ResultSet resultChecking = statementChecking.executeQuery();
			
			while (resultChecking.next()) {	
				Checking checkingAccount = new Checking();
				
				checkingAccount.setCheckingAccountID(resultChecking.getInt("checking_account_id"));
				checkingAccount.setCheckingBalance(resultChecking.getDouble("checking_balance"));
				checkingAccount.setCheckingAccountStatus(resultChecking.getBoolean("checking_account_status"));
				
				account.addCheckingAccount(checkingAccount);
			}
			
			//Retrieve Savings
			String sqlSavings = "SELECT * FROM employees INNER JOIN savings_balances ON (employees.account_id = savings_balances.employee_id) WHERE employees.account_id = ?";
			PreparedStatement statementSavings = conn.prepareStatement(sqlSavings);
			
			statementSavings.setInt(1, account.getAccountID());
			ResultSet resultSavings = statementSavings.executeQuery();
			
			while (resultSavings.next()) {
				
//				Integer checkingAccountID = resultChecking.getInt("checking_account_id");
				
				Savings savingsAccount = new Savings();
				
				savingsAccount.setSavingsAccountID(resultSavings.getInt("savings_account_id"));
				savingsAccount.setSavingsBalance(resultSavings.getDouble("savings_balance"));
				savingsAccount.setSavingsAccountStatus(resultSavings.getBoolean("savings_account_status"));
				
				account.addSavingsAccount(savingsAccount);
			}
			
			return account;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//Add Employee
	@Override
	public boolean addEmployee(AccountEmployee employee) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "INSERT INTO employees(username, password, first_name, last_name)"
				+ "VALUES (?,?,?,?)";
		
			PreparedStatement statement = conn.prepareStatement(sql);
		
			int index = 0;
			statement.setString(++index, employee.getUsername());
			statement.setString(++index, employee.getPassword());
			statement.setString(++index, employee.getFirstName());
			statement.setString(++index, employee.getLastName());
			statement.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	//Create Checking Account
	@Override
	public boolean createCheckingAccount(AccountEmployee employee) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "INSERT INTO checking_balances(employee_id) VALUES (?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
		
			statement.setInt(1, employee.getAccountID());
			statement.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//Create Savings Account
	@Override
	public boolean createSavingsAccount(AccountEmployee employee) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "INSERT INTO savings_balances(employee_id) VALUES (?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
		
			statement.setInt(1, employee.getAccountID());
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

	//Approve Checking
	@Override
	public boolean approveChecking(int accountID) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "UPDATE checking_balances SET checking_account_status = TRUE WHERE checking_account_id = ?;";
				
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, accountID);
			statement.execute();
			return true;
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
			return false;
	}
	
	//Approve Savings
	@Override
	public boolean approveSavings(int accountID) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "UPDATE savings_balances SET savings_account_status = TRUE WHERE savings_account_id = ?;";
				
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, accountID);
			statement.execute();
			return true;
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
			return false;
	}
	
	//Delete Checking
	@Override
	public boolean deleteChecking(int accountID) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "DELETE FROM checking_balances WHERE checking_account_id = ?;";
				
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, accountID);
			statement.execute();
			return true;
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
			return false;
	}
	
	//Delete Savings
	@Override
	public boolean deleteSavings(int accountID) {
		try(Connection conn = ConnectionUtils.getConnection()){
			String sql = "DELETE FROM savings_balances WHERE savings_account_id = ?;";
				
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, accountID);
			statement.execute();
			return true;
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
			return false;
	}
}
