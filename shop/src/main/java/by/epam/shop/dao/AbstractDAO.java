package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.connectionpool.ConnectionPool;
import by.epam.shop.entity.AbstractEntity;
import by.epam.shop.exception.DAOException;

/**
 * The Class AbstractDAO. Base abstract class for concrete DAO objects.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractDAO<T extends AbstractEntity> {
	protected static ConnectionPool connectionPool = ConnectionPool.getInstance();

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DAOException
	 */
	public abstract List<T> findAll() throws DAOException;

	/**
	 * Find entity by id.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 * @throws DAOException
	 */
	public abstract T findEntityById(Integer id) throws DAOException;

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 * @throws DAOException
	 */
	public abstract boolean delete(Integer id) throws DAOException;

	/**
	 * Delete.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 * @throws DAOException
	 */
	public abstract boolean delete(T entity) throws DAOException;

	/**
	 * Creates the.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 * @throws DAOException
	 */
	public abstract boolean create(T entity) throws DAOException;

	/**
	 * Update.
	 *
	 * @param entity
	 *            the entity
	 * @return the t
	 * @throws DAOException
	 */
	public abstract T update(T entity) throws DAOException;
}
