<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>User Management Application</title>
	<link rel="stylesheet" type="text/css" href="CSS/main.css" />
</head>
<body>
	<center>
		<h1>User Management</h1>
        <h2>
        	<a href="ShowNewFormServlet">Add New User</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="ListServlet">List All Users</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="LogoutServlet">Logout</a>
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Photo</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th>Actions</th>
                <th>Upload photo</th>
            </tr>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><img src="../metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MyProject/upload/images/<c:out value='${user.id}' />.jpg" style="weight:100px; height:100px;"></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.country}" /></td>
                    <td>
                    	<a href="ShowEditFormServlet?id=<c:out value='${user.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="DeleteServlet?id=<c:out value='${user.id}' />">Delete</a>
                   	</td>
                   	<td>
                    	<form action="UploadPhotoServlet" method="post" enctype="multipart/form-data"><input name="file" type="file" size="20"><input type="hidden" name="id" value="<c:out value='${user.id}' />"><input type="submit" value="upload" /></form>         	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>