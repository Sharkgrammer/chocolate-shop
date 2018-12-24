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

                    <div class="col-sm-2" >
                        <a href="shopServlet?id=${review.getChocoID()}" class="shop-link">

                            <div class="center text-center">
                                <h3>${review.getTitle()} (${review.isLiked()})</h3>
                                <h5>By: <b>${review.getUser()}</b> on <b>${review.getDate()}</b></h5>
                            </div>

                            <p>${review.getData()}</p>

                        </a>
                    </div>

                </c:forEach>

            </div>

        </div>

        <div class="col-sm-1"></div>


    </div>
</div>