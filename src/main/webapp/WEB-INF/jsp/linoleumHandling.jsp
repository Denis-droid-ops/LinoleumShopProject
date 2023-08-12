<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 29.07.2023
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Linoleum handling</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <h1 align="center">Linoleum</h1>
    <a href="/admin/linoleumHandling?addLinoleum" class="btn btn-primary btn-lg mb-3">Add linoleum</a>

    <table class="table table-bordered table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">Linoleum id</th>
            <th scope="col">Name</th>
            <th scope="col">Protect</th>
            <th scope="col">Thickness</th>
            <th scope="col">Price </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="linoleum" items="${requestScope.linoleums}">
            <tr>
                <td>
                        ${linoleum.id}
                </td>
                <td>
                        ${linoleum.name}
                </td>
                <td>
                        ${linoleum.protect}
                </td>
                <td>
                        ${linoleum.thickness}
                </td>
                <td>
                        ${linoleum.price}
                </td>
                <td>
                    <a href="/admin/linoleumHandling?updateLinoleum&id=${linoleum.id}" class="btn btn-primary btn-sm">Update linoleum</a>
                </td>
                <td>
                    <form action="/admin/linoleumHandling" method="post">
                        <input type="hidden" name="id" value="${linoleum.id}"/>
                        <input type="hidden" name="action" value="deleteLinoleum"/>
                        <button type="submit" class="btn btn-danger btn-sm">Delete linoleum</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>


</div>
<%@include file="footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>

