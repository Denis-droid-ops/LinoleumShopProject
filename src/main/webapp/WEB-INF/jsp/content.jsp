<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 11.04.2023
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
  <%@include file="header.jsp"%>
  <div class="container">
      <h1 align="center">All users</h1>
      <table class="table table-bordered table-hover">
           <thead class="thead-light">
              <tr>
                  <th scope="col">Name</th>
                  <th scope="col">Email</th>
                  <th scope="col">Phone number</th>
                  <th scope="col">Role</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="user" items="${requestScope.users}">
                  <tr>
                      <form action="/users" method="post">
                          <input type="hidden" name="id" value="${user.id}"/>

                      <td>

                          ${user.name}
                      </td>
                      <td>

                              ${user.email}
                      </td>
                      <td>

                              ${user.phoneNumber}
                      </td>
                      <td>
                          <select name="role" id="role">
                              <c:set var="role" value="${user.role}"/>
                              <c:if test = "${role=='USER'}">
                                  <option selected="true" value="USER">USER</option>
                                  <option value="ADMIN">ADMIN</option>
                              </c:if>
                              <c:if test = "${role=='ADMIN'}">
                                  <option value="USER">USER</option>
                                  <option selected="true" value="ADMIN">ADMIN</option>
                              </c:if>

                          </select>
                              <button type="submit" onclick="demo()" class="btn btn-primary">Change role</button>
                              <script>
                                 function demo() {
                                  alert("Role changed sucess!");
                                 }
                              </script>
                      </td>
                      </form>
                  </tr>
              </c:forEach>
              </tbody>
      </table>
      <button type=button class="btn btn-danger">Delete account</button>

  </div>
  <%@include file="footer.jsp"%>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
