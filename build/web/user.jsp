<html>
    <head>
        <%@page import="java.util.List"%>
        <%@page import="data.user"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@include file="css.jsp" %>
        <jsp:include page="/userServlet?mode=3" />

        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&key=AIzaSyCcrxqE9VgXuldbLPFlK8KzN_RPczOEiVI"></script>

        <script>
            var lat;
            var lng;

            function getXml(url) {
                var request = new XMLHttpRequest();
                request.open("GET", url, false);
                request.send(null);
                var xml = request.responseText;
                var xmlDoc = jQuery.parseXML(xml);
                var loc = xmlDoc.getElementsByTagName("location");

                lat = loc[0].getElementsByTagName("lat")[0].innerHTML;
                lng = loc[0].getElementsByTagName("lng")[0].innerHTML;
            }

            getXml("https://maps.googleapis.com/maps/api/geocode/xml?address=${address}&key=AIzaSyCcrxqE9VgXuldbLPFlK8KzN_RPczOEiVI");

            var map;
            function initialize() {
                var mapOptions = {
                    zoom: 15,
                    center: new google.maps.LatLng(lat, lng)
                };
                map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            }

            google.maps.event.addDomListener(window, 'load', initialize);
        </script>

    </head>

    <body>
        <%@include file="header.jsp" %>
        <div style="padding-bottom: 100px"></br></div>

        <div class="container-fluid">

            <div class="row">
                <div class="col-sm-1" ></div>

                <div class="col-sm-10 content border border-dark rounded "> 

                    <div class="row">
                        <div class="col-sm-4">
                            <%@include file="userUpdate.jsp" %>
                        </div>

                        <div class="col-sm-8 center text-center">
                            <div id="map-canvas" class="userMap border border-dark"/>
                        </div>
                    </div>

                </div>

                <div class="col-sm-1"></div>

            </div>
        </div>
    </div>

    </br>

    <%@include file="modal.jsp" %>
    <%@include file="footer.jsp" %>    

</body>
</html>
