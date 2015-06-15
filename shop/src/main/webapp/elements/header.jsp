<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="header">
		<div>
			<form method="POST" action="Controller">
				<input id="searchField" type="text" value="Поиск по сайту" />
				<input id="searchButton" type="submit" value="" />
			</form>
		</div>
		<div>
			<form method="POST" action="Controller">
				<input id="enButton" type="submit" name="en" value="">
			</form>
			<form method="POST" action="Controller">
				<input id="ruButton" type="submit" name="ru" value="">
			</form>
		</div>
	</div>
</body>
</html>