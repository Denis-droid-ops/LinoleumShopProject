<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 13.06.2023
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container-fluid">
    <h1 align="center">Ordering </h1>
    <div class="row g-2 pt-4">
        <div class="col ml-5">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="${sessionScope.orderLinoleum.imagePath}" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">Choosed linoleum:</h5>
                    <h5 class="card-title">${sessionScope.orderLinoleum.name}</h5>
                    <p class="card-text">Protect: ${sessionScope.orderLinoleum.protect}</p>
                    <p class="card-text">Thickness: ${sessionScope.orderLinoleum.thickness}</p>
                    <h5 class="card-title">Price: ${sessionScope.orderLinoleum.price} P/m2</h5>
                </div>
            </div>
            <br>
            <a href="/?cancelOrder" class="btn btn-danger btn-lg">Cancel ordering</a>
        </div>

        <div class="col">
            <c:if test = "${not empty sessionScope.layoutDto}">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Entered address(layout):</h5>
                        <p class="card-title">City: ${sessionScope.layoutDto.city}</p>
                        <p class="card-text">Street: ${sessionScope.layoutDto.street}</p>
                        <p class="card-text">Home number: ${sessionScope.layoutDto.homeNum}</p>
                        <p class="card-title">Room count: ${sessionScope.layoutDto.roomCount}</p>
                        <p class="card-title">Layout row type: ${sessionScope.layoutDto.layoutRowType}</p>

                    </div>
                </div>
            </c:if>

           <c:if test = "${empty sessionScope.layoutDto}">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Entered address(layout):</h5>
                    <p class="card-title">City: ${sessionScope.createLayoutDto.city}</p>
                    <p class="card-text">Street: ${sessionScope.createLayoutDto.street}</p>
                    <p class="card-text">Home number: ${sessionScope.createLayoutDto.homeNum}</p>
                    <p class="card-title">Room count: ${sessionScope.createLayoutDto.roomCount}</p>
                    <p class="card-title">Layout row type: ${sessionScope.createLayoutDto.layoutRowType}</p>

                </div>
            </div>
        </c:if>
            <c:if test = "${not empty sessionScope.choosedFragments}">
             <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Choosed fragments:</h5>
                    <c:forEach var="chFragment" items="${sessionScope.choosedFragments}">
                        <p class="card-text font-weight-bold">Fragment type: ${chFragment.fType}</p>
                        <p class="card-text">Width: ${chFragment.width}</p>
                        <p class="card-text">Length: ${chFragment.length}</p>
                    </c:forEach>
                </div>
             </div>
            </c:if>

            <c:if test = "${not empty sessionScope.withoutLFragments}">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Choosed fragments:</h5>
                        <c:forEach var="withoutLFragment" items="${sessionScope.withoutLFragments}">
                            <p class="card-text">Width: ${withoutLFragment.fWidth}</p>
                            <p class="card-text">Length: ${withoutLFragment.fLength}</p>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <c:if test = "${not empty sessionScope.customLFragments}">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Choosed fragments:</h5>
                        <c:forEach var="customLFragment" items="${sessionScope.customLFragments}">
                            <p class="card-text font-weight-bold">Fragment type: ${customLFragment.fType}</p>
                            <p class="card-text">Width: ${customLFragment.width}</p>
                            <p class="card-text">Length: ${customLFragment.length}</p>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>

        <div class="col mr-5">
            <div class="card" style="width: 25rem;">
                <div class="card-body">
                    <h5 class="card-title">Enter other details:</h5>

                    <form action="/order" method="post">
                        <input type="hidden" name="action" value="enterOrderDetails"/>
                     <c:if test = "${not empty sessionScope.withoutLFragments && sessionScope.transporting.equals('DELIVERY')}">
                        <div class="form-group">
                            <label for="city" style="font-size:20px;" class="font-weight-bold">City</label>
                            <input type="text" class="form-control" id="city" name="city" placeholder="Enter city">
                        </div>
                        <div class="form-group">
                            <label for="street" style="font-size:20px;" class="font-weight-bold">Street</label>
                            <input type="text" class="form-control" id="street" name="street" placeholder="Enter street">
                        </div>
                        <div class="form-group">
                            <label for="homeNum" style="font-size:20px;" class="font-weight-bold">Home number</label>
                            <input type="number" class="form-control" id="homeNum" name="homeNum" placeholder="Enter home number">
                        </div>
                     </c:if>

                        <div class="form-group">
                            <label for="transportingDate" style="font-size:20px;" class="font-weight-bold">Transporting date</label>
                            <input type="datetime-local" class="form-control" id="transportingDate" name="transportingDate" placeholder="Enter transporting date">
                        </div>
                       <c:if test = "${sessionScope.transporting.equals('DELIVERY')}">
                        <div class="form-group">
                            <label for="apartmentNum" style="font-size:20px;" class="font-weight-bold">Apartment number</label>
                            <input type="number" class="form-control" id="apartmentNum" name="apartmentNum" placeholder="Enter apartment number">
                        </div>
                       </c:if>
                        <c:if test = "${!sessionScope.transporting.equals('DELIVERY')}">
                            <div class="form-group">
                                <input type="hidden" name="apartmentNum" value="0"/>
                            </div>
                        </c:if>

                        <button type="submit" class="btn btn-primary btn-lg">Create order</button>
                        <c:if test = "${not empty requestScope.errors}">
                            <div style="color: red">
                                <c:forEach var="error" items="${requestScope.errors}">
                                    <span>${error.message}</span>
                                </c:forEach>
                            </div>
                        </c:if>
                    </form>

                </div>
            </div>

        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
