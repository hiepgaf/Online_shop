package by.epam.shop.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

import by.epam.shop.connectionpool.ConnectionPool;

/**
 * The listener interface for receiving context events. Initializes log4j in the
 * event the creation of the servlet context.
 *
 * @see ContextEvent
 */
@WebListener
public class ContextListener implements ServletContextListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String log4jConfigFile = context.getInitParameter("log4j-config-location");
		String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
		PropertyConfigurator.configure(fullPath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.shutDown();
	}
}
