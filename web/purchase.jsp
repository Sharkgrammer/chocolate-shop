<!DOCTYPE html>
<html>
    <head>
        <%@include file="css.jsp" %>
    </head>

    <body>
        <%@include file="header.jsp" %> 
        <div style="padding-bottom: 100px"></br></div>

        <div class="container-fluid" id='admin'>

            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded">

                    <div class="row">

                        <div class="col-sm-4 text-center center">
                            <h3>Personal Details</h3>

                            <form name="create" method="POST" >

                                <table style="width:100%">
                                    <tbody>

                                        <tr>
                                            <td><input type="text" required name="name" placeholder="Card Holder" class="contact-form" /></td>
                                        </tr>

                                        <tr>
                                            <td><input type="number" required name="name" placeholder="Card Number"  class="contact-form"/></td>
                                        </tr>

                                        <tr>
                                            <td><input type="number" required name="name" placeholder="Csv Number" class="contact-form" /></td>
                                        </tr>

                                        <tr>
                                            <td><textarea rows="3" required cols="25" name="desc" placeholder="Address" class="contact-form"></textarea></td>
                                        </tr>

                                    </tbody>
                                </table>
                            </form>
                        </div>

                        <div class="col-sm-8">
                            
                            <h3 class="text-center">Purchases here</h3>

                            <jsp:include page="/purchaseServlet?mode=1" />
                            
                            <c:forEach items="${purchList}" var="purch">
                                
                            </c:forEach>

                        </div>

                    </div>

                </div>

                <div class="col-sm-1"></div>

            </div>
        </div>

        <%@include file="modal.jsp" %>
        <%@include file="footer.jsp" %>

    </body>
</html>
