<!-- 
	글작성에 사용되는 JSP 
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="board.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/write.css">
</head>
<body>
	<!-- 네브바 -->
	<%@ include file="nav.jsp"%>
	
	<% Bbs bbs = new Bbs(); 
	   BbsDao dao = BbsDao.getInstance();
	   
	%>
	<div class="contexter">
		<form action="/CreateAction" method="post">
			<h1>글쓰기</h1>

			<label><b>Title</b></label> <input type="text"
				placeholder="Enter Title" name="title" required> <label><b>Content</b></label>
			<textarea placeholder="Enter Contents" name="content"></textarea>
			<button type="submit">등록</button>
			<button type="reset">취소</button>
		</form>
	</div>

</body>
</html>