<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>save_view.jsp</h4>
	<form action="save" method="post">
		<table border="1">
			<tr>
				<td>번호</td> <td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>이름</td> <td><input type="text" name="name"></td>
			</tr>
			<tr> 
				<td colspan="2">
					<input type="submit" value="저장">
					<a href="content">목록보기</a> 
				</td> 
			</tr>
		</table>
	</form>
</body>
</html>