package by.epam.shop.action.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.User;
import by.epam.shop.manager.ConfigurationManager;

public class ShowOrdersAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		OrderDAO orderDAO = new OrderDAO();
		List<Order> orders = orderDAO.findEntitiesByUserId(user.getId());
		request.setAttribute("orders", orders);
		return configurationManager.getProperty("path.page.orders");
	}
}
