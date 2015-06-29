package by.epam.shop.manager;

import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class ConfigurationManager.
 */
public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
	private static ConfigurationManager instance;
	private static AtomicBoolean isNull = new AtomicBoolean(true);
	private static ReentrantLock lock = new ReentrantLock();

	private ConfigurationManager() {
	}

	/**
	 * Gets the single instance of ConfigurationManager.
	 *
	 * @return single instance of ConfigurationManager
	 */
	public static ConfigurationManager getInstance() {
		if (isNull.get()) {
			lock.lock();
			instance = new ConfigurationManager();
			isNull.set(false);
			lock.unlock();
		}
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
