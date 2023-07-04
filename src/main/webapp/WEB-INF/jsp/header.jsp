<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 11.04.2023
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/headerStyle.css"/>
</head>
<nav class="navbar fixed-top navbar-expand-lg navbar navbar-light" style="background-color:#180041;">
    <a class="navbar-brand text-white">Linoleum shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link active text-white" href="/">Home <span class="sr-only">(current)</span></a>
            <c:if test = "${sessionScope.user.role.name().equals('ADMIN')}">
                <a class="nav-item nav-link text-white" href="${pageContext.request.contextPath}/admin/users">All users</a>
                <a class="nav-item nav-link text-white" href="${pageContext.request.contextPath}/admin/orders">All orders</a>
            </c:if>
            <a class="nav-item nav-link text-white" href="#">
                Pricing
            </a>
        </div>
    </div>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <c:if test = "${empty sessionScope.user}">
            <a class="btn btn-outline-primary btn-lg text-white" href="${pageContext.request.contextPath}/registration">Registration</a>
            <a class="btn btn-outline-primary btn-lg text-white" href="${pageContext.request.contextPath}/login">Login</a>
            </c:if>
            <c:if test = "${not empty sessionScope.user}">
                <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/logout" method="post">
                    <h5 class="text-white">User: ${sessionScope.user.name}    </h5>
                    <button class="btn btn-outline-primary btn-lg text-white" type="submit">Logout</button>
                </form>
            </c:if>
        </li>
    </ul>
</nav>
