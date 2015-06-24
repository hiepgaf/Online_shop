package by.epam.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import by.epam.shop.entity.Order;
import by.epam.shop.entity.Product;

public class OrderDAO extends AbstractDAO<Order> {
	private static Logger log = Logger.getLogger(OrderDAO.class);
	private static final String SQL_SELECT_ORDER = "SELECT * FROM internet_shop.orders JOIN internet_shop.status ON (orders.status_id = status.id)";
	private static final String SQL_SELECT_ORDER_BY_ID = "SELECT * FROM internet_shop.orders JOIN internet_shop.status ON (orders.status_id = status.id) WHERE orders.id= ?";
	private static final String SQL_SELECT_ORDER_BY_USER_ID = "SELECT * FROM internet_shop.orders JOIN internet_shop.status ON (orders.status_id = status.id) WHERE orders.users_id= ?";
	private static final String SQL_CREATE_ORDER = "INSERT INTO internet_shop.orders (users_id, status_id) VALUES (?,?)";
	private static final String SQL_CREATE_ORDERS_PRODUCTS = "INSERT INTO internet_shop.orders_products (orders_id, products_id) VALUES (?,?)";
	private static final String SQL_UPDATE_ORDER_STATUS = "UPDATE internet_shop.orders SET status_id= ? WHERE id= ?";
	private static final String SQL_DELETE_ORDER = "DELETE FROM internet_shop.orders WHERE id= ?";
	private static final String SQL_DELETE_ORDERS_PRODUCTS = "DELETE FROM internet_shop.orders_products WHERE orders_id= ?";
	private static final String SQL_SELECT_ORDERS_PRODUCTS = "SELECT * FROM internet_shop.orders_products WHERE orders_id= ?";
	private static final String SQL_SELECT_STATUS = "SELECT * FROM internet_shop.status WHERE description= ?";

	@Override
	public List<Order> findAll() {
		ArrayList<Order> orders = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_ORDER)) {
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("orders.id"));
				order.setUser(new UserDAO().findEntityById(resultSet
						.getInt("orders.users_id")));
				order.setStatus(resultSet.getString("status.description"));
				order.setDate(resultSet.getDate("orders.date"));
				order.setProducts(findProductsOfOrder(order));
				orders.add(order);
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return orders;
	}

	@Override
	public Order findEntityById(Integer id) {
		Order order = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_ORDER_BY_ID)) {
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				order.setId(resultSet.getInt("orders.id"));
				order.setUser(new UserDAO().findEntityById(resultSet
						.getInt("orders.users_id")));
				order.setStatus(resultSet.getString("status.description"));
				order.setDate(resultSet.getDate("orders.date"));
				order.setProducts(findProductsOfOrder(order));
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return order;
	}

	public List<Order> findEntitiesByUserId(int id) {
		ArrayList<Order> orders = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_ORDER_BY_USER_ID)) {
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("orders.id"));
				order.setUser(new UserDAO().findEntityById(resultSet
						.getInt("orders.users_id")));
				order.setStatus(resultSet.getString("status.description"));
				order.setDate(resultSet.getDate("orders.date"));
				order.setProducts(findProductsOfOrder(order));
				orders.add(order);
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return orders;
	}

	@Override
	public boolean delete(Integer id) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_DELETE_ORDER)) {
			prepareStatement.setInt(1, id);
			deleteFromOrdersProducts(id);
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return flag;
	}

	@Override
	public boolean delete(Order entity) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_DELETE_ORDER)) {
			prepareStatement.setInt(1, entity.getId());
			deleteFromOrdersProducts(entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return flag;
	}

	@Override
	public boolean create(Order entity) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatementOrder = connection
				.prepareStatement(SQL_CREATE_ORDER,
						Statement.RETURN_GENERATED_KEYS)) {
			prepareStatementOrder.setInt(1, entity.getUser().getId());
			prepareStatementOrder.setInt(2, findStatusId(entity.getStatus()));
			int count = prepareStatementOrder.executeUpdate();
			ResultSet resultSet = prepareStatementOrder.getGeneratedKeys();
			if (resultSet.next()) {
				entity.setId(new Integer(resultSet.getInt(1)));
			}
			if (count == 1 && createOrdersProducts(entity)) {
				flag = true;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return flag;
	}

	@Override
	public Order update(Order entity) {
		throw new UnsupportedOperationException();
	}

	public boolean updateStatus(Order entity) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_UPDATE_ORDER_STATUS)) {
			prepareStatement.setInt(1, findStatusId(entity.getStatus()));
			prepareStatement.setInt(2, entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return flag;
	}

	private ArrayList<Product> findProductsOfOrder(Order order) {
		ArrayList<Product> products = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_ORDERS_PRODUCTS)) {
			ProductDAO productDAO = new ProductDAO();
			prepareStatement.setInt(1, order.getId());
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Product product = productDAO.findEntityById(resultSet
						.getInt("products_id"));
				products.add(product);
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return products;
	}

	private Integer findStatusId(String status) {
		Integer id = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_STATUS)) {
			prepareStatement.setString(1, status);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return id;
	}

	private void deleteFromOrdersProducts(int id) {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_DELETE_ORDERS_PRODUCTS)) {
			prepareStatement.setInt(1, id);
			prepareStatement.executeUpdate();
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
	}

	private boolean createOrdersProducts(Order entity) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_CREATE_ORDERS_PRODUCTS)) {
			for (Product product : entity.getProducts()) {
				prepareStatement.setInt(1, entity.getId());
				prepareStatement.setInt(2, product.getId());
				int count = prepareStatement.executeUpdate();
				if (count != 1) {
					flag = false;
					break;
				}
				flag = true;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return flag;
	}
}
