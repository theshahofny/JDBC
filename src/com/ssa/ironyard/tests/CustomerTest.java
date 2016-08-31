package com.ssa.ironyard.tests;

import static org.junit.Assert.*;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.ssa.ironyard.main.Customer;
import com.ssa.ironyard.main.CustomerDAO;
import com.ssa.ironyard.main.CustomerDaoImpl;

public class CustomerTest {


	static String URL = "jdbc:mysql://localhost/ssa_bank?" +"user=root&password=root";
	
	DataSource datasource;
	Connection connection;
	
	CustomerDAO dao;
	
	@Test
	public void setUp() {
		
		MysqlDataSource mysqlDataSource = new MysqlDataSource();
		mysqlDataSource.setURL(URL);
		this.dao = new CustomerDaoImpl (mysqlDataSource);
		this.dao.clear();
	}
	
	@Test
	public void create(){
		Customer c1 = new Customer("Ami", "Shah");
		Customer c1check;
		c1check = dao.insert(c1);
		assertTrue(c1check.getFirstName().equals(c1.getFirstName()));
		
	}
	
	public void read(){
		//create a customer
		//insert customer
		//read same customer
		//read int id = 0; see what returns
		//delete customer
		//delete with ID
	}

}
