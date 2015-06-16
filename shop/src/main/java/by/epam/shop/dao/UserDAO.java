package by.epam.shop.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import by.epam.shop.entity.User;
import by.epam.shop.exception.DAOException;

public class UserDAO extends AbstractDAO<User> {
	private static UserDAO instance;
	private static final String SQL_SELECT_USERS = "SELECT * FROM internet_shop.users";
	private static final String SQL_CREATE_USER = "INSERT INTO internet_shop.users (id, login, password, email, black_list_flag, access_level) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE_USER = "UPDATE internet_shop.users SET login= ?, password= ?, email= ?, black_list_flag= ?, access_level= ? WHERE id= ?";
	private static final String SQL_DELETE_USER = "DELETE FROM internet_shop.users WHERE id= ?";

	private UserDAO() {
		super();
	}

	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	@Override
	protected ArrayList<User> parseResultSet(ResultSet resultSet)
			throws DAOException {
		ArrayList<User> users = new ArrayList<>();
		try {
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setBlackListFlag(resultSet.getInt("black_list_flag"));
				user.setAccessLevel(resultSet.getInt("access_level"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return users;
	}

	@Override
	protected String getSelectQuery() {
		return SQL_SELECT_USERS;
	}

	@Override
	protected String getCreateQuery() {
		return SQL_CREATE_USER;
	}

	@Override
	protected String getUpdateQuery() {
		return SQL_UPDATE_USER;
	}

	@Override
	protected String getDeleteQuery() {
		return SQL_DELETE_USER;
	}

	@Override
	protected void prepareStatementForCreate(
			PreparedStatement prepareStatement, User entity)
			throws SQLException {
		prepareStatement.setInt(1, entity.getId());
		prepareStatement.setString(2, entity.getLogin());
		prepareStatement.setString(3, entity.getPassword());
		prepareStatement.setString(4, entity.getEmail());
		prepareStatement.setInt(5, entity.getBlackListFlag());
		prepareStatement.setInt(6, entity.getAccessLevel());
	}

	@Override
	protected void prepareStatementForUpdate(
			PreparedStatement prepareStatement, User entity)
			throws SQLException {
		prepareStatement.setString(1, entity.getLogin());
		prepareStatement.setString(2, entity.getPassword());
		prepareStatement.setString(3, entity.getEmail());
		prepareStatement.setInt(4, entity.getBlackListFlag());
		prepareStatement.setInt(5, entity.getAccessLevel());
		prepareStatement.setInt(6, entity.getId());
	}
}
