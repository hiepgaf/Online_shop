package by.epam.shop.dao;

import by.epam.shop.exception.TechnicalException;

public class DAOFactory {
	private static DAOFactory instance;

	private enum TypeDAO {
		ORDER_DAO, PRODUCT_DAO, USER_DAO
	}

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	@SuppressWarnings("rawtypes")
	public AbstractDAO createDAO(String DAOType) throws TechnicalException {
		TypeDAO type = TypeDAO.valueOf(DAOType.toUpperCase());
		switch (type) {
		case ORDER_DAO:
			return new OrderDAO();
		case PRODUCT_DAO:
			return new ProductDAO();
		case USER_DAO:
			return new UserDAO();
		default:
			throw new TechnicalException("such type of DAO doesn't exist");
		}
	}
}
