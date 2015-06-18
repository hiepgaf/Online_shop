package by.epam.shop.service.user;

import org.apache.commons.codec.digest.DigestUtils;

public class Encryption {
	public static String hashMD5(String text) {
		return DigestUtils.md5Hex(text);
	}
}
