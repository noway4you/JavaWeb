<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.concurrent.LinkedBlockingDeque"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String[] names1 = {"A","B","C","D","E"};
	pageContext.setAttribute("names1",names1);
	
	LinkedList<String> list = new LinkedList<>();
	pageContext.setAttribute("list",list);
	list.add("a");
	list.add("b");
	list.add("c");
	list.add("d");
	list.add("e");
	
	HashMap<Integer,Object> map = new HashMap<>();
	pageContext.setAttribute("map",map);
	map.put(1,"Aa");
	map.put(2,"Bb");
	map.put(3,"Cc");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		names1[2] = ${names1[2]}<br>
		list[1] = ${list[1]}<br>
		${map[1]}<br>
	</body>
</html>