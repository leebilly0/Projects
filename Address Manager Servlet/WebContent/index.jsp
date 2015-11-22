
<%@ page import="java.util.*, com.swengsol.model.Account, com.swengsol.model.Username, com.swengsol.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
</head>
<body>
<% String username = request.getParameter("username"); %>
<%= username %>

Homepage<br><br>

<a href="DisplayContacts.jsp?username=<%=username%>">View All Contacts</a>



</body>
</html>