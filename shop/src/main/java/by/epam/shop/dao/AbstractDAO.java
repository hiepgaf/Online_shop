package by.epam.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.shop.connectionpool.ConnectionPool;
import by.epam.shop.entity.AbstractEntity;
import by.epam.shop.exception.DAOException;

public abstract class AbstractDAO<T extends AbstractEntity> {
	public ConnectionPool connectionPool = ConnectionPool.getInstance();

	protected abstract String getSelectQuery();

	protected abstract String getCreateQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getDeleteQuery();

	protected abstract ArrayList<T> parseResultSet(ResultSet resultSet)
			throws DAOException;

	protected abstract void prepareStatementForCreate(
			PreparedStatement prepareStatement, T entity) throws SQLException;

	protected abstract void prepareStatementForUpdate(
			PreparedStatement prepareStatement, T entity) throws SQLException;

	public List<T> findAll() throws DAOException {
		ArrayList<T> entities = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement(getSelectQuery())) {
			ResultSet resultSet = prepareStatement.executeQuery();
			entities = parseResultSet(resultSet);
			connectionPool.freeConnection(connection);
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		return entities;
	}

	public T findEntityById(Integer id) throws DAOException {
		ArrayList<T> entities = new ArrayList<>();
		T entity = null;
		String sql = getSelectQuery() + " where id = ?";
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement(sql)) {
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			entities = parseResultSet(resultSet);
			connectionPool.freeConnection(connection);
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		if (entities.size() <= 1) {
			entity = entities.iterator().next();
		} else {
			throw new DAOException("Received more than one record");
		}
		return entity;
	}

	public boolean delete(Integer id) throws DAOException {
		boolean flag = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement(getDeleteQuery())) {
			prepareStatement.setInt(1, id);
			int count = prepareStatement.executeUpdate();
			connectionPool.freeConnection(connection);
			if (count == 1) {
				flag = true;
			} else {
				if (count > 1) {
					throw new DAOException(
							"On delete modify more then 1 record: " + count);
				}
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		return flag;
	}

	public boolean delete(T entity) throws DAOException {
		boolean flag = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement(getDeleteQuery())) {
			prepareStatement.setInt(1, entity.getId());
			int count = prepareStatement.executeUpdate();
			connectionPool.freeConnection(connection);
			if (count == 1) {
				flag = true;
			} else {
				if (count > 1) {
					throw new DAOException(
							"On delete modify more then 1 record: " + count);
				}
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		return flag;
	}

	public boolean create(T entity) throws DAOException {
		boolean flag = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement(getCreateQuery())) {
			prepareStatementForCreate(prepareStatement, entity);
			int count = prepareStatement.executeUpdate();
			connectionPool.freeConnection(connection);
			if (count == 1) {
				flag = true;
			} else {
				if (count > 1) {
					throw new DAOException(
							"On create modify more then 1 record: " + count);
				}
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		return flag;
	}

	public T update(T entity) throws DAOException {
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement prepareStatement = connection
						.prepareStatement(getCreateQuery())) {
			prepareStatementForUpdate(prepareStatement, entity);
			int count = prepareStatement.executeUpdate();
			connectionPool.freeConnection(connection);
			if (count == 0) {
				throw new DAOException("Record doesn't exist");
			} else {
				if (count > 1) {
					throw new DAOException(
							"On update modify more then 1 record: " + count);
				}
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e);
		}
		return entity;
	}
}
