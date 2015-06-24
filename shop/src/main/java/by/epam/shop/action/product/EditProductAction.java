package by.epam.shop.action.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.Product;
import by.epam.shop.entity.User;
import by.epam.shop.manager.ConfigurationManager;

public class EditProductAction implements Action {
	private static ConfigurationManager configurationManager = ConfigurationManager
			.getInstance();

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String picturePath = request.getParameter("picture");
		String publisher = request.getParameter("publisher");
		String developer = request.getParameter("developer");
		String imprintYear = request.getParameter("imprintYear");
		ProductDAO productDAO = new ProductDAO();
		if (type.length() * name.length() * price.length()
				* description.length() * picturePath.length()
				* publisher.length() * developer.length()
				* imprintYear.length() == 0) {
			List<String> types = productDAO.findAllProductTypes();
			List<String> pictures = productDAO.findAllProductPicturePath();
			request.setAttribute("types", types);
			request.setAttribute("picturePath", pictures);
			request.setAttribute("message",
					MessageKeys.ADD_PRODUCT_BLANK_FIELDS);
			return configurationManager.getProperty("path.page.editproduct");
		}
		Product product = new Product();
		product.setId(Integer.parseInt(request.getParameter("product_id")));
		product.setType(type);
		product.setName(name);
		product.setPrice(Integer.parseInt(price));
		product.setDescription(description);
		product.setPicturePath(picturePath);
		product.setPublisher(publisher);
		product.setDeveloper(developer);
		product.setImprintYear(Integer.parseInt(imprintYear));
		if (productDAO.update(product) != null) {
			request.setAttribute("message", MessageKeys.EDIT_PRODUCT_SUCCESS);
			return configurationManager.getProperty("path.page.success");
		}
		request.setAttribute("message", MessageKeys.EDIT_PRODUCT_ERROR);
		return configurationManager.getProperty("path.page.error");
	}

}
