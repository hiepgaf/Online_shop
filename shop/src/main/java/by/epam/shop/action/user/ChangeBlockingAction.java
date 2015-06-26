package by.epam.shop.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.entity.User;

/**
 * The Class ChangeBlockingAction. Only available to the administrator. Changes
 * the blocking of selected user. If user is blocked, he couldn't make
 * purchases.
 */
public class ChangeBlockingAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (sessionUser == null || sessionUser.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		int userId = Integer.parseInt(request.getParameter("user_id"));
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findEntityById(userId);
		if (user.getBlackListFlag() == 0) {
			user.setBlackListFlag(1);
		} else {
			user.setBlackListFlag(0);
		}
		userDAO.update(user);
		if (user.getId() == sessionUser.getId()) {
			request.getSession().setAttribute("user", user);
		}
		List<User> users = userDAO.findAll();
		request.setAttribute("users", users);
		return configurationManager.getProperty("path.page.users");
	}
}
