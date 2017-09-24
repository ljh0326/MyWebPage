<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" href="/css/registerForm.css">
</head>
<body>
    <div>
        <h1>Register Form</h1>
    </div>
    <!-- 회원가입 폼 
    	 TODO 아이디 중복 체크
     -->
    <form action="/RegistAction" style="border : 1px solid #ccc" method = "post">
        <div class="container">
            <label><b>Email</b>
                <input type="text" placeholder="Enter Email" name="email" required>
            </label>
            <label><b>User Name</b>
                <input type="text" placeholder="Enter UserName" name="uname" required>
            </label>
            <label><b>Phon Num</b>
                <input type="text" placeholder="Enter your phon number" name="pnum" required>
            </label>
            <label><b>Password</b>
                <input type="password" placeholder="Enter Password" name="psw" required>
            </label>
            <label><b>Repeat Password</b>
                <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
            </label>

            <input type="checkbox" checked="checked" name = "rememberID"> Remember me
            <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>

            <div class="clearfix">
                <button type="button" class="cancelbtn">Cancel</button>
                <button type="submit" class="signupbtn">Sign Up</button>
            </div>
        </div>
    </form>
</body>
</html>