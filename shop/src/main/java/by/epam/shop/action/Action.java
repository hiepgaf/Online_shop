package by.epam.shop.action;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.exception.DAOException;
import by.epam.shop.exception.TechnicalException;

public interface Action {
	String execute(HttpServletRequest request) throws DAOException, TechnicalException;
}
