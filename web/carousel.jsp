<jsp:include page="/chocolateServlet?mode=3&filt=7" />

<div class="container-fluid">
    <div class="row">
      <div class="col-sm-2"> <div class="caraspace"> <!-- Future ad space --> </div> </div>
      <div class="col-sm-8">
        <div class="caramain rounded">
          <div id="carousel" class="carousel slide center" data-ride="carousel">

              <div class="carousel-inner">

                  <div class="carousel-item active">
                      <a href="shopServlet?id=${firstChoco.getId()}&mode=1" class="shop-link">
                          <img class="d-block cara" src="${firstChoco.getFirstImage()}" alt="${firstChoco.getName()}">
                          <div class="carousel-caption d-none d-md-block">
                              <h5>${firstChoco.getName()}</h5>
                          </div>
                      </a>
                  </div>

                  <c:forEach items="${listCara}" var="choco">

                      <div class="carousel-item">
                          <a href="shopServlet?id=${choco.getId()}&mode=1" class="shop-link">
                              <img class="d-block cara" src="${choco.getFirstImage()}" alt="${choco.getName()}">
                              <div class="carousel-caption d-none d-md-block">
                                  <h5>${choco.getName()}</h5>
                              </div>
                          </a>
                      </div>

                  </c:forEach>

              </div>

            <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>

          </div>
        </div>

      </div>
      <div class="col-sm-2"> <div class="caraspace"> <!-- Future ad space --> </div> </div>
      
  </div>
</div>