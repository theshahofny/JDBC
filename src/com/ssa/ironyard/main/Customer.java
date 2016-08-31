package com.ssa.ironyard.main;

public class Customer {

	
	String firstName;
	String lastName;
	int id;
	
	
	public Customer(String firstName, String lastName, int id) {
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	

	public Customer(String firstName, String lastName) {

		this.firstName = firstName;
		this.lastName = lastName;
	}



	public Customer() {
		// TODO Auto-generated constructor stub
	}



	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
