<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = "";
	String errType = request.getParameter("errType");
	if(errType!=null){
		switch(errType){
			case "1": message = "Account has existed";break;
			case "2": message = "SQL error";break;
			case "3": message = "Regist failure";break;
		}
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<div><%= message %></div><br>
		<script>
			function checkForm(){
				return true;
			}
		</script>
		<form action="Register" method="post" onsubmit="return checkForm();">
			Account : <input name="account"><br><br>
			Password : <input name="password"><br><br>
			Name : <input name="name"><br><br>
			<input type="submit" value="Register">
		</form>
		
	</body>
</html>