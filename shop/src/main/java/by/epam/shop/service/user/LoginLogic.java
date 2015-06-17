package by.epam.shop.service.user;

import by.epam.shop.dao.AbstractDAO;
import by.epam.shop.dao.DAOFactory;
import by.epam.shop.entity.User;

public class LoginLogic {
	public static boolean checkLogin(String login, String password) {
		DAOFactory factory = DAOFactory.getInstance();
		AbstractDAO<User> userDAO = factory.createDAO("USER_DAO");
		return ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASS.equals(enterPass);
	}
}
