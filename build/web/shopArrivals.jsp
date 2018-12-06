<%@page import="java.util.List"%>
<%@page import="data.chocolate"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="css.jsp" %>
<div class="container-fluid" id='shop'>
    
    <jsp:include page="/chocolateServlet?mode=1" />
    
    <div class="row">
      <div class="col-sm-1" ></div>
      
      <div class="col-sm-10 content border border-dark rounded ">
          
          <div class="search-container">
            <form action="/action_page.php">
                <div>
                    <input type="text" placeholder="Search.." name="search" class="bar">
                    <button type="submit"><img src="images/search.png" width="15" height="15"/></button>
                </div>
            </form>
          </div>
          
          <header class="text-center">
              <h3> <---- New Arrivals ----> </h3>
          </header>
          
          </br>
          
          <div class="row text-center" style="overflow: hidden">
              <c:forEach items="${list}" var="choco">
                  
                <div class="col-sm-3">

                    <h3> ${choco.getName()} </h3> 

                    <img src="${choco.getImage_folder()}" style='height: 50%; width: 50%; object-fit: contain'/>

                    <p class="text-left">${choco.getDescription()}</p>

                </div>
          
              </c:forEach>
                  
          </div
          
          </br>
          
          <div class="container text-center">
              <h3><b><a href="#">To see more of our products, please click here</a><b></h3>
          </div>
          
      </div>
      
      <div class="col-sm-1"></div>
      
  </div>
</div>