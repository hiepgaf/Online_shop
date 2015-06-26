package by.epam.shop.service.user;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * The Class Encryption. Encrypts a string to store it in encrypted form.
 */
public class Encryption {

	/**
	 * Hash md5.
	 *
	 * @param text
	 *            the text
	 * @return the string
	 */
	public static String hashMD5(String text) {
		return DigestUtils.md5Hex(text);
	}
}
