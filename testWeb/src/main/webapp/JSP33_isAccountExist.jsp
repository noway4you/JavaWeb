<%@page import="org.json.JSONObject"%>
<%@page import="javax.servlet.jsp.jstl.sql.Result"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<sql:setDataSource
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost/brad"
	user="root"
	password="root"
/>   
<sql:query var="result">
	select count(account) count from member where account = ?
	<sql:param>${param.account}</sql:param>
</sql:query>
<%
	Result rs = (Result)pageContext.getAttribute("result");
	long count = (Long)rs.getRows()[0].get("count");
	JSONObject root = new JSONObject();
	root.put("count",count);
	pageContext.setAttribute("count",root.toString());  //JSON format
%>
${count}