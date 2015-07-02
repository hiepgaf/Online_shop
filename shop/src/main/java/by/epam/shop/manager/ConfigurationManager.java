package by.epam.shop.manager;

import java.util.ResourceBundle;

/**
 * The Class ConfigurationManager.
 */
public class ConfigurationManager {
	private static final String PROPERTY_FILE = "config";
	private ResourceBundle resourceBundle;
	private static ConfigurationManager instance = new ConfigurationManager();

	private ConfigurationManager() {
		resourceBundle = ResourceBundle.getBundle(PROPERTY_FILE);
	}

	/**
	 * Gets the single instance of ConfigurationManager.
	 *
	 * @return single instance of ConfigurationManager
	 */
	public static ConfigurationManager getInstance() {
		return instance;
	}

	/**
	 * Gets the property.
	 *
	 * @param key
	 *            the key
	 * @return the property
	 */
	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
