<div id="gdprmodal" class="modal">

    <div class="modal-gdpr">
        <div class="modal-edge">
            <h2>Sharkolate uses Cookies and other tracking technologies</h2>
        </div>
        <div style=" padding: 2px 16px;">
            </br> 

            <h5>In order to use this site you must accept our cookies and other tracking technologies</h5>
            <h5>If you do not accept these, you cannot use this site. Filthy EU casual </h5>
            <a href="terms.html"><h5>Learn more here</h5></a>
            <div class="center text-center">
                <button type="button" class="btn btn-success" id="close">Accept</button>
                <a type="button" class="btn btn-danger" href="reject.html">Reject</a>
            </div>

            </br>
        </div>
        <div class="modal-edge"></br></div>
    </div>

</div>

<div id="newsmodal" class="modal">
    <div class="modal-news-padding center text-center rounded">
        <h2 id="newstitle">Hi there! Interested in some high quality deals and news?</h2>
        <div class="modal-news">
            <form name="create" action="contactServlet?mode=2" method="POST">
                <h3>Do you want the latest news in chocolate?</h3>
                <h3>The newest tasty goods hot off the shelf?</h3>
                <h3>The greatest deals on the best chocolate on the Internet?</h3>
                <h3>Sign up to our newsletter!</h3>
                <input type="text" name="emai" placeholder="Email.." id="newsemail" class="border border-dark rounded modal-news-box">
                </br></br>
                <input type="submit" value="I love deals!"class="btn btn-success modal-news-btn" id="newsaccept"/>
                <button type="button" class="btn btn-danger modal-news-btn" id="newsreject">I hate savings</button>
            </form>
        </div>
    </div>
</div>

<div id="shopmodal" class="modal">
    <div class="modal-shop-padding center text-center rounded">
        <h3>Add to cart?</h3>
        <div class="modal-shop">
            <button type="button" class="btn btn-success modal-shop-btn" id="shopaccept">Yes</button>
            <button type="button" class="btn btn-danger modal-shop-btn" id="shopreject">No</button>
        </div>
    </div>
</div>

<div id="buymodal" class="modal">
    <div class="modal-shop-padding center text-center rounded">
        <h3>Buy from cart?</h3>
        <div class="modal-shop">
            <button type="button" class="btn btn-success modal-shop-btn" id="buyaccept">Yes</button>
            <button type="button" class="btn btn-danger modal-shop-btn" id="buyreject">No</button>
        </div>
    </div>
</div>


<script>
    var modal = document.getElementById('gdprmodal');
    var span = document.getElementById("close");
    modal.style.display = "none";
    //Check cookies here for GDPR compliance before showing it
    if (!checkCookie("gdpr", "yes")) {
        modal.style.display = "block";
    }

    span.onclick = function () {
        modal.style.display = "none";
        setCookie("gdpr", "yes", 10);
    };

    //Check cookies here if email has been given to us, if not do other things.
    var newsletter;
    if (!checkCookie("email", "yes")) {
        newsletter = setInterval(newsletterCheck, 1000);
    }

    var modalnews = document.getElementById('newsmodal');
    var newsemail = document.getElementById('newsemail');
    var spannewsno = document.getElementById("newsreject");
    var spannewsyes = document.getElementById("newsaccept");
    var titlenews = document.getElementById("newstitle");
    var newsCounter = 0;
    modalnews.style.display = "none";

    spannewsno.onclick = function () {
        modalnews.style.display = "none";
    };
    spannewsyes.onclick = function () {
        modalnews.style.display = "none";
        contactLoading();
        //do something with the email here?
        //Such as uploading + checks and whatnot
        newsCounter = 5;
        setCookie("email", "yes", 10);
    };

    var modalshop = document.getElementById('shopmodal');
    var spanshopyes = document.getElementById("shopaccept");
    var spanshopno = document.getElementById("shopreject");
    modalshop.style.display = "none";

    spanshopno.onclick = function () {
        modalshop.style.display = "none";
    };
    spanshopyes.onclick = function () {
        sendToCart();
    };

    var modalbuy = document.getElementById('buymodal');
    var spanbuyyes = document.getElementById("buyaccept");
    var spanbuyno = document.getElementById("buyreject");
    modalbuy.style.display = "none";

    spanbuyno.onclick = function () {
        modalbuy.style.display = "none";
    };
    spanbuyyes.onclick = function () {
        buy();
    };



    function newsletterCheck() {
        //Dont show newsletter request if the gdpr notice is up
        if (modal.style.display === "none" && modalnews.style.display === "none") {
            switch (newsCounter) {
                case 1:
                    titlenews.innerHTML = "Are you sure you're not interested in some high quality deals and news?";
                    break;
                case 2:
                    titlenews.innerHTML = "Are you super positive?";
                    break;
                case 3:
                    titlenews.innerHTML = "You may miss out on some great deals if you do not sign up!";
                    break;
                case 4:
                    titlenews.innerHTML = "Final chance to get some of these amazing deals";
                    break;
                case 5:
                    clearInterval(newsletter);
                    setCookie("email", "yes", 10);
                    return;
            }
            newsCounter++;
            modalnews.style.display = "block";
        }
    }

    function setCookie(name, value, days) {
        var d = new Date();
        d.setTime(d.getTime() + (days * 24 * 60 * 60 * 1000));
        document.cookie = name + "=" + value + ";" + ("expires=" + d.toUTCString());
    }

    function getCookie(name) {
        var cookieName = name + "=";
        var cookie = document.cookie.split(';');
        for (var i = 0; i < cookie.length; i++) {
            var cookieTemp = cookie[i];
            while (cookieTemp.charAt(0) === ' ') {
                cookieTemp = cookieTemp.substring(1);
            }
            if (cookieTemp.indexOf(name) === 0) {
                return cookieTemp.substring(cookieName.length, cookieTemp.length);
            }
        }
        return false;
    }

    function checkCookie(name, value) {
        if (getCookie(name) == value) {
            return true;
        }
        return false;
    }
</script>