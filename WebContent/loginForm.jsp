<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" href="/css/loginForm.css">
</head>
<body>

<!-- 쿠키 -->
	<%
	    //아이디 변수를 생성
		String id = "";
		//쿠키를 받는다.
		Cookie[] cookies = request.getCookies();
		
		//만약 쿠키값이 있으면 주어진 값과 일치하는지 확인 후 값을 id변수에 저장한다.
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("rememberID")) {
					id = cookies[i].getValue();
				}
			}
		}
	%>
	
	<%
		//타겟 값을 다시 받는다. 현재 안쓰는 기능
		//String target = request.getParameter("target");
	%>

	<form action="/LoginAction" method="get">
		<div class="imgcontainer">
			<img
				src="http://schoolmus.com/gnu/data/cheditor4/1401/e583193771e97cdc880e768b0661ef44_Fzexwc958Q4t.png"
				alt="Avatar" class="avater">
		</div>
		<div>
			<label for=""><b> Username </b> <input type="text"
				placeholder="Enter Username" name="uname" value="<%=id%>" required>
			</label> <label for=""><b> Password </b> <input type="password"
				placeholder="Enter Passward" name="psw" required> </label> 
				<!-- <input type="text" name="target" value="<//%=target%>"> -->
			<button type="submit">Login</button>
			<input type="checkbox" <%=!id.equals("") ? "checked" : ""%>
				name="remember"> Remember me
		</div>
		<div class="container" style="background-color: #f1f1f1">
			<button class="cancelbtn">Cancel</button>
			<span class="psw">Forgot <a href="#">password?</a></span>
		</div>

	</form>
</body>
</html>