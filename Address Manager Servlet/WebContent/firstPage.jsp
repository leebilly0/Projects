<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Customer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Receipt</title>
</head>
<body>
<% if (session.getAttribute("form") == null){ %>
<script>window.location.href = "Login.jsp";</script>
<% } else{%>	
<%

Account temp = (Account)session.getAttribute("form");

%>
	<form action="secondPage.jsp" method="post">
		First name: <input type="text" name="firstName" value="<%= temp.getCustomer().getFname() %>"><br><br>
		Last name: <input type="text" name="lastName" value="<%= temp.getCustomer().getLname() %>">
		<br><br> 
		<% } %>
	<input type="submit" value="Next"/> 
	</form>
</body>
</html>