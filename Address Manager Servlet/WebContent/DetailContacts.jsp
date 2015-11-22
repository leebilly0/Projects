<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Customer, com.swengsol.model.Contact, com.swengsol.service.ContactsService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detail Contacts</title>
</head>
<body>

<%
String username = request.getParameter("username");
Contact temp = (new ContactsService()).getContact(Integer.parseInt(request.getParameter("id")));
%>


		First name: <%= temp.getFname() %><br><br>
		Last name: <%= temp.getLname() %> <br><br>
		Street: <%= temp.getStreet() %><br><br>
		City: <%= temp.getCity() %><br><br>
		State: <%= temp.getState() %><br><br>
		Zipcode: <%= temp.getZipcode() %><br><br>
		Phone Number 1: <%= temp.getPhoneNumberOne() %><br><br>
		Phone Number 2: <%= temp.getPhoneNumberTwo() %><br><br>
		Email 1: <%= temp.getEmailOne() %><br><br>
		Email 2: <%= temp.getEmailTwo() %><br><br>
		Anniversary Date 1: <%= temp.getAnniversaryOne() %><br><br>
		Anniversary Date 2: <%= temp.getAnniversaryTwo() %><br><br>
		Anniversary Date 3: <%= temp.getAnniversaryThree() %><br><br>
		<br/>
		<br/>
	<input type="button" onCLick="javascript: window.location.href='DisplayContacts.jsp?username=<%=username%>'" value="Return to View Contacts">
	</form>

</body>
</html>