package by.epam.shop.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.shop.entity.Product;

/**
 * The Class ProductDAOTest. Checks selection of correct product from the
 * database.
 */
public class ProductDAOTest {
	private ProductDAO productDAO;
	private Product product;

	@Before
	public void setUp() throws Exception {
		productDAO = new ProductDAO();
		product = new Product();
		product.setId(1);
		product.setType("РПГ");
		product.setName("Ведьмак 3: Дикая охота");
		product.setPrice(430000);
		product.setDescription("«Ведьмак 3: Дикая Охота» – ролевая игра нового поколения, действие которой разворачивается в удивительном фэнтезийном мире, где необходимо принимать сложные решения и отвечать за их последствия.");
		product.setPicturePath("images/products/Vedmak_3.jpg");
		product.setPublisher("1С-СофтКлаб");
		product.setDeveloper("CD Projekt Red");
		product.setImprintYear(2015);
	}

	@After
	public void tearDown() throws Exception {
		productDAO = null;
	}

	@Test
	public void findProductTest() {
		Product findProduct = productDAO.findEntityById(1);
		assertEquals(product, findProduct);
	}

}
