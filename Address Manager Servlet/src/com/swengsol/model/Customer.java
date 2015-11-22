package com.swengsol.model;

public class Customer {
	private String fname = "";
	private String lname = "";
	private String street = "";
	private String city = "";
	private String state = "";
	private String zipcode = "";
	private String phoneNumber = "";
	private String email = "";
	
	
	public Customer() 
	{
	}
	
	public Customer(String in_fname, String in_lname, String in_street, String in_city, String in_state, String in_zipcode, String in_phoneNumber, String in_email) 
	{
		this.fname = in_fname;
		this.lname = in_lname;
		this.street = in_street;
		this.city = in_city;
		this.state = in_state;
		this.zipcode = in_zipcode;
		this.phoneNumber = in_phoneNumber;
		this.email = in_email;
	}
	
	public void setFname(String in_fname) 
	{
		this.fname = in_fname;
	}
	
	public void setLname(String in_lname)
	{
		this.lname = in_lname;
	}
	
	public void setStreet(String in_street)
	{
		this.street = in_street;
	}
	
	public void setCity(String in_city)
	{
		this.city = in_city;
	}
	
	public void setState(String in_state)
	{
		this.state = in_state;
	}
	
	public void setZipcode(String in_zipcode)
	{
		this.zipcode = in_zipcode;
	}

	public void setPhoneNumber(String in_phoneNumber)
	{
		this.phoneNumber = in_phoneNumber;
	}
	
	public void setEmail(String in_email)
	{
		this.email = in_email;
	}

	public String getFname() 
	{
		return fname;
	}
	
	public String getLname()
	{
		return lname;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public String getZipcode()
	{
		return zipcode;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public String getEmail()
	{
		return email;
	}
}

