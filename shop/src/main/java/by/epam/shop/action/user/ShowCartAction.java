package by.epam.shop.action.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.entity.Product;
import by.epam.shop.entity.User;

/**
 * The Class ShowCartAction. Shows user's shopping cart.
 */
public class ShowCartAction implements Action {

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		if (user.getShoooppingCart().isEmpty()) {
			request.setAttribute("message", MessageKeys.SHOW_CART_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		int fullPrice = 0;
		for (Product product : user.getShoooppingCart()) {
			fullPrice += product.getPrice();
		}
		request.setAttribute("cart", user.getShoooppingCart());
		request.setAttribute("full_price", fullPrice);
		return configurationManager.getProperty("path.page.cart");
	}

}
