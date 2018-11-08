<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Chocolate Shop</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link href="css/main.css" rel="stylesheet">
    
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Mali" rel="stylesheet">

  </head>

  <body>
      
   <%@include file="header.jsp" %> 
   <div style="padding-bottom: 100px"></br></div>
       
   <%@include file="carousel.jsp" %>
   <div style="padding-bottom: 20px"></br></div>
   
   <%@include file="shop.jsp" %> 
   <div style="padding-bottom: 20px"></br></div>
   
   <%@include file="footer.jsp" %>
   
    <!-- Bootstrap core JavaScript -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

  </body>

</html>