<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/purchaseServlet?mode=2" />
        <%@page import="java.util.List"%>
        <%@page import="data.chocolate"%>
        <%@page import="data.purchase"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@include file="css.jsp" %>

        <script>

            function updateTotal() {
                var result = 0;

                for (var x = 1; x <= ${purchCount}; x++) {
                    result += document.getElementById(x).innerHTML * 1;
                }

                if (document.getElementById("fast").checked) {
                    result += 5;
                }

                document.getElementById("total").innerHTML = "Total &euro;" + Math.round(result * 100) / 100;
            }

            function removeFromCart(id) {
                var oldcookie = getCookie("cart");
                var newcookie = oldcookie.replace("@" + oldcookie.split("@")[id], "");

                //ajax this later pls
                eraseCookie("cart");
                setCookie("cart", newcookie, 1);
                window.location.reload();
            }

            function buy() {
                var str = "";
                if (document.getElementById("card").value.length < 3) {
                    str += "Card Holder";
                }
                if (document.getElementById("no").value.length < 6) {
                    if (str != "") {
                        str += ", ";
                    }
                    str += "Card Number";
                }
                if (document.getElementById("csv").value.length < 3) {
                    if (str != "") {
                        str += ", ";
                    }
                    str += "CSV Number";
                }
                if (document.getElementById("add").value.length < 6) {
                    if (str != "") {
                        str += ", ";
                    }
                    str += "Address";
                }


                if (str == "") {
                    window.open("purchaseServlet?mode=3", "_self");
                } else {
                    modalbuy.style.display = 'none';
                    alert("Error in: " + str);
                }

            }

        </script>
    </head>

    <body>
        <%@include file="header.jsp" %> 
        <div style="padding-bottom: 100px"></br></div>

        <div class="container-fluid" id='admin'>

            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded">

                    <div class="row">

                        <div class="col-sm-4 text-center center">
                            <h3>Personal Details</h3>

                            <form name="create" method="POST" >

                                <table style="width:100%">
                                    <tbody>

                                        <tr>
                                            <td><input type="text" required id="card" placeholder="Card Holder" class="contact-form" /></td>
                                        </tr>

                                        <tr>
                                            <td><input type="number" min="0" required id="no" placeholder="Card Number"  class="contact-form"/></td>
                                        </tr>

                                        <tr>
                                            <td><input type="number" min="0" required id="csv" placeholder="Csv Number" class="contact-form" /></td>
                                        </tr>

                                        <tr>

                                            <td><textarea rows="3" required cols="25" id="add" placeholder="Address" class="contact-form"></textarea></td>
                                        </tr>

                                        <tr>
                                            <td><h5>Delivery</h5></td>                                            
                                        </tr>

                                        <tr>
                                            <td><input type="radio" onclick="updateTotal();" name="del" id="free" checked> Free    
                                                <input type="radio" onclick="updateTotal();" name="del" id="fast"> Fast</td>
                                        </tr>

                                    </tbody>
                                </table>
                            </form>
                        </div>

                        <div class="col-sm-8">

                            <h1 class="text-center">${result}</h1>

                            <script>
                                setTimeout(function () {
                                    if ("${result}" == "Items Bought!") {
                                        window.location.href = "/ChocolateShop";
                                    }
                                }, 100);
                            </script>

                            <h3 class="text-center">Purchases here</h3>

                            <div class="row" style="padding-right:5px">
                                <c:forEach items="${purchList}" var="purch">
                                    <div class="col-sm-4 purchContainerEx">
                                        <div class="purchContainerIn border border-dark rounded">
                                            <a href="shopServlet?id=${purch.getChoco().getId()}&mode=1" class="shop-link">
                                                <h3 class="text-center center">${purch.getChoco().getName()}</h3>

                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <p>Price: &euro;${purch.getChoco().getPrice()}</p>
                                                    </div>

                                                    <div class="col-sm-6">
                                                        <p>Amount: ${purch.getAmount()}</p>
                                                    </div>
                                                </div>

                                                <p class="text-center"><b>Total: &euro;<script>document.write(Math.round(${purch.getAmount()} * ${purch.getChoco().getPrice()} * 100) / 100);</script></b></p>
                                            </a>
                                            <p id="${purch.getCart()}" style="display:none"></p>
                                            <script>
                                                document.getElementById(${purch.getCart()}).innerHTML = (${purch.getAmount()} * ${purch.getChoco().getPrice()});
                                            </script>
                                            <button class="shop-but" onclick="removeFromCart(${purch.getCart()})">Remove</button>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="row purchMargin">
                                <div class="col-sm-6">
                                    <b><h4 id="total" class="text-center">Total: &euro;0</h4></b>
                                </div>

                                <div class="col-sm-6">
                                    <c:if test="${purchCount != 0}">
                                        <button class="shop-but" onclick="modalbuy.style.display = 'block';">Purchase Cart Goods</button>
                                    </c:if>
                                </div>

                            </div>

                        </div>

                    </div>

                </div>

                <div class="col-sm-1"></div>

            </div>
        </div>

        <%@include file="modal.jsp" %>
        <%@include file="footer.jsp" %>


        <script>
            updateTotal();
        </script>
    </body>
</html>
