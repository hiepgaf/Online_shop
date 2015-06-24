package by.epam.shop.action.product;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.manager.ConfigurationManager;

public class DeleteProductAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		int productId = Integer.parseInt(request.getParameter("product_id"));
		ProductDAO productDAO = new ProductDAO();
		if (productDAO.delete(productId)) {
			request.setAttribute("message", MessageKeys.DELETE_PRODUCT_SUCCESS);
			return configurationManager.getProperty("path.page.success");
		}
		request.setAttribute("message", MessageKeys.DELETE_PRODUCT_ERROR);
		return configurationManager.getProperty("path.page.error");
	}

}
