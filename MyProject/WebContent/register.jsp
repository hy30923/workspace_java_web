<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Account Register</title>
	<link rel="stylesheet" type="text/css" href="CSS/main.css" />
</head>
<body>
<div align="center">
	<center>
		<h1>Account Register</h1>
	</center>
	<form action="RegisterServlet" method="post">
	    Account:<input type="text" name="account" maxlength="20" autocomplete="off" style="width: 250px;" /><br/>
		Password:<input type="password" name="password" maxlength="20" autocomplete="off" style="width: 250px;" /><br/>
		Password Verify:<input type="password" name="password_verify" maxlength="20" autocomplete="off" style="width: 250px;" /><br/>
		Verify number:<input type="text" name="number" maxlength="4" autocomplete="off" onkeyup="value=value.replace(/[^\d]/g,'')" style="width: 100px;"/>		
   		<input type="hidden" id="correct_number" name="correct_number"><div id="verifyNumber"></div>
		<script src="verifyGenerator.js"></script>
  		<input type="submit" value="Register" />
    </form>	
</div>
</body>
</html>