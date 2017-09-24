package myWeb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * DAO에서 제공한 데이터 바탕으로 로그인을 처리하는 페이지
 */

@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDAO dao = new UserDAO();
	
	
	@Override
	//get으로 req와 resp를 다시 보낸다.
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//브라우저에서 보내준 요청을 바탕으로 id, pwd, tg, 체크 유무를 파악한다.
		String id = request.getParameter("uname");
		String pwd = request.getParameter("psw");
		String tg = request.getParameter("target");
		String save = request.getParameter("remember");

		
		//어떤 타입으로 출력할지 명시 후 출력객체생성
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//세션을 생성해준다.
		HttpSession session = request.getSession();
		
		//dao클래스에서 id와 비밀번호를 보내 로직을 처리한다. 
		int result = dao.login(id, pwd);
		
		//1. 아이디비밀번호 같으면 로그인성공
		if (result == 1) {
			
			//세션 속성을 부여해준다.
			session.setAttribute("id", id);			
			//체크 포인트가 되어있으면 쿠키를 만들어 준다.
			if (save != null) {
				Cookie cookie = new Cookie("rememberID", id);
				cookie.setMaxAge(60 * 30);
				response.addCookie(cookie);
			//없으면 쿠키 제거
			} else {
				Cookie cookie = new Cookie("rememberID", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}			
			//tg값이 널이면 메인으로 널이아니면 설정해준 tg값으로 이동한다. 현재 사용안하는 기능
			if (tg != null)
				response.sendRedirect(tg);
			else
				response.sendRedirect("index.jsp");
		} 
		//2. 비밀번호 다르면 로그인 안됨
		else if (result == 0) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} 
		//3. 아이디가 없어도 로그인 안된다.
		else if (result == -1) {
			out.println("<script>");
			out.println("alert('없는 아이디 입니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		//4. 데이터 베이스 오류 
		else if (result == -2){
			out.println("<script>");
			out.println("alert('데이터베이스 오류입니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
