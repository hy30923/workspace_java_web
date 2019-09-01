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
<div align="center">
	<center>
		<h1>User List Login</h1>
	</center>
	<form action="LoginServlet" method="post" style="display:inline;">
			Account:<input type="text" name="account" maxlength="20" autocomplete="off" style="width: 250px;" /><br/>
			Password:<input type="password" name="password" maxlength="20" autocomplete="off" style="width: 250px;" /><br/>
			Verify number:<input type="text" name="number" maxlength="4" autocomplete="off" onkeyup="value=value.replace(/[^\d]/g,'')" style="width: 100px;"/>
			<input type="hidden" id="correct_number" name="correct_number"><div id="verifyNumber"></div>
			<script src="verifyGenerator.js"></script>
			<div></div><input type="submit" value="Login" />
	</form>
	<form action="ShowRegisterFormServlet" style="display:inline;"><input type="submit" value="Register" /></form>
</div>
</body>
</html>