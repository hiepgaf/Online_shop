package by.epam.shop.action.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.User;

/**
 * The Class AllOrdersPageAction. Only available to the administrator. Jumps to
 * the all orders page.
 */
public class AllOrdersPageAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.CANCEL_ORDER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		OrderDAO orderDAO = new OrderDAO();
		List<Order> orders = orderDAO.findAll();
		request.setAttribute("orders", orders);
		return configurationManager.getProperty("path.page.allorders");
	}

}
