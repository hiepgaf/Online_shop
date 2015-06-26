package by.epam.shop.action.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.Product;
import by.epam.shop.entity.User;

/**
 * The Class MakeOrderAction. Creates an order of products in the shopping cart.
 */
public class MakeOrderAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		if (user.getBlackListFlag() == 1) {
			request.setAttribute("message", MessageKeys.BUY_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		List<Product> products = user.getShoooppingCart();
		if (products == null) {
			request.setAttribute("message", MessageKeys.MAKE_ORDER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		Order order = new Order();
		order.setProducts(products);
		order.setStatus("active");
		order.setUser(user);
		OrderDAO orderDAO = new OrderDAO();
		if (orderDAO.create(order)) {
			user.removeAllProducts();
			request.setAttribute("message", MessageKeys.MAKE_ORDER_SUCCESS);
			return configurationManager.getProperty("path.page.success");
		} else {
			request.setAttribute("message", MessageKeys.MAKE_ORDER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
	}

}
