<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="hello"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<hello:tag1></hello:tag1><hr>
		<hello:tag2></hello:tag2><hr>
		<hello:lottery></hello:lottery><hr>
		<table width="100%" border="1">
			<tr>
				<th>Name</th>
				<th>Price</th>
			</tr>
			<hello:item name="A" price="1000"></hello:item>
			<hello:item name="B" price="2000"></hello:item>
			<hello:item name="C" price="3000"></hello:item>
			<hello:item name="D" price="4000"></hello:item>
		</table>
	</body>
</html>