<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.test.apis.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<sql:setDataSource
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost/brad"
	user="root"
	password="root"
/>

<sql:update>delete from food</sql:update>
<sql:update>alter table food auto_increment = 1</sql:update>

<c:import url="https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelFood.aspx" var="data"></c:import>

<c:set var="allData" value="${MyUtils.parseFood(data)}"></c:set>

<c:forEach var="row" items="${allData}">
	<c:catch var="err">
		<sql:update>
		insert into food(name,city,town,picture,lat,lng) values (?,?,?,?,?,?)
		<sql:param>${row.name}</sql:param>
		<sql:param>${row.city}</sql:param>
		<sql:param>${row.town}</sql:param>
		<sql:param>${row.picture}</sql:param>
		<sql:param>${row.lat}</sql:param>
		<sql:param>${row.lng}</sql:param>
	</sql:update>
	</c:catch>
	${err}
</c:forEach>

