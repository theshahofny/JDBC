package com.ssa.ironyard.main.resources;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import com.mysql.cj.jdbc.MysqlDataSource;


public class ConnectTests {

	static String URL = "jdbc:mysql://localhost/ssa_bank?" +"user=root&password=root";
	
	DataSource datasource;
	Connection connection;
	
	@Before
	public void setupDB() throws SQLException{
		
		MysqlDataSource mysqlDataSource = new MysqlDataSource();
		mysqlDataSource.setURL(URL);
		Connection connection = mysqlDataSource.getConnection();
		this.datasource = mysqlDataSource;
		this.connection = connection;
	}
	
	@Test
	public void datacsource() throws SQLException
	{
		Statement sql = connection.createStatement();
		ResultSet results = sql.executeQuery("Select * From customers Where id = 1");
		
		assertTrue("", results.next());
		assertEquals("", 1 , results.getInt(1));
		
		assertEquals("", "Ami", results.getString(2));
		assertEquals("", "Shah", results.getString(3));
		
	}
	
	@Test
	public void prepare() throws SQLException{
		PreparedStatement preparedStatement = this.connection.prepareStatement("Select * From customers Where ID = ?");
		preparedStatement.setInt(1, 1);
		Statement sql = connection.createStatement();
		ResultSet results = sql.executeQuery("Select * From customers Where id = 1");
		
		assertTrue("", results.next());
		assertEquals("", 1 , results.getInt(1));
		
		assertEquals("", "Ami", results.getString(2));
		assertEquals("", "Shah", results.getString(3));
		
	}
	
//	@Test
//	public void create() throws SQLException{
//		PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT into customers(firstName, lastName) Values(?, ?)");
//		preparedStatement.setString(1, "Ami");
//		preparedStatement.setString(2, "Doe");
//		assertEquals("", 1, preparedStatement.executeUpdate());
//		Statement sql = connection.createStatement();
//		ResultSet results = sql.executeQuery("Select * From customers Where id = 1");
//		
//		assertTrue("", results.next());
//		assertEquals("", 1 , results.getInt(1));
//		
//		assertEquals("", "Ami", results.getString(2));
//		assertEquals("", "Shah", results.getString(3));
//		
//	}
//	
//	@Test
//	public void delete() throws SQLException{
//		PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT into customers(firstName, lastName) Values(?, ?)");
//		preparedStatement.setString(1, "Ami");
//		preparedStatement.setString(2, "Granger");
//		assertEquals("", 1 , preparedStatement.executeUpdate());
//		
//		Statement sql = connection.createStatement();
//		ResultSet results = sql.executeQuery("Select * From customers Where id = 1");
//		
//		assertTrue("", results.next());
//		assertEquals("", 1 , results.getInt(1));
//	
//		
//		preparedStatement = this.connection.prepareStatement("delete from customers where LastName = 'Granger' ");
//		assertEquals("", 1 ,preparedStatement.executeUpdate());
//		
//		
//	}
	
//	@Test
//	public void tester() throws SQLException{
//		
//		
//		PreparedStatement prepareStatement = this.connection.prepareStatement("INSERT into customers(firstName, lastName) Values(?, ?)",
//																				Statement.RETURN_GENERATED_KEYS);
//		prepareStatement.setString(1, "Ami");
//		prepareStatement.setString(2, "Granger");
//		
//		assertEquals("", 1 , prepareStatement.executeUpdate());
//		
//		Statement sql = connection.createStatement();
//
//		ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
//
//		assertTrue("", generatedKeys.next());
//		
//		System.out.println("inserted customer with Id" + generatedKeys.getInt(1));
//	
//		
//		prepareStatement = this.connection.prepareStatement("delete from customers where LastName = 'Granger' ");
//		assertEquals("", 1 ,prepareStatement.executeUpdate());
//		
//	}
	
	@Test
	public void tester() throws SQLException{
		
		PreparedStatement prepareStatement = this.connection.prepareStatement("UPDATE customers SET firstName = ?, lastName=? Where id =?");
		prepareStatement.setString(1, "Ami");
		prepareStatement.setString(2, "GOO");
		
		prepareStatement.setInt(3, 2);
		
		assertEquals("", 1 , prepareStatement.executeUpdate());
		
		Statement sql = connection.createStatement();

		ResultSet generatedKeys = prepareStatement.getGeneratedKeys();

		assertTrue("", generatedKeys.next());

		System.out.println("inserted customer with Id" + generatedKeys.getInt(1));

		
		prepareStatement = this.connection.prepareStatement("delete from customers where LastName = 'Granger' ");
		assertEquals("", 1 ,prepareStatement.executeUpdate());
		
	}
	
}
