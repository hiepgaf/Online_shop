<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="title">
		<fmt:message key="cart.title" />
	</div>
	<form method="POST" action="Controller">
		<c:forEach items="${cart}" var="product">
			<div id="cart">
				<div id="cartName">${product.name }</div>
				<div id="cartPrice">${product.price }
					<fmt:message key="products.money" />
				</div>
				<div>
					<input name="action" type="hidden" value="remove_from_cart" /> <input
						name="product_id" type="hidden" value="${product.id }" /> <input
						id="deleteFromCartButton" type="submit"
						value="<fmt:message key="product.remove" />" />
				</div>
			</div>
			<div style="clear: left"></div>
		</c:forEach>
	</form>
	<div align="center">
		<fmt:message key="cart.price.message" />
		: ${full_price }
		<fmt:message key="products.money" />
	</div>
	<div align="center">
		<form method="POST" action="Controller">
			<input name="action" type="hidden" value="make_order" /><input
				id="makeOrderButton" type="submit"
				value="<fmt:message key="cart.makeorder" />" />
		</form>
	</div>
</body>
</html>