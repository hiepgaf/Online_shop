<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div>
		<h1>Регистрация нового пользователя</h1>
	</div>
	<div>
		<form method="POST" action="Controller">
			<table>
				<tr>
					<td style="vertical-align: top"><fmt:message key="reg.login" />:</td>
					<td><input type="text" name="login" /> <br /> <b
						style="color: red; font-size: 10px;"> <fmt:message
								key="reg.info.login" />
					</b></td>
				</tr>
				<tr>
					<td style="vertical-align: top"><fmt:message
							key="reg.password" />:</td>
					<td><input type="password" name="password" /> <br /> <b
						style="color: red; font-size: 10px;"> <fmt:message
								key="reg.info.password" />
					</b></td>
				</tr>
				<tr>
					<td style="vertical-align: top"><fmt:message
							key="reg.password.repeat" />:</td>
					<td><input type="password" name="password_repeat" /> <br /></td>
				</tr>
				<tr>
					<td style="vertical-align: top"><fmt:message key="reg.email" />:</td>
					<td><input type="text" name="email" /> <br /> <b
						style="color: red; font-size: 10px;"> <fmt:message
								key="reg.info.email" />
					</b></td>
				</tr>
			</table>
			<input class="button" type="submit"
				value="<fmt:message key="menu.button.register" />" />
		</form>
		<form method="POST" action="Controller">
			<div>
				<input name="action" type="hidden" value="register" />
			</div>
			<div>
				<input class="input" type="text" name="login" value="" />
			</div>
			<div>
				<input class="input" type="password" name="password" value="" />
			</div>
			<div>
				<input class="input" type="password" name="password_repeat" value="" />
			</div>
			<div>
				<input class="input" type="text" name="email" value="" />
			</div>
			<div>
				<input class="button" type="submit"
					value="<fmt:message key="menu.button.register" />" />
			</div>
		</form>
	</div>
</body>
</html>