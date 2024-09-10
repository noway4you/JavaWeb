<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Method: ${pageContext.request.method}<br>
		1. ${paramValues.habbit[0]}<br>
		2. ${paramValues.habbit[1]}<br>
		3. ${paramValues.habbit[2]}<br>
		4. ${paramValues.habbit[3]}<br>
		5. ${paramValues.habbit[4]}<br>
		6. ${paramValues.habbit[5]}<br>
		<hr>
		${pageContext.request.remoteAddr}<br>
		${pageContext.request.locale}<br>
		${pageContext.request.locale.displayLanguage}<br>
		${pageContext.request.locale.displayCountry}<br>
	</body>
</html>