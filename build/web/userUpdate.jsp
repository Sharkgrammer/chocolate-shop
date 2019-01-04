<div>
    <h3 class="text-center">${result}</h3>
    <form name="create" action="userServlet?mode=4" method="POST">

        <input type="text" required id="name" name="name" placeholder="Name" class="contact-form"/>

        <input type="text" required id="emai" name="emai" placeholder="Email" class="contact-form"/>

        <input type="password" required id="pass" name="pass" placeholder="Password" class="contact-form"/>

        <input type="text" required id="addr" name="addr" placeholder="Address" class="contact-form"/>
        
        <input type="submit" value="Update" name="submit" class="contact-but" /> <input type="reset" class="contact-but"/>
        
    </form>

</div>