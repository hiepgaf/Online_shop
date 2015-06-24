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
		<fmt:message key="allorders.title" />
	</div>
	<div style="width: 120%; padding-left: 10%; padding-top: 30px">
		<table id="orderTable">
			<tr>
				<th><fmt:message key="user.id" /></th>
				<th><fmt:message key="user.login" /></th>
				<th><fmt:message key="user.email" /></th>
				<th><fmt:message key="user.accesslevel" /></th>
				<th><fmt:message key="user.blacklist" /></th>
				<th></th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id }</td>
					<td>${user.login }</td>
					<td>${user.email }</td>
					<td>${user.accessLevel }</td>
					<td>${user.blackListFlag }</td>
					<td><div style="width: 180px">
							<form method="POST" action="Controller">
								<input name="action" type="hidden" value="change_blocking" /> <input
									name="user_id" type="hidden" value="${user.id }" /> <input
									class="buyButton" type="submit"
									value="<fmt:message key="user.changeblocking.button" />" />
							</form>
						</div></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>