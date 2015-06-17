package by.epam.shop.manager;

import java.util.ResourceBundle;

public class MessageManager {
	private  ResourceBundle resourceBundle = ResourceBundle
			.getBundle("resources.messages");

	public
	MessageManager() {
	}

	public  String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
