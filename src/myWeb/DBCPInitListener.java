package myWeb;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBCPInitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		try{
			ServletContext context = sce.getServletContext();
			String drivers = context.getInitParameter("jdbcdriver");
			Class.forName(drivers);
			
		}catch(Exception e){
			e.printStackTrace( );
		}
		
		
	}
	
}
