package com.swengsol.model;

public class Username
{
	private String username = "";
	private String password = "";
	
	public Username() 
	{
	}
	
	public Username(String in_username, String in_password) 
	{
		this.username = in_username;
		this.password = in_password;
	}
	
	public void setUsername(String in_username)
	{
		this.username = in_username;
	}
	
	public void setPassword(String in_password)
	{
		this.password = in_password;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public boolean validate()
	{
		return (!isEmpty(username) && !isEmpty(password));
	}
	
	private boolean isEmpty(String s)
	{
		return (s == null || s.length() == 0);
	}
}
