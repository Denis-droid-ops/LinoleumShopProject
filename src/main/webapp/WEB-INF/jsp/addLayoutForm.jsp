<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 01.08.2023
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding layout</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container-fluid">
    <h1 align="center">Adding layout</h1>
    <div class="col d-flex justify-content-center">
        <div class="card" style="width: 35rem;">
            <div class="card-body">
                <form action="/admin/layouts" method="post">
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



                    <div class="form-group">
                        <label for="layoutNameId" style="font-size:20px;" class="font-weight-bold">Choose layout name</label>
                        <select class="form-select" id="layoutNameId" name="layoutNameId" aria-label="Default select example">
                            <c:forEach var="layout" items="${requestScope.layouts}">
                            <option value="${layout.layoutName.id}">${layout.layoutName.lnName.toLowerCase()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Add layout</button>
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
<%@include file="footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>