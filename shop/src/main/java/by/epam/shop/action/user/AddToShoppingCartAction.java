package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.Product;
import by.epam.shop.entity.User;
import by.epam.shop.exception.DAOException;

/**
 * The Class AddToShoppingCartAction. Adds selected product to the user's
 * shopping cart.
 */
public class AddToShoppingCartAction implements Action {
	private static Logger log = Logger.getLogger(AddToShoppingCartAction.class);

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
		int id = Integer.parseInt(request.getParameter("product_id"));
		ProductDAO productDAO = new ProductDAO();
		try {
			Product product = productDAO.findEntityById(id);
			if (product == null) {
				request.setAttribute("message", MessageKeys.FIND_PRODUCT_ERROR);
				return configurationManager.getProperty("path.page.error");
			}
			user.addProduct(product);
			request.getSession().setAttribute("user", user);
		} catch (DAOException e) {
			log.error(e);
			request.setAttribute("message", MessageKeys.DATABASE_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		request.setAttribute("message", MessageKeys.ADD_TO_SHOPPING_CART_SUCCESS);
		return configurationManager.getProperty("path.page.success");
	}
}
