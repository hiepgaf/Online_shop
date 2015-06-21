<%@ page isErrorPage="true" isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${sessionScope.locale == 'ru' or empty sessionScope.locale}">
	<fmt:setLocale value="ru_RU" scope="session" />
</c:if>
<c:if test="${sessionScope.locale == 'en'}">
	<fmt:setLocale value="en_US" scope="session" />
</c:if>
<fmt:setBundle basename="locale" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="title.error" /></title>
</head>
<body>
	<fmt:message key="${message}" />
</body>
</html>