<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.swengsol.model.Customer, com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Contact, com.swengsol.service.ContactsService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lab 4</title>
</head>
<body>
<%
	String username = request.getParameter("username");
  List<Contact> contacts = (new ContactsService()).getAllContacts(username);
  //if (null == account) {
	 // customers = CustomerService.getAllCustomers();
 // }
  
  	if( request.getParameterNames().hasMoreElements()){
  		if (request.getParameter("message") == null)
  		{
  			out.println("");
  		}
  		else
  		{
  		out.println(request.getParameter("message"));
  		}
  	}
  		
  	int temp = (new ContactsService()).getUserID(username);
  	
  
%>
	<h1>Welcome <%=username%> <a href="KillPage.jsp">Logout</a></h1> 
  <h2>Listing of Customers</h2>
  <table>
  <tr>
  	<td>First Name</td>
  	<td>Last Name</td>
  </tr>
 <%
      for (Contact contact : contacts) {
  %>
  <tr>
  <td><%= contact.getFname() %></td>
	<td><%= contact.getLname() %></td>
	<td><a href="DetailContacts.jsp?id=<%=contact.getId()%>&username=<%=username%>">Details</a></td>
	<td><a href="EditContact.jsp?id=<%=contact.getId()%>&username=<%=username%>">Edit</a></td>
	<td><a href="DeleteContact.jsp?id=<%=contact.getId()%>&username=<%=username%>">Delete</a></td>
  </tr>
  <%	
  	}
  %>  
 
 

 
  </table>
  <p><a href="addContactOne.jsp?username=<%=username%>">Add A Contact</a></p>
</body>
</html>