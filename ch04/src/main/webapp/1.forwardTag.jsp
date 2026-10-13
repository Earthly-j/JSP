<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>1.forwardTag</title>
	<%-- 
		날짜: 2024/08/07
	 	이름: 박준영
	 	내용: JSP 액션태그 실습 
	--%>
	<!-- 
		- forward: 서버 측에서 요청을 다른 페이지로 전달하며, 클라이언트는 이를 알지 못한다
			서버 내부에서 요청을 다른 페이지로 전달하고 싶을 때 사용
		 	 예를 들어, 로그인 후 특정 페이지로 이동시키는 경우
		 
		- href: 클라이언트 측에서 새로운 요청을 서버에 보내며, 브라우저 URL이 변경
				클라이언트가 직접 링크를 클릭하여 다른 페이지로 이동하고 싶을 때 사용
	 -->
</head>
<body>
	<h3>1.forward 액션태그</h3>
	
	<a href="./proc/forward1.jsp">forward 함수</a>
	<a href="./proc/forward2.jsp">forward 태그</a>
	
	


</body>
</html>