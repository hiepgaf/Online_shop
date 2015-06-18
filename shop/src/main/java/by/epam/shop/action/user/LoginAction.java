package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.exception.DAOException;
import by.epam.shop.exception.TechnicalException;

public class LoginAction implements Action {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) throws DAOException,
			TechnicalException {
		String page = null;

		return page;
	}

}
