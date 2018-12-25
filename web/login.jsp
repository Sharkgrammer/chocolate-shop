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

            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded center text-center">

                    <h1>${boop}</h1>

                    <header class="text-center">
                        <h3> Login </h3>
                    </header>
                    <div class="container-fluid">

                        <div class="row">
                            <div class="col-sm-3" ></div>

                            <div class="col-sm-6">
                                <form name="create" action="userServlet?mode=2" method="POST">
                                    <input type="text" required name="emai" placeholder="Email" class="contact-form"/>
                                    <input type="password" required name="pass" placeholder="Password" class="contact-form" />

                                    <input type="submit" value="Login" name="submit" class="contact-but"/> <input type="reset" class="contact-but"/>
                                </form></br>

                                <header class="text-center">
                                    <h3> Not Registered? </h3>
                                </header>

                                <%@include file="userCreate.jsp" %>
                            </div>

                            <div class="col-sm-3"></div>

                        </div>
                    </div>

                </div>

                <div class="col-sm-1"></div>

            </div>
        </div>

        <div style="padding-bottom: 20px"></br></div>
        <%@include file="modal.jsp" %>
        <%@include file="footer.jsp" %>

    </body>
</html>
