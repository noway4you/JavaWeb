<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<sql:setDataSource
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost/brad"
	user="root"
	password="root"
/>
<c:set var="rpp">10</c:set>
<c:set var="page">${empty param.page?1:param.page}</c:set>
<c:set var="start">${rpp*(page-1)}</c:set>
<c:set var="prev">${page==1?page:page-1}</c:set>
<c:set var="next">${page+1}</c:set>
<c:set var="sql">select * from gift limit ${start},${rpp}</c:set>
<sql:query var="result">${sql}</sql:query>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		
		<a href="?page=${prev}">prev</a> | <a href="?page=${next}">next</a>
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