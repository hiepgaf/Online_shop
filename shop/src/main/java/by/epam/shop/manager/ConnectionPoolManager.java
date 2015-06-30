package by.epam.shop.manager;

import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class ConnectionPoolManager.
 */
public class ConnectionPoolManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("connection_pool");
	private static ConnectionPoolManager instance;
	private static AtomicBoolean isNull = new AtomicBoolean(true);
	private static ReentrantLock lock = new ReentrantLock();

	private ConnectionPoolManager() {
	}

	/**
	 * Gets the single instance of ConnectionPoolManager.
	 *
	 * @return single instance of ConnectionPoolManager
	 */
	public static ConnectionPoolManager getInstance() {
		if (isNull.get()) {
			lock.lock();
			try {
				instance = new ConnectionPoolManager();
				isNull.set(false);
			} finally {
				lock.unlock();
			}
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
