package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Request;
import com.revature.repositories.AccountRepositoryJDBC;
import com.revature.repositories.RequestRepositoryJDBC;

public class RequestService implements RequestServiceInterface{
	
	private static RequestServiceInterface requestService = new RequestService();
	
	private static final Logger LOGGER = Logger.getLogger(RequestService.class);

	private RequestService() { }

	public static RequestServiceInterface getInstance() {
		return requestService;
	}

	@Override
	public Request listRequestbyID(Request request) {
		return  RequestRepositoryJDBC.getInstance2().lookAtRequestByEmployee(request);
	}

	@Override
	public List<Request> listAllPendingRequests() {
		return RequestRepositoryJDBC.getInstance2().lookAtPendingRequests();
	}

	@Override
	public List<Request> listAllResolvedRequests() {
		return  RequestRepositoryJDBC.getInstance2().lookAtResolvedRequests();
	}

	@Override
	public Request listPendingRequestsbyID(Request request) {
		return  RequestRepositoryJDBC.getInstance2().lookAtPendingRequestByID(request);
	}

	@Override
	public Request listResolvedRequestsbyID(Request request) {
		return  RequestRepositoryJDBC.getInstance2().lookAtPendingRequestByID(request);
	}
	
	@Override
	public boolean insertingRequests(Request request) {
		return RequestRepositoryJDBC.getInstance2().insertRequest(request);
	}

	@Override
	public boolean updatingRequests(Request request) {
		LOGGER.trace("Going through this Service method" + request.getStatus());
		return RequestRepositoryJDBC.getInstance2().updateRequest(request);
	}
	
}
