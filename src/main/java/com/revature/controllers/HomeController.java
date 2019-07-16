package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.models.Account;

public class HomeController implements HomeControllerInterface {

	
	private static HomeControllerInterface homeController = new HomeController();

	private HomeController() {}

	public static HomeControllerInterface getInstance() {
		return homeController;
}

	@Override
	public String EmployeeHome(HttpServletRequest request) {
		Account loggedInAccount = (Account)request.getSession().getAttribute("loggedIn");
		
		if(loggedInAccount == null) {
			return "login.html";
		}

		return "Employee.html";
	}

	@Override
	public String ManagerHome(HttpServletRequest request) {
		Account loggedInAccount = (Account)request.getSession().getAttribute("loggedIn");
		
		if(loggedInAccount == null) {
			return "login.html";
		}

		return "Manager.html";
	}
	
	

}
