package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;

/**
 * The Class LogOutAction. Removes user from session.
 */
public class LogOutAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return configurationManager.getProperty("path.page.main");
	}
}
