<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 
int data = 50;

int cube(int n){

	return n * n * n;
}
%>

<%= "Value of the variable is: " + data %><br/>
<%= "Cube of 3: " + cube(3) %><br/>
<%= "Current Time: " + Calendar.getInstance().getTime() %><br/>
<%= "Welcome " + request.getParameter("uname") %><br/>

</body>
</html>