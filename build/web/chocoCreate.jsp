<div>
    <form name="create" action="chocolateServlet?mode=2" method="POST">

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
                    <td><input type="file" onchange="document.getElementById('imgloc').value = window.URL.createObjectURL(this.files[0])" onClick="autofill();return false;"></td>
                </tr>

                <tr>
                    <td>Flavour</td>
                    <td><input type="text" required name="flav" placeholder="Flavour" /></td>
                </tr>
                
            </tbody>
        </table>
        <input type="submit" value="Submit" name="submit" /> <input type="reset">
    </form>
    
</div>