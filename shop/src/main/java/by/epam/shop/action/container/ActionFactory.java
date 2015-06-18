package by.epam.shop.action.container;

import javax.servlet.http.HttpServletRequest;

import by.epam.shop.action.Action;
import by.epam.shop.manager.MessageManager;

public class ActionFactory {
	private static MessageManager messageManager = MessageManager.getInstance();
	private static ActionFactory instance;
	private volatile static boolean instanceCreated;

	private ActionFactory() {
	}

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
			request.setAttribute("wrongAction",
					action + messageManager.getProperty("message.wrongaction"));
		}
		return current;
	}
}
