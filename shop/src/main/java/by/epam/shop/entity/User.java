package by.epam.shop.entity;

import java.util.ArrayList;

/**
 * The Class User.
 */
public class User extends AbstractEntity {
	private String login;
	private String password;
	private String email;
	private int blackListFlag;
	private int accessLevel;
	private ArrayList<Product> shoooppingCart = new ArrayList<>();

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login
	 *            the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the black list flag.
	 *
	 * @return the black list flag
	 */
	public int getBlackListFlag() {
		return blackListFlag;
	}

	/**
	 * Sets the black list flag.
	 *
	 * @param blackListFlag
	 *            the new black list flag
	 */
	public void setBlackListFlag(int blackListFlag) {
		this.blackListFlag = blackListFlag;
	}

	/**
	 * Gets the access level.
	 *
	 * @return the access level
	 */
	public int getAccessLevel() {
		return accessLevel;
	}

	/**
	 * Sets the access level.
	 *
	 * @param accessLevel
	 *            the new access level
	 */
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	/**
	 * Gets the shooopping cart.
	 *
	 * @return the shooopping cart
	 */
	public ArrayList<Product> getShoooppingCart() {
		return shoooppingCart;
	}

	/**
	 * Sets the shooopping cart.
	 *
	 * @param shoooppingCart
	 *            the new shooopping cart
	 */
	public void setShoooppingCart(ArrayList<Product> shoooppingCart) {
		this.shoooppingCart = shoooppingCart;
	}

	/**
	 * Adds the product.
	 *
	 * @param product
	 *            the product
	 */
	public void addProduct(Product product) {
		shoooppingCart.add(product);
	}

	/**
	 * Removes the product.
	 *
	 * @param product
	 *            the product
	 */
	public void removeProduct(Product product) {
		shoooppingCart.remove(product);
	}

	/**
	 * Removes the all products.
	 */
	public void removeAllProducts() {
		shoooppingCart.removeAll(shoooppingCart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.shop.entity.AbstractEntity#hashCode()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.shop.entity.AbstractEntity#equals(java.lang.Object)
	 */
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
