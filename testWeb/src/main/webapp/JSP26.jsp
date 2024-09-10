<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<sql:setDataSource
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost/brad"
	user="root"
	password="root"
/>
<c:set var="sql">select * from gift</c:set>
<c:if test="${!empty param.key}">
	<c:set var="sql">select * from gift where name like "%${param.key}%" or address like "%${param.key}%"</c:set>
</c:if>
<sql:query var="result">${sql}</sql:query>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Data Count = ${result.rowCount}<hr>
		<form>
			KeyWord = <input name="key" value="${param.key}">
			<input type="submit" vale="search">
			
		</form>
		<hr>
		<table border="1" width="100%">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Address</th>
				<th>Picture</th>
				<th>City</th>
			</tr>
			<c:forEach var="gift" items="${result.rows}">
				<tr>
					<td>${gift.id}</td>
					<td>${gift.name}</td>
					<td>${gift.address}</td>
					<td><img src="${gift.picture}" height="90px" width="160px"></td>
					<td>${gift.city}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>