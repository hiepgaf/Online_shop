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
	<ul>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="go_to_page" /> <input
					name="page" type="hidden" value="path.page.main" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.main"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_types_id" type="hidden" value="1" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.action"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_types_id" type="hidden" value="2" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.rpg"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_types_id" type="hidden" value="3" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.strategy"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_types_id" type="hidden" value="4" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.simulator"/>" />
			</form></li>
		<li><form action="Controller" method="POST">
				<input name="action" type="hidden" value="show_products" /> <input
					name="product_types_id" type="hidden" value="5" /> <input
					class="button" type="submit"
					value="<fmt:message key="menu.button.quest"/>" />
			</form></li>
	</ul>
</body>
</html>