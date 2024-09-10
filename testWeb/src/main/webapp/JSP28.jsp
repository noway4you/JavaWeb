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
<c:catch var="err">
	<sql:update>
		update member set name = ? where id = ?
		<sql:param>abc123</sql:param>
		<sql:param>10</sql:param>
	</sql:update>
</c:catch>
${err}