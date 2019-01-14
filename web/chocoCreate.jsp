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

        <input type="text" required name="name" placeholder="Name" class="contact-form"/>

        <textarea rows="2" required cols="25" name="desc" placeholder="Description" class="contact-form"></textarea>

        <input type="text" required name="type" placeholder="Type" class="contact-form"/>
        
        <input type="number" required name="weig" min="0" placeholder="Weight" class="contact-form"/>
        
        <input type="text" required name="prod" placeholder="Producer" class="contact-form"/>
        
        <input type="hidden" id="imgloc" name="img" value="null"/>
        
        <input type="file" id="imgfiles" multiple onchange="fileMultiStr()" class="contact-form">
        
        <input type="text" required name="flav" placeholder="Flavour" class="contact-form"/>
        
        <input type="text" required name="pric" placeholder="Price (0.00)" class="contact-form"/>
        
        <input type="submit" value="Create" name="submit" class="contact-but"/> <input type="reset" class="contact-but">
    </form>

</div>