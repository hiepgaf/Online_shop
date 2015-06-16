package by.epam.shop.dao;

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

	@Override
	protected String getSelectQuery() {
		return SQL_SELECT_PRODUCT;
	}

	@Override
	protected String getCreateQuery() {
		return SQL_CREATE_PRODUCT;
	}

	@Override
	protected String getUpdateQuery() {
		return SQL_UPDATE_PRODUCT;
	}

	@Override
	protected String getDeleteQuery() {
		return SQL_DELETE_PRODUCT;
	}

	@Override
	protected ArrayList<Product> parseResultSet(ResultSet resultSet)
			throws DAOException {
		ArrayList<Product> products = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));
				product.setDescription(resultSet.getString("description"));
				product.setPicturePath(resultSet
						.getString("product_pictures_id"));
				products.add(product);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return products;
	}

	@Override
	protected void prepareStatementForCreate(
			PreparedStatement prepareStatement, Product entity)
			throws SQLException {
		prepareStatement.setInt(1, entity.getId());
		prepareStatement.setString(2, entity.getName());
		prepareStatement.setInt(3, entity.getPrice());
		prepareStatement.setString(4, entity.getDescription());
		prepareStatement.setString(5, entity.getPicturePath());
	}

	@Override
	protected void prepareStatementForUpdate(
			PreparedStatement prepareStatement, Product entity)
			throws SQLException {
		prepareStatement.setString(1, entity.getName());
		prepareStatement.setInt(2, entity.getPrice());
		prepareStatement.setString(3, entity.getDescription());
		prepareStatement.setString(4, entity.getPicturePath());
		prepareStatement.setInt(5, entity.getId());
	}

}
