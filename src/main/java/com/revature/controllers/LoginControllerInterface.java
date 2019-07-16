package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

public interface LoginControllerInterface {
	
	public Object login(HttpServletRequest request);
	public String logout(HttpServletRequest request);
	public Object viewAccount(HttpServletRequest request);

}
