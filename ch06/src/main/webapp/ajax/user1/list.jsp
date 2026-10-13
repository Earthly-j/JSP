<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ajax::user1</title>
	<script>
		
		window.onload = function() {
			const btnSubmit = document.getElementByName('submit')[0];
			const formUser = document.getElementsByTagName('form')[0];
			
			btnSubmit.oncilck = (e) => {
				e.preventDefault();
				
				// json 데이터 생성
				const uid = formUser.uid.value;
				const name = formUser.name.value;
				const birth = formUser.birth.value;
				const age = formUser.age.value;
				const hp = formUser.hp.value;
				
				// json 생성
				const jsonData = {
						"uid": uid,
						"name": name,
						"birth": birth,
						"age": age,
						"hp": hp
				}
				console.log(jsonData);
				
				// 서버 전송
				fetch('./rgisterProc.jsp', {
					method: 'POST',
					headers: {'Content-Type': 'application/json'},
					body: jsonData
				})
				.then(response => response.json())
				.then(data => {
					console.log(data);
					
					// 서버에서 결과 데이터 수신
					if(data.result > 0){
						alert('등록 성공!');
					}else{
						alert('등록 실패!');
					}
					
				})
				.catch(err => {
					console.log(err);
				});
			}
			
		}
	
	
	
	
	
	
	
	
	
	</script>
</head>
<body>
	<h3>user1</h3>
	
	<a href="./register.jsp">등록</a>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>휴대폰</th>
			<th>나이</th>
			<th>관리</th>
		</tr>
	</table>
	
	
	
</body>
</html>