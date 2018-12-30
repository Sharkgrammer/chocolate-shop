<html>
    <head>
        <%@page import="java.util.List"%>
        <%@page import="data.chocolate"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@include file="css.jsp" %>

        <script>
            function updateScreen() {
                document.getElementById('total').innerHTML = "Total cost: &euro;" + Math.round((${chocoShop.getPrice()} * document.getElementById('count').value) * 100) / 100;
            }

            function sendToCart() {
                window.open("purchaseServlet?mode=1&id=${chocoShop.getId()}&amt=" + Math.round(document.getElementById('count').value, 0), "_self");
            }
        </script>

    </head>

    <body>
        <%@include file="header.jsp" %> 
        <div style="padding-bottom: 100px"></br></div>

        <div class="container-fluid">

            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded "> 

                    <div class="container-fluid" id='shop'>

                        <div class="row">

                            <div class="col-sm-6" ><%@include file="carouselShopItem.jsp" %></div>

                            <div class="col-sm-6" >
                                <div class="text-center">
                                    <h1>${result}</h1>
                                    <h1>${chocoShop.getName()}</h1>
                                </div> 

                                <div class="text-left">
                                    <h3>${chocoShop.getDescription()}</h3>
                                </div> 

                                </br>

                                <div class="border border-dark rounded shop-box">
                                    <h3 class="text-center">Chocolate Details</h3>
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="text-left col-sm-6">
                                                <h5>Type: ${chocoShop.getType()}</h5>
                                                <h5>Flavour: ${chocoShop.getFlavour()}</h5>
                                            </div>
                                            <div class="text-right col-sm-6">
                                                <h5>Producer: ${chocoShop.getProducer()}</h5>
                                                <h5>Weight: ${chocoShop.getWeight()} grams</h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="border border-dark rounded shop-box">
                                    <div class="container-fluid">
                                        <div class="row text-center">
                                            <c:if test="${userid != 0}">
                                                <div class="col-sm-5">

                                                    <button class="shop-but" onClick="modalshop.style.display = 'block';">Buy ${chocoShop.getName()}</button>  
                                                </div>
                                                <div class="col-sm-2">
                                                    <input id="count" type="number" onKeyUp="updateScreen()" onclick="updateScreen()" required name="price" value="1" class="shop-counter"/>
                                                </div>
                                            </c:if>   
                                            <div class="col-sm-5">
                                                <p>Cost Per Unit: &euro;${chocoShop.getPrice()}</p>
                                                <span id="total">Total Cost: &euro;${chocoShop.getPrice()}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>

                    </div></br>

                    <c:if test="${chocoShop.getReviewsCount() != 0}">
                        <header class="text-center">
                            <h3> Chocolate Reviews </h3>
                        </header>
                        <div class="row text-center" style="overflow: hidden;">
                            <c:forEach items="${chocoShop.getReviews()}" var="review">
                                <div class="col-sm-3 border border-dark rounded shop-margin">
                                    <h2> ${review.getTitle()} (${review.isLiked()})</h2>
                                    <h3> ${review.getUser()} </h3>
                                    <h4> ${review.getDate()} </h4> 
                                    <p class="text-left">${review.getData()}</p>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>

                </div>

                <div class="col-sm-1"></div>

            </div>
        </div>

        <%@include file="modal.jsp" %>
        <%@include file="footer.jsp" %>

        <script>
            updateScreen();
        </script>
    </body>
</html>
