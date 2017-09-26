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

	//dao객체와 user객체를 생성해준다.
	private UserDao dao = UserDao.getInstance();
	User user = new User();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//세션 객체를 생성해 준다.
		HttpSession session = req.getSession();
		
		//인코딩 타입과 출력 객체를 생성해준다.
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		//넘어온 값들을 user객체에 대입해준다.
		user.setE_mail((String)session.getAttribute("id"));
		System.out.println(user.getE_mail());
		user.setName(req.getParameter("name"));
		user.setPassword(req.getParameter("psw"));
		user.setPhonnum(req.getParameter("pnum"));

		//유저 객체를 업데이트 유저에 넣어서 결과를 받아온다.
		int result = dao.updateUser(user);
		
		
		//1.1 돌아온 값이 1이면 업데이트 성공
		if(result == 1){
			out.println("<script>");
			out.println("alert('성공적으로 수정했습니다.')");
			out.println("location.href = 'index.jsp'");
			out.println("</script>");
		}
		//1.2 돌아온 값이 2이면 데이터베이스 오류다.
		else{
			out.println("<script>");
			out.println("alert('데이터베이스 오류')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
