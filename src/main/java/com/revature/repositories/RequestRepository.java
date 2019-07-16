package com.revature.repositories;

import java.util.List;
import java.util.ArrayList;
import com.revature.models.*;
import com.revature.util.ReimbursementConnectionUtil;

public interface RequestRepository  {
	
	public boolean insertRequest(Request request);
	public List<Request> lookAtPendingRequests();
	public List<Request> lookAtResolvedRequests();
	public Request lookAtPendingRequestByID(Request request);
	public Request lookAtResolvedRequestsByID(Request request);
	public Request lookAtRequestByEmployee(Request request);
	public boolean updateRequest (Request request);
	
}
