<%@page import="java.sql.PreparedStatement"%>
<%@page import="tw.test.bcrypt.BCrypt"%>
<%@page import="java.util.Properties"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user = "root";
	String user_password = "root";
	String url = "jdbc:mysql://localhost/brad";
	String sql_insert = "insert into member (account,password,name,icon) values (?,?,?,?)";
	
	Properties prop = new Properties();
	prop.put("user",user);
	prop.put("password",user_password);
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection(url,prop);

	String account = request.getParameter("account");
	String password = BCrypt.hashpw(request.getParameter("password"),BCrypt.gensalt());
	String name = request.getParameter("name");
	byte[] buffer = (byte[])request.getAttribute("icon");
	try{
		PreparedStatement prep = conn.prepareStatement(sql_insert);
		prep.setString(1,account);
		prep.setString(2,password);
		prep.setString(3,name);
		prep.setBytes(4,buffer);
		prep.executeUpdate();
		response.sendRedirect("JSP33_MainPage.jsp");
	}catch(Exception e){
		
	}
	
%>