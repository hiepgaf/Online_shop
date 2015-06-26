package by.epam.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.shop.entity.User;

/**
 * The Class UserDAO.
 */
public class UserDAO extends AbstractDAO<User> {
	private static Logger log = Logger.getLogger(UserDAO.class);
	private static final String SQL_SELECT_USERS = "SELECT * FROM internet_shop.users";
	private static final String SQL_SELECT_USERS_BY_ID = "SELECT * FROM internet_shop.users WHERE id= ?";
	private static final String SQL_SELECT_USERS_BY_LOGIN = "SELECT * FROM internet_shop.users WHERE login= ?";
	private static final String SQL_SELECT_USERS_BY_LOGIN_AND_PASSWORD = "SELECT * FROM internet_shop.users WHERE login= ? AND password= ?";
	private static final String SQL_CREATE_USER = "INSERT INTO internet_shop.users (login, password, email, black_list_flag, access_level_id) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE_USER = "UPDATE internet_shop.users SET login= ?, password= ?, email= ?, black_list_flag= ?, access_level_id= ? WHERE id= ?";
	private static final String SQL_DELETE_USER = "DELETE FROM internet_shop.users WHERE id= ?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.shop.dao.AbstractDAO#findAll()
	 */
	@Override
	public List<User> findAll() {
		ArrayList<User> users = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_USERS)) {
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setBlackListFlag(resultSet.getInt("black_list_flag"));
				user.setAccessLevel(resultSet.getInt("access_level_id"));
				users.add(user);
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.shop.dao.AbstractDAO#findEntityById(java.lang.Integer)
	 */
	@Override
	public User findEntityById(Integer id) {
		User user = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_USERS_BY_ID)) {
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setBlackListFlag(resultSet.getInt("black_list_flag"));
				user.setAccessLevel(resultSet.getInt("access_level_id"));
			} else {
				return null;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return user;
	}

	/**
	 * Find entity by login.
	 *
	 * @param login
	 *            the login
	 * @return the user
	 */
	public User findEntityByLogin(String login) {
		User user = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_USERS_BY_LOGIN)) {
			prepareStatement.setString(1, login);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setBlackListFlag(resultSet.getInt("black_list_flag"));
				user.setAccessLevel(resultSet.getInt("access_level_id"));
			} else {
				return null;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return user;
	}

	/**
	 * Find entity by login and password.
	 *
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 * @return the user
	 */
	public User findEntityByLoginAndPassword(String login, String password) {
		User user = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_SELECT_USERS_BY_LOGIN_AND_PASSWORD)) {
			prepareStatement.setString(1, login);
			prepareStatement.setString(2, password);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setBlackListFlag(resultSet.getInt("black_list_flag"));
				user.setAccessLevel(resultSet.getInt("access_level_id"));
			} else {
				return null;
			}
			connectionPool.freeConnection(connection);
		} catch (SQLException e) {
			log.error(e);
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.shop.dao.AbstractDAO#delete(java.lang.Integer)
	 */
	@Override
	public boolean delete(Integer id) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_DELETE_USER)) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.epam.shop.dao.AbstractDAO#delete(by.epam.shop.entity.AbstractEntity)
	 */
	@Override
	public boolean delete(User entity) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_DELETE_USER)) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.epam.shop.dao.AbstractDAO#create(by.epam.shop.entity.AbstractEntity)
	 */
	@Override
	public boolean create(User entity) {
		boolean flag = false;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_CREATE_USER)) {
			prepareStatement.setString(1, entity.getLogin());
			prepareStatement.setString(2, entity.getPassword());
			prepareStatement.setString(3, entity.getEmail());
			prepareStatement.setInt(4, entity.getBlackListFlag());
			prepareStatement.setInt(5, entity.getAccessLevel());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.epam.shop.dao.AbstractDAO#update(by.epam.shop.entity.AbstractEntity)
	 */
	@Override
	public User update(User entity) {
		User user = null;
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement prepareStatement = connection
				.prepareStatement(SQL_UPDATE_USER)) {
			prepareStatement.setString(1, entity.getLogin());
			prepareStatement.setString(2, entity.getPassword());
			prepareStatement.setString(3, entity.getEmail());
			prepareStatement.setInt(4, entity.getBlackListFlag());
			prepareStatement.setInt(5, entity.getAccessLevel());
			prepareStatement.setInt(6, entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				return user;
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return null;
	}

}
