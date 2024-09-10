<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Properties"%>
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
	
		<c:catch var="err">
			<%
				Class.forName("com.mysql.cj.jdbc.Driver");
				Properties prop = new Properties();
				prop.put("user","root");
				prop.put("password","root");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/brad",prop);
				
				String sql = "insert into member (account,password,name) values (?,?,?)";
				PreparedStatement prep = conn.prepareStatement(sql);
				prep.setString(1,"asd");
				prep.setString(2,"123");
				prep.setString(3,"asd123");
				int n = prep.executeUpdate();
				pageContext.setAttribute("n",n);
			%>
		</c:catch>
		<c:if test="${!empty err}">Server Busy : ${err}</c:if>
		<c:choose>
			<c:when test="${n>0}">Success</c:when>
			<c:otherwise>Failure</c:otherwise>
		</c:choose>
		
	</body>
</html>