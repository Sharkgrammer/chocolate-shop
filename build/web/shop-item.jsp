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

        <div class="container-fluid">

            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded "> 

                    <div class="container-fluid" id='shop'>

                        <div class="row">

                            <div class="col-sm-7" ><%@include file="carouselShopItem.jsp" %></div>

                            <div class="col-sm-5" >
                                <div class="text-center">
                                    <h1>${choco.getName()}</h1>
                                </div> 

                                <div class="text-left">
                                    <h1>${choco.getDescription()}</h1>
                                </div> 

                            </div>

                        </div>

                    </div>

                    </br></br>
                </div>

                <div class="col-sm-1"></div>

            </div>
        </div>

    </div>

    <%@include file="modal.jsp" %>
    <%@include file="footer.jsp" %>

</body>
</html>
