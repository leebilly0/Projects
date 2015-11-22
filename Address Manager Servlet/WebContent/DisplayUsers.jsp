<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.swengsol.model.Customer, com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.service.UsersService, com.swengsol.service.AccountService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lab 4</title>
</head>
<body>
<%
  List<Account> accounts = (new AccountService()).getAllAccounts();
  //if (null == account) {
	 // customers = CustomerService.getAllCustomers();
 // }
%>
  <h1>Listing of Customers</h1>
  <table>
  <tr>
  	<td>First Name</td>
  	<td>Last Name</td>
  	<td>Username</td>
  </tr>
 <%
      for (Account acct : accounts) {
  %>
  <tr>
  <td><%= acct.getCustomer().getFname() %></td>
	<td><%= acct.getCustomer().getLname() %></td>
	<td><%= acct.getUsername().getUsername() %></td>
  </tr>
  <%	
  	}
  %>  
 

 
  </table>
  <p><a href="index.jsp">Return to homepage</a></p>
</body>
</html>