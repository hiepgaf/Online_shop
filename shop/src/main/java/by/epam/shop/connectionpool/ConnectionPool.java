package by.epam.shop.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import by.epam.shop.manager.ConnectionPoolManager;

public class ConnectionPool {
	private static Logger log = Logger.getLogger(ConnectionPool.class);
	private String user;
	private String password;
	private String url;
	private ArrayBlockingQueue<Connection> connectionPool;
	private static int poolSize;
	private static ConnectionPool instance;
	private volatile static boolean instanceCreated;

	private ConnectionPool() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			ConnectionPoolManager manager = ConnectionPoolManager.getInstance();
			poolSize = Integer.parseInt(manager.getProperty("pool_size"));
			user = manager.getProperty("user");
			password = manager.getProperty("password");
			url = manager.getProperty("url");
			connectionPool = new ArrayBlockingQueue<>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				connectionPool.put(DriverManager.getConnection(url, user,
						password));
			}
		} catch (SQLException | InterruptedException e) {
			log.error(e);
		}
	}

	public static ConnectionPool getInstance() {
		if (!instanceCreated) {
			synchronized (ConnectionPool.class) {
				try {
					if (!instanceCreated) {
						instance = new ConnectionPool();
						instanceCreated = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = connectionPool.take();
		} catch (InterruptedException e) {
			log.error(e);
		}
		return connection;
	}

	public void freeConnection(Connection connection) {
		try {
			if (!connection.isClosed()) {
				connectionPool.put(connection);
			}
		} catch (SQLException | InterruptedException e) {
			log.error(e);
		}
	}
}
