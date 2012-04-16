<%-- 
    Document   : add contact
    Created on : 8.5.2011, 18:21:43
    Author     : xbogar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add contact</title>
    </head>
    <body>
        <h1>Add contact</h1>
       
        <c:if test="${not empty error}">
            <div style="border: solid 1px black; background-color: palegoldenrod; padding: 10px">
                <c:out value="${error}"/>
            </div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/WebContactManager" method="post">
        <table>
            <tr>
                <th>Name:</th>
                <td><input type="text" name="name" value="${contact.name}"/></td>
            </tr>
            <tr>
                <th>Phone Number:</th>
                <td><input type="text" name="phoneNumber" value="${contact.phoneNumber}"/></td>
            </tr>
            <tr>
                <th>Address:</th>
                <td><input type="text" name="address" value="${contact.address}"/></td>
            </tr>
            <tr>
                <th>Mail:</th>
                <td><input type="text" name="mail" value="${contact.mail}"/></td>
            </tr>
            <tr>
                <th>Notes:</th>
                <td><input type="text" name="others" value="${contact.others}"/></td>
            </tr>
        </table>    
        <input type="Submit" value="Add contact" />
        </form>    

    </body>
</html>

