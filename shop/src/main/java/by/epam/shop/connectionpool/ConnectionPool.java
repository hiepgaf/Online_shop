package by.epam.shop.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Driver;

/**
 * The Class ConnectionPool. Contains a initialize connections to the database.
 */
public class ConnectionPool {
	private static final String PROPERTY_FILE = "connection_pool";
	private static Logger log = Logger.getLogger(ConnectionPool.class);
	private String user;
	private String password;
	private String url;
	private ArrayBlockingQueue<Connection> connectionPool;
	private static int poolSize;
	private static ConnectionPool instance;
	private static AtomicBoolean isNull = new AtomicBoolean(true);
	private static ReentrantLock lock = new ReentrantLock();

	private ConnectionPool() {
		try {
			DriverManager.registerDriver(new Driver());
			ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY_FILE);
			poolSize = Integer.parseInt(resourceBundle.getString("pool_size"));
			user = resourceBundle.getString("user");
			password = resourceBundle.getString("password");
			url = resourceBundle.getString("url");
			connectionPool = new ArrayBlockingQueue<>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				connectionPool.put(DriverManager.getConnection(url, user, password));
			}
		} catch (SQLException | InterruptedException e) {
			log.fatal(e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * Gets the single instance of ConnectionPool.
	 *
	 * @return single instance of ConnectionPool
	 */
	public static ConnectionPool getInstance() {
		if (isNull.get()) {
			lock.lock();
			if (isNull.get()) {
				try {
					instance = new ConnectionPool();
					isNull.set(false);
				} finally {
					lock.unlock();
				}
			}
		}
		return instance;
	}

	/**
	 * Gets the connection. If there is no free connections, blocking the user
	 * as long as the connection is not available.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = connectionPool.take();
		} catch (InterruptedException e) {
			log.error(e);
		}
		return connection;
	}

	/**
	 * Put connection to the pool.
	 *
	 * @param connection
	 *            the connection
	 */
	public void freeConnection(Connection connection) {
		try {
			if (!connection.isClosed()) {
				connectionPool.put(connection);
			}
		} catch (SQLException | InterruptedException e) {
			log.error(e);
		}
	}

	/**
	 * Shut down the connection pool. Closes all connections present in it.
	 */
	public void shutDown() {
		Iterator<Connection> iterator = connectionPool.iterator();
		while (iterator.hasNext()) {
			try {
				iterator.next().close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}
}
