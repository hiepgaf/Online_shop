package by.epam.shop.dao;

import java.util.List;

import by.epam.shop.connectionpool.ConnectionPool;
import by.epam.shop.entity.AbstractEntity;

public abstract class AbstractDAO<T extends AbstractEntity> {
	public static ConnectionPool connectionPool = ConnectionPool.getInstance();

	public abstract List<T> findAll();

	public abstract T findEntityById(Integer id);

	public abstract boolean delete(Integer id);

	public abstract boolean delete(T entity);

	public abstract boolean create(T entity);

	public abstract T update(T entity);
}
