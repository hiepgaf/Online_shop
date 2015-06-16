package by.epam.shop.dao;

import by.epam.shop.exception.TechnicalException;

public class DAOFactory {
	private static DAOFactory instance;

	private enum TypeDAO {
		OrderDAO, ProductDAO, UserDAO
	}

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	public AbstractDAO<?> createDAO(String DAOType) throws TechnicalException {
		TypeDAO type = TypeDAO.valueOf(DAOType.toUpperCase());
		switch (type) {
		case OrderDAO:
			return new OrderDAO();
		case ProductDAO:
			return new ProductDAO();
		case UserDAO:
			return new UserDAO();
		default:
			throw new TechnicalException("such type of DAO doesn't exist");
		}
	}
}
