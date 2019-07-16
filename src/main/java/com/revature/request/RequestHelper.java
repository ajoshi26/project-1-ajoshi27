package com.revature.request;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.controllers.HomeController;
import com.revature.controllers.LoginController;
import com.revature.controllers.RegisterController;
import com.revature.repositories.AccountRepositoryJDBC;

public class RequestHelper {

	private RequestHelper() {}
	
	private static final Logger LOGGER = Logger.getLogger(RequestHelper.class);
	public static Object process(HttpServletRequest request) {
		
		switch(request.getRequestURI()) {
		case "/project-1-ajoshi26/login.do":
			return LoginController.getInstance().login(request);
		case "/project-1-ajoshi26/logout.do":
			return LoginController.getInstance().logout(request);
		case "/project-1-ajoshi26/employeehome.do":
			return HomeController.getInstance().EmployeeHome(request);
		case "/project-1-ajoshi26/managerhome.do":
			return HomeController.getInstance().ManagerHome(request);
		case "/project-1-ajoshi26/register.do":
			return RegisterController.getInstance().register(request);
		case "/project-1-ajoshi26/viewMyAccount.do":
			return LoginController.getInstance().viewAccount(request);
		case "/project-1-ajoshi26/updateAccount.do":
			return RegisterController.getInstance().updateInfo(request);
		case "/project-1-ajoshi26/viewAllAccounts.do":
			return RegisterController.getInstance().viewEmployees(request);
		case "/project-1-ajoshi26/updateRequests.do":
			LOGGER.trace("Going through this update request" + " " + request.getParameter("RequestID") + " " + request.getParameter("RequestType"));
			return RegisterController.getInstance().updateRequests(request);
		case "/project-1-ajoshi26/insertRequest.do":
			return RegisterController.getInstance().insertRequests(request);
		case "/project-1-ajoshi26/lookRequestByID.do":
			return RegisterController.getInstance().viewRequestsbyEmployee(request);
		case "/project-1-ajoshi26/viewPendingRequests.do":
			return RegisterController.getInstance().viewPendingRequests(request);
		case "/project-1-ajoshi26/viewResolvedRequests.do":
			return RegisterController.getInstance().viewPendingRequests(request);
		case "/project-1-ajoshi26/viewPendingRequestsID.do":
			return RegisterController.getInstance().viewPendingRequestsByID(request);
		case "/project-1-ajoshi26/viewResolvedRequestsID.do":
			return RegisterController.getInstance().viewResolvedRequestsByID(request);
		default:
			return "404";
		}
	}
}
