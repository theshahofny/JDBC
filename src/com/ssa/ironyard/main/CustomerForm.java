package com.ssa.ironyard.main;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerForm {

	default String projection(){
		
		return "id, first, last";
	}
	default String table(){
		return "customers";
	}; //customers
	
	default Customer map(ResultSet results) throws SQLException{
	Customer cust = new Customer();
	cust.setFirstName(results.getString("first"));
	cust.setLastName(results.getString("last"));
	cust.setId(results.getInt("id"));
	return cust;
	}
	
	default String preparedInsert(){
		return  "INSERT INTO" + table() + "(firstName, lastName) Values(?, ?)";
	}
	default String preparedUpdate(){
		return "UPDATE" + table() + "SET (firstName, lastName) Values(?, ?) Where id = ?";
		
	}
	
	default String preparedRead(){
		return "SELECT "+ projection() + "FROM"+ table() + "Where id = ?";
	}
	
	default String preparedDelete(){
		return "DELETE " + "FROM" + table() + "where id = ?";
	}
	
	
	
}
