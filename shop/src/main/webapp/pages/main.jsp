<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${sessionScope.locale == 'ru' or empty sessionScope.locale}">
	<fmt:setLocale value="ru_RU" />
</c:if>
<c:if test="${sessionScope.locale == 'en'}">
	<fmt:setLocale value="en_US" />
</c:if>
<fmt:setBundle basename="locale" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<title>Интернет-магазин</title>
</head>
<body>
	<div>
		<%@include file="/elements/header.jsp"%>
	</div>
	<div id="loginbar">
		<%@include file="/elements/login.jsp"%>
	</div>
	<div>
		<div id="navbar">
			<%@include file="/elements/menu.jsp"%>
		</div>
	</div>
</body>
</html>