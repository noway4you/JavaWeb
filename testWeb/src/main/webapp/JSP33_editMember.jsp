<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.test.bcrypt.*" %>
<%@page import="javax.servlet.jsp.jstl.sql.Result"%>
<%@page import="java.util.Base64"%>
<%@page import="tw.test.apis.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:if test="${empty member.account}">
	<c:redirect url="JSP33_LogIn.jsp"></c:redirect>
</c:if>
<sql:setDataSource
		driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost/brad"
		user="root"
		password="root"
/>

<c:choose>
	<c:when test="${!empty param.editID}">
		<sql:query var="result">
			select * from member where id = ?
			<sql:param>${param.editID}</sql:param>
		</sql:query>
		<c:if test="${result.rowCount == 0}"><c:redirect url="JSP33_MainPage.jsp"></c:redirect></c:if>
	
		<%
			Result res = (Result)pageContext.getAttribute("result");
			long id = (Long)res.getRows()[0].get("id");
			String account = (String)res.getRows()[0].get("account");
			String password = (String)res.getRows()[0].get("password");
			String name = (String)res.getRows()[0].get("name");
			byte[] icon = (byte[])res.getRows()[0].get("icon");
			String iconBase64 = Base64.getEncoder().encodeToString(icon);
			
			Member member = new Member();
			member.setId((int)id);
			member.setAccount(account);
			member.setPassword(password);
			member.setName(name);
			member.setIcon(iconBase64);
			
			session.setAttribute("editMember",member);
			
		%>
	</c:when>
	<c:otherwise>
		<c:if test="${pageContext.request.method != 'POST'}"><c:redirect url="JSP33_LogOut.jsp"></c:redirect></c:if>
		<sql:update>
			update member set account = ?,password = ?,name = ? where id = ?
			<sql:param>${param.account}</sql:param>	
			<sql:param>${BCrypt.hashpw(param.password,BCrypt.gensalt())}</sql:param>	
			<sql:param>${param.name}</sql:param>	
			<sql:param>${editMember.id}</sql:param>	
		</sql:update>
		<%
			session.removeAttribute("editMember");
		%>
		<c:redirect url="JSP33_MainPage.jsp"></c:redirect>
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Edit Member Page
		<hr>
		<form action="JSP33_editMember.jsp" method="post">
			Account:<input name="account" value="${editMember.account}"><br><br>
			Password:<input type="password" name="password"><br><br>
			Name:<input name="name" value="${editMember.name}"><br><br>
			Icon:<img src="data:image/png; base64,${editMember.icon}"><br><br>
			<input type="submit" value="Update">
		</form>
	</body>
</html>