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
	<div class="title">${product.name }</div>
	<form method="POST" action="Controller">
		<div id="detProductImage">
			<center>
				<img src="${product.picturePath }" width="70%" align="middle">
			</center>
		</div>
		<div id="detProductInf">
			<div class="detProductInfBlock">
				<fmt:message key="product.name" />
				${product.name }
			</div>
			<div class="detProductInfBlock">
				<fmt:message key="product.genre" />
				${product.type }
			</div>
			<div class="detProductInfBlock">
				<fmt:message key="product.publisher" />
				${product.publisher }
			</div>
			<div class="detProductInfBlock">
				<fmt:message key="product.developer" />
				${product.developer }
			</div>
			<div class="detProductInfBlock">
				<fmt:message key="product.imprintyear" />
				${product.imprintYear }
			</div>
			<div class="detProductInfBlock">
				<fmt:message key="product.price" />
				${product.price }
				<fmt:message key="products.money" />
			</div>
		</div>
		<div style="clear: left"></div>
		<div id="detProductDescription">
			<fmt:message key="product.description" />
			${product.description}
		</div>
		<div id="detBuy">
			<input name="action" type="hidden" value="add_to_shopping_cart" /> <input
				name="product_id" type="hidden" value="${product.id }" /> <input
				class="buyButton" type="submit"
				value="<fmt:message key="products.buy" />" />
		</div>
	</form>
	<c:if test="${sessionScope.user.accessLevel == 2 }">
		<div style="width: 120px; padding-left: 41%; margin-top: 20px">
			<form method="POST" action="Controller">
				<input name="action" type="hidden" value="delete_product" /> <input
					name="product_id" type="hidden" value="${product.id }" /> <input
					class="buyButton" type="submit"
					value="<fmt:message key="product.delete" />" />
			</form>
		</div>
		<div style="width: 120px; padding-left: 41%; margin-top: 20px">
			<form method="POST" action="Controller">
				<input name="action" type="hidden" value="edit_product_page" /> <input
					name="product_id" type="hidden" value="${product.id }" /> <input
					class="buyButton" type="submit"
					value="<fmt:message key="product.edit" />" />
			</form>
		</div>
	</c:if>
	<div style="clear: left"></div>
</body>
</html>