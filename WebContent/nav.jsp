<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/nav.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script>
	$(function() {
		var pull = $('#pull');
		menu = $('nav ul');
		menuHeight = menu.height();
		$(pull).on('click', function(e) {
			e.preventDefault();
			menu.slideToggle();
		});
		$(window).resize(function() {
			var w = $(window).width();
			if (w > 600 && menu.is(':hidden')) {
				menu.removeAttr('style');
			}
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<nav id="nav" class="clearfix">
	<ul class="clearfix">
		<li><a href="#" target="_blank"><i class="fa fa-home"
				style="font-size: 22px"></i></a></li>

		<li class="dropdown"><a href="javascript:void(0)" class="dropbtn">MENU</a>
			<div class="dropdown-content">
				<a href="#coffe">COFFEE</a> <a href="#beverages">BEVERAGES</a> <a
					href="#dessert">DESSERT</a>
			</div></li>
		
		<!-- 1.1 세션에 저장되어있는 아이디가 없으면 LOG IN 과 SIGN UP을 띄운다 -->
		<c:choose>
		<c:when test ="${empty sessionScope.id}">
		<li style="float: right"><a href="registerForm.jsp">SIGN UP</a></li>
		<li style="float: right"><a href="loginForm.jsp">LOG IN</a></li>
		<li style="float: right"><a href="board.jsp">RESERVETION</a></li>
		<li style="float: right"><a href="http://blog.naver.com/ljh0326s"
			target="_blank">BLOG</a></li>
		<li style="float: right"><a href="https://github.com/ljh0326"
			target="_blank"><i class="fa fa-github" style="font-size: 22px"></i></a></li>
		</c:when>
		<c:otherwise>
		<!-- 1.2 세션에 저장되어 있는 아이디가 있으면 LOG OUT 과 MyPage 띄운다. -->
		<li style="float: right"><a href="myPage.jsp">MY PAGE</a></li>
		<li style="float: right"><a href="/logoutAction.jsp">LOG OUT</a></li>
		<li style="float: right"><a href="board.jsp">RESERVETION</a></li>
		<li style="float: right"><a href="http://blog.naver.com/ljh0326s"
			target="_blank">BLOG</a></li>
		<li style="float: right"><a href="https://github.com/ljh0326"
			target="_blank"><i class="fa fa-github" style="font-size: 22px"></i></a></li>
		</c:otherwise>
		</c:choose>
	</ul>
	<a id="pull" href="#">Menu</a> </nav>
</body>
</html>