package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.repositories.AccountRepositoryJDBC;

public class AccountService implements AccountServiceInterface {
	
	private static AccountServiceInterface accountService = new AccountService();
	
	private AccountService() { }
	
	public static AccountServiceInterface getInstance() {
		return accountService;
	}

	@Override
	public boolean registerNewAccount(Account account) {
		return AccountRepositoryJDBC.getInstance().createAccount(account);
	}

	@Override
	public Account checkAccount(Account account) {
		Account loggedInAccount = AccountRepositoryJDBC.getInstance().findAccount(account);
		
		if((loggedInAccount.getUsername().equals(account.getUsername()) && 
			(loggedInAccount.getPassword().equals(account.getPassword())))){
			
			return loggedInAccount;
		}
		
		return null;
		
	}

	@Override
	public boolean updateAccount(Account account) {
		
		return AccountRepositoryJDBC.getInstance().updateAccount(account);
	}

	@Override
	public List <Account> viewAccount() {
		return AccountRepositoryJDBC.getInstance().selectAll();
	}

	@Override
	public Account viewOneAccount(Account account) {
		return AccountRepositoryJDBC.getInstance().selectAccountByID(account);

	}
	
	
	
	

}
