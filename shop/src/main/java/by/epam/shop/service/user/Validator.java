package by.epam.shop.service.user;

import java.util.regex.Pattern;

import by.epam.shop.constant.MessageKeys;
import by.epam.shop.entity.User;

public class Validator {
	public static final Pattern LOGIN_PATTERN = Pattern
			.compile("\\A[A-Za-z]][\\w]{7,14}\\z");
	public static final Pattern PASSWORD_PATTERN = Pattern
			.compile("\\A[\\w]{8,15}\\z");
	public static final Pattern EMAIL_PATTERN = Pattern
			.compile("\\A[\\w]*[.]*[\\w]+@[a-z]+\\.[a-z]{2,5}");

	public static String validateUser(User user) {
		if (!LOGIN_PATTERN.matcher(user.getLogin()).matches()) {
			return MessageKeys.REGISTER_LOGIN_PATTERN_ERROR;
		}
		if (!PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
			return MessageKeys.REGISTER_PASSWORD_PATTERN_ERROR;
		}
		if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
			return MessageKeys.REGISTER_EMAIL_PATTERN_ERROR;
		}
		return null;
	}
}
