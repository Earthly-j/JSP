<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>3.pageContext</title>
	<%-- 
		날짜: 2024/08/06
	 	이름: 박준영
	 	내용: pageContext 내장객체 실습 
	--%>
	
</head>
<body>
	<h3>3.pageContext 내장객체</h3>
	
	<h4>include</h4> 
	<%@ include file="./inc/header.jsp" %> 
	<%
		// pageContext 동적 include
		pageContext.include("./inc/header.jsp");
		pageContext.include("./inc/footer.jsp");
	%> 
	
	
	<h4>forward</h4>
	<a href="./proc/forward1.jsp"></a>
	<a href="./proc/forward2.jsp"></a>
	
	
	
</body>
</html>