<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 29.07.2023
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Layouts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <h1 align="center">Layouts</h1>
    <br>
    <a href="/admin/layouts?addLayout" class="btn btn-primary btn-lg">Add layout</a>
    <br>
    <table class="table table-bordered table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">Layout id</th>
            <th scope="col">City</th>
            <th scope="col">Street</th>
            <th scope="col">Home number</th>
            <th scope="col">Room count</th>
            <th scope="col">Row type</th>
            <th scope="col">Layout type</th>
            <th scope="col">Layout name</th>

        </tr>
        </thead>
        <tbody>
          <c:forEach var="layout" items="${requestScope.layouts}">
            <tr>
                <td>
                        ${layout.id}
                </td>
                <td>
                        ${layout.city}
                </td>
                <td>
                        ${layout.street}
                </td>
                <td>
                        ${layout.homeNum}
                </td>
                <td>
                        ${layout.roomCount}
                </td>
                <td>
                        ${layout.layoutRowType}
                </td>
                <td>
                    <form action="/admin/layouts" method="post">
                        <input type="hidden" name="action" value="updateLType"/>
                        <input type="hidden" name="id" value="${layout.id}"/>
                    <select name="lType" id="lType">

                        <c:if test = "${layout.lType=='TEMPLATE'}">
                            <option selected="true" value="TEMPLATE">TEMPLATE</option>
                            <option value="CUSTOM">CUSTOM</option>
                        </c:if>
                        <c:if test = "${layout.lType=='CUSTOM'}">
                            <option value="TEMPLATE">TEMPLATE</option>
                            <option selected="true" value="CUSTOM">CUSTOM</option>
                        </c:if>

                    </select>
                    <button type="submit" onclick="demo()" class="btn btn-primary btn-sm">Change layout type</button>
                    <script>
                        function demo() {
                            alert("Change layout type?");
                        }
                    </script>
                    </form>
                </td>
                <td>
                        ${layout.layoutName.lnName}
                </td>
                <td>
                   <form action="/admin/layouts" method="post">
                      <input type="hidden" name="id" value="${layout.id}"/>
                      <input type="hidden" name="action" value="deleteLayout"/>
                      <button type="submit" class="btn btn-danger btn-sm">Delete layout</button>
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
