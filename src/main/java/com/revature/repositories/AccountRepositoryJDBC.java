package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.util.ReimbursementConnectionUtil;

public class AccountRepositoryJDBC implements AccountRepository {
	
	private static final Logger LOGGER = Logger.getLogger(AccountRepositoryJDBC.class);
	
	private static AccountRepository accountRepository;
	
	private AccountRepositoryJDBC() {}
	
	public static AccountRepository getInstance() {
		if(accountRepository == null) {
			accountRepository = new AccountRepositoryJDBC();
		}
		
		return accountRepository;
	}
	
	
	@Override
	public boolean createAccount(Account account) {
		
		try(Connection connection = ReimbursementConnectionUtil.getConnection()){
			int parameterIndex = 0;
			
			//Put sequence into the first parameter (seq_ID.nextval)
			String query = "INSERT INTO ACCOUNT VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement UserStatement = connection.prepareStatement(query);
			
			UserStatement.setLong(++parameterIndex,account.getId());
			UserStatement.setString(++parameterIndex,account.getFirstName());
			UserStatement.setString(++parameterIndex, account.getLastName());
			UserStatement.setString(++parameterIndex, account.getEmail());
			UserStatement.setString(++parameterIndex, account.getUsername());
			UserStatement.setString(++parameterIndex, account.getPassword());
			UserStatement.setString(++parameterIndex, account.getRole());
			
			
			if(UserStatement.executeUpdate() > 0) {
				return true;
			}
			
		}catch(SQLException e) {
			LOGGER.error("Could not create user account.", e);
		}
		
		return false;
	}

	@Override
	public Account findAccount(Account account) {
		LOGGER.trace("Finding Account Information");

		try(Connection connection = ReimbursementConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "SELECT * FROM ACCOUNT WHERE A_USERNAME = ? AND A_PASSWORD = ? AND A_ROLE = ?";

			PreparedStatement UserStatement = connection.prepareStatement(sql);
			UserStatement.setString(++parameterIndex, account.getUsername());
			UserStatement.setString(++parameterIndex, account.getPassword());
			UserStatement.setString(++parameterIndex, account.getRole());
			
			
			ResultSet result = UserStatement.executeQuery();
			
			if(result.next()) {
				LOGGER.trace("Found Account");
				return new Account(
						result.getLong("A_ID"),
						result.getString("A_FIRST_NAME"),
						result.getString("A_LAST_NAME"),
						result.getString("A_EMAIL"),
						result.getString("A_USERNAME"),
						result.getString("A_PASSWORD"),
						result.getString("A_ROLE"));
			}

		}catch(SQLException e) {
			LOGGER.error("Could not find Account.", e);
		}
		return new Account();
	}

	@Override
	public boolean updateAccount(Account account) {
		try(Connection connection = ReimbursementConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String query = "UPDATE ACCOUNT SET A_USERNAME = ?, A_PASSWORD = ? WHERE A_ID = ?";
			
			PreparedStatement UserStatement = connection.prepareStatement(query);
			
			
			UserStatement.setString(++parameterIndex, account.getUsername());
			UserStatement.setString(++parameterIndex, account.getPassword());
			UserStatement.setLong(++parameterIndex, account.getId());
			
			LOGGER.trace("Update user account..." + account.getUsername() + account.getPassword() + account.getId());
			
			if(UserStatement.executeUpdate() > 0) {
				return true;
			}
			
		}catch(SQLException e) {
			LOGGER.error("Could not update user account.", e);
		}
		
		return false;
	}

	@Override
	public List<Account> selectAll() {
		try(Connection connection = ReimbursementConnectionUtil.getConnection()) {
			String command = "SELECT * FROM ACCOUNT WHERE A_ROLE = 'E'";
			PreparedStatement statement = connection.prepareStatement(command);
			ResultSet result = statement.executeQuery();
			

			List<Account> customerList = new ArrayList<>();
			while(result.next()) {
				customerList.add(new Account(
						result.getInt("A_ID"),
						result.getString("A_FIRST_NAME"),
						result.getString("A_LAST_NAME"),
						result.getString("A_EMAIL"),
						result.getString("A_USERNAME"),
						result.getString("A_PASSWORD"),
						result.getString("A_ROLE")
						));
			}

			return customerList;
		} catch (SQLException e) {
			LOGGER.warn("Exception selecting all customers", e);
		} 
		return new ArrayList<>();
	}
	
	public Account selectAccountByID(Account account) {
		try(Connection connection = ReimbursementConnectionUtil.getConnection()) {
			int parameterIndex = 0;
			String sql = "SELECT * FROM ACCOUNT WHERE A_ROLE = 'E' AND A_ID = ?";

			PreparedStatement UserStatement = connection.prepareStatement(sql);
			UserStatement.setLong(++parameterIndex, account.getId());
			
			
			ResultSet result = UserStatement.executeQuery();
			
			if(result.next()) {
				LOGGER.trace("Found Account");
				return new Account(
						result.getLong("A_ID"),
						result.getString("A_FIRST_NAME"),
						result.getString("A_LAST_NAME"),
						result.getString("A_EMAIL"),
						result.getString("A_USERNAME"),
						result.getString("A_PASSWORD"),
						result.getString("A_ROLE"));
			}

		} catch (SQLException e) {
			LOGGER.warn("Exception selecting employee", e);
		} 
		
		return new Account();
	}


//	public static void main(String[] args) {
//		AccountRepositoryJDBC account = new AccountRepositoryJDBC();
//		
//		LOGGER.info(account.findAccount(new Account(2, "Shreeram","Joshi", "sjoshi21@yahoo.com",
//				"sjoshi76", "password","E")));
//	}
	

	
}
