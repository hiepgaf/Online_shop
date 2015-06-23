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
		<fmt:message key="orders.title" />
	</div>
	<form method="POST" action="Controller">
		<c:forEach items="${orders}" var="order">
			<div id="order">
				<div id="orderId">${order.id }</div>
				<div id="cartPrice">${price }
					<fmt:message key="products.money" />
				</div>
				<div id="orderDate">${order.date }</div>
				<div id="orderStatus">${order.status }</div>
				<c:if test="${order.status == 'active' }">
					<div>
						<input name="action" type="hidden" value="cancel_order" /> <input
							name="order_id" type="hidden" value="${order.id }" /> <input
							id="cancelOrderButton" type="submit"
							value="<fmt:message key="order.cancel" />" />
					</div>
				</c:if>
			</div>
			<div style="clear: left"></div>
		</c:forEach>
	</form>
</body>
</html>