<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Customer, java.util.*, com.swengsol.service.CreateAccountService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	if (request.getAttribute("account") == null)
	{
		%> ERROR THERE IS NO ACCOUNT INFORMATION <%
	} else
	{
	Account temp = (Account) request.getAttribute("account");
	
	CreateAccountService createAccount = new CreateAccountService();
	
	createAccount.persistAccount(temp);
	
 %>
Account created for <b><%=temp.getUsername().getUsername() %></b><br><br>

<p> First Name: <%=temp.getCustomer().getFname() %> </p>
<p> Last Name: <%=temp.getCustomer().getLname() %> </p>
<p> Street: <%=temp.getCustomer().getStreet() %> </p>
<p> City: <%=temp.getCustomer().getCity() %> </p>
<p> State: <%=temp.getCustomer().getState() %> </p>
<p> Zipcode: <%=temp.getCustomer().getZipcode() %> </p>
<p> Phone Number: <%=temp.getCustomer().getPhoneNumber() %> </p>
<p> Email: <%=temp.getCustomer().getEmail() %> </p>
<a href="Login.jsp">Back to Homepage</a>

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