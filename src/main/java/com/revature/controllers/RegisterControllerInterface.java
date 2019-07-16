package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

public interface RegisterControllerInterface {
	public Object register(HttpServletRequest request);
	public Object updateInfo(HttpServletRequest request);
	public Object viewEmployees(HttpServletRequest request);
	public Object insertRequests(HttpServletRequest request);
	public Object updateRequests(HttpServletRequest request);
	public Object viewRequestsbyEmployee(HttpServletRequest request);
	public Object viewPendingRequests(HttpServletRequest request);
	public Object viewResolvedRequests(HttpServletRequest request);
	public Object viewPendingRequestsByID(HttpServletRequest request);
	public Object viewResolvedRequestsByID(HttpServletRequest request);
}
