package by.epam.shop.entity;

import java.util.Collections;
import java.util.List;

public class User extends AbstractEntity {
	private String login;
	private String password;
	private String email;
	private int blackListFlag;
	private int accessLevel;
	private List<Product> shoooppingCart;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBlackListFlag() {
		return blackListFlag;
	}

	public void setBlackListFlag(int blackListFlag) {
		this.blackListFlag = blackListFlag;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public List<Product> getShoooppingCart() {
		return Collections.unmodifiableList(shoooppingCart);
	}

	public void setShoooppingCart(List<Product> shoooppingCart) {
		this.shoooppingCart = shoooppingCart;
	}

	public void addProduct(Product product) {
		shoooppingCart.add(product);
	}

	public void removeProduct(Product product) {
		shoooppingCart.remove(product);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + accessLevel;
		result = prime * result + blackListFlag;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((shoooppingCart == null) ? 0 : shoooppingCart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accessLevel != other.accessLevel)
			return false;
		if (blackListFlag != other.blackListFlag)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (shoooppingCart == null) {
			if (other.shoooppingCart != null)
				return false;
		} else if (!shoooppingCart.equals(other.shoooppingCart))
			return false;
		return true;
	}

}
