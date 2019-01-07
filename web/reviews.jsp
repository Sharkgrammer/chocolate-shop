<jsp:include page="/reviewServlet?mode=2" />

<div class="container-fluid" id='reviews'>
    <div class="row">
        <div class="col-sm-1" ></div>

        <div class="col-sm-10 content border border-dark rounded ">

            <header class="text-center">
                <h3> New Reviews </h3>
            </header></br>

            <div class="row">

                <c:forEach items="${revList}" var="review">
                    <div class='col-sm-3 shop-margin-top'>
                        <a href="shopServlet?id=${review.getChocoID()}&mode=1" class="shop-link">
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
                        </a>
                    </div>
                </c:forEach>

            </div>

        </div>

        <div class="col-sm-1"></div>


    </div>
</div>