package by.epam.shop.action.product;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.Product;
import by.epam.shop.manager.ConfigurationManager;

public class ShowConcreteProductAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		int productId = Integer.parseInt(request.getParameter("product_id"));
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.findEntityById(productId);
		request.setAttribute("product", product);
		return configurationManager.getProperty("path.page.product");
	}

}
