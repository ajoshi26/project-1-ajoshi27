package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;

public interface AccountRepository {

	public boolean createAccount(Account account);
	public Account findAccount(Account account);
	public boolean updateAccount(Account account);
	public Account selectAccountByID(Account account);
	public List<Account> selectAll();
}