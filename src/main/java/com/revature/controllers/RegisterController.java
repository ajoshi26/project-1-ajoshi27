package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.Request;
import com.revature.repositories.AccountRepositoryJDBC;
import com.revature.services.AccountService;
import com.revature.services.RequestService;

public class RegisterController implements RegisterControllerInterface{
	
	private static RegisterControllerInterface registerController = new RegisterController();
	
	private static final Logger LOGGER = Logger.getLogger(RegisterController.class);

	private RegisterController() {}

	public static RegisterControllerInterface getInstance() {
		return registerController;
	}
	
	@Override
	public Object register(HttpServletRequest request) {
			if (request.getMethod().equals("GET")) {
				return "register.html";
			}
			
			Account registeredAccount = new Account(request.getParameter("firstName"),request.getParameter("lastName"),
					request.getParameter("Email"),request.getParameter("username"),request.getParameter("password"),
					request.getParameter("Role"));
			
			if (AccountService.getInstance().registerNewAccount(registeredAccount)){
				
				if(request.getParameter("role").equals("M")) {
					return HomeController.getInstance().ManagerHome(request);
				}
				else if(request.getParameter("role").equals("E")) {
					return HomeController.getInstance().EmployeeHome(request);
				}
				
			} else {
				return "REGISTRATION UNSUCCESSFUL";
			}
	 return null;
	}
			

	@Override
	public Object updateInfo(HttpServletRequest request) {
		return  AccountService.getInstance().updateAccount(new Account(request.getParameter("username"),request.getParameter("password"),
				((Account)(request.getSession().getAttribute("loggedIn"))).getId()));
	}

	@Override
	public Object viewEmployees(HttpServletRequest request) {
		return AccountService.getInstance().viewAccount();
	}

	@Override
	public Object insertRequests(HttpServletRequest request) {
		return RequestService.getInstance().insertingRequests(new Request(request.getParameter("requestType")));
	}
	
	@Override
	public Object updateRequests(HttpServletRequest request) {
		LOGGER.trace("Going through this method" + " " + RequestService.getInstance().updatingRequests(new Request(Long.parseLong(request.getParameter("RequestID")),
				request.getParameter("RequestStatus"))));
		return RequestService.getInstance().updatingRequests(new Request(Long.parseLong(request.getParameter("RequestID")),
				request.getParameter("RequestStatus")));
	}

	@Override
	public Object viewPendingRequests(HttpServletRequest request) {
		return RequestService.getInstance().listAllPendingRequests();
	}

	@Override
	public Object viewResolvedRequests(HttpServletRequest request) {
		return RequestService.getInstance().listAllResolvedRequests();
	}
	
	@Override
	public Object viewRequestsbyEmployee(HttpServletRequest request) {
		return RequestService.getInstance().listRequestbyID(new Request(0,null,null,Long.parseLong(request.getParameter("accountID"))));
	}

	@Override
	public Object viewPendingRequestsByID(HttpServletRequest request) {
		return RequestService.getInstance().listPendingRequestsbyID(new Request(0,null,null,Long.parseLong(request.getParameter("accountID"))));
	}

	@Override
	public Object viewResolvedRequestsByID(HttpServletRequest request) {
		return RequestService.getInstance().listResolvedRequestsbyID(new Request(0,null,null,Long.parseLong(request.getParameter("accountID"))));
	}


}
