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
	<c:choose>
		<c:when test="${empty sessionScope.user}">
			<div id="innerlogin">
				<form method="POST" action="Controller">
					<div>
						<input name="action" type="hidden" value="login" />
					</div>
					<div>
						<input class="input" type="text" name="login" value="<fmt:message key="field.login" />" />
					</div>
					<div>
						<input class="input" type="password" name="password"
							value="********" />
					</div>
					<div>
						<input class="button" type="submit" value="<fmt:message key="menu.button.login" />" />
					</div>
				</form>
				<div id="register">
					<a href="pages/register.jsp"><fmt:message key="menu.button.register" /></a>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div id="innerlogin">
				<div><fmt:message key="menu.user.appeal" /> ${sessionScope.user.login}</div>
				<div>
					<form method="POST" action="Controller">
						<input name="action" type="hidden" value="show_cart_action" />
						<input class="button" type="submit" name="shopping_cart"
							value="<fmt:message key="menu.button.shoppingcart" />">
					</form>
				</div>
				<div>
					<form method="POST" action="Controller">
						<input name="action" type="hidden" value="show_orders" /> <input
							class="button" type="submit" name="orders" value="<fmt:message key="menu.button.orders" />">
					</form>
				</div>
				<div>
					<form method="POST" action="Controller">
						<input name="action" type="hidden" value="logout" /> <input
							class="button" type="submit" name="log_out" value="<fmt:message key="menu.button.exit" />">
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>