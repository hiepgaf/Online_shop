package by.epam.shop.manager;

import java.util.ResourceBundle;

/**
 * The Class ConfigurationManager.
 */
public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("config");
	private static ConfigurationManager instance;
	private volatile static boolean instanceCreated;

	private ConfigurationManager() {
	}

	/**
	 * Gets the single instance of ConfigurationManager.
	 *
	 * @return single instance of ConfigurationManager
	 */
	public static ConfigurationManager getInstance() {
		if (!instanceCreated) {
			synchronized (ConfigurationManager.class) {
				try {
					if (!instanceCreated) {
						instance = new ConfigurationManager();
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
