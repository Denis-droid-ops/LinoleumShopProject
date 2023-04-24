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
    <a class="navbar-brand text-white" href="#">Linoleum shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link active text-white" href="#">Home <span class="sr-only">(current)</span></a>
            <a class="nav-item nav-link text-white" href="#">Features</a>
            <a class="nav-item nav-link text-white" href="#">Pricing</a>
        </div>
    </div>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <button type="button" class="btn btn-outline-primary btn-lg text-white">Registration</button>
            <button type="button" class="btn btn-outline-primary btn-lg text-white">Login</button>
        </li>
    </ul>
</nav>
