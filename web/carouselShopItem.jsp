<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page import="java.util.List"%>
<%@page import="data.chocolate"%> 

<div class="caramain rounded" style="padding:5px;">
    <div id="carousel" class="carousel slide center" data-ride="carousel">

        <div class="carousel-inner">

            <div class="carousel-item active">
                <img class="d-block shop-cara" src="${chocoShop.getFirstImage()}" alt="${chocoShop.getName()}">
            </div>


            <c:forEach items="${chocoImages}" var="image">

                <div class="carousel-item">
                    <img class="d-block shop-cara" style="width:100%" src="${image}" alt="${choco.getName()}">
                </div>

            </c:forEach>
        </div>

        <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>

    </div>
</div>
