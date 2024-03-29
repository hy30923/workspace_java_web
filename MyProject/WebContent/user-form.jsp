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
		<c:if test="${user != null}">
			<form action="UpdateUserServlet" method="post">
        </c:if>
        <c:if test="${user == null}">
			<form action="InsertServlet" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${user != null}">
            			Edit User
            		</c:if>
            		<c:if test="${user == null}">
            			Add New User
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${user != null}">
        			<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
        		</c:if>            
            <tr>
                <th>User Name: </th>
                <td>
                	<input type="text" name="name" size="45" autocomplete="off" 
                			value="<c:out value='${user.name}' />" required
                		/>
                </td>
            </tr>
            <tr>
                <th>User Email: </th>
                <td>
                	<input type="text" name="email" size="45" autocomplete="off" 
                			value="<c:out value='${user.email}' />" required
                	/>
                </td>
            </tr>
            <tr>
                <th>Country: </th>
                <td>
                	<input type="text" name="country" size="15" autocomplete="off"
                			value="<c:out value='${user.country}' />" required
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
