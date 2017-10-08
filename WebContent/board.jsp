<!-- 
	글목록을 보여주는 JSP
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.*"%>
<%@ page import="board.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/board.css">
<title>Insert title here</title>
</head>
<body>

	<%
		Bbs bbs = new Bbs();
		BbsDao dao = BbsDao.getInstance();
		List<Bbs> list = dao.selectAllBbs();
	%>
	<!-- 네브바 -->
	<%@ include file="nav.jsp" %>
	<h2>예약 게시판</h2>

	<table>
		<tr>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getUserId()%></td>
			<td><a href="./boardContent.jsp?id=<%=list.get(i).getId() %>"><%=list.get(i).getTitle() %></a></td>
			<td><%=list.get(i).getDate()%></td>
			<td><%=list.get(i).getCount()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<button>
		<a href="./write.jsp">글 작성</a>
	</button>
</body>
</html>