<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 04.08.2023
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Layout names</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <h1 align="center">Layout names</h1>
    <br>
    <a href="/admin/layoutNames?addLayoutName" class="btn btn-primary btn-lg">Add layout name</a>
    <br>
    <table class="table table-bordered table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">Layout name id</th>
            <th scope="col">Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="layoutName" items="${requestScope.layoutNames}">
            <tr>
                <td>
                        ${layoutName.id}
                </td>
                <td>
                        ${layoutName.lnName}
                </td>

                <td>
                    <form action="/admin/layoutNames" method="post">
                        <input type="hidden" name="id" value="${layoutName.id}"/>
                        <input type="hidden" name="action" value="deleteLayoutName"/>
                        <button type="submit" class="btn btn-danger btn-sm">Delete layout name</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</div>
<%@include file="footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
