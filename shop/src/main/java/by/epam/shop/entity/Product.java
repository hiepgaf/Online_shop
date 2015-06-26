package by.epam.shop.entity;

/**
 * The Class Product.
 */
public class Product extends AbstractEntity {
	private String type;
	private String name;
	private int price;
	private String description;
	private String picturePath;
	private String publisher;
	private String developer;
	private int imprintYear;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price
	 *            the new price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the picture path.
	 *
	 * @return the picture path
	 */
	public String getPicturePath() {
		return picturePath;
	}

	/**
	 * Sets the picture path.
	 *
	 * @param picturePath
	 *            the new picture path
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	/**
	 * Gets the publisher.
	 *
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Sets the publisher.
	 *
	 * @param publisher
	 *            the new publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Gets the developer.
	 *
	 * @return the developer
	 */
	public String getDeveloper() {
		return developer;
	}

	/**
	 * Sets the developer.
	 *
	 * @param developer
	 *            the new developer
	 */
	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	/**
	 * Gets the imprint year.
	 *
	 * @return the imprint year
	 */
	public int getImprintYear() {
		return imprintYear;
	}

	/**
	 * Sets the imprint year.
	 *
	 * @param imprintYear
	 *            the new imprint year
	 */
	public void setImprintYear(int imprintYear) {
		this.imprintYear = imprintYear;
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
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((developer == null) ? 0 : developer.hashCode());
		result = prime * result + imprintYear;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((picturePath == null) ? 0 : picturePath.hashCode());
		result = prime * result + price;
		result = prime * result
				+ ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (developer == null) {
			if (other.developer != null)
				return false;
		} else if (!developer.equals(other.developer))
			return false;
		if (imprintYear != other.imprintYear)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picturePath == null) {
			if (other.picturePath != null)
				return false;
		} else if (!picturePath.equals(other.picturePath))
			return false;
		if (price != other.price)
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
