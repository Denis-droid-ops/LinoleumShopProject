<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 05.06.2023
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order layout</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container-fluid">
    <h1 align="center">Ordering</h1>
    <div class="row g-2 pt-4">
        <div class="col-lg-4  ml-5">
          <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="${sessionScope.orderLinoleum.imagePath}" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Choosed linoleum:</h5>
                <h5 class="card-title">${sessionScope.orderLinoleum.name}</h5>
                <p class="card-text">Protect: ${sessionScope.orderLinoleum.protect}</p>
                <p class="card-text">Thickness: ${sessionScope.orderLinoleum.thickness}</p>
                <h5 class="card-title">Price: ${sessionScope.orderLinoleum.price} P/m2</h5>
             <a href="/?another=true" class="btn btn-outline-primary">Choose another</a>
            </div>
          </div>
            <br>
            <a href="/?cancelOrder" class="btn btn-danger btn-lg">Cancel ordering</a>
        </div>

    <div class="col justify-content-around">
        <div class="card" style="width: 35rem;">
            <div class="card-body">
                <c:if test = "${empty sessionScope.withoutLayout}">
                <h5 class="card-title">Enter the data for search layout and sizes:</h5>
                <form action="/orderLayout" method="post">
                    <div class="form-group">
                        <label for="city" style="font-size:20px;" class="font-weight-bold">City/location</label>
                        <input type="text" class="form-control" id="city" name="city" placeholder="Enter city">

                    </div>
                    <div class="form-group">
                        <label for="street" style="font-size:20px;" class="font-weight-bold">Street</label>
                        <input type="text" class="form-control" id="street" name="street" placeholder="Enter street">
                    </div>
                    <div class="form-group">
                        <label for="homeNum" style="font-size:20px;" class="font-weight-bold">Home number</label>
                        <input type="text" class="form-control" id="homeNum" name="homeNum" placeholder="Enter home number">
                    </div>
                    <div class="form-group">
                        <label for="roomCount" style="font-size:20px;" class="font-weight-bold">Room count</label>
                        <input type="number" class="form-control" id="roomCount" name="roomCount" placeholder="Enter room count">
                    </div>
                    <div class="form-group">
                        <label for="layoutRowType" style="font-size:20px;" class="font-weight-bold">Choose layout row type</label>
                        <select class="form-select" id="layoutRowType" name="layoutRowType" aria-label="Default select example">
                            <option value="ONE_LINE">One line</option>
                            <option value="DIFFERENT">Different order</option>
                            <option value="ANGULAR">Angular</option>
                            <option value="OVER_ARCH">Over arch</option>
                            <option value="HOME">Home</option>
                        </select>

                    </div>

                    <button type="submit" class="btn btn-primary btn-lg">Find(enter) sizes</button>
                    <c:if test = "${not empty requestScope.errors}">
                        <div style="color: red">
                            <c:forEach var="error" items="${requestScope.errors}">
                                <span>${error.message}</span>
                            </c:forEach>
                        </div>
                        ${requestScope.errors.clear()}
                    </c:if>

                </form>
                <a href="/orderLayout?withoutLayout=1" class="btn btn-primary btn-lg">Enter sizes without layout</a>
                </c:if>

                <c:if test = "${not empty sessionScope.withoutLayout}">
                    <h5 class="card-title">Enter your sizes:</h5>
                    <form action="/orderLayout" method="post">
                        <input type="hidden" name="action" value="enterSize"/>
                        <div class="form-group">
                            <label for="width" style="font-size:20px;" class="font-weight-bold">Width</label>
                            <input type="number" step="0.5" class="form-control" id="width" name="width" placeholder="Enter width">
                        </div>
                        <div class="form-group">
                            <label for="length" style="font-size:20px;" class="font-weight-bold">Length</label>
                            <input type="number" step="0.01" class="form-control" id="length" name="length" placeholder="Enter length">
                        </div>

                        <button type="submit" class="btn btn-primary btn-lg">Enter sizes</button>
                        <c:if test = "${not empty requestScope.errors}">
                            <div style="color: red">
                                <c:forEach var="error" items="${requestScope.errors}">
                                    <span>${error.message}</span>
                                </c:forEach>
                            </div>
                        </c:if>
                    </form>
                  <c:if test = "${empty requestScope.errors}">

                    <c:forEach var="withoutLFragment" items="${sessionScope.withoutLFragments}">
                        <h5>Entered size:</h5>
                        <p>Width: ${withoutLFragment.fWidth} Length: ${withoutLFragment.fLength}</p>

                    </c:forEach>

                   <c:if test = "${not empty sessionScope.withoutLFragments}">
                       <form action="/orderLayout" method="post">
                        <input type="hidden" name="action" value="calculateCost"/>
                        <button type="submit" class="btn btn-primary btn-lg">Calculate cost</button>
                       </form>
                   </c:if>

                    <c:if test = "${not empty sessionScope.cost}">
                        <h5 class="card-title">Total cost(without delivery price - 400p): ${sessionScope.cost}</h5>
                    </c:if>

                  </c:if>

                </c:if>
            </div>
        </div>
        <br>
        <c:if test = "${not empty sessionScope.cost}">
            <form class="form-inline" action="/order" method="post">
                <h5 class="card-title">Choose transporting type:</h5>
                <div class="form-check ml-2">
                    <input class="form-check-input" type="radio" name="radioDefault" id="delivery" value="DELIVERY">
                    <label class="form-check-label" for="delivery">
                        Delivery
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input ml-1" type="radio" name="radioDefault" id="pickup" value="PICKUP" checked>
                    <label class="form-check-label" for="pickup">
                        Pickup
                    </label>
                    <button type="submit" class="btn btn-primary btn-lg ml-1">Continue ordering</button>
                </div>
            </form>

        </c:if>
     </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>