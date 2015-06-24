package by.epam.shop.action.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.User;
import by.epam.shop.manager.ConfigurationManager;

public class AddProductPageAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		ProductDAO productDAO = new ProductDAO();
		List<String> types = productDAO.findAllProductTypes();
		List<String> picturePath = productDAO.findAllProductPicturePath();
		request.setAttribute("types", types);
		request.setAttribute("picturePath", picturePath);
		return configurationManager.getProperty("path.page.addproduct");
	}
}