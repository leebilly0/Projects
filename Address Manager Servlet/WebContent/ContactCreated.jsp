<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Contact, java.util.*, com.swengsol.service.CreateAccountService, com.swengsol.service.ContactsService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Created</title>
</head>
<body>
<%
	String username = request.getParameter("username");	

	if (request.getAttribute("contact") == null)
	{
		%> ERROR THERE IS NO ACCOUNT INFORMATION <%
	} else
	{
	Contact temp = (Contact) request.getAttribute("contact");
	
	ContactsService createAccount = new ContactsService();
	
	createAccount.persistContact(temp, username);
	
	
	
 %>

<%=username%>

<p> First Name: <%=temp.getFname() %> </p>
<p> Last Name: <%=temp.getLname() %> </p>
<p>	Street: <%= temp.getStreet() %></p>
<p>	City: <%= temp.getCity() %></p>
<p>	State: <%= temp.getState() %></p>
<p>	Zipcode: <%= temp.getZipcode() %></p>
<p>	Phone Number 1: <%= temp.getPhoneNumberOne() %></p>
<p>	Phone Number 2: <%= temp.getPhoneNumberTwo() %></p>
<p>	Email 1: <%= temp.getEmailOne() %></p>
<p>	Email 2: <%= temp.getEmailTwo() %></p>
<p>	Anniversary Date 1: <%= temp.getAnniversaryOne() %></p>
<p>	Anniversary Date 2: <%= temp.getAnniversaryTwo() %></p>
<p>	Anniversary Date 3: <%= temp.getAnniversaryThree() %></p>
<a href="DisplayContacts.jsp?username=<%=username%>&message=Contact Successfully Added">Back to Contacts Page</a>

<%
//***NO LONGER KILL SESSiON***

request.removeAttribute("account");
session.removeAttribute("form");

response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
	}
%>
</body>
</html>