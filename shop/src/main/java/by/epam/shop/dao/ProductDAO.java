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
	private static final String SQL_SELECT_PRODUCT = "SELECT * FROM internet_shop.products JOIN internet_shop.product_types ON (products.product_types_id = product_types.id) JOIN internet_shop.product_pictures ON (products.product_pictures_id = product_pictures.id)";
	private static final String SQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM internet_shop.products JOIN internet_shop.product_types ON (products.product_types_id = product_types.id) JOIN internet_shop.product_pictures ON (products.product_pictures_id = product_pictures.id) WHERE products.id= ?";
	private static final String SQL_SELECT_PRODUCT_BY_TYPE = "SELECT * FROM internet_shop.products JOIN internet_shop.product_types ON (products.product_types_id = product_types.id) JOIN internet_shop.product_pictures ON (products.product_pictures_id = product_pictures.id) WHERE product_types.description= ?";
	private static final String SQL_CREATE_PRODUCT = "INSERT INTO internet_shop.products (product_types_id, name, price, description, product_pictures_id, publisher, developer, imprint_year) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE_PRODUCT = "UPDATE internet_shop.products SET product_types_id= ?, name= ?, price= ?, description= ?, product_pictures_id= ?, publisher= ?, developer= ?, imprint_year= ? WHERE id= ?";
	private static final String SQL_DELETE_PRODUCT = "DELETE FROM internet_shop.products WHERE id= ?";
	private static final String SQL_SELECT_PICTURE = "SELECT * FROM internet_shop.product_pictures WHERE path= ?";
	private static final String SQL_SELECT_TYPE = "SELECT * FROM internet_shop.product_types WHERE description= ?";
	private static final String SQL_CHECK_ACTIVE_ORDER = "SELECT * FROM internet_shop.products JOIN internet_shop.orders_products ON (products.id = orders_products.products_id) JOIN internet_shop.orders ON (orders.id = orders_products.orders_id) WHERE orders.status_id = 1 AND products.id= ?";
	private static final String SQL_SELECT_PRODUCT_TYPES = "SELECT * FROM internet_shop.product_types";
	private static final String SQL_SELECT_PRODUCT_PICTURES = "SELECT * FROM internet_shop.product_pictures";

	@Override
	public List<Product> findAll() {
		ArrayList<Product> products = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_PRODUCT)) {
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("products.id"));
				product.setType(resultSet
						.getString("product_types.description"));
				product.setName(resultSet.getString("products.name"));
				product.setPrice(resultSet.getInt("products.price"));
				product.setDescription(resultSet
						.getString("products.description"));
				product.setPicturePath(resultSet
						.getString("product_pictures.path"));
				product.setPublisher(resultSet.getString("products.publisher"));
				product.setDeveloper(resultSet.getString("products.developer"));
				product.setImprintYear(resultSet
						.getInt("products.imprint_year"));
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
				product.setId(resultSet.getInt("products.id"));
				product.setType(resultSet
						.getString("product_types.description"));
				product.setName(resultSet.getString("products.name"));
				product.setPrice(resultSet.getInt("products.price"));
				product.setDescription(resultSet
						.getString("products.description"));
				product.setPicturePath(resultSet
						.getString("product_pictures.path"));
				product.setPublisher(resultSet.getString("products.publisher"));
				product.setDeveloper(resultSet.getString("products.developer"));
				product.setImprintYear(resultSet
						.getInt("products.imprint_year"));
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return product;
	}

	public List<Product> findEntitiesByType(String type) {
		ArrayList<Product> products = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_PRODUCT_BY_TYPE)) {
			prepareStatement.setString(1, type);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("products.id"));
				product.setType(resultSet
						.getString("product_types.description"));
				product.setName(resultSet.getString("products.name"));
				product.setPrice(resultSet.getInt("products.price"));
				product.setDescription(resultSet
						.getString("products.description"));
				product.setPicturePath(resultSet
						.getString("product_pictures.path"));
				product.setPublisher(resultSet.getString("products.publisher"));
				product.setDeveloper(resultSet.getString("products.developer"));
				product.setImprintYear(resultSet
						.getInt("products.imprint_year"));
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
			int typeId = findTypeId(entity.getType());
			int pictureId = findPictureId(entity.getPicturePath());
			prepareStatement.setInt(1, typeId);
			prepareStatement.setString(2, entity.getName());
			prepareStatement.setInt(3, entity.getPrice());
			prepareStatement.setString(4, entity.getDescription());
			prepareStatement.setInt(5, pictureId);
			prepareStatement.setString(6, entity.getPublisher());
			prepareStatement.setString(7, entity.getDeveloper());
			prepareStatement.setInt(8, entity.getImprintYear());
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
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_UPDATE_PRODUCT)) {
			int typeId = findTypeId(entity.getType());
			int pictureId = findPictureId(entity.getPicturePath());
			prepareStatement.setInt(1, typeId);
			prepareStatement.setString(2, entity.getName());
			prepareStatement.setInt(3, entity.getPrice());
			prepareStatement.setString(4, entity.getDescription());
			prepareStatement.setInt(5, pictureId);
			prepareStatement.setString(6, entity.getPublisher());
			prepareStatement.setString(7, entity.getDeveloper());
			prepareStatement.setInt(8, entity.getImprintYear());
			prepareStatement.setInt(9, entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				return entity;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return null;
	}

	public List<String> findAllProductTypes() {
		ArrayList<String> types = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_PRODUCT_TYPES)) {
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				types.add(resultSet.getString("description"));
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return types;
	}

	public List<String> findAllProductPicturePath() {
		ArrayList<String> path = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_PRODUCT_PICTURES)) {
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				path.add(resultSet.getString("path"));
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
				.prepareStatement(SQL_CHECK_ACTIVE_ORDER)) {
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				flag = true;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return flag;
	}

	private Integer findTypeId(String type) {
		Integer id = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_TYPE)) {
			prepareStatement.setString(1, type);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				id = new Integer(resultSet.getInt("id"));
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return id;
	}

	private Integer findPictureId(String path) {
		Integer id = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_PICTURE)) {
			prepareStatement.setString(1, path);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				id = new Integer(resultSet.getInt("id"));
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return id;
	}
}
