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

        <script>

            $j(document).ready(function () {
                document.getElementById("main").style = "visibility:visible;";
                document.getElementById("loading").style = "display:none";

            });

        </script>

        <div style="padding-bottom: 100px"></br></div>

        <div class="container-fluid" id='shop'>
            <c:if test="${options != 0}">
                <jsp:include page="/chocolateServlet?mode=4&filt=7" />
            </c:if>

            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded">
                    <jsp:include page="/shopServlet?mode=4" />

                    <div class="row">
                        <div class="col-sm-2">
                            <div class="filter-container">
                                <select id="shopType" class="shop-filt"></select>
                            </div>
                        </div>

                        <div class="col-sm-2">
                            <div class="filter-container">
                                <select id="shopProd" class="shop-filt"></select>
                            </div>
                        </div>

                        <div class="col-sm-2">
                            <div class="filter-container">
                                <select id="shopFlav" class="shop-filt"></select>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <%@include file="search.jsp" %> 
                        </div>
                        </br>
                    </div>

                    <script>
                        function linkLoad(link) {
                            window.open(link, "_self");
                        }

                        <c:choose>
                            <c:when test="${resultType == 0}">
                        document.getElementById("shopType").innerHTML = "<option>Type</option>";
                            </c:when>
                            <c:otherwise>
                        document.getElementById("shopType").innerHTML = "<option>${resultType}</option>";
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${resultProd == 0}">
                        document.getElementById("shopProd").innerHTML = "<option>Producer</option>";
                            </c:when>
                            <c:otherwise>
                        document.getElementById("shopProd").innerHTML = "<option>${resultProd}</option>";
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${resultFlav == 0}">
                        document.getElementById("shopFlav").innerHTML = "<option>Flavour</option>";
                            </c:when>
                            <c:otherwise>
                        document.getElementById("shopFlav").innerHTML = "<option>${resultFlav}</option>";
                            </c:otherwise>
                        </c:choose>

                        <c:forEach items="${listType}" var="type">
                        document.getElementById("shopType").innerHTML += "<option onclick='linkLoad(&quot;shopServlet?mode=5&search=${type}&quot;);'>${type}</option>";
                        </c:forEach>

                        <c:forEach items="${listProd}" var="prod">
                        document.getElementById("shopProd").innerHTML += "<option onclick='linkLoad(&quot;shopServlet?mode=6&search=${prod}&quot;);'>${prod}</option>";
                        </c:forEach>

                        <c:forEach items="${listFlav}" var="flav">
                        document.getElementById("shopFlav").innerHTML += "<option onclick='linkLoad(&quot;shopServlet?mode=7&search=${flav}&quot;);'>${flav}</option>";
                        </c:forEach>

                    </script>

                    <%@include file="loading.jsp" %>

                    <div id='main' style='visibility:hidden;'>
                        <c:choose>
                            <c:when test="${listShop.size() != 0}">
                                <div class="row text-center" style="overflow: hidden;">
                                    <c:forEach items="${listShop}" var="choco">
                                        <div class='col-sm-4 shop-margin-top'>
                                            <div class="border border-dark rounded shop-margin">
                                                <a href="shopServlet?id=${choco.getId()}&mode=1" class="shop-link">
                                                    <h4> ${choco.getName()} </h4> 
                                                    <img src="${choco.getFirstImage()}" class="shop-norm-img"/>
                                                    <p class="text-left">${choco.getDescription()}</p>
                                                </a>
                                            </div>
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
                </div>

                <div class="col-sm-1"></div>

            </div>
        </div>

        <%@include file="modal.jsp" %>
        <%@include file="footer.jsp" %>

    </body>
</html>
