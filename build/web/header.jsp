<script>

    function setCookie(name, value, days) {
        var expires = "";
        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toUTCString();
        }
        document.cookie = name + "=" + (value.toString() || "") + expires + ";";
    }

    function getCookie(name) {
        name = name + "=";
        var cookieList = document.cookie.split(';');
        for (var i = 0; i < cookieList.length; i++) {
            var cookie = cookieList[i];
            while (cookie.charAt(0) == ' ')
                cookie = cookie.substring(1, cookie.length);
            if (cookie.indexOf(name) == 0)
                return cookie.substring(name.length, cookie.length);
        }
        return null;
    }

    function eraseCookie(name) {
        document.cookie = name + '=; Max-Age=-99999999;';
    }

</script>

<%@page import="db.databaseConnections"%>
<%
    databaseConnections database = new databaseConnections();
    String key = "0000000000";
    int id = 0;

    try {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("key")) {
                    key = cookie.getValue();
                } else if (cookie.getName().equals("id")) {
                    id = Integer.parseInt(cookie.getValue());
                }
            }
        }
    } catch (Exception e) {
        //nada pls
    }

    boolean result = database.checkAuthKey(key, id);
    boolean admin = database.isAdmin(id);

%>

<nav class="navbar navbar-expand-lg navbar-dark bg-main fixed-top">
    <div class="container">
        <a href="/ChocolateShop"><img src="images/logo.png" class="logo"/></a>
        <h2 class="text-center title">Sharkolate</h2>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse navsize" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <li class="nav-item">
                    <a class="nav-link" href="/ChocolateShop">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="shop.jsp">Shop</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/ChocolateShop#reviews">Reviews</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/ChocolateShop#about">About</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/ChocolateShop#contact">Contact</a>
                </li>

                <% if (result == true) {
                        if (admin == true) { %>
                        <li class="nav-item">
                            <a class="nav-link" href="admin.jsp">Admin</a>
                        </li>
                    <% } %>
                 <li class="nav-item">
                    <a class="nav-link" href="#">Cart</a>
                </li>
                    
                <li class="nav-item">
                    <a class="nav-link" onclick="eraseCookie('key'); eraseCookie('id');window.location.reload(false);" href="#logout">Logout</a>
                </li>
                <% } else { %>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><h5><b>Log&nbsp;In/Sign&nbsp;Up</b></h5></a>
                </li>
                <% }%>

            </ul> 
        </div>

    </div>
</nav>