<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div align="center">
            <h1>Hello: ${user}</h1>

            <table border="1">
                <form:form action="savephone" method="POST" modelAttribute="phone">
                    <tr>
                        <td>Contact name:</td>
                        <td><form:input path="username"/></td>
                    </tr>
                    <tr>
                        <td>Phone number:</td>
                        <td><form:input path="phone"/></td>
                    </tr>
                    <tr>

                        <td colspan="2" align="center">
                            <input type="submit" value="Add">
                        </td>
                    </tr>
                </form:form>
            </table>


            <table border="1">
                <th>No</th>
                <th>Username</th>
                <th>Phone</th>
                <th>Actions</th>

                <c:forEach var="phone" items="${phoneList}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${phone.username}</td>
                        <td>${phone.phone}</td>
                        <td>
                            <a href="editphone?id=${phone.id}">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="deletephone?id=${phone.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
