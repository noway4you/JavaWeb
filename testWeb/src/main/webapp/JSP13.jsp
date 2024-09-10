<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="member" class="tw.test.apis.Member"></jsp:useBean>
<jsp:setProperty property="id" value="1" name="member"/>
<jsp:setProperty property="account" value="${param.account}" name="member"/>
<jsp:setProperty property="name" value="${param.name}" name="member"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Member:<br>
		<jsp:getProperty property="id" name="member"/><br>
		<jsp:getProperty property="account" name="member"/><br>
		<jsp:getProperty property="name" name="member"/><br>
		<hr>
		Member:<br>
		<%=member.getId() %><br>
		<%=member.getAccount() %><br>
		<%=member.getName() %><br>
		<hr>
		Member:<br>
		${member.id}<br>
		${member.account}<br>
		${member.name}<br>
		<hr>
		${member}<br>
		${Math.random()}
	</body>
</html>