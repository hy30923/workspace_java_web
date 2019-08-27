<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="u" class="com.abc.User" scope="session" />
<jsp:setProperty property="*" name="u" />

Record:<br/>
<jsp:getProperty property="name" name="u" /><br/>
<jsp:getProperty property="password" name="u" /><br/>
<jsp:getProperty property="email" name="u" /><br/>
<a href="second.jsp">second.jsp</a>