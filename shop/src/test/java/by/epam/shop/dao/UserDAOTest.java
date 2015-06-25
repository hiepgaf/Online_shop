package by.epam.shop.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.shop.entity.User;
import by.epam.shop.service.user.Encryption;

public class UserDAOTest {
	private UserDAO userDAO;
	private User user;

	@Before
	public void setUp() throws Exception {
		userDAO = new UserDAO();
		user = new User();
		user.setId(1);
		user.setLogin("admin");
		user.setPassword(Encryption.hashMD5("admin"));
		user.setEmail("admin@gmail.com");
		user.setAccessLevel(2);
	}

	@After
	public void tearDown() throws Exception {
		userDAO = null;
		user = null;
	}

	@Test
	public void findUserTest() {
		User findUser = userDAO.findEntityById(1);
		assertEquals(user, findUser);
	}
}
