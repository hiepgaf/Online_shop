package by.epam.shop.action.product;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.Product;
import by.epam.shop.exception.DAOException;

/**
 * The Class ShowConcreteProductAction. Shows detailed information about the
 * selected product.
 */
public class ShowConcreteProductAction implements Action {
	private static Logger log = Logger.getLogger(ShowConcreteProductAction.class);

	@Override
	public String execute(HttpServletRequest request) {
		int productId = Integer.parseInt(request.getParameter("product_id"));
		ProductDAO productDAO = new ProductDAO();
		try {
			Product product = productDAO.findEntityById(productId);
			request.setAttribute("product", product);
		} catch (DAOException e) {
			log.error(e);
			request.setAttribute("message", MessageKeys.DATABASE_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		return configurationManager.getProperty("path.page.product");
	}

}
