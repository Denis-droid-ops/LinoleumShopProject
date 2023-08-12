<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 02.08.2023
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding/Updating fragment</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container-fluid">
    <c:if test = "${param.updateFragment!=null}">
        <h1 align="center">Update fragment</h1>
    </c:if>
    <c:if test = "${param.updateFragment==null}">
        <h1 align="center">Adding fragment</h1>
    </c:if>
    <div class="col d-flex justify-content-center">
        <div class="card" style="width: 35rem;">
            <div class="card-body">
                <form action="/admin/fragments" method="post">
                    <c:if test = "${param.updateFragment!=null}">
                        <input type="hidden" name="action" value="updateFragment">
                        <input type="hidden" name="id" value="${requestScope.entityFragment.id}">
                    </c:if>
                    <div class="form-group">
                        <label for="fragmentType" style="font-size:20px;" class="font-weight-bold">Choose fragment type</label>
                        <select class="form-select" id="fragmentType" name="fragmentType" aria-label="Default select example">
                            <option value="KITCHEN" <c:if test="${requestScope.entityFragment.fType.name().equals('KITCHEN')}">
                                selected="selected"
                            </c:if>>Kitchen
                            </option>
                            <option value="CORRIDOR" <c:if test="${requestScope.entityFragment.fType.name().equals('CORRIDOR')}">
                                selected="selected"
                            </c:if>>Corridor
                            </option>
                            <option value="HALL" <c:if test="${requestScope.entityFragment.fType.name().equals('HALL')}">
                                selected="selected"
                            </c:if>>Hall
                            </option>
                            <option value="BEDROOM" <c:if test="${requestScope.entityFragment.fType.name().equals('BEDROOM')}">
                                selected="selected"
                            </c:if>>Bedroom
                            </option>
                            <option value="LIVING" <c:if test="${requestScope.entityFragment.fType.name().equals('LIVING')}">
                                selected="selected"
                            </c:if>>Living room
                            </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="width" style="font-size:20px;" class="font-weight-bold">Width</label>
                        <input type="number" step="0.5" class="form-control" id="width" name="width"  value="${requestScope.entityFragment.width}" placeholder="Enter width">
                    </div>
                    <div class="form-group">
                        <label for="length" style="font-size:20px;" class="font-weight-bold">Length</label>
                        <input type="number" step="0.01" class="form-control" id="length" name="length" value="${requestScope.entityFragment.length}" placeholder="Enter length">
                    </div>

                    <div class="form-group">
                        <label for="layoutNameId" style="font-size:20px;" class="font-weight-bold">Choose layout name for fragment</label>
                        <select class="form-select" id="layoutNameId" name="layoutNameId" aria-label="Default select example">
                            <c:forEach var="layoutName" items="${requestScope.layoutNames}">
                                <option <c:if test="${requestScope.entityFragment.layoutName.id==layoutName.id}">
                                        selected="selected"
                                        </c:if>
                                        value="${layoutName.id}">${layoutName.lnName.toLowerCase()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <c:if test = "${param.updateFragment!=null}">
                        <button type="submit" class="btn btn-primary btn-lg">Update fragment</button>
                    </c:if>
                    <c:if test = "${param.updateFragment==null}">
                        <button type="submit" class="btn btn-primary btn-lg">Add fragment</button>
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
