package com.swengsol.model;

public class Contact {
	private String fname = "";
	private String lname = "";
	private String street = "";
	private String city = "";
	private String state = "";
	private String zipcode = "";
	private String phoneNumberOne = "";
	private String phoneNumberTwo = "";
	private String emailOne = "";
	private String emailTwo = "";
	private String anniversaryOne = "";
	private String anniversaryTwo = "";
	private String anniversaryThree = "";
	private int id = 0;
	
	
	public Contact() 
	{
	}
	
	public Contact (String in_fname, String in_lname, String in_street, String in_city, String in_state, String in_zipcode, String in_phoneNumberOne, String in_phoneNumberTwo, String in_emailOne, String in_emailTwo, String in_anniversaryOne, String in_anniversaryTwo, String in_anniversaryThree) 
	{
		this.fname = in_fname;
		this.lname = in_lname;
		this.street = in_street;
		this.city = in_city;
		this.state = in_state;
		this.zipcode = in_zipcode;
		this.phoneNumberOne = in_phoneNumberOne;
		this.phoneNumberTwo = in_phoneNumberTwo;
		this.emailOne = in_emailOne;
		this.emailTwo = in_emailTwo;
		this.anniversaryOne = in_anniversaryOne;
		this.anniversaryTwo = in_anniversaryTwo;
		this.anniversaryThree = in_anniversaryThree;
	}
	
	public void setId(int in_id)
	{
		this.id = in_id;
	}
	
	public int getId()
	{
		return this.id;
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

	public void setPhoneNumberOne(String in_phoneNumberOne)
	{
		this.phoneNumberOne = in_phoneNumberOne;
	}
	
	public void setPhoneNumberTwo(String in_phoneNumberTwo)
	{
		this.phoneNumberTwo = in_phoneNumberTwo;
	}
	
	public void setAnniversaryOne(String in_anniversaryOne)
	{
		this.anniversaryOne = in_anniversaryOne;
	}
	
	public void setAnniversaryTwo(String in_anniversaryTwo)
	{
		this.anniversaryTwo = in_anniversaryTwo;
	}
	
	public void setAnniversaryThree(String in_anniversaryThree)
	{
		this.anniversaryThree = in_anniversaryThree;
	}
	
	public void setEmailOne(String in_emailOne)
	{
		this.emailOne = in_emailOne;
	}
	
	public void setEmailTwo(String in_emailTwo)
	{
		this.emailTwo = in_emailTwo;
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
	
	public String getPhoneNumberOne()
	{
		return phoneNumberOne;
	}
	
	public String getPhoneNumberTwo()
	{
		return phoneNumberTwo;
	}
	
	public String getEmailOne()
	{
		return emailOne;
	}
	
	public String getEmailTwo()
	{
		return emailTwo;
	}
	
	public String getAnniversaryOne()
	{
		return anniversaryOne;
	}
	
	public String getAnniversaryTwo()
	{
		return anniversaryTwo;
	}
	
	public String getAnniversaryThree()
	{
		return anniversaryThree;
	}
}

