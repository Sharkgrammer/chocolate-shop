<jsp:include page="/chocolateServlet?mode=1&filt=8" />
<div class="container-fluid" id='shop'>
    
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
              <h3> New Arrivals </h3>
          </header>
          
          </br>

          <div class="row text-center" style="overflow: hidden">
              <c:forEach items="${listNew}" var="choco">
                  <div class="col-sm-3">
                      <a href="shopServlet?id=${choco.getId()}" class="shop-link">

                          <h3> ${choco.getName()} </h3> 
                          <img src="${choco.getFirstImage()}" class="shop-arrival-img"/>
                          <p class="text-left">${choco.getDescription()}</p>

                      </a>

                  </div>

              </c:forEach>

          </div>
          
          </br>
          
          <div class="container text-center">
              <h3><b><a href="shop.jsp">To see more of our products, please click here</a><b></h3>
          </div>
          
      </div>
      
      <div class="col-sm-1"></div>
      
  </div>
</div>