package by.epam.shop.service.user;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.epam.shop.entity.User;

@RunWith(Parameterized.class)
public class ValidatorTest {
	private String login;
	private String password;
	private String email;
	private User user;

	public ValidatorTest(String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
	}

	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setEmail(email);
	}

	@After
	public void tearDown() throws Exception {
		user = null;
	}

	@Parameters
	public static Collection<String[]> stepUpStringvalues() {
		return Arrays.asList(new String[][] {
				{ "Ivan", "ivan555", "ivan@gmail.com" },
				{ "vasia_pupkin", "2541_asdf", "vasia.pupkin@gmail.com" },
				{ "alena456", "333333333", "alena2012@tut.by" } });
	}

	@Test
	public void validationTest() {
		assertNull(Validator.validateUser(user));
	}
}
