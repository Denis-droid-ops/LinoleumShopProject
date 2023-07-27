<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 18.07.2023
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Roll cutting</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container-fluid">
    <h1 align="center">Roll is cutted!</h1>
    <div class="row g-2 pt-4">
        <div class="col ml-5">
            <div class="card" style="width: 40rem;">
                <div class="card-body">
                    <h5 class="card-title">Cut from:</h5>
                    <c:forEach var="roll" items="${requestScope.rollDtos}">
                        <p class="card-text font-weight-bold">Roll id: ${roll.id}</p>
                        <p class="card-text">Part num: ${roll.partNum}</p>
                        <p class="card-text">Roll model: ${roll.linoleum}</p>
                        <p class="card-text">Is remain: ${roll.remain}</p>
                        <p class="card-text">Roll remain width: ${roll.rWidth}</p>
                        <p class="card-text">Roll remain length: ${roll.rLength}</p>

                    </c:forEach>

                </div>
            </div>
            <br>
        </div>

        <div class="col ml-5">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Cut fragments:</h5>
                    <c:if test = "${not empty requestScope.fragments}">
                    <c:forEach var="fragment" items="${requestScope.fragments}">
                        <p class="card-text">Fragment width: ${fragment.width}</p>
                        <p class="card-text">Fragment length: ${fragment.length}</p>
                        <hr style="border-width: 3px;">
                    </c:forEach>
                    </c:if>

                    <c:if test = "${empty requestScope.fragments}">
                    <c:forEach var="fragmentWL" items="${requestScope.fragmentsWL}">
                        <p class="card-text">Fragment width: ${fragmentWL.fWidth}</p>
                        <p class="card-text">Fragment length: ${fragmentWL.fLength}</p>
                        <hr style="border-width: 3px;">
                    </c:forEach>
                    </c:if>

                </div>
            </div>
            <br>
            <a href="/" class="btn btn-primary btn-lg">Go home page</a>
            <br>
            <br>
            <a href="/admin/orders" class="btn btn-primary btn-lg">View all orders</a>
            <h5>Warning! If you close or refresh page data will lost</h5>
        </div>




    </div>
    <%@include file="footer.jsp"%>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
