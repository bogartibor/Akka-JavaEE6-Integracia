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
        <title>Add number to list of numbers</title>
    </head>
    <body>
        <h1>Add number</h1>
       
        <c:if test="${not empty error}">
            <div style="border: solid 1px black; background-color: palegoldenrod; padding: 10px">
                <c:out value="${error}"/>
            </div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/WebServlet" method="post">
        <table>
            <tr>
                <th>Add number:</th>
                <td><input type="text" name="number" value="${number}"/></td>
            </tr>
            
        </table>    
        <input type="Submit" value="Add" />
        </form>    

    </body>
</html>

