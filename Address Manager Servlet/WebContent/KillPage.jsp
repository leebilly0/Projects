<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Customer, java.util.*, com.swengsol.service.CreateAccountService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<%
//***NO LONGER KILL SESSiON***

request.removeAttribute("account");
session.removeAttribute("form");
request.removeAttribute("contact");
session.removeAttribute("form2");
session.invalidate();

response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<script>
 alert("Logout Successful");
</script>

<script>window.location.href = "Login.jsp";</script>
	
	
</body>
</html>