package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Request;
import com.revature.util.ReimbursementConnectionUtil;

public class RequestRepositoryJDBC implements RequestRepository {
	
	private static final Logger LOGGER = Logger.getLogger(RequestRepositoryJDBC.class);
	
	private static RequestRepository requestRepository = null;

	private RequestRepositoryJDBC() {}

	public static RequestRepository getInstance2() {
		if(requestRepository == null) {
			requestRepository = new RequestRepositoryJDBC();
		}

		return requestRepository;
	}
	
	@Override
	public boolean insertRequest(Request request) {

		try(Connection connection = ReimbursementConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String query = "INSERT INTO REQUEST VALUES (?, ?, 'Request not taken', ?)";

			PreparedStatement UserStatement = connection.prepareStatement(query);

			UserStatement.setLong(++parameterIndex, request.getId());
			UserStatement.setString(++parameterIndex, request.getType());
			UserStatement.setLong(++parameterIndex, request.getAccountId());


			if(UserStatement.executeUpdate() > 0) {
				return true;
			}

		}catch(SQLException e) {
			LOGGER.error("Could not create request.", e);
		}

		return false;

	}

	@Override
	public List<Request> lookAtPendingRequests(){
		try(Connection connection = ReimbursementConnectionUtil.getConnection()) {
			String command = "SELECT * FROM REQUEST WHERE R_STATUS = 'Request not taken'";
			PreparedStatement statement = connection.prepareStatement(command);
			ResultSet result = statement.executeQuery();

			List<Request> RequestList = new ArrayList<>();
			while(result.next()) {
				RequestList.add(new Request(
						result.getLong("R_ID"),
						result.getString("R_TYPE"),
						result.getString("R_STATUS"),
						result.getLong("A_ID"))
						);
			}

			return RequestList;
		} catch (SQLException e) {
			LOGGER.warn("Error on selecting on all the employees", e);
		} 
		return new ArrayList<>();
	}
	
	@Override
	public List<Request> lookAtResolvedRequests() {
		try(Connection connection = ReimbursementConnectionUtil.getConnection()) {
			String command = "SELECT * FROM REQUEST WHERE R_STATUS = 'Accept' OR R_STATUS = 'Deny'";
			PreparedStatement statement = connection.prepareStatement(command);
			ResultSet result = statement.executeQuery();

			List<Request> RequestList = new ArrayList<>();
			while(result.next()) {
				RequestList.add(new Request(
						result.getLong("R_ID"),
						result.getString("R_TYPE"),
						result.getString("R_STATUS"),
						result.getLong("A_ID"))
						);
			}

			return RequestList;
		} catch (SQLException e) {
			LOGGER.warn("Error on selecting on all the employees", e);
		} 
		return new ArrayList<>();
	}

	@Override
	public Request lookAtRequestByEmployee(Request request){
		try(Connection connection = ReimbursementConnectionUtil.getConnection()) {

			int parameterIndex = 0;
			String sql = "SELECT * FROM REQUEST WHERE A_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(++parameterIndex,request.getAccountId());
			ResultSet result = statement.executeQuery();

			//List<Request> RequestList = new ArrayList<>();
			if(result.next()) {
				return new Request(
						result.getLong("R_ID"),
						result.getString("R_TYPE"),
						result.getString("R_STATUS"),
						result.getLong("A_ID"));
			}

		} catch (SQLException e) {
			LOGGER.warn("Error on selecting on the employees", e);
		}
		return null; 
	}
	
	@Override
	public Request lookAtPendingRequestByID(Request request) {
		try(Connection connection = ReimbursementConnectionUtil.getConnection()) {
			String command = "SELECT * FROM REQUEST WHERE R_STATUS = 'Request not taken' AND A_ID = ?";
			PreparedStatement statement = connection.prepareStatement(command);
			
			int parameterIndex = 0;
			
			statement.setLong(++parameterIndex,request.getAccountId());
			ResultSet result = statement.executeQuery();

			//List<Request> RequestList = new ArrayList<>();
			if(result.next()) {
				return new Request(
						result.getLong("R_ID"),
						result.getString("R_TYPE"),
						result.getString("R_STATUS"),
						result.getLong("A_ID"));
			}
		} catch (SQLException e) {
			LOGGER.warn("Error on selecting on all the employees", e);
		} 
		return null;
	}

	@Override
	public Request lookAtResolvedRequestsByID(Request request) {

		try(Connection connection = ReimbursementConnectionUtil.getConnection()) {
			String command = "SELECT * FROM REQUEST WHERE R_STATUS = 'Accept' OR R_STATUS = 'DENY' AND A_ID = ?";
			PreparedStatement statement = connection.prepareStatement(command);

			int parameterIndex = 0;

			statement.setLong(++parameterIndex,request.getAccountId());
			ResultSet result = statement.executeQuery();

			//List<Request> RequestList = new ArrayList<>();
			if(result.next()) {
				return new Request(
						result.getLong("R_ID"),
						result.getString("R_TYPE"),
						result.getString("R_STATUS"),
						result.getLong("A_ID"));
			} 
		}
		catch (SQLException e) {
			LOGGER.warn("Error on selecting on all the employees", e); 
		}
		
		return null;
	}
	
	@Override
	public boolean updateRequest(Request request) {
		
		try(Connection connection = ReimbursementConnectionUtil.getConnection()) {

			int parameterIndex = 0;
			String sql = "UPDATE REQUEST SET R_STATUS = ? WHERE R_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			LOGGER.trace("Updating request...");
			
			statement.setString(++parameterIndex, request.getStatus());
			statement.setLong(++parameterIndex,request.getId());
			ResultSet result = statement.executeQuery();
			
			LOGGER.trace("Parameters passed: " + request.getId() + " " + request.getStatus());

			if(statement.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			LOGGER.warn("Error on selecting on the employees", e);
	}
		
		return false;
}

	
	public static void main(String[] args) {
		RequestRepositoryJDBC request = new RequestRepositoryJDBC();
		LOGGER.info(request.updateRequest(new Request(1,"Approve")));
		
	}
	
	}

