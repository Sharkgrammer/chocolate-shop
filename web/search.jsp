<script type="text/javascript">
    function searchItemClick(x) {
        document.getElementById("target").value = document.getElementById("search" + x).innerHTML;
        $("#searchForm").submit();
    }

    $j(document).ready(function () {

        var timeoutID = null;

        function findMember(str) {
            $j.get("shopServlet?mode=3&search=" + str, function (data, status) {
                var dataStr = data.split(",");
                //alert(dataStr);
                console.log(dataStr);

                if (dataStr.length != 0) {
                    var responseStr = "";
                    for (var x = 0; x < dataStr.length - 1; x++) {
                        responseStr += "<a onclick='searchItemClick(" + x + ")'><p id='search" + x + "'>" + dataStr[x] + "</p></a>";
                    }

                    document.getElementById("response").innerHTML = responseStr;
                }

            });
        }

        $j('#target').keyup(function (e) {
            clearTimeout(timeoutID);
            timeoutID = setTimeout(findMember.bind(undefined, e.target.value), 500);
        });

    });

</script>

<div class="search-container">
    <form action="shopServlet" id="searchForm">
        <div>
            <input type="hidden" value="2" name="mode" id="mode"/>
            <div class="row">
                <div class="col-sm-12">
                    <input type="text" placeholder="Search.." name="search" autocomplete="off" class="bar search-input" id="target"/>
                </div>
            </div>

            <div class="text-center search-response border border-dark rounded">
                <div id="response"></div>
            </div>

        </div>
    </form>
</div>