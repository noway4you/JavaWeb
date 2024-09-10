<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String account = (String)session.getAttribute("account");
	if(account==null){
		response.sendRedirect("test27f.html");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Welcome , ${account} <br><br>
		<a href="JSP43_Signout.jsp">Sign out</a>
	</body>
</html>