<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 03.07.2023
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User orders</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container ml-5">
    <h1 align="center">All orders</h1>

    <table class="table table-bordered table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">Status</th>
            <th scope="col">Transporting</th>
            <th scope="col">Transporting date</th>
            <th scope="col">Cost</th>
            <th scope="col">Linoleum name</th>
            <th scope="col">Street</th>
            <th scope="col">Home number</th>
            <th scope="col">Apartment number</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${requestScope.userOrders}" >
            <tr>
                <td>
                        ${order.status}
                </td>
                <td>
                        ${order.transporting}
                </td>
                <td>
                        ${order.transportingDate}
                </td>
                <td>
                        ${order.cost}
                </td>

                <td>
                        ${order.linoleum.name}
                </td>

                <td>
                    <c:if test="${not empty order.layout}">
                        ${order.layout.street}
                    </c:if>
                    <c:if test="${not empty order.deliveryAddress}">
                        ${order.deliveryAddress.dStreet}
                    </c:if>
                </td>
                <td>
                    <c:if test="${not empty order.layout}">
                        ${order.layout.homeNum}
                    </c:if>
                    <c:if test="${not empty order.deliveryAddress}">
                        ${order.deliveryAddress.dHomeNum}
                    </c:if>
                </td>

                <td>
                        ${order.apartmentNum}
                </td>
                <td>
                    <form action="/orders" method="post">
                        <input type="hidden" name="id" value="${order.id}"/>
                        <button type="submit" class="btn btn-primary btn-sm">Order details</button>
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