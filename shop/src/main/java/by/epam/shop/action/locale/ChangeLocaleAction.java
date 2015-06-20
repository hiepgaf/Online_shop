package by.epam.shop.action.locale;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.manager.ConfigurationManager;

public class ChangeLocaleAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		String lang = request.getParameter("locale");
		request.getSession().setAttribute("locale", lang);
		return configurationManager.getProperty("path.page.main");
	}
}
