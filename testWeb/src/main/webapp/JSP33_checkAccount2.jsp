<%@page import="java.util.Base64"%>
<%@page import="tw.test.apis.Member"%>
<%@page import="javax.servlet.jsp.jstl.sql.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.test.bcrypt.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<c:if test="${empty param.account}">
	<c:redirect url="JSP33_LogIn.jsp"></c:redirect>
</c:if>

<sql:setDataSource
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost/brad"
	user="root"
	password="root"
/>

<sql:query var="result">
	select * from member where account = ?
	<sql:param>${param.account}</sql:param>
</sql:query>

<c:if test="${result.rowCount == 0}">
	<c:redirect url="JSP33_LogIn.jsp"></c:redirect>
</c:if>

<c:choose>
	<c:when test="${BCrypt.checkpw(param.password,result.rows[0].password)}">
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
			
			session.setAttribute("member",member);
		%>
		<c:redirect url="JSP33_MainPage.jsp"></c:redirect>
	</c:when>
	<c:otherwise>
		<c:redirect url="JSP33_LogIn.jsp"></c:redirect>
	</c:otherwise>
</c:choose>