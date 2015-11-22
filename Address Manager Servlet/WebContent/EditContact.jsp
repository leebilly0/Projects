<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Customer, com.swengsol.model.Contact, com.swengsol.service.ContactsService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Contact</title>
</head>
<body>

<%
Contact temp = (new ContactsService()).getContact(Integer.parseInt(request.getParameter("id")));
String username = request.getParameter("username");
%>

<form action="UpdateContact.jsp?id=<%=request.getParameter("id")%>&username=<%=username%>" method="post">
		First name: <input type="text" name="firstName" value="<%= temp.getFname() %>"><br><br>
		Last name: <input type="text" name="lastName" value="<%= temp.getLname() %>">
		<br><br>
		Street: <input type="text" name="street" value="<%= temp.getStreet() %>"><br><br>
		City: <input type="text" name="city" value="<%= temp.getCity() %>"><br><br>
		State: <input type="text" name="state" value="<%= temp.getState() %>"><br><br>
		Zipcode: <input type="number" id="zip" name="zipcode" size="5" maxlength="5" value="<%= temp.getZipcode() %>"><br><br>
		Phone Number 1: <input type="tel" name="phoneNumberOne" value="<%= temp.getPhoneNumberOne() %>"><br><br>
		Phone Number 2: <input type="tel" name="phoneNumberTwo" value="<%= temp.getPhoneNumberTwo() %>"><br><br>
		Email 1: <input type="email" name="emailOne" value="<%= temp.getEmailOne() %>"><br><br>
		Email 2: <input type="email" name="emailTwo" value="<%= temp.getEmailTwo() %>"><br><br>
		Anniversary Date 1: <input type="text" name="anniversaryOne" value="<%= temp.getAnniversaryOne() %>"><br><br>
		<br/>
		Anniversary Date 2: <input type="text" name="anniversaryTwo" value="<%= temp.getAnniversaryTwo() %>"><br><br>
		<br/>
		Anniversary Date 3: <input type="text" name="anniversaryThree" value="<%= temp.getAnniversaryThree() %>"><br><br>
		<br/>
		<br/>
	<input type="button" onCLick="javascript: window.location.href='CancelPage.jsp?username=<%=username%>'" value="Cancel"><input type="submit" value="Next"/> 
	</form>
	
	
</body>
</html>