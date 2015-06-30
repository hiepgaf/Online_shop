package by.epam.shop.action.product;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.User;
import by.epam.shop.exception.DAOException;

/**
 * The Class DeleteProductAction. Only available to the administrator. Deletes
 * selected product from database.
 */
public class DeleteProductAction implements Action {
	private static Logger log = Logger.getLogger(DeleteProductAction.class);

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		int productId = Integer.parseInt(request.getParameter("product_id"));
		ProductDAO productDAO = new ProductDAO();
		try {
			if (productDAO.delete(productId)) {
				request.setAttribute("message", MessageKeys.DELETE_PRODUCT_SUCCESS);
				return configurationManager.getProperty("path.page.success");
			} else {
				request.setAttribute("message", MessageKeys.DELETE_PRODUCT_ERROR);
				return configurationManager.getProperty("path.page.error");
			}
		} catch (DAOException e) {
			log.error(e);
			request.setAttribute("message", MessageKeys.DATABASE_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
	}

}
