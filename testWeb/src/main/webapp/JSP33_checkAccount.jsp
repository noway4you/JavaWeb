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
		<c:set var="member" value="${result.rows[0]}" scope="session"></c:set>
		<c:redirect url="JSP33_MainPage.jsp"></c:redirect>
	</c:when>
	<c:otherwise>
		<c:redirect url="JSP33_LogIn.jsp"></c:redirect>
	</c:otherwise>
</c:choose>