<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 18.07.2023
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding roll</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container-fluid">
    <h1 align="center">Adding roll</h1>
    <div class="col d-flex justify-content-center">
        <div class="card" style="width: 35rem;">
            <div class="card-body">
                <form action="/admin/rolls" method="post">
                    <div class="form-group">
                        <label for="partNum" style="font-size:20px;" class="font-weight-bold">Part number</label>
                        <input type="number" class="form-control" id="partNum" name="partNum" placeholder="Enter part number">
                    </div>
                    <div class="form-group">
                        <label for="width" style="font-size:20px;" class="font-weight-bold">Width</label>
                        <input type="number" step="0.5" class="form-control" id="width" name="width" placeholder="Enter width">
                    </div>
                    <div class="form-group">
                        <label for="length" style="font-size:20px;" class="font-weight-bold">Length</label>
                        <input type="number" class="form-control" id="length" name="length" placeholder="Enter length">
                    </div>
                    <div class="form-group">
                        <label for="linoleum" style="font-size:20px;" class="font-weight-bold">Choose the linoleum model:</label>
                        <select class="form-select" id="linoleum" name="linoleum" aria-label="Default select example">
                            <c:forEach var="linoleum" items="${requestScope.linoleums}">
                                <option value="${linoleum.id}">"${linoleum.name}"</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Add roll</button>
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
