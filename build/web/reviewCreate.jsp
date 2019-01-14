<div>
    <form action="reviewServlet?mode=1" method="POST">

        <input type="number" required name="choc" min="0" placeholder="Choco" id="revCreateChoc" class="contact-form"/></td>

        <input type="number" required name="user" min="0" placeholder="User" id="revCreateUser" class="contact-form"/></td>

        <textarea rows="2" required cols="25" name="data" placeholder="Review" class="contact-form"></textarea>
        
        <input type="text" required name="titl" placeholder="Title" class="contact-form" />

        <input type="hidden" required id="but" name="but" value=""/>

        <input type="button" value="Liked" class="contact-but" onclick="document.getElementById('but').value = 'true';">

        <input type="button" value="Disliked" class="contact-but" onclick="document.getElementById('but').value = 'false';">

        <input type="submit" value="Submit" name="submit" class="contact-but" /> <input type="reset" class="contact-but">

    </form>

</div>