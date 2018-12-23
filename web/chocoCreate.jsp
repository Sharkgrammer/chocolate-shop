<script>
    function getBase64(file, onLoadCallback) {
        return new Promise(function (resolve, reject) {
            var reader = new FileReader();
            reader.onload = function () {
                resolve(reader.result);
            };
            reader.onerror = reject;
            reader.readAsDataURL(file);
        });
    }

    function fileMultiStr() {
        document.getElementById('imgloc').value = "";
        var files = document.getElementById("imgfiles").files;

        for (var i = 0; i < files.length; i++) {
            var promise = getBase64(files[i]);
            promise.then(function (result) {
                document.getElementById('imgloc').value += result;
                if (i < files.length - 1) {
                    document.getElementById('imgloc').value += "--breaker--";
                }
            });
        }
    }

</script>

<div>
    <form name="create" action="chocolateServlet?mode=2&filt=0" method="POST">

        <table border="1">
            <tbody>

                <tr>
                    <td>Name</td>
                    <td><input type="text" required name="name" placeholder="Name" /></td>
                </tr>

                <tr>
                    <td>Description</td>
                    <td><textarea rows="2" required cols="25" name="desc" placeholder="Description"></textarea></td>
                </tr>

                <tr>
                    <td>Type</td>
                    <td><input type="text" required name="type" placeholder="Type" /></td>
                </tr>

                <tr>
                    <td>Weight</td>
                    <td><input type="number" required name="weig" placeholder="Weight" /></td>
                </tr>

                <tr>
                    <td>Producer</td>
                    <td><input type="text" required name="prod" placeholder="Producer" /></td>
                </tr>

                <tr>
                <input type="hidden" id="imgloc" name="img" value="null"/>
                <td>Image</td>
                <td><input type="file" id="imgfiles" multiple onchange="fileMultiStr()"></td>
                </tr>

                <tr>
                    <td>Flavour</td>
                    <td><input type="text" required name="flav" placeholder="Flavour" /></td>
                </tr>
                 <tr>
                    <td>Price - Enter Float Value (0.00)</td>
                    <td><input type="text" required name="pric" placeholder="Price" /></td>
                </tr>
            </tbody>
        </table>
        <input type="submit" value="Submit" name="submit" /> <input type="reset">
    </form>

</div>