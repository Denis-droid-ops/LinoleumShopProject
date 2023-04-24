<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 21.04.2023
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Linoleums</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container-fluid">
    <h1>Linoleum shop catalog</h1>
    <div class="row g-2 pt-4">
        <div class="col-sm-2" style="background-color:yellow;height:800px;"></div>
        <div class="col-sm-10">

            <div class="row g-2 pt-4">
                <c:forEach var="lin" items="${requestScope.linoleums}">
                <div class="col-sm-4 pt-3" style="background-color:blue;">
                    <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="${lin.imagePath}" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${lin.name}</h5>
                            <p class="card-text">Protect: ${lin.protect}</p>
                            <p class="card-text">Thickness: ${lin.thickness}</p>
                            <h5 class="card-title">Price: ${lin.price} P/m2</h5>
                            <a href="#" class="btn btn-primary">Buy</a>
                        </div>
                    </div>
                </div>
                </c:forEach>
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

