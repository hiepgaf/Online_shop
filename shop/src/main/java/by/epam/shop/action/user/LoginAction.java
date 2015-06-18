package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;
import by.epam.shop.action.Action;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.entity.User;
import by.epam.shop.manager.ConfigurationManager;
import by.epam.shop.service.user.Encryption;

public class LoginAction implements Action {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		if (login.length() * password.length() == 0) {
			request.setAttribute("message", "nulls");
			return configurationManager.getProperty("path.page.error");
		}
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findEntityByLogin(login);
		if (user == null) {
			request.setAttribute("message", "null user");
			return configurationManager.getProperty("path.page.error");
		}
		if (!Encryption.encryptMD5(password).equals(user.getPassword())) {
			request.setAttribute("message", "password WRONG!");
			return configurationManager.getProperty("path.page.error");
		}
		request.setAttribute("user", user);
		return configurationManager.getProperty("path.page.error");
	}
}
