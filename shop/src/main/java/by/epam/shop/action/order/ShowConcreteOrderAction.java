package by.epam.shop.action.order;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.dao.OrderDAO;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.Product;
import by.epam.shop.manager.ConfigurationManager;

public class ShowConcreteOrderAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		OrderDAO orderDAO = new OrderDAO();
		Order order = orderDAO.findEntityById(orderId);
		int price = 0;
		for (Product product : order.getProducts()) {
			price += product.getPrice();
		}
		request.setAttribute("products", order.getProducts());
		request.setAttribute("full_price", price);
		request.setAttribute("order_id", order.getId());
		return configurationManager.getProperty("path.page.order");
	}

}
