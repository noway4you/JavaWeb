<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Multiplication table</h1>
		<form>
		    Start = <input type="number" name="start">
			Row x Col = <input type="number" name="rows">x<input type="number" name="cols">
			<input type="submit" value="change">
		</form>
		<table border="1" width="100%">
			<%
				int start,rows,cols;
				if(request.getParameter("start")==null) start = 2;
				else start = Integer.parseInt(request.getParameter("start"));
				if(request.getParameter("rows")==null) rows = 2;
				else rows = Integer.parseInt(request.getParameter("rows"));
				if(request.getParameter("cols")==null) cols = 4;
				else cols = Integer.parseInt(request.getParameter("cols"));
				
				for(int i=1;i<=rows;i++){
					out.print("<tr>");
					for(int j=1;j<=cols;j++){
						out.print("<td>");
						int new_start = start+(i-1)*cols+j-1;
						for(int k=1;k<=9;k++){
							out.print(new_start+" x "+k+" = "+(new_start * k)+"<br>");
						}
						out.print("</td>");
					}
					out.print("</tr>");
				}
			%>
		</table>
	</body>
</html>