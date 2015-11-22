<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Customer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Second Page</title>
</head>
<body>
<% if (session.getAttribute("form") == null){ %>
<script>window.location.href = "Login.jsp";</script>
<% } else{%>
<%

Account temp = (Account)session.getAttribute("form");

%>
	<form action="thirdPage.jsp" method="post">	
		<%
		if( request.getParameterNames().hasMoreElements()){
			temp.getCustomer().setFname(request.getParameter("firstName"));
			temp.getCustomer().setLname(request.getParameter("lastName")); 
		}
		%>
		First Name: <%= temp.getCustomer().getFname() %><br>
		Last Name: <%= temp.getCustomer().getLname() %> <br>
		Street: <input type="text" name="street" value="<%= temp.getCustomer().getStreet() %>"><br><br>
		City: <input type="text" name="city" value="<%= temp.getCustomer().getCity() %>"><br><br>
		State: <input type="text" name="state" value="<%= temp.getCustomer().getState() %>"><br><br>
		Zipcode: <input type="number" id="zip" name="zipcode" size="5" maxlength="5" value="<%= temp.getCustomer().getZipcode() %>"><br><br>
		Phone Number: <input type="tel" name="phoneNumber" size="10" maxlength="10" value="<%= temp.getCustomer().getPhoneNumber() %>"><br><br>
		Email: <input type="email" name="email" value="<%= temp.getCustomer().getEmail() %>"><br><br>
		<br/>
		<% } %>
		<input type="button" onCLick="javascript: window.location.href='firstPage.jsp'" value="Previous"><input type="submit"/> 
	</form>

</body>
</html>