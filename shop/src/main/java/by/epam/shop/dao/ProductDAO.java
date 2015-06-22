package by.epam.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.shop.entity.Product;

public class ProductDAO extends AbstractDAO<Product> {
	private static Logger log = Logger.getLogger(ProductDAO.class);
	private static final String SQL_SELECT_PRODUCT = "SELECT * FROM internet_shop.products";
	private static final String SQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM internet_shop.products WHERE id= ?";
	private static final String SQL_SELECT_PRODUCT_BY_TYPE = "SELECT * FROM internet_shop.products WHERE product_types_id= ?";
	private static final String SQL_CREATE_PRODUCT = "INSERT INTO internet_shop.products (id, name, price, description, product_pictures_id) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE_PRODUCT = "UPDATE internet_shop.products SET name= ?, price= ?, description= ?, product_pictures_id= ? WHERE id= ?";
	private static final String SQL_DELETE_PRODUCT = "DELETE FROM internet_shop.products WHERE id= ?";
	private static final String SQL_SELECT_PICTURE = "SELECT * FROM internet_shop.product_pictures WHERE id= ?";
	private static final String SQL_SELECT = "SELECT * FROM internet_shop.products JOIN internet_shop.orders_products ON (products.id = orders_products.products_id) JOIN internet_shop.orders ON (orders_products.orders_id = orders.id) WHERE products.id= ?";

	@Override
	public List<Product> findAll() {
		ArrayList<Product> products = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_PRODUCT)) {
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));
				product.setDescription(resultSet.getString("description"));
				product.setPicturePath(findPicturePath(resultSet
						.getInt("product_pictures_id")));
				products.add(product);
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return products;
	}

	@Override
	public Product findEntityById(Integer id) {
		Product product = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_PRODUCT_BY_ID)) {
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));
				product.setDescription(resultSet.getString("description"));
				product.setPicturePath(findPicturePath(resultSet
						.getInt("product_pictures_id")));
			} else {
				return null;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return product;
	}

	public List<Product> findEntitiesByType(int product_types_id) {
		ArrayList<Product> products = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_PRODUCT_BY_TYPE)) {
			prepareStatement.setInt(1, product_types_id);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));
				product.setDescription(resultSet.getString("description"));
				product.setPicturePath(findPicturePath(resultSet
						.getInt("product_pictures_id")));
				products.add(product);
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return products;
	}

	@Override
	public boolean delete(Integer id) {
		if (checkActiveOrder(id)) {
			return false;
		}
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_DELETE_PRODUCT)) {
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
	public boolean delete(Product entity) {
		if (checkActiveOrder(entity.getId())) {
			return false;
		}
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_DELETE_PRODUCT)) {
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
	public boolean create(Product entity) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_CREATE_PRODUCT)) {
			prepareStatement.setInt(1, entity.getId());
			prepareStatement.setString(2, entity.getName());
			prepareStatement.setInt(3, entity.getPrice());
			prepareStatement.setString(4, entity.getDescription());
			prepareStatement.setString(5, entity.getPicturePath());
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
	public Product update(Product entity) {
		Product product = new Product();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_UPDATE_PRODUCT)) {
			prepareStatement.setString(1, entity.getName());
			prepareStatement.setInt(2, entity.getPrice());
			prepareStatement.setString(3, entity.getDescription());
			prepareStatement.setString(4, entity.getPicturePath());
			prepareStatement.setInt(5, entity.getId());
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));
				product.setDescription(resultSet.getString("description"));
				product.setPicturePath(findPicturePath(resultSet
						.getInt("product_pictures_id")));
			} else {
				return null;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return product;
	}

	private String findPicturePath(int pictureId) {
		String path = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_PICTURE)) {
			prepareStatement.setInt(1, pictureId);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				path = resultSet.getString("path");
			} else {
				return null;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return path;
	}

	private boolean checkActiveOrder(int id) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT)) {
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				if ("active".equals(resultSet.getString("status"))) {
					flag = true;
				}
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return flag;
	}
}
