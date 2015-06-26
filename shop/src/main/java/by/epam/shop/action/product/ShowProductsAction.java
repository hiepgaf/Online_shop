package by.epam.shop.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.Product;

/**
 * The Class ShowProductsAction. Shows list of products selected by type.
 */
public class ShowProductsAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		String type = request.getParameter("product_type");
		ProductDAO productDAO = new ProductDAO();
		List<Product> products = productDAO.findEntitiesByType(type);
		if (products.isEmpty()) {
			request.setAttribute("message", MessageKeys.FIND_PRODUCTS_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		request.setAttribute("products", products);
		return configurationManager.getProperty("path.page.products");
	}

}
