<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LogIn</title>
	</head>
	<body>
		LogIn Page
		<hr>
		<form action="JSP33_checkAccount2.jsp" method="post">
			Account:<input name="account"><br><br>
			Password:<input type="password" name="password"><br><br>
			<input type="submit" value="Login">
		</form>
	</body>
</html>