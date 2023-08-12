<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 05.08.2023
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding/Updating linoleum</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container-fluid">
    <c:if test = "${param.updateLinoleum!=null}">
        <h1 align="center">Update linoleum</h1>
    </c:if>
    <c:if test = "${param.updateLinoleum==null}">
        <h1 align="center">Adding linoleum</h1>
    </c:if>
    <div class="col d-flex justify-content-center">
        <div class="card" style="width: 35rem;">
            <div class="card-body">
                <form action="/admin/linoleumHandling" method="post" enctype="multipart/form-data">
                    <c:if test = "${param.updateLinoleum!=null}">
                        <input type="hidden" name="action" value="updateLinoleum">
                        <input type="hidden" name="id" value="${requestScope.entityLinoleum.id}">
                        <input type="hidden" name="imagePath" value="${requestScope.entityLinoleum.imagePath}">
                    </c:if>
                    <div class="form-group">
                        <label for="image" style="font-size:20px;" class="font-weight-bold">Load image for linoleum</label>
                        <input type="file" class="form-control" id="image" name="image" placeholder="Enter file"
                        <c:if test = "${param.updateLinoleum==null}">required </c:if>  >
                    </div>
                    <div class="form-group">
                        <label for="name" style="font-size:20px;" class="font-weight-bold">Name</label>
                        <input type="text" class="form-control" id="name" name="name"  value="${requestScope.entityLinoleum.name}" placeholder="Enter name" required>
                    </div>
                    <div class="form-group">
                        <label for="protect" style="font-size:20px;" class="font-weight-bold">Protect</label>
                        <input type="number" step="0.05" class="form-control" id="protect" name="protect"  value="${requestScope.entityLinoleum.protect}" placeholder="Enter protect" required>
                    </div>
                    <div class="form-group">
                        <label for="thickness" style="font-size:20px;" class="font-weight-bold">Thickness</label>
                        <input type="number" step="0.05" class="form-control" id="thickness" name="thickness" value="${requestScope.entityLinoleum.thickness}" placeholder="Enter thickness" required>
                    </div>
                    <div class="form-group">
                        <label for="price" style="font-size:20px;" class="font-weight-bold">Price</label>
                        <input type="number" step="1" class="form-control" id="price" name="price" value="${requestScope.entityLinoleum.price}" placeholder="Enter thickness" required>
                    </div>

                    <c:if test = "${param.updateLinoleum!=null}">
                        <button type="submit" class="btn btn-primary btn-lg">Update linoleum</button>
                    </c:if>
                    <c:if test = "${param.updateLinoleum==null}">
                        <button type="submit" class="btn btn-primary btn-lg">Add linoleum</button>
                    </c:if>

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
