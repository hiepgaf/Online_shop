package by.epam.shop.action.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;
import by.epam.shop.dao.ProductDAO;
import by.epam.shop.entity.User;
import by.epam.shop.exception.DAOException;

/**
 * The Class AddProductPageAction. Only available to the administrator. Jumps to
 * the page add product.
 */
public class AddProductPageAction implements Action {
	private static Logger log = Logger.getLogger(AddProductPageAction.class);

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || user.getAccessLevel() != 2) {
			request.setAttribute("message", MessageKeys.USER_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
		ProductDAO productDAO = new ProductDAO();
		try {
			List<String> types = productDAO.findAllProductTypes();
			List<String> picturePath = productDAO.findAllProductPicturePath();
			ArrayList<Integer> imprintYears = new ArrayList<>();
			for (int i = 1990; i < 2016; i++) {
				imprintYears.add(new Integer(i));
			}
			request.setAttribute("types", types);
			request.setAttribute("picturePath", picturePath);
			request.setAttribute("imprintYears", imprintYears);
			return configurationManager.getProperty("path.page.addproduct");
		} catch (DAOException e) {
			log.error(e);
			request.setAttribute("message", MessageKeys.DATABASE_ERROR);
			return configurationManager.getProperty("path.page.error");
		}
	}
}
