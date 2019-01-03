<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/adminServlet" />
        <%@page import="java.util.List"%>
        <%@page import="data.chocolate"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@include file="css.jsp" %>
        <script>
            window.onload = function () {
            <c:forEach items = "${output}" var = "graph" >
                var chart${graph.getId()} = new CanvasJS.Chart("${graph.getId()}", {
                    animationEnabled: true,
                    exportEnabled: true,
                    title: {
                        text: "${graph.getName()}"
                    },
                    axisX: {
                        title: "${graph.getxLabel()}"
                    },
                    axisY: {
                        title: "${graph.getyLabel()}"
                    },
                    data: [{
                            type: "${graph.getType()}",
                            indexLabelFontColor: "#5A5757",
                            indexLabelPlacement: "outside",
                            dataPoints: ${graph.getData()}
                        }]
                });
                chart${graph.getId()}.render();
            </c:forEach>
            }
        </script>


    </head>

    <body>
        <%@include file="header.jsp" %> 
        <div style="padding-bottom: 100px"></br></div>

        <div class="container-fluid" id='admin'>

            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded">
                    <h1 class="text-center">Database Forms</h1>
                    <div class="row border border-dark rounded adminPad">
                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Chocolate</h3>
                                <%@include file="chocoCreate.jsp" %>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Stock</h3>
                                <%@include file="stockCreate.jsp" %>
                                <h3 class="text-center">Review</h3>
                                <%@include file="reviewCreate.jsp" %>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">User</h3>
                                <%@include file="userCreate.jsp" %>
                                <h3 class="text-center">Activity</h3>
                                <%@include file="actCreate.jsp" %>
                            </div>
                        </div>
                    </div>

                    <h1 class="text-center">Data Charts</h1>

                    <div class="row border border-dark rounded adminPad">
                        
                        <c:forEach items="${output}" var="graph">
                            <div class="col-sm-6 border border-dark">
                                <div id ="${graph.getId()}" class="adminGraph"></div>                 
                            </div>
                        </c:forEach>
                        
                    </div>
                </div>

                <div class="col-sm-1"></div>

            </div>
        </div>

        <%@include file="modal.jsp" %>
        <%@include file="footer.jsp" %>

        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

    </body>
</html>
