package com.revature.services;

import java.util.List;

import com.revature.models.Account;

public interface AccountServiceInterface {
	
	public boolean registerNewAccount(Account account);
	public Account checkAccount (Account account);
	public boolean updateAccount (Account account);
	public Account viewOneAccount(Account account);
	public List<Account> viewAccount();

}
