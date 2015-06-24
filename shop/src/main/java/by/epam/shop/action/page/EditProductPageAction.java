package by.epam.shop.action.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.Product;
import by.epam.shop.entity.User;
import by.epam.shop.manager.ConfigurationManager;

public class EditProductPageAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null | user.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.CANCEL_ORDER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		int productId = Integer.parseInt(request.getParameter("product_id"));
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.findEntityById(productId);
		List<String> types = productDAO.findAllProductTypes();
		List<String> picturePath = productDAO.findAllProductPicturePath();
		request.setAttribute("types", types);
		request.setAttribute("picturePath", picturePath);
		request.setAttribute("product", product);
		return configurationManager.getProperty("path.page.editproduct");
	}
}
