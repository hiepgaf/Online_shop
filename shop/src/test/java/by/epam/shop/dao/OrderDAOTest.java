package by.epam.shop.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.shop.entity.Order;
import by.epam.shop.entity.User;
import by.epam.shop.service.user.Encryption;

/**
 * The Class OrderDAOTest. Check selection of correct order from the database.
 */
public class OrderDAOTest {
	private OrderDAO orderDao;
	private Order order;
	private User user;

	@Before
	public void setUp() throws Exception {
		orderDao = new OrderDAO();
		user = new User();
		user.setId(1);
		user.setLogin("admin");
		user.setPassword(Encryption.hashMD5("admin"));
		user.setEmail("admin@gmail.com");
		user.setAccessLevel(2);
	}

	@After
	public void tearDown() throws Exception {
		orderDao = null;
		order = null;
		user = null;
	}

	@Test
	public void checkUserTest() {
		order = orderDao.findEntityById(59);
		assertEquals(user, order.getUser());
	}

	@Test
	public void checkStatusTest() {
		order = orderDao.findEntityById(59);
		String actualStatus = order.getStatus();
		assertEquals("active", actualStatus);
	}
}
