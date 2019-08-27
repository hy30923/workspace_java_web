<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="judge.jsp">
<%
int num1 = (int) (Math.random() * 100);
int num2 = (int) (Math.random() * 100 + 1);
String op = "";

int ans = 0;

switch((int)(Math.random() * 4)){

	case 0:
		
		op = "+";
		ans = num1 + num2;
		break;
		
	case 1:
		
		op = "-";
		ans = num1 - num2;
		break;
		
	case 2:
		
		op = "*";
		ans = num1 * num2;
		break;
		
	case 3:
		
		op = "/";
		ans = num1 / num2;
		break;
		
	default:
		
		break;
}

out.print(num1 + " " + op + " " + num2 + " = ?");
out.print("<input type=\"hidden\" name=\"ans\" value=" + ans + ">");
%>
<br/><br/><input type="text" name="thisAns" autocomplete="off" /><br/><br/>
<input type="submit" value="Submit" />
</form>
</body>
</html>