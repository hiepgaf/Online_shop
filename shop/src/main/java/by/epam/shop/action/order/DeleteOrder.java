package by.epam.shop.action.order;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.manager.ConfigurationManager;

public class DeleteOrder implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		OrderDAO orderDAO = new OrderDAO();
		Order order = orderDAO.findEntityById(orderId);
		if (orderDAO.delete(order)) {
			request.setAttribute("message", MessageKeys.DELETE_ORDER_SUCCESS);
			return configurationManager.getProperty("path.page.success");
		}
		request.setAttribute("message", MessageKeys.DELETE_ORDER_ERROR);
		return configurationManager.getProperty("path.page.error");
	}

}
