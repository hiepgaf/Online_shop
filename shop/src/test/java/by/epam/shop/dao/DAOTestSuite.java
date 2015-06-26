package by.epam.shop.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The Class DAOTestSuite. Contains the suite of DAO tests.
 */
@Suite.SuiteClasses({ OrderDAOTest.class, ProductDAOTest.class,
		UserDAOTest.class })
@RunWith(Suite.class)
public class DAOTestSuite {
}
