<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>modify.jsp</h4>
	<form action="modifySave" method="post">
		번호 : <input type="text" name="id" value="${list.id }"><br>
		이름 : <input type="text" name="name" value="${list.name }"><br>
		<input type="submit" value="수정">
		<a href="content">목록보기</a>
	</form>
</body>
</html>