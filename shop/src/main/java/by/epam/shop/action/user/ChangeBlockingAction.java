package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.dao.UserDAO;
import by.epam.shop.entity.User;

public class ChangeBlockingAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter("selected");
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findEntityByLogin(login);
		if (user.getBlackListFlag() == 0) {
			user.setBlackListFlag(1);
		} else {
			user.setBlackListFlag(0);
		}
		userDAO.update(user);
		return null;
	}
}
