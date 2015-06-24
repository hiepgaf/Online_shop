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
		<fmt:message key="editproduct.title" />
	</div>
	<div style="width: 300px; padding-left: 30%; padding-top: 50px">
		<c:if test="${not empty message }">
			<div style="margin-bottom: 10px; color: red; font-size: 18px">
				<fmt:message key="${message }" />
			</div>
		</c:if>
		<form method="POST" action="Controller">
			<div>
				<input name="action" type="hidden" value="edit_product" />
			</div>
			<div>
				<input name="product_id" type="hidden" value="${product.id }" />
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<fmt:message key="product.genre" />
				:
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 12px">
				<select name="type"><c:forEach items="${types}" var="type">
						<option>${type }</option>
					</c:forEach></select>
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<fmt:message key="product.name" />
				:
			</div>
			<div>
				<input id="regInput" type="text" name="name" value="${product.name }" />
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<fmt:message key="product.price" />
				,
				<fmt:message key="products.money" />
				:
			</div>
			<div>
				<input id="regInput" type="text" name="price" value="${product.price }" />
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<fmt:message key="product.description" />
				:
			</div>
			<div>
				<textarea rows="10" cols="34" name="description">${product.description }</textarea>
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<fmt:message key="product.image" />
				:
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 12px">
				<select name="picture"><c:forEach items="${picturePath}"
						var="path">
						<option>${path }</option>
					</c:forEach></select>
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<fmt:message key="product.publisher" />
				:
			</div>
			<div>
				<input id="regInput" type="text" name="publisher" value="${product.publisher }" />
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<fmt:message key="product.developer" />
				:
			</div>
			<div>
				<input id="regInput" type="text" name="developer" value="${product.developer }" />
			</div>
			<div style="font-size: 18px; margin-top: 12px; margin-bottom: 5px">
				<fmt:message key="product.imprintyear" />
				:
			</div>
			<div>
				<input id="regInput" type="text" name="imprintYear" value="${product.imprintYear }" />
			</div>
			<div style="margin-top: 16px; padding-bottom: 20px">
				<input id="regButton" type="submit"
					value="<fmt:message key="editproduct.button" />" />
			</div>
		</form>
	</div>
</body>
</html>