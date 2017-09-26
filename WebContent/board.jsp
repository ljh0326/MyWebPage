<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="myWeb.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/myPage.css">
<title>Insert title here</title>
</head>
<body>

	<%
		String id = (String) session.getAttribute("id");

		UserDao dao = UserDao.getInstance();
		User user = new User();

		user = dao.getUserInfo(id);
	%>
	<h2>내 정보</h2>

	<form action="/UpdateAction">
		<table>
			<tr>
				<td>E_mial</td>
				<td><%=user.getE_mail()%></td>
			</tr>
			<tr>
				<td>NAME</td>
				<td><input type="text" name="name" id=""
					value="<%=user.getName()%>"></td>
			</tr>
			<tr>
				<td>PASSWORD</td>
				<td><input type="password" name="psw" id=""
					value="<%=user.getPassword()%>"></td>
			</tr>
			<tr>
				<td>가입일</td>
				<td><%=user.getIn_date()%></td>
			</tr>
			<tr>
				<td>전화 번호</td>
				<td><input type="text" name="pnum" id=""
					value="<%=user.getPhonnum()%>"></td>
			</tr>
			<tr>
				<td>포인트</td>
				<td><%=user.getPoint()%></td>
			</tr>
		</table>
		<input type="submit" value="수정하기">
	</form>

</body>
</html>