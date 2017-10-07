package board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet("/CreateAction")
public class CreateAction extends HttpServlet {

	private BbsDao dao = BbsDao.getInstance();
	private Bbs bbs = new Bbs();

	@Override
	// get으로 req와 resp를 다시 보낸다.

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// utf-8형식으로 값을 넘겨준다.
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		
		bbs.setTitle(req.getParameter("title"));
		bbs.setContent(req.getParameter("content"));
		bbs.setUserId((String)session.getAttribute("id"));
		
		int result = dao.createBbs(bbs);
		
		if(result == 1){
			out.println("<script>");
			out.println("alert('등록성공.')");
			out.println("location.href = 'board.jsp'"); 
			out.println("</script>");
		}else{
			out.println("<script>");
			out.println("alert('등록실패.')");
			out.println("location.href = 'board.jsp'"); 
			out.println("</script>");
		}
	}
}
