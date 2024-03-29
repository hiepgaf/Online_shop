package by.epam.shop.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.entity.User;
import by.epam.shop.exception.DAOException;

/**
 * The Class ChangeBlockingAction. Only available to the administrator. Changes
 * the blocking of selected user. If user is blocked, he couldn't make
 * purchases.
 */
public class ChangeBlockingAction implements Action {
	private static Logger log = Logger.getLogger(ChangeBlockingAction.class);

	@Override
	public String execute(HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null || sessionUser.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		int userId = Integer.parseInt(request.getParameter("user_id"));
		UserDAO userDAO = new UserDAO();
		try {
			User user = userDAO.findEntityById(userId);
			if (user == null) {
				request.setAttribute("message", MessageKeys.FIND_USER_ERROR);
				return configurationManager.getProperty("path.page.error");
			}
			if (user.getBlackListFlag() == 0) {
				user.setBlackListFlag(1);
			} else {
				user.setBlackListFlag(0);
			}
			userDAO.update(user);
			if (user.getId().equals(sessionUser.getId())) {
				request.getSession().setAttribute("user", user);
			}
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
