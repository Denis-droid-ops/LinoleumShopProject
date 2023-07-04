<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 07.06.2023
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order fragments</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp"%>
<div class="container-fluid">
    <h1 align="center">Ordering </h1>
    <div class="row g-2 pt-4">
        <div class="col ml-5">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="${sessionScope.orderLinoleum.imagePath}" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">Choosed linoleum:</h5>
                    <h5 class="card-title">${sessionScope.orderLinoleum.name}</h5>
                    <p class="card-text">Protect: ${sessionScope.orderLinoleum.protect}</p>
                    <p class="card-text">Thickness: ${sessionScope.orderLinoleum.thickness}</p>
                    <h5 class="card-title">Price: ${sessionScope.orderLinoleum.price} P/m2</h5>
                    <c:if test = "${empty sessionScope.cost}">
                     <a href="/?another=true" class="btn btn-outline-primary">Choose another</a>
                    </c:if>
                </div>
            </div>
            <br>
            <form class="form justify-content-center" action="/cancelOrderServlet" method="post">
                <button type="submit" class="btn btn-danger btn-lg">Cancel ordering</button>
            </form>
        </div>
       <c:if test = "${not empty sessionScope.layoutDto}">
        <div class="col">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Entered address(layout):</h5>
                    <p class="card-title">City: ${sessionScope.layoutDto.city}</p>
                    <p class="card-text">Street: ${sessionScope.layoutDto.street}</p>
                    <p class="card-text">Home number: ${sessionScope.layoutDto.homeNum}</p>
                    <p class="card-title">Room count: ${sessionScope.layoutDto.roomCount}</p>
                    <p class="card-title">Layout row type: ${sessionScope.layoutDto.layoutRowType}</p>
                    <c:if test = "${empty sessionScope.cost}">
                        <a href="/orderLayout?another=true" class="btn btn-outline-primary">Choose another</a>
                    </c:if>
                </div>
            </div>
        </div>
       </c:if>

      <c:if test = "${empty sessionScope.layoutDto}">
        <div class="col">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Entered address(layout):</h5>
                    <p class="card-title">City: ${sessionScope.createLayoutDto.city}</p>
                    <p class="card-text">Street: ${sessionScope.createLayoutDto.street}</p>
                    <p class="card-text">Home number: ${sessionScope.createLayoutDto.homeNum}</p>
                    <p class="card-title">Room count: ${sessionScope.createLayoutDto.roomCount}</p>
                    <p class="card-title">Layout row type: ${sessionScope.createLayoutDto.layoutRowType}</p>
                    <c:if test = "${empty sessionScope.cost}">
                     <a href="/orderLayout?another=true" class="btn btn-outline-primary">Choose another</a>
                    </c:if>
                </div>
            </div>
        </div>
      </c:if>

        <div class="col mr-5">
            <div class="card" style="width: 25rem;">
                <div class="card-body">
                 <c:if test = "${empty sessionScope.customLayout}">
                  <c:if test = "${empty sessionScope.cost}">
                    <form action="/orderFragments" method="post">
                        <h3 align="center">Choose the fragments</h3>

                        <c:if test = "${not empty sessionScope.layoutFragments}">
                         <c:forEach var="fragment" items="${sessionScope.layoutFragments}">
                         <div class="form-check">
                             <input class="form-check-input" type="checkbox" name="fragmentId" value="${fragment.id}" id="flexCheckDefault">
                             <label class="form-check-label" for="flexCheckDefault" style="font-size:20px;">
                                Width: ${fragment.width} Length: ${fragment.length} (${fragment.fType})
                             </label>

                         </div>
                         </c:forEach>
                        </c:if>

                        <button type="submit" class="btn btn-primary btn-lg">Calculate cost</button>
                        <c:if test = "${param.checkError!=null}">
                            <div style="color:red">
                                <span>You should choose at least one fragment!</span>
                            </div>
                        </c:if>
                    </form>
                  </c:if>

                    <c:if test = "${not empty sessionScope.cost}">
                        <h5 class="card-title">Choosed fragments(sizes):</h5>
                         <c:forEach var="chFragment" items="${sessionScope.choosedFragments}">
                            <p class="card-text font-weight-bold">Fragment type: ${chFragment.fType}</p>
                            <p class="card-text">Width: ${chFragment.width}</p>
                            <p class="card-text">Length: ${chFragment.length}</p>

                         </c:forEach>
                        <h5 class="card-title">Total cost(without delivery price - 400p): ${sessionScope.cost}</h5>

                    </c:if>
                 </c:if>


                    <c:if test = "${not empty sessionScope.customLayout}">
                        <h5 class="card-title">Enter your sizes:</h5>
                        <form action="/orderFragments" method="post">
                            <input type="hidden" name="action" value="enterSize"/>
                            <div class="form-group">
                                <label for="fragmentType" style="font-size:20px;" class="font-weight-bold">Choose fragment type</label>
                                <select class="form-select" id="fragmentType" name="fragmentType" aria-label="Default select example">
                                    <option value="KITCHEN">Kitchen</option>
                                    <option value="CORRIDOR">Corridor</option>
                                    <option value="HALL">Hall</option>
                                    <option value="BEDROOM">Bedroom</option>
                                    <option value="LIVING">Living room</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="width" style="font-size:20px;" class="font-weight-bold">Width</label>
                                <input type="number" step="0.5" class="form-control" id="width" name="width" placeholder="Enter width">
                            </div>
                            <div class="form-group">
                                <label for="length" style="font-size:20px;" class="font-weight-bold">Length</label>
                                <input type="number" step="0.01" class="form-control" id="length" name="length" placeholder="Enter length">
                            </div>

                            <button type="submit" class="btn btn-primary btn-lg">Enter sizes</button>

                        </form>

                        <c:forEach var="customLFragment" items="${sessionScope.customLFragments}">
                            <h5>Entered fragments:</h5>
                            <p>Width: ${customLFragment.width} Length: ${customLFragment.length} Fragment type: ${customLFragment.fType}</p>

                        </c:forEach>

                        <c:if test = "${not empty sessionScope.customLFragments}">
                            <form action="/orderFragments" method="post">
                                <input type="hidden" name="action" value="calculateCost"/>
                                <button type="submit" class="btn btn-primary btn-lg">Calculate cost</button>
                            </form>
                        </c:if>

                        <c:if test = "${not empty sessionScope.cost}">
                            <h5 class="card-title">Total cost(without delivery price - 400p): ${sessionScope.cost}</h5>
                        </c:if>
                    </c:if>

                </div>
            </div>
           <c:if test = "${not empty sessionScope.cost}">
               <form class="form" action="/order" method="post">
                   <h5 class="card-title">Choose transporting type:</h5>
                   <div class="form-check">
                       <input class="form-check-input" type="radio" name="radioDefault" id="delivery" value="DELIVERY">
                       <label class="form-check-label" for="delivery">Delivery</label>
                   </div>
                   <div class="form-check">
                       <input class="form-check-input" type="radio" name="radioDefault" id="pickup" value="PICKUP" checked>
                       <label class="form-check-label" for="pickup">Pickup</label>
                   </div>
                   <button type="submit" class="btn btn-primary btn-lg">Continue ordering</button>
               </form>
           </c:if>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
