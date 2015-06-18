package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.entity.User;
import by.epam.shop.manager.ConfigurationManager;
import by.epam.shop.manager.MessageManager;
import by.epam.shop.service.user.Encryption;

public class RegisterAction implements Action {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_PASSWORD_REPEAT = "password_repeat";
	private static final String PARAM_NAME_EMAIL = "email";
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();
	private static MessageManager messageManager = MessageManager.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = Encryption.hashMD5(request
				.getParameter(PARAM_NAME_PASSWORD));
		String passwordRepeat = Encryption.hashMD5(request
				.getParameter(PARAM_NAME_PASSWORD_REPEAT));
		String email = request.getParameter(PARAM_NAME_EMAIL);
		if (login.length() * password.length() * passwordRepeat.length()
				* email.length() == 0) {
			request.setAttribute("fieldMessage",
					messageManager.getProperty("message.blankfields"));
			return configurationManager.getProperty("path.page.register");
		}
		if (!password.equals(passwordRepeat)) {
			request.setAttribute("passwordsMessage",
					messageManager.getProperty("message.password_mismatch"));
			return configurationManager.getProperty("path.page.register");
		}
		UserDAO userDAO = new UserDAO();
		if (userDAO.findEntityByLogin(login) == null) {
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			user.setEmail(email);
			user.setAccessLevel(1); // default value for simple user
			userDAO.create(user);
			request.getSession().setAttribute("user", user);
			return configurationManager.getProperty("path.page.main");
		} else {
			request.setAttribute("loginError",
					messageManager.getProperty("message.registerloginerror"));
			return configurationManager.getProperty("path.page.register");
		}
	}
}
