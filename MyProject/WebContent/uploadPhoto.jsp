<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jspsmart.upload.SmartUpload"%>
<%@	page import="com.jspsmart.upload.SmartUploadException"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload</title>
</head>
<body>
<%
SmartUpload smart = new SmartUpload();
smart.initialize(pageContext);
smart.upload();
smart.save("uploads");
String id = smart.getRequest().getParameter("id");
String name = smart.getFiles().getFile(0).getFileName();
%>
<jsp:forward page="UploadPhotoServlet">
    <jsp:param name="id" value="<%= id %>" />
    <jsp:param name="name" value="<%= name %>" />
</jsp:forward>
</body>
</html>