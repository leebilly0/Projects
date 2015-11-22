<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*, com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Customer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<form action="LoginServlet" method="post">
        <p style="color:red">${message}</p>
        <p>Username:<input type="text" name="username" value="" /></p>
        <p>Password:<input type="password" name="password" value=""/></p>
                
        <a href="firstPage.jsp?step=0">Create An Account</a> <input type="submit" name="next" value="Login" />      
    </form>
   
    <%

//don't create new customer/user/account on this page?
Customer cust = new Customer();
Username user = new Username();
Account account = new Account(cust, user);

//change this to regular html?
session.setAttribute("form", account);

%>


</body>
</html>