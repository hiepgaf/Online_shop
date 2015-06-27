package by.epam.shop.action.user;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.entity.Product;
import by.epam.shop.entity.User;

/**
 * The Class RemoveFromShoppingCartAction. Removes selected product from user's
 * shopping cart.
 */
public class RemoveFromShoppingCartAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		int id = Integer.parseInt(request.getParameter("product_id"));
		Iterator<Product> iterator = user.getShoooppingCart().iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == id) {
				iterator.remove();
				break;
			}
		}
		request.getSession().setAttribute("user", user);
		request.setAttribute("message",
				MessageKeys.REMOVE_FROM_SHOPPING_CART_SUCCESS);
		return configurationManager.getProperty("path.page.success");
	}

}
