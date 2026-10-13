<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	String param = request.getQueryString();
	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String age = request.getParameter("age");

%>



<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>result2</title>
</head>
<body>
	<h3>POST 요청 데이터 수신</h3>
	
	
	<a href="../1.request.jsp">뒤로가기</a>

</body>
</html>