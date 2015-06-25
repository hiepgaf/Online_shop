package by.epam.shop.action.page;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.manager.ConfigurationManager;

public class GoToPageAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		String page = request.getParameter("page");
		return configurationManager.getProperty(page);
	}
}
