package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;

public class LoginAction implements Action {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String page= null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		if(LoginLogic.checkLogin(login, pass)) {
		request.setAttribute("user", login);
		page = ConfigurationManager.getProperty("path.page.main");
		} else{
		request.setAttribute("errorLoginPassMessage",
		MessageManager.getProperty("message.loginerror"));
		page = ConfigurationManager.getProperty("path.page.login");
		}
		return page;
	}

}
