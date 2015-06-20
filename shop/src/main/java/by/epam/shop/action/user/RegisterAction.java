package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.entity.User;
import by.epam.shop.manager.ConfigurationManager;
import by.epam.shop.service.user.Encryption;
import by.epam.shop.service.user.Validator;

public class RegisterAction implements Action {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_PASSWORD_REPEAT = "password_repeat";
	private static final String PARAM_NAME_EMAIL = "email";
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		String passwordRepeat = request
				.getParameter(PARAM_NAME_PASSWORD_REPEAT);
		String email = request.getParameter(PARAM_NAME_EMAIL);
		if (login.length() * password.length() * passwordRepeat.length()
				* email.length() == 0) {
			request.setAttribute("message", MessageKeys.REGISTER_BLANK_FIELDS);
			return configurationManager.getProperty("path.page.register");
		}
		if (!password.equals(passwordRepeat)) {
			request.setAttribute("message",
					MessageKeys.REGISTER_PASSWORD_MISMATCH);
			return configurationManager.getProperty("path.page.register");
		}
		User user = new User();
		user.setLogin(login);
		user.setPassword(Encryption.hashMD5(password));
		user.setEmail(email);
		user.setAccessLevel(1); // default value for simple user
		String validationMessage = Validator.validateUser(user);
		if (validationMessage != null) {
			request.setAttribute("message", validationMessage);
			return configurationManager.getProperty("path.page.register");
		} else {
			UserDAO userDAO = new UserDAO();
			if (userDAO.findEntityByLogin(login) == null) {
				request.getSession().setAttribute("user", user);
				return configurationManager.getProperty("path.page.main");
			} else {
				request.setAttribute("loginError",
						MessageKeys.REGISTER_LOGIN_ERROR);
				return configurationManager.getProperty("path.page.register");
			}
		}
	}
}
