package by.epam.shop.action;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.manager.ConfigurationManager;

/**
 * The Interface Action. Base interface for all actions available to user.
 */
public interface Action {

	static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	/**
	 * Execute.
	 *
	 * @param request
	 *            the request
	 * @return the string
	 */
	String execute(HttpServletRequest request);
}
