<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 16.07.2023
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container-fluid">
    <h1 align="center">Order details </h1>
    <div class="row g-2 pt-4">
        <div class="col ml-5">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="${requestScope.order.linoleum.imagePath}" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">Linoleum:</h5>
                    <h5 class="card-title">${requestScope.order.linoleum.name}</h5>
                    <p class="card-text">Protect: ${requestScope.order.linoleum.protect}</p>
                    <p class="card-text">Thickness: ${requestScope.order.linoleum.thickness}</p>
                    <h5 class="card-title">Price: ${requestScope.order.linoleum.price} P/m2</h5>
                </div>
            </div>
            <br>
            <c:if test="${requestScope.order.status eq 'NOTCOMPLETED'}">
                <form action="/admin/orders" method="post">
                    <input type="hidden" name="action" value="deleteOrder"/>
                    <input type="hidden" name="id" value="${order.id}"/>
                    <button type="submit" class="btn btn-danger btn-lg">Delete order</button>
                </form>
            </c:if>
        </div>

        <div class="col">
                <div class="card" style="width: 22rem;">
                    <div class="card-body">
                        <h5 class="card-title">Details:</h5>
                        <p class="card-title">Creating date: ${requestScope.order.creatingDate}</p>
                        <p class="card-title">Status: ${requestScope.order.status}</p>
                        <p class="card-title">Transporting: ${requestScope.order.transporting}</p>
                        <p class="card-title">Transporting date: ${requestScope.order.transportingDate}</p>
                        <p class="card-title">Cost: ${requestScope.order.cost}</p>
                        <p class="card-title">Apartment num: ${requestScope.order.apartmentNum}</p>
                        <br>
                        <p class="card-title">User name: ${requestScope.order.user.name}</p>
                        <p class="card-title">User email: ${requestScope.order.user.email}</p>
                        <p class="card-title">User phone number: ${requestScope.order.user.phoneNumber}</p>
                        <br>
                        <c:if test = "${not empty requestScope.order.layout}">
                         <p class="card-title">City: ${requestScope.order.layout.city}</p>
                         <p class="card-title">Street: ${requestScope.order.layout.street}</p>
                         <p class="card-title">Home number: ${requestScope.order.layout.homeNum}</p>
                         <p class="card-title">Room count: ${requestScope.order.layout.roomCount}</p>
                         <p class="card-title">Layout row type: ${requestScope.order.layout.layoutRowType}</p>
                         <br>
                         <p class="card-title">Layout name: ${requestScope.order.layout.layoutName.lnName}</p>
                        </c:if>
                        <c:if test = "${not empty requestScope.order.deliveryAddress}">
                            <p class="card-title">City: ${requestScope.order.deliveryAddress.dCity}</p>
                            <p class="card-title">Street: ${requestScope.order.deliveryAddress.dStreet}</p>
                            <p class="card-title">Home number: ${requestScope.order.deliveryAddress.dHomeNum}</p>

                        </c:if>


                    </div>
                </div>
            <br>

        </div>

        <div class="col mr-5">
            <div class="card" style="width: 22rem;">
                <div class="card-body">
                    <h5 class="card-title">Fragments:</h5>
                    <c:if test = "${not empty requestScope.fragments}">
                     <c:forEach var="fragment" items="${requestScope.fragments}">
                         <p class="card-text font-weight-bold">Fragment type: ${fragment.fType}</p>
                         <p class="card-text">Width: ${fragment.width}</p>
                         <p class="card-text">Length: ${fragment.length}</p>

                      </c:forEach>
                    </c:if>
                    <c:if test = "${not empty requestScope.fragmentsWL}">
                        <c:forEach var="fragment" items="${requestScope.fragmentsWL}">
                            <p class="card-text">Width: ${fragment.fWidth}</p>
                            <p class="card-text">Length: ${fragment.fLength}</p>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <br>
            <a href="/" class="btn btn-primary btn-lg">Go home page</a>
            <br>
            <br>
            <a href="/admin/orders" class="btn btn-primary btn-lg">View all orders</a>
        </div>
    </div>
    <%@include file="footer.jsp"%>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>

