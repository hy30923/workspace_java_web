<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" 
url="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=CST" 
user="root" password="qaz1234567" />

<!--  
<sql:update dataSource="${db}" var="count">
insert into students values (154, "Nasreen", "jaha", 25)
</sql:update>
-->

<sql:query dataSource="${db}" var="rs">
select * from students where id=? or id=?;
<sql:param value=151 />
<sql:param value=152 />
</sql:query>

<table border="2" width="100%">
<tr>
<th>Student ID</th>
<th>First Name</th>
<th>Last Name</th>
<th>Age</th>
</tr>
<c:forEach var="table" items="${rs.rows}">
<tr align="center">
<td><c:out value="${table.id}" /></td>
<td><c:out value="${table.first_name}" /></td>
<td><c:out value="${table.last_name}" /></td>
<td><c:out value="${table.age}" /></td>
</tr>
</c:forEach>
</table>
</body>
</html>