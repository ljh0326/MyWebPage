<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="board.*"%>
<%@ page import="user.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Bbs bbs = new Bbs();
		BbsDao dao = BbsDao.getInstance();

		bbs.setId((String) request.getParameter("id"));
		System.out.println(bbs.getId());
		bbs = dao.select(bbs);
	%>
	<!-- 네브바 -->
	<%@ include file="nav.jsp"%>

	<div class="container">
		<div class="content-form-div">
			<div class="content-form-div-div">
				<label class="id-label">작성자 : <%=bbs.getUserId()%></label>
				<hr>
				<label class="id-label">작성일 : <%=bbs.getDate()%></label>
				<hr>
				<label class="title-label">제목 : <%=bbs.getTitle()%></label>
				<hr>
				<label class="content-label">내용 : <br> <br><%=bbs.getContent()%></label><br>
				<br>

				<%
					if (session.getAttribute("id").equals(bbs.getUserId())) {
				%>
				<div>
					<a>수정</a>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>