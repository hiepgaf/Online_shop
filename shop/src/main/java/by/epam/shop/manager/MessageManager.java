package by.epam.shop.manager;

import java.util.ResourceBundle;

public class MessageManager {
	private ResourceBundle resourceBundle = ResourceBundle
			.getBundle("resources.messages");
	private static MessageManager instance;
	private volatile static boolean instanceCreated;

	private MessageManager() {
	}

	public static MessageManager getInstance() {
		if (!instanceCreated) {
			synchronized (MessageManager.class) {
				try {
					if (!instanceCreated) {
						instance = new MessageManager();
						instanceCreated = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return instance;
	}

	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
