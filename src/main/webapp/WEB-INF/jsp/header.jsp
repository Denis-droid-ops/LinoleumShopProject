<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 11.04.2023
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/headerStyle.css"/>
</head>
<nav class="navbar fixed-top navbar-expand-lg navbar navbar-light" style="background-color:#180041;">
    <a class="navbar-brand text-white">Linoleum shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link active text-white" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
            <c:if test = "${sessionScope.user.role.name().equals('ADMIN')}">
                <a class="nav-item nav-link text-white" href="${pageContext.request.contextPath}/admin/users">Users</a>
                <a class="nav-item nav-link text-white" href="${pageContext.request.contextPath}/admin/orders">All orders</a>
                <a class="nav-item nav-link text-white" href="${pageContext.request.contextPath}/admin/rolls">Stock</a>
                <a class="nav-item nav-link text-white" href="${pageContext.request.contextPath}/admin/layouts">Layouts</a>
                <a class="nav-item nav-link text-white" href="${pageContext.request.contextPath}/admin/layoutNames">Layout names</a>
                <a class="nav-item nav-link text-white" href="${pageContext.request.contextPath}/admin/fragments">Fragments</a>
                <a class="nav-item nav-link text-white" href="${pageContext.request.contextPath}/admin/linoleumHandling">Linoleums</a>

            </c:if>
            <c:if test = "${sessionScope.user.role.name().equals('USER')}">
                <a class="nav-item nav-link text-white" href="${pageContext.request.contextPath}/orders">User orders</a>
            </c:if>

        </div>
    </div>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <c:if test = "${empty sessionScope.user}">
            <a class="btn btn-outline-primary btn-lg text-white" href="${pageContext.request.contextPath}/registration">Registration</a>
            <a class="btn btn-outline-primary btn-lg text-white" href="${pageContext.request.contextPath}/login">Login</a>
            </c:if>

            <c:if test = "${not empty sessionScope.user}">
            <div class="row">

                <c:if test="${not empty pageContext.response.getHeader('Set-Cookie').contains('orderPage=/orderLayout') || not empty cookie['orderPage']}">

                    <form class="form-inline my-2 my-lg-0 mr-3" action="${pageContext.request.contextPath}/backToOrder" method="post">
                        <button class="btn btn-outline-primary btn-lg text-white ml-2" type="submit">Back to order</button>
                    </form>
                </c:if>


                <form class="form-inline my-2 my-lg-0 mr-3" action="${pageContext.request.contextPath}/logout" method="post">
                    <h5 class="text-white">User: ${sessionScope.user.name}    </h5>
                    <button class="btn btn-outline-primary btn-lg text-white ml-2" type="submit">Logout</button>
                </form>

            </div>
            </c:if>


        </li>
    </ul>
</nav>
