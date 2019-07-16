package com.revature.services;

import java.util.List;

import com.revature.models.Request;

public interface RequestServiceInterface {
	
	public List<Request> listAllPendingRequests();
	public List<Request> listAllResolvedRequests();
	public Request listPendingRequestsbyID(Request request);
	public Request listResolvedRequestsbyID(Request request);
	public Request listRequestbyID(Request request);
	public boolean insertingRequests(Request request);
	public boolean updatingRequests(Request request);
}
