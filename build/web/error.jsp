<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="css.jsp" %>
    </head>

    <body>
        <%@include file="header.jsp" %> 
        <div style="padding-bottom: 100px"></br></div>

        <div class="container-fluid">
            </br>
            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded center text-center" style="padding:10px">
                    <h1>Error ${code}</h1>
                    <p>An error with code ${code} has occurred</p>
                    <p>Return to the previous page or contact the system administrator</p>
                    <img src="images/404.png"/>
                </div>

                <div class="col-sm-1"></div>

            </div>
            </br>
        </div>

        <%@include file="modal.jsp" %>
        <%@include file="footer.jsp" %>

    </body>
</html>
