<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.test.bcrypt.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:if test="${empty member.account}">
	<c:redirect url="JSP33_LogIn.jsp"></c:redirect>
</c:if>
<c:if test="${!empty param.account}">
	<c:if test="${pageContext.request.method != 'POST'}"><c:redirect url="LogIn.jsp"></c:redirect></c:if>
	<sql:setDataSource
		driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost/brad"
		user="root"
		password="root"
	/>
	<sql:update>
		insert into member (account,password,name) values (?,?,?)
		<sql:param>${param.account}</sql:param>	
		<sql:param>${BCrypt.hashpw(param.password,BCrypt.gensalt())}</sql:param>	
		<sql:param>${param.name}</sql:param>	
	</sql:update>
	<c:redirect url="JSP33_MainPage.jsp"></c:redirect>
</c:if>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
		<script type="text/javascript">
			function isAccountExist(){
				$.get("JSP33_isAccountExist.jsp?account="+$("#account").val(),function(data,status){
					if(status == "success"){
						$("#mesg").html(data.count>0?"Account Exist":"");
					}
				});
			};
		</script>
	</head>
	<body>
		Add Member Page
		<hr>
		<form action="test22b" method="post" enctype="multipart/form-data">
			Account:<input name="account" id="account" onblur="isAccountExist()"><span id="mesg"></span><br><br>
			Password:<input type="password" name="password"><br><br>
			Name:<input name="name"><br><br>
			Icon:<input type="file" name="icon"><br><br>
			<input type="submit" value="Add">
		</form>
	</body>
</html>