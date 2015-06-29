package by.epam.shop.action.order;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.constant.OrderStatuses;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.exception.DAOException;

/**
 * The Class CancelOrderAction. Cancels the order if its status is "active".
 */
public class CancelOrderAction implements Action {
	private static Logger log = Logger.getLogger(CancelOrderAction.class);

	@Override
	public String execute(HttpServletRequest request) {
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		OrderDAO orderDAO = new OrderDAO();
		try {
			Order order = orderDAO.findEntityById(orderId);
			if (OrderStatuses.ACTIVE.equals(order.getStatus())) {
				order.setStatus(OrderStatuses.CANCELED);
				if (orderDAO.updateStatus(order)) {
					request.setAttribute("message", MessageKeys.CANCEL_ORDER_SUCCESS);
					return configurationManager.getProperty("path.page.success");
				}
			}
		} catch (DAOException e) {
			log.error(e);
			request.setAttribute("message", MessageKeys.DATABASE_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		request.setAttribute("message", MessageKeys.CANCEL_ORDER_ERROR);
		return configurationManager.getProperty("path.page.error");
	}
}
