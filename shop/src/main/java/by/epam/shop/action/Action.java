package by.epam.shop.action;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	String execute(HttpServletRequest request);
}
