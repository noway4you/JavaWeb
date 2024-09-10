<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//  level => pageContext > request > session > application
	String name1 = "Gary";
	pageContext.setAttribute("name1",name1);
	
	String name2 = "AAA";
	request.setAttribute("name1",name2);
	
//  if not remove session syntax , it will still exist	
	String name3 = "BBB";
	session.setAttribute("name1",name3);
	
	String name4 = "CCC";
	application.setAttribute("name1",name4);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Name: ${name1}<br>
	</body>
</html>