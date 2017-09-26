package myWeb;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * 웹어플리케이션 실행시 가장 처음 초기화 해주기위한 클래스 
 * forName은 웹어플리케이션 실행 시 한번만 실행해 주면된다 그래서 contextListener를 이용해 초기화
 * 등록은 xml에서 해주면 된다.
 */

public class DBCPInitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	//초기화 메서드
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		//1. 서블릿컨텍스트를 받아온 후 xml에서 jdbcdriver의 값을 받아온 후 Class.forName을 설정한다.
		try{
			ServletContext context = sce.getServletContext();
			String drivers = context.getInitParameter("jdbcdriver");
			Class.forName(drivers);
			
		}catch(Exception e){
			e.printStackTrace( );
		}
		
		
	}
	
}
