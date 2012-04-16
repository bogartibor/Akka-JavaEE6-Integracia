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
        <title>Contacts</title>
    </head>
    <body>
        <h1>Contacts</h1>
        <table border="1px">
            <tr >
                <td><b>name</b></td>
                <td><b>phone number</b></td>
                <td><b>address</b></td>
                <td><b>mail</b></td>
                <td><b>others</b></td>
            </tr>
        <c:forEach items="${contacts}" var="contact">
            <tr>
                <td><c:out value="${contact.name}"/></td>
                <td><c:out value="${contact.phoneNumber}"/></td>
                <td><c:out value="${contact.address}"/></td>
                <td><c:out value="${contact.mail}"/></td>
                <td><c:out value="${contact.others}"/></td>
            </tr>    
        </c:forEach>
        </table>
        
        <a href="${pageContext.request.contextPath}/addContact.jsp">Add contact</a>
    </body>
</html>
