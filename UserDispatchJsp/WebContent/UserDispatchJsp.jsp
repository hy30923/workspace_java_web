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

if(request.getParameter("gender").equals("male")){
	
	response.sendRedirect("https://www.pchome.com.tw");
}

else{
	
	response.sendRedirect("https://tw.yahoo.com");
}

%>
</body>
</html>