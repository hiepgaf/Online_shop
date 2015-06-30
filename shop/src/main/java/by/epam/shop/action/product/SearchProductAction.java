package by.epam.shop.action.product;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.Product;
import by.epam.shop.exception.DAOException;

/**
 * The Class SearchProductAction. Searches a product by name.
 */
public class SearchProductAction implements Action {
	private static Logger log = Logger.getLogger(SearchProductAction.class);

	@Override
	public String execute(HttpServletRequest request) {
		String text = request.getParameter("text_search");
		Pattern textPattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
		ProductDAO productDAO = new ProductDAO();
		try {
			List<Product> products = productDAO.findAll();
			ArrayList<Product> findProducts = new ArrayList<Product>();
			for (Product product : products) {
				if (textPattern.matcher(product.getName()).find()) {
					findProducts.add(product);
				}
			}
			if (findProducts.isEmpty()) {
				request.setAttribute("message", MessageKeys.FIND_PRODUCTS_ERROR);
				return configurationManager.getProperty("path.page.error");
			} else {
				request.setAttribute("products", findProducts);
				return configurationManager.getProperty("path.page.products");
			}
		} catch (DAOException e) {
			log.error(e);
			request.setAttribute("message", MessageKeys.DATABASE_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
	}
}
