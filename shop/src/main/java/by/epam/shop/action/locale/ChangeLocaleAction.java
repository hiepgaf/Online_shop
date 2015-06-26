package by.epam.shop.action.locale;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;

/**
 * The Class ChangeLocaleAction. Changes current locale in session.
 */
public class ChangeLocaleAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String lang = request.getParameter("locale");
		request.getSession().setAttribute("locale", lang);
		return configurationManager.getProperty("path.page.main");
	}
}
