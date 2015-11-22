<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Customer, com.swengsol.model.Contact" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Second Page</title>
</head>
<body>
<%
//THIS IS NOT CORRECT
//Account temp = (Account)session.getAttribute("form");

Contact temp = (Contact)session.getAttribute("form2");
String username = request.getParameter("username");

%>
	<form action="CreateContact" method="post">	
		<%
		if( request.getParameterNames().hasMoreElements()){
			temp.setFname(request.getParameter("firstName"));
			temp.setLname(request.getParameter("lastName")); 
			temp.setStreet(request.getParameter("street"));
			temp.setCity(request.getParameter("city")); 
			temp.setState(request.getParameter("state"));
			temp.setZipcode(request.getParameter("zipcode"));
			temp.setPhoneNumberOne(request.getParameter("phoneNumberOne"));
			temp.setPhoneNumberTwo(request.getParameter("phoneNumberTwo"));
			temp.setEmailOne(request.getParameter("emailOne"));
			temp.setEmailTwo(request.getParameter("emailTwo"));
		}
		%>
		First Name: <%= temp.getFname() %><br>
		Last Name: <%= temp.getLname() %> <br>
		Anniversary Date 1: <input type="text" name="anniversaryOne" value="<%= temp.getAnniversaryOne() %>"><br><br>
		<br/>
		Anniversary Date 2: <input type="text" name="anniversaryTwo" value="<%= temp.getAnniversaryTwo() %>"><br><br>
		<br/>
		Anniversary Date 3: <input type="text" name="anniversaryThree" value="<%= temp.getAnniversaryThree() %>">
		<input type="hidden" name="username" value="<%=username%>"><br><br>
		<br/>
		<input type="button" onCLick="javascript: window.location.href='CancelPage.jsp'" value="Cancel"> <input type="button" onCLick="javascript: window.location.href='addContactOne.jsp'" value="Previous"> <input type="submit"/> 
	</form>

</body>
</html>