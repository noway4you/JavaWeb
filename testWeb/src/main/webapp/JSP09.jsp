<%@page import="tw.test.apis.bike"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = request.getParameter("name");
	bike bike  = new bike();
	request.setAttribute("bike",10);
	request.setAttribute("name","AAA");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Here is JSP09<br><br>
		<jsp:forward page="JSP10.jsp"></jsp:forward>
	</body>
</html>