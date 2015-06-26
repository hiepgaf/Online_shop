package by.epam.shop.action.product;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.Product;

/**
 * The Class ShowConcreteProductAction. Shows detailed information about the
 * selected product.
 */
public class ShowConcreteProductAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		int productId = Integer.parseInt(request.getParameter("product_id"));
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.findEntityById(productId);
		request.setAttribute("product", product);
		return configurationManager.getProperty("path.page.product");
	}

}
