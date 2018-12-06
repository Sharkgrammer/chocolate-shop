<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

  <head>
      <%@include file="css.jsp" %> 
  </head>

  <body>
      
   <%@include file="header.jsp" %> 
   <div style="padding-bottom: 100px"></br></div>
   
   <%@include file="carousel.jsp" %>
   <div style="padding-bottom: 20px"></br></div>
   
   <%@include file="shopArrivals.jsp" %> 
   <div style="padding-bottom: 20px"></br></div>
   
   <%@include file="reviews.jsp" %> 
   <div style="padding-bottom: 20px"></br></div>
   
   <%@include file="footer.jsp" %>
   
   <%@include file="modal.jsp" %>
   
    <!-- Bootstrap core JavaScript -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

  </body>

</html>