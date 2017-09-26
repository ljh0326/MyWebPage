package myWeb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 정보를 수정하는 클래스
 * */
@WebServlet("/UpdateAction")
public class UpdateAction extends HttpServlet {

	private UserDao dao = UserDao.getInstance();
	User user = new User();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		System.out.println("진입");
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		user.setE_mail((String)session.getAttribute("id"));
		System.out.println(user.getE_mail());
		user.setName(req.getParameter("name"));
		user.setPassword(req.getParameter("psw"));
		user.setPhonnum(req.getParameter("pnum"));

		int result = dao.updateUser(user);
		
		if(result == 1){
			out.println("<script>");
			out.println("alert('성공적으로 수정했습니다.')");
			out.println("location.href = 'index.jsp'");
			out.println("</script>");
		} else{
			out.println("<script>");
			out.println("alert('데이터베이스 오류')");
			out.println("history.back()");
			out.println("</script>");
		}
		System.out.println(result);
	}

}
