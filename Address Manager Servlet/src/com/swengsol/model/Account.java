package com.swengsol.model;

public class Account
{
	private Customer customer;
	private Username username;
	
	public Account()
	{
	}
	
	public Account(Customer customer, Username username)
	{
		this.customer = customer;
		this.username = username;
	}
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	public Username getUsername()
	{
		return username;
	}
	
	public void setUsername(Username username)
	{
		this.username = username;
	}
}
