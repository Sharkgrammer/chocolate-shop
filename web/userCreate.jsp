<div>
    <form name="create" action="userServlet?mode=1" method="POST">

        <table border="1">
            <tbody>

                <tr>
                    <td>Name</td>
                    <td><input type="text" required name="name" placeholder="Name" /></td>
                </tr>

                <tr>
                    <td>Email</td>
                    <td><input type="text" required name="emai" placeholder="Email" /></td>
                </tr>

                <tr>
                    <td>Password</td>
                    <td><input type="password" required name="pass" placeholder="Password" /></td>
                </tr>

                <tr>
                    <td>Address</td>
                    <td><input type="text" required name="addr" placeholder="Address" /></td>
                </tr>

            </tbody>
        </table>
        <input type="submit" value="Submit" name="submit" /> <input type="reset">
    </form>

</div>