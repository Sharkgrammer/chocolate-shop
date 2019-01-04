<!DOCTYPE html>
<html>
    <head>
        <%@page import="java.util.List"%>
        <%@page import="data.chocolate"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@include file="css.jsp" %>
    </head>

    <body>
        <%@include file="header.jsp" %> 
        <div style="padding-bottom: 100px"></br></div>

        <div class="container-fluid" id='shop'>
            <c:if test="${options != 0}">
                <jsp:include page="/chocolateServlet?mode=4&filt=0" />
            </c:if>

            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded">

                    <div class="search-container">
                        <form action="shopServlet">
                            <div>
                                <input type="hidden" value="2" name="mode" id="mode">
                                <input type="text" placeholder="Search.." name="search" class="bar">
                                <button type="submit"><img src="images/search.png" width="15" height="15"/></button>
                            </div>
                        </form>
                    </div>

                    <header class="text-center">
                        <h3> Search Functions here later pls </h3>
                    </header>

                    </br>

                    <c:choose>
                        <c:when test="${listShop.size() != 0}">
                            <div class="row text-center" style="overflow: hidden;">
                                <c:forEach items="${listShop}" var="choco">
                                    <div class="col-sm-3 border border-dark rounded shop-margin">
                                        <a href="shopServlet?id=${choco.getId()}&mode=1" class="shop-link">
                                            <h4> ${choco.getName()} </h4> 
                                            <img src="${choco.getFirstImage()}" class="shop-norm-img"/>
                                            <p class="text-left">${choco.getDescription()}</p>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>    
                        <c:otherwise>
                            <h4>None found</h4>
                        </c:otherwise>
                    </c:choose>

                    </br>

                </div>

                <div class="col-sm-1"></div>

            </div>
        </div>

        <%@include file="modal.jsp" %>
        <%@include file="footer.jsp" %>

    </body>
</html>
