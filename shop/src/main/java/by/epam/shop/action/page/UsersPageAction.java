package by.epam.shop.action.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.entity.User;
import by.epam.shop.exception.DAOException;

/**
 * The Class UsersPageAction. Only available to the administrator. Jumps to the
 * all users page.
 */
public class UsersPageAction implements Action {
	private static Logger log = Logger.getLogger(UsersPageAction.class);

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.CANCEL_ORDER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		UserDAO userDAO = new UserDAO();
		try {
			List<User> users = userDAO.findAll();
			request.setAttribute("users", users);
			return configurationManager.getProperty("path.page.users");
		} catch (DAOException e) {
			log.error(e);
			request.setAttribute("message", MessageKeys.DATABASE_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
	}
}
