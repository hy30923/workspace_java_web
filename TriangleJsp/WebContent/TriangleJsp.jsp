<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String triangle = "*\n**\n***\n****\n*****";
String triangleJsp = "*</br>**</br>***</br>****</br>*****";

System.out.println(triangle);
out.print(triangleJsp);
%>
</body>
</html>