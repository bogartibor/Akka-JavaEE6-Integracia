<%-- 
    Document   : list contacts
    Created on : 8.5.2011, 18:21:43
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
        <h1>Listed numbers:</h1></br>
        <c:forEach items="${listHolder.getList()}" var="i">
                <c:out value="${i}, "/>
        </c:forEach>
        </table>
        
        <a href="${pageContext.request.contextPath}/addnumber.jsp">Add more</a>
    </body>
</html>
