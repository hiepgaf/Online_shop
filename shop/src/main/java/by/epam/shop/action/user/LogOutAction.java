package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.manager.ConfigurationManager;

public class LogOutAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return configurationManager.getProperty("path.page.main");
	}
}
