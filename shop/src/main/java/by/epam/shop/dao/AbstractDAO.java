package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.connectionpool.ConnectionPool;
import by.epam.shop.entity.AbstractEntity;

/**
 * The Class AbstractDAO. Base abstract class for concrete DAO objects.
 *
 * @param <T>
 *            the generic type
 */
public abstract class AbstractDAO<T extends AbstractEntity> {
	public static ConnectionPool connectionPool = ConnectionPool.getInstance();

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public abstract List<T> findAll();

	/**
	 * Find entity by id.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	public abstract T findEntityById(Integer id);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	public abstract boolean delete(Integer id);

	/**
	 * Delete.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 */
	public abstract boolean delete(T entity);

	/**
	 * Creates the.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 */
	public abstract boolean create(T entity);

	/**
	 * Update.
	 *
	 * @param entity
	 *            the entity
	 * @return the t
	 */
	public abstract T update(T entity);
}
