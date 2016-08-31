package com.ssa.ironyard.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.ssa.ironyard.main.Customer;
import com.ssa.ironyard.main.CustomerDAO;

public class CustomerDaoImpl implements CustomerDAO {
	final DataSource datasource;
	Connection connection;
	PreparedStatement preparedStatement;


	public CustomerDaoImpl(DataSource datasource) {

		this.datasource = datasource;
		setCon();
	}

	public void setCon(){
		try {
			connection = datasource.getConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//"profileSQL= true&useServerPrepStms=true";

	@Override
	public Customer insert(Customer customer){

		try {
			preparedStatement = this.connection.prepareStatement("INSERT into customers(firstName, lastName) Values(?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());

			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			generatedKeys.next();
			Customer dbCustomer = read(generatedKeys.getInt(1));
			return dbCustomer;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}


	@Override
	public boolean delete(Customer toDelete) {
		try {
			preparedStatement.setInt(1, toDelete.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}


	@Override
	public Customer update(Customer customer) {

		try {
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setInt(3, customer.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


	@Override
	public Customer read(int id) {
		try {
			preparedStatement.setInt(1, id);
			ResultSet results = preparedStatement.executeQuery();
			Customer customer = null;
			if (results.next())
				customer = new Customer(results.getString(2), results.getString(3), results.getInt(1));
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public int clear() {
		try {
		    return preparedStatement.executeUpdate();
		} catch (SQLException e) {
		    throw new RuntimeException();
		}
	}
	
	




}
