package by.epam.shop.action.order;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.User;
import by.epam.shop.exception.DAOException;

/**
 * The Class DeleteOrder. Only available to the administrator. Deletes selected
 * order.
 */
public class DeleteOrder implements Action {
	private static Logger log = Logger.getLogger(DeleteOrder.class);

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
			if (orderDAO.delete(order)) {
				request.setAttribute("message", MessageKeys.DELETE_ORDER_SUCCESS);
				return configurationManager.getProperty("path.page.success");
			}
		} catch (DAOException e) {
			log.error(e);
			request.setAttribute("message", MessageKeys.DATABASE_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		request.setAttribute("message", MessageKeys.DELETE_ORDER_ERROR);
		return configurationManager.getProperty("path.page.error");
	}

}
