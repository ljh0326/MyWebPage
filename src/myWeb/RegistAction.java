package myWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistAction")
public class RegistAction extends HttpServlet {

	private UserDao dao = UserDao.getInstance();
	User user = new User();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//utf-8형식으로 값을 넘겨준다.
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		
		// 파라미터로 넘어온 값을 저장한다.
		user.setE_mail(req.getParameter("email"));  
		user.setPassword(req.getParameter("psw"));
		user.setName(req.getParameter("uname"));
		user.setPhonnum(req.getParameter("pnum"));
		
		String save = req.getParameter("rememberID");
		String rPsw = req.getParameter("psw-repeat");
		
		//dao의 regist실행 성공여부 판단
		int result = dao.insertUser(user);

		//1.1 패스워드와 패스워드 확인이 같으면 등록여부 확인
		if (user.getPassword().equals(rPsw)) {
			//1.1.1 데이터베이스에 정상적으로 등록이 됐으면 체크여부 확인 후 아이디를 등록한다.
			if (result == 1) {
				//1.1.1.1 체크가 돼 있으면 쿠키를 생성한다. 
				if (save != null) {
					Cookie cookie = new Cookie("rememberID", user.getE_mail());
					cookie.setMaxAge(60 * 30);
					resp.addCookie(cookie);
				}
				//1.1.1.2 체크가 없으면 쿠키를 삭제한다.
				else{
					Cookie cookie = new Cookie("rememberID", "");
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
				}
				out.println("<script>");
				out.println("alert('등록성공.')");
				out.println("location.href = 'index.jsp'"); 
				out.println("</script>");
			}
			//1.1.2 데이터베이스에 등록이 안됐으면 등록실패 후 다시 입력하게 한다.
			else {
				out.println("<script>");
				out.println("alert('등록실패.')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
		//1.2 패스워드와 비밀번호 틀리면 다시 입력하게 
		else {
			out.println("<script>");
			out.println("alert('비밀번호가 서로 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}

	}

}
