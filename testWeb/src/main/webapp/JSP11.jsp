<%@page import="tw.test.apis.Member"%>
<%@page import="tw.test.apis.MemberDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member2 = new Member();
	member2.setId(2); member2.setAccount("bbb"); member2.setName("123");
%>
<jsp:useBean id="member1" class="tw.test.apis.Member"></jsp:useBean>
<jsp:setProperty property="id" value="1" name="member1"/>
<jsp:setProperty property="account" value="aaa" name="member1"/>
<jsp:setProperty property="name" value="aaa123" name="member1"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Member1:<br>
			<jsp:getProperty property="id" name="member1"/><br>
			<jsp:getProperty property="account" name="member1"/><br>
			<jsp:getProperty property="name" name="member1"/><br>
			<%=member1 %><br>
		<hr>
		Member2:<br>
			<%=member2.getId() %><br>
			<%=member2.getAccount() %><br>
			<%=member2.getName() %><br>
	</body>
</html>