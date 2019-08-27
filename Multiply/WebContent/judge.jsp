<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(Float.parseFloat(request.getParameter("thisAns")) == Float.parseFloat(request.getParameter("ans"))){
		
		out.print("Correct Answer!!");
}

else{
	
	throw new Exception("Wrong Answer!!");
}
%>
</body>
</html>