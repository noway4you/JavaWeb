<%@page import="tw.test.apis.MyTest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MyTest obj = new MyTest();
	int lottery = obj.getLottery();
	session.setAttribute("obj2",obj);
	session.setMaxInactiveInterval(10);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Lottery:<%=obj.getLottery() %><br>
		Lottery:${obj2.lottery}<br>
		<a href="JSP31.jsp">Next:JSP31.jsp</a>
	</body>
</html>

<% 
	obj.setLottery(1000);
%>