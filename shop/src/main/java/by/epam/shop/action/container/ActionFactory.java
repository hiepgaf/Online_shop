package by.epam.shop.action.container;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.constant.MessageKeys;

/**
 * A factory for creating Action objects.
 */
public class ActionFactory {
	private static ActionFactory instance;
	private volatile static boolean instanceCreated;

	private ActionFactory() {
	}

	/**
	 * Gets the single instance of ActionFactory.
	 *
	 * @return single instance of ActionFactory
	 */
	public static ActionFactory getInstance() {
		if (!instanceCreated) {
			synchronized (ActionFactory.class) {
				try {
					if (!instanceCreated) {
						instance = new ActionFactory();
						instanceCreated = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return instance;
	}

	/**
	 * Define command.
	 *
	 * @param request
	 *            the request
	 * @return the action
	 */
	public Action defineCommand(HttpServletRequest request) {
		Action current = null;
		String action = request.getParameter("action");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			ActionEnum currentEnum = ActionEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentAction();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", action
					+ MessageKeys.WRONG_ACTION);
		}
		return current;
	}
}
