<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel Page</title>
</head>
<body>

<%

String username = request.getParameter("username");

//***NO LONGER KILL SESSiON***

request.removeAttribute("contact");
session.removeAttribute("form2");

response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);


%>
<h1>Session Canceled</h1>
<input type="button" onCLick="javascript: window.location.href='DisplayContacts.jsp?username=<%=username%>'" value="Return To Display Contacts">

</body>
</html>