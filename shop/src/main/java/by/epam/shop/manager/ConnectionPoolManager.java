package by.epam.shop.manager;

import java.util.ResourceBundle;

public class ConnectionPoolManager {
	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("connection_pool");
	private static ConnectionPoolManager instance;
	private volatile static boolean instanceCreated;

	private ConnectionPoolManager() {
	}

	public static ConnectionPoolManager getInstance() {
		if (!instanceCreated) {
			synchronized (ConnectionPoolManager.class) {
				try {
					if (!instanceCreated) {
						instance = new ConnectionPoolManager();
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
