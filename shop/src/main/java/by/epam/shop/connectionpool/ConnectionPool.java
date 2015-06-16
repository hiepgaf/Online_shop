package by.epam.shop.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

public class ConnectionPool {
	private static Logger log = Logger.getLogger(ConnectionPool.class);
	private String user = "root";
	private String password = "2573211";
	private String url = "jdbc:mysql://localhost:3306/daotalk";
	private ArrayBlockingQueue<Connection> connectionPool;
	private static ConnectionPool instance;
	private static int connectionsAmount = 30;

	private ConnectionPool() throws SQLException, InterruptedException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		connectionPool = new ArrayBlockingQueue<>(connectionsAmount);
		for (int i = 0; i < connectionsAmount; i++) {
			connectionPool
					.put(DriverManager.getConnection(url, user, password));
		}
	}

	public static ConnectionPool getInstance() {
		if (instance != null) {
			return instance;
		} else {
			try {
				return new ConnectionPool();
			} catch (SQLException | InterruptedException e) {
				log.error(e);
			}
		}
		return instance;
	}

	public Connection getConnection() throws InterruptedException {
		return connectionPool.take();
	}

	public void freeConnection(Connection connection) throws SQLException,
			InterruptedException {
		if (!connection.isClosed()) {
			connectionPool.put(connection);
		}
	}
}
