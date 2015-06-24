package by.epam.shop.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.entity.User;
import by.epam.shop.manager.ConfigurationManager;

public class ChangeBlockingAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("user_id"));
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findEntityById(userId);
		if (user.getBlackListFlag() == 0) {
			user.setBlackListFlag(1);
		} else {
			user.setBlackListFlag(0);
		}
		userDAO.update(user);
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (user.getId() == sessionUser.getId()) {
			request.getSession().setAttribute("user", user);
		}
		List<User> users = userDAO.findAll();
		request.setAttribute("users", users);
		return configurationManager.getProperty("path.page.users");
	}
}
