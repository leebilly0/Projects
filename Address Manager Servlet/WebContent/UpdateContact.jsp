<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List, com.swengsol.model.Customer, com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Contact, com.swengsol.service.ContactsService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Contact</title>
</head>
<body>
<%String username = request.getParameter("username"); %>
<% (new ContactsService()).updateContact(request, Integer.parseInt(request.getParameter("id"))); %>

<h1>Contact Updated</h1>

<input type="button" onCLick="javascript: window.location.href='DisplayContacts.jsp?username=<%=username%>&message=Contact Updated'" value="Return To Display Contacts">

</body>
</html>