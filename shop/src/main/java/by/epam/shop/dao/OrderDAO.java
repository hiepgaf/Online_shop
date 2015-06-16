package by.epam.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.epam.shop.entity.Order;
import by.epam.shop.entity.Product;
import by.epam.shop.exception.DAOException;
import by.epam.shop.exception.TechnicalException;

public class OrderDAO extends AbstractDAO<Order> {
	private static final String SQL_SELECT_ORDER = "SELECT * FROM internet_shop.orders";
	private static final String SQL_CREATE_ORDER = "INSERT INTO internet_shop.orders (id, users_id, status, date) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE_ORDER = "UPDATE internet_shop.orders SET users_id= ?, status= ?, date= ? WHERE id= ?";
	private static final String SQL_DELETE_ORDER = "DELETE FROM internet_shop.orders WHERE id= ?";
	private static final String SQL_SELECT_ORDERS_PRODUCTS = "SELECT * FROM internet_shop.orders_products WHERE orders_id= ?";

	@Override
	protected String getSelectQuery() {
		return SQL_SELECT_ORDER;
	}

	@Override
	protected String getCreateQuery() {
		return SQL_CREATE_ORDER;
	}

	@Override
	protected String getUpdateQuery() {
		return SQL_UPDATE_ORDER;
	}

	@Override
	protected String getDeleteQuery() {
		return SQL_DELETE_ORDER;
	}

	@Override
	protected ArrayList<Order> parseResultSet(ResultSet resultSet)
			throws DAOException, TechnicalException {
		ArrayList<Order> orders = new ArrayList<>();
		try {
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
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return orders;
	}

	@Override
	protected void prepareStatementForCreate(
			PreparedStatement prepareStatement, Order entity)
			throws SQLException {
		prepareStatement.setInt(1, entity.getId());
		prepareStatement.setInt(2, entity.getUser().getId());
		prepareStatement.setString(3, entity.getStatus());
		prepareStatement.setDate(4, entity.getDate());
	}

	@Override
	protected void prepareStatementForUpdate(
			PreparedStatement prepareStatement, Order entity)
			throws SQLException {
		prepareStatement.setInt(1, entity.getUser().getId());
		prepareStatement.setString(2, entity.getStatus());
		prepareStatement.setDate(3, entity.getDate());
		prepareStatement.setInt(4, entity.getId());
	}

	private ArrayList<Product> findProductsOfOrder(Order order)
			throws DAOException, TechnicalException {
		ArrayList<Product> products = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement(SQL_SELECT_ORDERS_PRODUCTS)) {
			DAOFactory factory = DAOFactory.getInstance();
			@SuppressWarnings("unchecked")
			AbstractDAO<Product> productDAO = factory.createDAO("PRODUCT_DAO");
			prepareStatement.setInt(1, order.getId());
			ResultSet resultSet = prepareStatement.executeQuery();
			products = productDAO.parseResultSet(resultSet);
			connectionPool.freeConnection(connection);
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		return products;
	}

}
