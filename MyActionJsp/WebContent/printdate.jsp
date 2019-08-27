<%@page import="java.util.Date"%>
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
<%
try{

//	while(true){
		
		Date date = Calendar.getInstance().getTime();
		
		out.print("Today is: " + date + "\n");
		out.print(request.getParameter("name"));
//	}
}

catch(Exception e){
	
}

%>
</body>
</html>