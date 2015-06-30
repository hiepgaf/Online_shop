package by.epam.shop.service.product;

import java.util.regex.Pattern;

import by.epam.shop.constant.MessageKeys;
import by.epam.shop.entity.Product;

/**
 * The Class ProductValidator. Check product data for correctness.
 */
public class ProductValidator {

	public static final Pattern PRODUCT_NAME_PATTERN = Pattern.compile("\\A[А-ЯA-Z\\d][А-Яа-я\\w\\s:.!?,-]+\\z");
	public static final Pattern PRODUCT_PRICE_PATTERN = Pattern.compile("\\A[\\d]{4,7}\\z");
	public static final Pattern PRODUCT_PUBLISHER_PATTERN = Pattern.compile("\\A[А-ЯA-Z\\d][А-Яа-я\\w\\s:.!?,-]+\\z");
	public static final Pattern PRODUCT_DEVELOPER_PATTERN = Pattern.compile("\\A[А-ЯA-Z\\d][А-Яа-я\\w\\s:.!?,-]+\\z");

	/**
	 * Validate product. If data is not correct, returns a message describing a
	 * error, otherwise returns null.
	 *
	 * @param product
	 *            the product
	 * @return the string
	 */
	public static String validateProduct(Product product) {
		if (!PRODUCT_NAME_PATTERN.matcher(product.getName()).find()) {
			return MessageKeys.PRODUCT_NAME_PATTERN_ERROR;
		}
		if (!PRODUCT_PRICE_PATTERN.matcher(Integer.toString(product.getPrice())).find()) {
			return MessageKeys.PRODUCT_PRICE_PATTERN_ERROR;
		}
		if (!PRODUCT_PUBLISHER_PATTERN.matcher(product.getPublisher()).matches()) {
			return MessageKeys.PRODUCT_PUBLISHER_PATTERN_ERROR;
		}
		if (!PRODUCT_DEVELOPER_PATTERN.matcher(product.getDeveloper()).matches()) {
			return MessageKeys.PRODUCT_DEVELOPER_PATTERN_ERROR;
		}
		return null;
	}
}
