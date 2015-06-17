package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.dao.AbstractDAO;
import by.epam.shop.dao.DAOFactory;
import by.epam.shop.entity.User;
import by.epam.shop.exception.DAOException;
import by.epam.shop.exception.TechnicalException;
import by.epam.shop.manager.ConfigurationManager;

public class LoginAction implements Action {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) throws DAOException,
			TechnicalException {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		DAOFactory factory = DAOFactory.getInstance();
		@SuppressWarnings("unchecked")
		AbstractDAO<User> userDAO = factory.createDAO("USER_DAO");
		User user = userDAO.findByUniqueStringKey(PARAM_NAME_LOGIN, login);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				page = ConfigurationManager.getProperty("path.page.index");
			}
		}
		return page;
	}

}
