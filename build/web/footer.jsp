<footer class="py bg-main">

    <div class="row text-center ">

        <div class="col-sm-2">
            <a class="footer-link" href="/ChocolateShop">Home</a>
        </div>

        <div class="col-sm-2">
            <a class="footer-link" href="shop.jsp">Shop</a>
        </div>

        <% if (result == true) { %>
        
        <div class="col-sm-2">
            <a class="footer-link" href="user.jsp">Account</a>
        </div>

        <div class="col-sm-2">
            <a class="footer-link" href="purchase.jsp">Cart</a>
        </div>

        <% }else{ %>
        <div class="col-sm-2">
        </div>

        <div class="col-sm-2">
        </div>
        <% } %>
        <div class="col-sm-2">
            <a class="footer-link" href="#" onclick="switchLan()">Switch Language</a>
        </div>

        <div class="col-sm-2">
            <p class="footer-link-copy">Copyright &copy; Choco 2019</p>
        </div>

    </div>


</footer>

<!-- Bootstrap core JavaScript -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>