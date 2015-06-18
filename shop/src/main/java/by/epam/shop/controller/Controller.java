package by.epam.shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.action.container.ActionFactory;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static Logger log = Logger.getLogger(Controller.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String page = null;
		try {
			ActionFactory factory = ActionFactory.getInstance();
			Action action = factory.defineCommand(request);
			page = action.execute(request);
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			log.info(e);
		}
	}
}
