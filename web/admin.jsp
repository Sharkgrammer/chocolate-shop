<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/adminServlet" />
        <jsp:include page="/userServlet?mode=3" />
        <%@page import="java.util.List"%>
        <%@page import="data.chocolate"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@include file="css.jsp" %>
        <script>
            var $j = jQuery.noConflict();
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
            };
        </script>


    </head>

    <body>
        <%@include file="header.jsp" %> 
        <div style="padding-bottom: 100px"></br></div>

        <div class="container-fluid" id='admin'>

            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded">

                    <h1 class="text-center">Data Charts</h1>

                    <div class="row border border-dark rounded adminPad">

                        <c:forEach items="${output}" var="graph">
                            <div class="col-sm-6 border border-dark">
                                <div id ="${graph.getId()}" class="adminGraph"></div>                 
                            </div>
                        </c:forEach>

                    </div>
                    
                    <h1 class="text-center">Chocolate Database</h1>
                    <div class="row border border-dark rounded adminPad">
                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Create</h3>
                                <%@include file="chocoCreate.jsp" %>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Update</h3>
                                <%@include file="chocoUpdate.jsp" %>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Delete</h3>
                                <%@include file="chocoDelete.jsp" %>
                            </div>
                        </div>
                    </div>

                    <h1 class="text-center">Review Database</h1>
                    <div class="row border border-dark rounded adminPad">
                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Create</h3>
                                <%@include file="reviewCreate.jsp" %>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Update</h3>
                                <%@include file="reviewUpdate.jsp" %>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Delete</h3>
                                <%@include file="reviewDelete.jsp" %>
                            </div>
                        </div>
                    </div>

                    <h1 class="text-center">Stock Database</h1>
                    <div class="row border border-dark rounded adminPad">
                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Create</h3>
                                <%@include file="stockCreate.jsp" %>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Update</h3>
                                <%@include file="stockUpdate.jsp" %>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Delete</h3>
                                <%@include file="stockDelete.jsp" %>
                            </div>
                        </div>
                    </div>

                    <h1 class="text-center">User Database</h1>
                    <div class="row border border-dark rounded adminPad">
                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Create</h3>
                                <%@include file="userCreate.jsp" %>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Update</h3>
                                <%@include file="userUpdate.jsp" %>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="adminContainer text-center">
                                <h3 class="text-center">Delete</h3>
                                <%@include file="userDelete.jsp" %>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="col-sm-1"></div>

            </div>
        </div>


        <script>

            var stockCount = ${stockCount};
            var revCount = ${revCount};
            var chocCount = ${chocCount};
            var stockCur = 1, revCur = 1, chocCur = 1;
            var loop = false;

            function stockData(id) {
                $j.get("stockServlet?mode=2&id=" + id, function (data, status) {
                    if (data.split(",").length <= 1) {
                        if (mode == 0) {
                            if (stockCur < stockCount) {
                                stockCur++;
                            }
                        } else if (mode == 1) {
                            if (stockCur > 1) {
                                stockCur--;
                            }
                        } else if (mode == 2) {
                            stockCur = stockCount;
                            stockCur--;
                        } else if (mode == 3) {
                            stockCur++;
                        }
                        stockData(stockCur, mode);
                    } else {
                        document.getElementById("stockID").value = "ID: " + data.split(",")[0];
                        document.getElementById("stockChocoID").value = data.split(",")[1];
                        document.getElementById("stockAmt").value = data.split(",")[2];
                    }
                });
            }

            function reviewData(id, mode) {
                $j.get("reviewServlet?mode=3&id=" + id, function (data, status) {

                    if (data.split(",").length <= 1) {
                        if (mode == 0) {
                            if (revCur < revCount) {
                                revCur++;
                            }
                        } else if (mode == 1) {
                            if (revCur > 1) {
                                revCur--;
                            }
                        } else if (mode == 2) {
                            revCur = revCount;
                            revCur--;
                        } else if (mode == 3) {
                            revCur++;
                        }
                        reviewData(revCur, mode);
                    } else {
                        document.getElementById("revID").value = "ID: " + data.split(",")[0];
                        document.getElementById("revChocoID").value = data.split(",")[1];
                        document.getElementById("revUserID").value = data.split(",")[2];
                        document.getElementById("revData").value = data.split(",")[3];
                        document.getElementById("revTitle").value = data.split(",")[4];
                        document.getElementById("revbut").value = data.split(",")[5];
                    }
                });
            }

            function chocolateData(id) {
                $j.get("chocolateServlet?mode=5&filt=0&id=" + id, function (data, status) {
                    if (data.split(",").length <= 1) {
                        if (mode == 0) {
                            if (chocCur < chocCount) {
                                chocCur++;
                            }
                        } else if (mode == 1) {
                            if (chocCur > 1) {
                                chocCur--;
                            }
                        } else if (mode == 2) {
                            chocCur = chocCount;
                            chocCur--;
                        } else if (mode == 3) {
                            chocCur++;
                        }
                        chocolateData(chocCur, mode);
                    } else {
                        document.getElementById("chocoID").value = "ID: " + data.split(",")[0];
                        document.getElementById("chocoName").value = data.split(",")[1];
                        document.getElementById("chocoDesc").value = data.split(",")[2];
                        document.getElementById("chocoType").value = data.split(",")[3];
                        document.getElementById("chocoWeig").value = data.split(",")[4];
                        document.getElementById("chocoProd").value = data.split(",")[5];
                        document.getElementById("chocoFlav").value = data.split(",")[6];
                        document.getElementById("chocoPric").value = data.split(",")[7];
                    }

                });
            }

            function chocNext() {
                if (chocCur < chocCount) {
                    chocCur++;
                    chocolateData(chocCur, 0);
                }

            }

            function chocBack() {
                if (chocCur > 1) {
                    chocCur--;
                    chocolateData(chocCur, 1);
                }
            }

            function chocStart() {
                chocCur = 1;
                chocolateData(chocCur, 3);
            }

            function chocEnd() {
                chocCur = chocCount;
                chocolateData(chocCur, 2);
            }

            function stockNext() {
                if (stockCur < stockCount) {
                    stockCur++;
                    stockData(stockCur, 0);
                }

            }

            function stockBack() {
                if (stockCur > 1) {
                    stockCur--;
                    stockData(stockCur, 1);
                }
            }

            function stockStart() {
                stockCur = 1;
                stockData(stockCur, 3);
            }

            function stockEnd() {
                stockCur = stockCount;
                stockData(stockCur, 2);
            }

            function revNext() {
                if (revCur < revCount) {
                    revCur++;
                    reviewData(revCur, 0);
                }

            }

            function revBack() {
                if (revCur > 1) {
                    revCur--;
                    reviewData(revCur, 1);
                }
            }

            function revStart() {
                revCur = 1;
                reviewData(revCur, 3);
            }

            function revEnd() {
                revCur = revCount;
                reviewData(revCur, 2);
            }

            chocStart();
            stockStart();
            revStart();


        </script>


        <%@include file="modal.jsp" %>
        <%@include file="footer.jsp" %>

        <script src="js/canvasjs.min.js"></script>

    </body>
</html>
