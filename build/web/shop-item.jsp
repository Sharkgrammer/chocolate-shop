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

            function showReview() {
                var isHidden = document.getElementById("reviewButton").style.display == "none";
                if (isHidden) {
                    document.getElementById("reviewButton").style = "display:inital";
                } else {
                    document.getElementById("reviewButton").style = "display:none";
                }
                //set user id + choco id + hide em
                document.getElementById("revCreateChoc").style = "display:none";
                document.getElementById("revCreateUser").style = "display:none";
                document.getElementById('revCreateChoc').readOnly = true;
                document.getElementById('revCreateUser').readOnly = true;
                document.getElementById("revCreateChoc").value = ${chocoShop.getId()};
                document.getElementById("revCreateUser").value = getCookie("id");
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

                            <div class="col-sm-6" style="padding:20px;"><%@include file="carouselShopItem.jsp" %></div>

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
                                                <div class="col-sm-5" >

                                                    <button class="shop-but" onClick="modalshop.style.display = 'block';">Buy ${chocoShop.getName()}</button>  
                                                </div>
                                                <div class="col-sm-2">
                                                    <input id="count" type="number" onKeyUp="updateScreen()" onclick="updateScreen()" min="0" required name="price" value="1" class="shop-counter"/>
                                                </div>
                                            </c:if>   
                                            <div class="col-sm-5">
                                                <p>Cost Per Unit: &euro;${chocoShop.getPrice()}</p>
                                                <span id="total">Total Cost: &euro;${chocoShop.getPrice()}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                            
                                <c:if test="${userid != 0}">
                                    <div class="border border-dark rounded shop-box">
                                        <div class="container-fluid">
                                            <div class="row text-center">
                                                <div class="col-sm-2"></br></div>
                                                <div class="col-sm-8">
                                                    <div style="height:50px;">
                                                        <button class="shop-but" onClick="showReview();">Review Chocolate</button>  
                                                    </div>
                                                    <div id="reviewButton" style="display:none">
                                                        <%@include file="reviewCreate.jsp" %>
                                                    </div>

                                                </div> 
                                                <div class="col-sm-2"></br></div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>  



                            </div>

                        </div>

                    </div></br>

                    <c:if test="${chocoShop.getReviewsCount() != 0}">
                        <header class="text-center">
                            <h3> Chocolate Reviews </h3>
                        </header>
                        <div class="row" style="overflow: hidden;">
                            <c:forEach items="${chocoShop.getReviews()}" var="review">
                                <div class='col-sm-3 shop-margin-top'>
                                    <div class="border border-dark rounded shop-margin-review">
                                        <h2 class='text-center'> ${review.getTitle()}</h2>
                                        <div class="row">
                                            <div class='col-sm-6'>
                                                <h3> ${review.getUser()} </h3>
                                                <h4> ${review.getDate()} </h4> 
                                            </div>

                                            <div class='col-sm-6'>
                                                <c:choose>
                                                    <c:when test="${review.isLiked()}">
                                                        <img class='img-review' src='images/thumbsUp.png'/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img class='img-review' src='images/thumbsDown.png'/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>

                                        </div>
                                        <p class="text-left">${review.getData()}</p>
                                    </div>
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
