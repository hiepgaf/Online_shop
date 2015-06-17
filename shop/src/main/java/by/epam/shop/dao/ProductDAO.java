package by.epam.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.epam.shop.entity.Product;
import by.epam.shop.exception.DAOException;

public class ProductDAO extends AbstractDAO<Product> {
	private static final String SQL_SELECT_PRODUCT = "SELECT * FROM internet_shop.products";
	private static final String SQL_CREATE_PRODUCT = "INSERT INTO internet_shop.products (id, name, price, description, product_pictures_id) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE_PRODUCT = "UPDATE internet_shop.products SET name= ?, price= ?, description= ?, product_pictures_id= ? WHERE id= ?";
	private static final String SQL_DELETE_PRODUCT = "DELETE FROM internet_shop.products WHERE id= ?";
	private static final String SQL_SELECT_PICTURE = "SELECT * FROM internet_shop.product_pictures WHERE id= ?";

	@Override
	public String getSelectQuery() {
		return SQL_SELECT_PRODUCT;
	}

	@Override
	public String getCreateQuery() {
		return SQL_CREATE_PRODUCT;
	}

	@Override
	public String getUpdateQuery() {
		return SQL_UPDATE_PRODUCT;
	}

	@Override
	public String getDeleteQuery() {
		return SQL_DELETE_PRODUCT;
	}

	@Override
	public ArrayList<Product> parseResultSet(ResultSet resultSet)
			throws DAOException {
		ArrayList<Product> products = new ArrayList<>();
		try {
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
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return products;
	}

	@Override
	public void prepareStatementForCreate(PreparedStatement prepareStatement,
			Product entity) throws SQLException {
		prepareStatement.setInt(1, entity.getId());
		prepareStatement.setString(2, entity.getName());
		prepareStatement.setInt(3, entity.getPrice());
		prepareStatement.setString(4, entity.getDescription());
		prepareStatement.setString(5, entity.getPicturePath());
	}

	@Override
	public void prepareStatementForUpdate(PreparedStatement prepareStatement,
			Product entity) throws SQLException {
		prepareStatement.setString(1, entity.getName());
		prepareStatement.setInt(2, entity.getPrice());
		prepareStatement.setString(3, entity.getDescription());
		prepareStatement.setString(4, entity.getPicturePath());
		prepareStatement.setInt(5, entity.getId());
	}

	private String findPicturePath(int pictureId) throws DAOException {
		String path;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement(SQL_SELECT_PICTURE)) {
			prepareStatement.setInt(1, pictureId);
			ResultSet resultSet = prepareStatement.executeQuery();
			resultSet.next();
			path = resultSet.getString("path");
			connectionPool.freeConnection(connection);
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		return path;
	}
}