package by.epam.shop.manager;

import java.util.ResourceBundle;

/**
 * The Class ConnectionPoolManager.
 */
public class ConnectionPoolManager {
	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("connection_pool");
	private static ConnectionPoolManager instance;
	private volatile static boolean instanceCreated;

	private ConnectionPoolManager() {
	}

	/**
	 * Gets the single instance of ConnectionPoolManager.
	 *
	 * @return single instance of ConnectionPoolManager
	 */
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

	/**
	 * Gets the property.
	 *
	 * @param key the key
	 * @return the property
	 */
	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
