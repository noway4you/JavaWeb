<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<c:set var="x" value="100"/>
		<c:set var="x" value="123" scope="request"/>
		<c:set var="y">777</c:set>
		x = ${x}<br>
		x = ${pageScope.x}<br>
		x = ${requestScope.x}<br>
		<hr>
		y = ${y}
		<hr>
		Hello,World<br>
		<c:out value="Hello,World"/><br>
		<c:out value="${x}"/><br>
		<c:out value="${param.x}" default="no parameter"/><br>
		<hr>
		<jsp:useBean id="member" class="tw.test.apis.Member"></jsp:useBean>
		<c:set target="${member}" property="id">1</c:set>
		<c:set target="${member}" property="account">A</c:set>
		<c:set target="${member}" property="name">A1</c:set>
		id:${member.id} => account:${member.account} , name:${member.name}
		<hr>
		<c:remove var="member"/>
		${member}
	</body>
</html>