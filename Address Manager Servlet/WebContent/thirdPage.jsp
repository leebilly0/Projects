<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Customer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Third Page</title>
</head>
<body>
<% if (session.getAttribute("form") == null){ %>
<script>window.location.href = "Login.jsp";</script>
<% } else{%>
<%

Account temp = (Account)session.getAttribute("form");

%>	
	<form action="CreateAccount" method="post">
	<% 
		if( request.getParameterNames().hasMoreElements()){
			temp.getCustomer().setStreet(request.getParameter("street"));
			temp.getCustomer().setCity(request.getParameter("city"));
			temp.getCustomer().setState(request.getParameter("state"));
			temp.getCustomer().setZipcode(request.getParameter("zipcode"));
			temp.getCustomer().setPhoneNumber(request.getParameter("phoneNumber"));
			temp.getCustomer().setEmail(request.getParameter("email"));
		}
		%>
		First Name: <%= temp.getCustomer().getFname() %><br>
		Last Name: <%= temp.getCustomer().getLname() %> <br>
		Street: <%= temp.getCustomer().getStreet() %><br>
		City: <%= temp.getCustomer().getCity() %><br>
		State:  <%= temp.getCustomer().getState() %> <br>
		Zipcode: <%= temp.getCustomer().getZipcode() %><br>
		Phone Number:  <%= temp.getCustomer().getPhoneNumber() %> <br>
		Email: <%= temp.getCustomer().getEmail() %><br><br>
		Create a username and password for this account<br>
		Username: <input type="text" name="username"><br><br>
		Password: <input type="password" name="password"><br>
		<br/>
	<% }%>	
		<input type="button" onClick="javascript: window.location.href='secondPage.jsp'" value="Previous"><input type="submit"/>
	</form><br>
</body>
</html>