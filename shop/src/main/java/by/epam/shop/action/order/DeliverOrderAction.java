package by.epam.shop.action.order;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.User;

/**
 * The Class DeliverOrderAction. Only available to the administrator. Changes
 * the status of selected order to "delivered".
 */
public class DeliverOrderAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		OrderDAO orderDAO = new OrderDAO();
		Order order = orderDAO.findEntityById(orderId);
		if ("active".equals(order.getStatus())) {
			order.setStatus("delivered");
			if (orderDAO.updateStatus(order)) {
				request.setAttribute("message",
						MessageKeys.DELIVER_ORDER_SUCCESS);
				return configurationManager.getProperty("path.page.success");
			}
		}
		request.setAttribute("message", MessageKeys.DELIVER_ORDER_ERROR);
		return configurationManager.getProperty("path.page.error");
	}
}
