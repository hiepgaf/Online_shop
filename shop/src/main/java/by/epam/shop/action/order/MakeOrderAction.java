package by.epam.shop.action.order;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.Product;
import by.epam.shop.entity.User;
import by.epam.shop.manager.ConfigurationManager;

public class MakeOrderAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		String[] selected = request.getParameterValues("selected");
		if (selected == null) {
			request.setAttribute("message", MessageKeys.MAKE_ORDER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		ProductDAO productDAO = new ProductDAO();
		Order order = new Order();
		order.setUser(user);
		order.setStatus("active");
		for (int i = 0; i < selected.length; i++) {
			Product product = productDAO.findEntityById(Integer
					.parseInt(selected[i]));
			order.addProduct(product);
		}
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.create(order);
		request.setAttribute("message",
				MessageKeys.ADD_TO_SHOPPING_CART_SUCCESS);
		return configurationManager.getProperty("path.page.success");
	}

}
