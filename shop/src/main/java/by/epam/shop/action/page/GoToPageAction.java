package by.epam.shop.action.page;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;

/**
 * The Class GoToPageAction. Jumps to selected page.
 */
public class GoToPageAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String page = request.getParameter("page");
		return configurationManager.getProperty(page);
	}
}
