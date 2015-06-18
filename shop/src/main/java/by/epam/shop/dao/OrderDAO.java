package by.epam.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.shop.entity.Order;
import by.epam.shop.entity.Product;

public class OrderDAO extends AbstractDAO<Order> {
	private static Logger log = Logger.getLogger(OrderDAO.class);
	private static final String SQL_SELECT_ORDER = "SELECT * FROM internet_shop.orders";
	private static final String SQL_SELECT_ORDER_BY_ID = "SELECT * FROM internet_shop.orders WHERE id= ?";
	private static final String SQL_CREATE_ORDER = "INSERT INTO internet_shop.orders (id, users_id, status, date) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE_ORDER = "UPDATE internet_shop.orders SET users_id= ?, status= ?, date= ? WHERE id= ?";
	private static final String SQL_DELETE_ORDER = "DELETE FROM internet_shop.orders WHERE id= ?";
	private static final String SQL_SELECT_ORDERS_PRODUCTS = "SELECT * FROM internet_shop.orders_products WHERE orders_id= ?";

	@Override
	public List<Order> findAll() {
		ArrayList<Order> orders = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_ORDER)) {
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setUser(new UserDAO().findEntityById(resultSet
						.getInt("users_id")));
				order.setStatus(resultSet.getString("status"));
				order.setDate(resultSet.getDate("date"));
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
		Order order = new Order();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_ORDER_BY_ID)) {
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				order.setId(resultSet.getInt("id"));
				order.setUser(new UserDAO().findEntityById(resultSet
						.getInt("users_id")));
				order.setStatus(resultSet.getString("status"));
				order.setDate(resultSet.getDate("date"));
				order.setProducts(findProductsOfOrder(order));
			} else {
				return null;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return order;
	}

	@Override
	public boolean delete(Integer id) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_DELETE_ORDER)) {
			prepareStatement.setInt(1, id);
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
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_CREATE_ORDER)) {
			prepareStatement.setInt(1, entity.getId());
			prepareStatement.setInt(2, entity.getUser().getId());
			prepareStatement.setString(3, entity.getStatus());
			prepareStatement.setDate(4, entity.getDate());
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
	public Order update(Order entity) {
		Order order = new Order();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_UPDATE_ORDER)) {
			prepareStatement.setInt(1, entity.getUser().getId());
			prepareStatement.setString(2, entity.getStatus());
			prepareStatement.setDate(3, entity.getDate());
			prepareStatement.setInt(4, entity.getId());
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				order.setId(resultSet.getInt("id"));
				order.setUser(new UserDAO().findEntityById(resultSet
						.getInt("users_id")));
				order.setStatus(resultSet.getString("status"));
				order.setDate(resultSet.getDate("date"));
				order.setProducts(findProductsOfOrder(order));
			} else {
				return null;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return order;
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
				Product product = new Product();
				product = productDAO.findEntityById(resultSet
						.getInt("products_id"));
				products.add(product);
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return products;
	}
}
