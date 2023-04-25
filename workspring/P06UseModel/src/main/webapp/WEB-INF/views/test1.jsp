<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println("model test");
%>
<br>
${ObjectTest}
<br>
${lists}
	<ul>
<c:forEach var="list" items="${lists}">
	<li>${list}</li>	
</c:forEach>
	</ul>
	<br>
	이름 : ${name}
</body>
</html>