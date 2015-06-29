package by.epam.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.shop.entity.Product;
import by.epam.shop.exception.DAOException;

/**
 * The Class ProductDAO.
 */
public class ProductDAO extends AbstractDAO<Product> {
	private static final String SQL_SELECT_PRODUCT = "SELECT * FROM internet_shop.products JOIN internet_shop.product_types ON (products.product_types_id = product_types.id) JOIN internet_shop.product_pictures ON (products.product_pictures_id = product_pictures.id)";
	private static final String SQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM internet_shop.products JOIN internet_shop.product_types ON (products.product_types_id = product_types.id) JOIN internet_shop.product_pictures ON (products.product_pictures_id = product_pictures.id) WHERE products.id= ?";
	private static final String SQL_SELECT_PRODUCT_BY_TYPE = "SELECT * FROM internet_shop.products JOIN internet_shop.product_types ON (products.product_types_id = product_types.id) JOIN internet_shop.product_pictures ON (products.product_pictures_id = product_pictures.id) WHERE product_types.description= ?";
	private static final String SQL_CREATE_PRODUCT = "INSERT INTO internet_shop.products (product_types_id, name, price, description, product_pictures_id, publisher, developer, imprint_year) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE_PRODUCT = "UPDATE internet_shop.products SET product_types_id= ?, name= ?, price= ?, description= ?, product_pictures_id= ?, publisher= ?, developer= ?, imprint_year= ? WHERE id= ?";
	private static final String SQL_DELETE_PRODUCT = "DELETE FROM internet_shop.products WHERE id= ?";
	private static final String SQL_DELETE_ORDERS_PRODUCTS = "DELETE FROM internet_shop.orders_products WHERE products_id= ?";
	private static final String SQL_SELECT_PICTURE = "SELECT * FROM internet_shop.product_pictures WHERE path= ?";
	private static final String SQL_SELECT_TYPE = "SELECT * FROM internet_shop.product_types WHERE description= ?";
	private static final String SQL_CHECK_ACTIVE_ORDER = "SELECT * FROM internet_shop.products JOIN internet_shop.orders_products ON (products.id = orders_products.products_id) JOIN internet_shop.orders ON (orders.id = orders_products.orders_id) WHERE orders.status_id = 1 AND products.id= ?";
	private static final String SQL_SELECT_PRODUCT_TYPES = "SELECT * FROM internet_shop.product_types";
	private static final String SQL_SELECT_PRODUCT_PICTURES = "SELECT * FROM internet_shop.product_pictures";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.shop.dao.AbstractDAO#findAll()
	 */
	@Override
	public List<Product> findAll() throws DAOException {
		ArrayList<Product> products = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PRODUCT)) {
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("products.id"));
				product.setType(resultSet.getString("product_types.description"));
				product.setName(resultSet.getString("products.name"));
				product.setPrice(resultSet.getInt("products.price"));
				product.setDescription(resultSet.getString("products.description"));
				product.setPicturePath(resultSet.getString("product_pictures.path"));
				product.setPublisher(resultSet.getString("products.publisher"));
				product.setDeveloper(resultSet.getString("products.developer"));
				product.setImprintYear(resultSet.getInt("products.imprint_year"));
				products.add(product);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return products;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.shop.dao.AbstractDAO#findEntityById(java.lang.Integer)
	 */
	@Override
	public Product findEntityById(Integer id) throws DAOException {
		Product product = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_ID)) {
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt("products.id"));
				product.setType(resultSet.getString("product_types.description"));
				product.setName(resultSet.getString("products.name"));
				product.setPrice(resultSet.getInt("products.price"));
				product.setDescription(resultSet.getString("products.description"));
				product.setPicturePath(resultSet.getString("product_pictures.path"));
				product.setPublisher(resultSet.getString("products.publisher"));
				product.setDeveloper(resultSet.getString("products.developer"));
				product.setImprintYear(resultSet.getInt("products.imprint_year"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return product;
	}

	/**
	 * Find entities by type.
	 *
	 * @param type
	 *            the type
	 * @return the list
	 * @throws DAOException
	 */
	public List<Product> findEntitiesByType(String type) throws DAOException {
		ArrayList<Product> products = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_TYPE)) {
			prepareStatement.setString(1, type);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("products.id"));
				product.setType(resultSet.getString("product_types.description"));
				product.setName(resultSet.getString("products.name"));
				product.setPrice(resultSet.getInt("products.price"));
				product.setDescription(resultSet.getString("products.description"));
				product.setPicturePath(resultSet.getString("product_pictures.path"));
				product.setPublisher(resultSet.getString("products.publisher"));
				product.setDeveloper(resultSet.getString("products.developer"));
				product.setImprintYear(resultSet.getInt("products.imprint_year"));
				products.add(product);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return products;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.shop.dao.AbstractDAO#delete(java.lang.Integer)
	 */
	@Override
	public boolean delete(Integer id) throws DAOException {
		if (checkActiveOrder(id)) {
			return false;
		}
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_PRODUCT)) {
			deleteFromOrdersProducts(id);
			prepareStatement.setInt(1, id);
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.epam.shop.dao.AbstractDAO#delete(by.epam.shop.entity.AbstractEntity)
	 */
	@Override
	public boolean delete(Product entity) throws DAOException {
		if (checkActiveOrder(entity.getId())) {
			return false;
		}
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_PRODUCT)) {
			deleteFromOrdersProducts(entity.getId());
			prepareStatement.setInt(1, entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.epam.shop.dao.AbstractDAO#create(by.epam.shop.entity.AbstractEntity)
	 */
	@Override
	public boolean create(Product entity) throws DAOException {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_CREATE_PRODUCT)) {
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
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.epam.shop.dao.AbstractDAO#update(by.epam.shop.entity.AbstractEntity)
	 */
	@Override
	public Product update(Product entity) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT)) {
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
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return null;
	}

	/**
	 * Find all product types.
	 *
	 * @return the list
	 * @throws DAOException
	 */
	public List<String> findAllProductTypes() throws DAOException {
		ArrayList<String> types = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PRODUCT_TYPES)) {
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				types.add(resultSet.getString("description"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return types;
	}

	/**
	 * Find all product picture path.
	 *
	 * @return the list
	 * @throws DAOException
	 */
	public List<String> findAllProductPicturePath() throws DAOException {
		ArrayList<String> path = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PRODUCT_PICTURES)) {
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				path.add(resultSet.getString("path"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return path;
	}

	/**
	 * Check active order.
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 * @throws DAOException
	 */
	private boolean checkActiveOrder(int id) throws DAOException {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_CHECK_ACTIVE_ORDER)) {
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	/**
	 * Find type_id.
	 *
	 * @param type
	 *            the type
	 * @return the integer
	 * @throws DAOException
	 */
	private Integer findTypeId(String type) throws DAOException {
		Integer id = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_TYPE)) {
			prepareStatement.setString(1, type);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				id = new Integer(resultSet.getInt("id"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return id;
	}

	/**
	 * Find picture_id.
	 *
	 * @param path
	 *            the path
	 * @return the integer
	 * @throws DAOException
	 */
	private Integer findPictureId(String path) throws DAOException {
		Integer id = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PICTURE)) {
			prepareStatement.setString(1, path);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				id = new Integer(resultSet.getInt("id"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return id;
	}

	/**
	 * Delete from orders_products.
	 *
	 * @param productId
	 *            the product id
	 * @throws DAOException
	 */
	private void deleteFromOrdersProducts(int productId) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_ORDERS_PRODUCTS)) {
			prepareStatement.setInt(1, productId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.freeConnection(connection);
		}
	}
}
