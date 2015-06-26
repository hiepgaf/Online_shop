package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.entity.User;
import by.epam.shop.service.user.Encryption;

/**
 * The Class LogInAction. Adds user in session.
 */
public class LogInAction implements Action {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = Encryption.hashMD5(request
				.getParameter(PARAM_NAME_PASSWORD));
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findEntityByLoginAndPassword(login, password);
		if (user == null) {
			request.setAttribute("message", MessageKeys.LOG_IN_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		request.getSession().setAttribute("user", user);
		return configurationManager.getProperty("path.page.main");
	}
}
