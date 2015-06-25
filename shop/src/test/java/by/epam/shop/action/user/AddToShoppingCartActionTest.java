package by.epam.shop.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

import by.epam.shop.action.Action;
import by.epam.shop.action.container.ActionFactory;
import by.epam.shop.entity.Product;
import by.epam.shop.entity.User;
import by.epam.shop.service.user.Encryption;

@RunWith(MockitoJUnitRunner.class)
public class AddToShoppingCartActionTest extends Mockito {
	@Mock
	private HttpServletRequest mockRequest;
	@Mock
	private HttpServletResponse mockResponse;
	@Mock
	private HttpSession mockSession;
	@Mock
	private RequestDispatcher mockRd;
	private User user;
	private Product product;

	@Before
	public void setUp() throws Exception {
		user = new User();
		product = new Product();
		user.setId(1);
		user.setLogin("admin");
		user.setPassword(Encryption.hashMD5("admin"));
		user.setEmail("admin@gmail.com");
		user.setAccessLevel(2);
		product.setId(1);
		product.setType("RPG");
		product.setName("Ведьмак 3: Дикая охота");
		product.setPrice(430000);
		product.setDescription("«Ведьмак 3: Дикая Охота» – ролевая игра нового поколения, действие которой разворачивается в удивительном фэнтезийном мире, где необходимо принимать сложные решения и отвечать за их последствия.");
		product.setPicturePath("images/products/Vedmak_3.jpg");
		product.setPublisher("1С-СофтКлаб");
		product.setDeveloper("CD Projekt Red");
		product.setImprintYear(2015);
	}

	@Test
	public void shoppingCartTest() throws ServletException, IOException {
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("user")).thenReturn(user);
		when(mockRequest.getParameter("action")).thenReturn(
				"add_to_shopping_cart");
		when(mockRequest.getParameter("product_id")).thenReturn("1");
		ActionFactory factory = ActionFactory.getInstance();
		Action action = factory.defineCommand(mockRequest);
		String page = action.execute(mockRequest);
		assertEquals(product, user.getShoooppingCart().get(0));
		assertEquals("/pages/success.jsp", page);
	}

}
