<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form method="POST" action="Controller">
		<div>
			<input name="action" type="hidden" value="login" />
		</div>
		<div>
			<input class="input" type="text" name="login" value="Логин" />
		</div>
		<div>
			<input class="input" type="password" name="password" value="********" />
		</div>
		<div>
			<input class="button" type="submit" value="Войти" />
		</div>
	</form>
	<div id="register">
		<a href="#">Зарегестрироваться</a>
	</div>
</body>
</html>