package user;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 로그인 필터 설정
 * 필터 가장먼저 실행했을때 실행해야 하는거 xml에서 설정가능
 */
public class LoginCheckFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	//필터기능을 정해주는 메서드 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//1. request를 형변환, 세션도 형변환해준다.
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();

		boolean login = false;

		//2. 세션에 값이 있고 id에 해당하는 값이 있다면(로그인 상태라면) login을 true로 해준다.
		if (session != null) {
			if (session.getAttribute("id") != null) {
				login = true;
			}
		}

		//3.1 로그인이 있다면 전해준 req와 resp로 계속 과정을 진행한다.
		if (login) {
			chain.doFilter(request, response);
		}
		//3.2 로그인이 안되어있다면 로그인폼으로 target값과 함께 요청을 전달한다.
		else {
			((HttpServletResponse) response).sendRedirect("loginForm.jsp?target=" + httpRequest.getRequestURI());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
