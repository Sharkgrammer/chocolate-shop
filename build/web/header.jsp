<script>
    var $j = jQuery.noConflict();
    
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

    function logout() {
        eraseCookie('key');
        eraseCookie('id');
        eraseCookie("cart");
        window.open("/ChocolateShop", "_self");
    }

    function switchLan() {
        var result = getCookie("lan");
        if (result == null) {
            setCookie("lan", "fr", 1);
        } else if (result == "fr") {
            setCookie("lan", "en", 1);
        } else {
            setCookie("lan", "fr", 1);
        }
        window.location.reload();
    }

    function contactLoading() {
        document.getElementById("newsmodal").style = "display:none;";
        document.getElementById("mainIndex").style = "display:none;";
        document.getElementById("loading").style = "display:initial";
        document.getElementById("mainLoad").style = "display:initial";
    }

</script>

<%@page import="db.databaseConnections"%>
<%
    databaseConnections database = new databaseConnections();
    String key = "0000000000";
    int id = 0;
    String lan = "";

    try {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("key")) {
                    key = cookie.getValue();
                } else if (cookie.getName().equals("id")) {
                    id = Integer.parseInt(cookie.getValue());
                } else if (cookie.getName().equals("lan")) {
                    lan = cookie.getValue();
                }
            }
        }
    } catch (Exception e) {
        //nada pls
    }

    boolean result = database.checkAuthKey(key, id);
    boolean admin = database.isAdmin(id);
    boolean frLang = lan.equals("fr");

%>

<nav class="navbar navbar-expand-lg navbar-dark bg-main fixed-top">
    <div class="container">
        <a href="/ChocolateShop"><img src="images/logo.png" class="logo"/></a>
        <% if (frLang == true) { %>
        <h2 class="text-center title">Requin&nbsp;au&nbsp;chocolat</h2>
        <% } else { %>
        <h2 class="text-center title">Sharkolate</h2>
        <% }%>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse navsize" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <% if (frLang == true) { %>

                <li class="nav-item">
                    <a class="nav-link" href="/ChocolateShop">Accueil
                        <span class="sr-only">(current)</span>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="shop.jsp">Boutique</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/ChocolateShop#reviews">Avis</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/ChocolateShop#about">Sur</a>
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
                    <a class="nav-link" href="user.jsp">Compte</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="purchase.jsp">Panier</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" onclick="logout();" href="#logout">Connectez&nbsp;Out</a>
                </li>
                <% } else { %>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><h5><b>Se&nbsp;connecter/S'inscrire</b></h5></a>
                </li>
                <% }%>

                <% } else { %>

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
                    <a class="nav-link" href="user.jsp">Account</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="purchase.jsp">Cart</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" onclick="logout();" href="#logout">Logout</a>
                </li>
                <% } else { %>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><h5><b>Log&nbsp;In/Sign&nbsp;Up</b></h5></a>
                </li>

                <% }%>

                <% }%>

            </ul> 
        </div>

    </div>
</nav>