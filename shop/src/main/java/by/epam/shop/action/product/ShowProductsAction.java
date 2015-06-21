package by.epam.shop.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.Product;
import by.epam.shop.manager.ConfigurationManager;

public class ShowProductsAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		String type = request.getParameter("product_type");
		ProductDAO productDAO = new ProductDAO();
		List<Product> products = productDAO.findEntitiesByType(type);
		if (products == null) {
			request.setAttribute("message", MessageKeys.FIND_PRODUCTS_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		request.setAttribute("products", products);
		return configurationManager.getProperty("path.page.products");
	}

}