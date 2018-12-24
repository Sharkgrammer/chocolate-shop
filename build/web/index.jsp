<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="data.chocolate"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
   
   <%@include file="about.jsp" %> 
   <div style="padding-bottom: 20px"></br></div>
   
   <%@include file="contact.jsp" %> 
   <div style="padding-bottom: 20px"></br></div>
   
   <%@include file="modal.jsp" %>
   <%@include file="footer.jsp" %>
   
  </body>

</html>