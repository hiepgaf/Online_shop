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
	<div class="title">
		<fmt:message key="products.title" />
	</div>
	<form method="POST" action="Controller">
		<c:forEach items="${products}" var="product">
			<div id="productInf">
				<div id="productImage">
					<center>
						<img src="${product.picturePath }" width="70%" align="middle">
					</center>
				</div>
				<div id="productNameDescription">
					<div id="productName">${product.name }</div>
					<div id="productsShortDescription">${product.description }</div>
				</div>
				<div id="priceBuy">
					<div id="productPrice">${product.price } <fmt:message key="products.money" /></div>
					<div>
						<input name="action" type="hidden" value="add_to_shopping_cart" />
						<input name="product_id" type="hidden" value="${product.id }" />
						<input class="buyButton" type="submit" value="<fmt:message key="products.buy" />" />
					</div>
				</div>
			</div>
			<div style="clear: left"></div>
		</c:forEach>
	</form>
</body>
</html>