<%-- 
    Document   : index
    Created on : 22.4.2012, 10:20:45
    Author     : xbogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listed numbers</title>
    </head>
    <body>
        <h1>Listed numbers:</h1>
        <c:forEach items="${listHolder.getList()}" var="i">
                <c:out value="${i} "/>
        </c:forEach>
        <br />
        <a href="${pageContext.request.contextPath}/add.jsp">Add more</a>
    </body>
</html>
