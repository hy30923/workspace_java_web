<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="CSS/main.css" />
</head>
<body>
<form action="LoginServlet" method="post">
	<div align="center">
		Account:<input type="text" name="account" maxlength="20" value="hy30923" autocomplete="off" style="width: 250px;" /><br/>
		Password:<input type="password" name="password" maxlength="20" value="admin123" autocomplete="off" style="width: 250px;" /><br/>
		Verify number:<input type="text" name="number" maxlength="4" autocomplete="off" onkeyup="value=value.replace(/[^\d]/g,'')" style="width: 100px;"/>
		<input type="hidden" id="correct_number" name="correct_number"><div id="verifyNumber"></div>
		<script src="verifyGenerator.js"></script>
		<input type="submit" value="Login" />
	</div>
</form>
</body>
</html>