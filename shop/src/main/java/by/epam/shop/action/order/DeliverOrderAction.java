package by.epam.shop.action.order;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.constant.OrderStatuses;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.User;
import by.epam.shop.exception.DAOException;

/**
 * The Class DeliverOrderAction. Only available to the administrator. Changes
 * the status of selected order to "delivered".
 */
public class DeliverOrderAction implements Action {
	private static Logger log = Logger.getLogger(DeliverOrderAction.class);

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		OrderDAO orderDAO = new OrderDAO();
		try {
			Order order = orderDAO.findEntityById(orderId);
			if (order == null) {
				request.setAttribute("message", MessageKeys.FIND_ORDER_ERROR);
				return configurationManager.getProperty("path.page.error");
			}
			if (OrderStatuses.ACTIVE.equals(order.getStatus())) {
				order.setStatus(OrderStatuses.DELIVERED);
				if (orderDAO.updateStatus(order)) {
					request.setAttribute("message", MessageKeys.DELIVER_ORDER_SUCCESS);
					return configurationManager.getProperty("path.page.success");
				}
			}
			request.setAttribute("message", MessageKeys.DELIVER_ORDER_ERROR);
			return configurationManager.getProperty("path.page.error");
		} catch (DAOException e) {
			log.error(e);
			request.setAttribute("message", MessageKeys.DATABASE_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
	}
}
