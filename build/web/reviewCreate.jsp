<div>
    <form name="create" action="reviewServlet?mode=1" method="POST">

        <table border="1">
            <tbody>

                <tr>
                    <td>Choco ID</td>
                    <td><input type="number" required name="choc" placeholder="Choco" /></td>
                </tr>

                <tr>
                    <td>User ID</td>
                    <td><input type="number" required name="user" placeholder="User" /></td>
                </tr>

                <tr>
                    <td>Data</td>
                    <td><textarea rows="2" required cols="25" name="data" placeholder="Data"></textarea></td>
                </tr>
                
                <tr>
                    <td>Title</td>
                    <td><input type="text" required name="titl" placeholder="Title" /></td>
                </tr>
                
                <tr>
                    <input type="hidden" required id="but" name="but" value=""/>
                    <td>Liked</td>
                    <td><input type="button" value="Liked" onclick="document.getElementById('but').value = 'true';">
                        <input type="button" value="Disliked" onclick="document.getElementById('but').value = 'false';"></td>
                </tr>

            </tbody>
        </table>
        <input type="submit" value="Submit" name="submit" /> <input type="reset">
    </form>

</div>