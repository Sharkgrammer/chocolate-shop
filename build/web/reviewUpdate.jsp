<div>
    <form action="reviewServlet?mode=4" method="POST">

        <input type="text" id="revID" readonly="true" required name="revID" value="0" class="contact-form"/>

        <input type="number" id="revChocoID" min="0" required name="revChocoID" placeholder="Choco" class="contact-form"/>

        <input type="number" id="revUserID" min="0" required name="revUserID" placeholder="User" class="contact-form"/>

        <textarea rows="2" id="revData" required cols="25" name="revData" placeholder="Review" class="contact-form"></textarea>

        <input type="text" id="revTitle" required name="revTitle" placeholder="Title" class="contact-form" />

        <input type="button" value="Liked" class="contact-but" onclick="document.getElementById('revbut').value = 'true';">

        <input type="button" value="Disliked" class="contact-but" onclick="document.getElementById('revbut').value = 'false';">

        <input type="text" readonly="true" required id="revbut" name="revbut" class="contact-form" />

        <input type="button" value="Back" onclick="revBack()" name="Back" class="contact-but"/> <input type="button" onclick="revNext()" value="Next" name="Next" class="contact-but"/> 

        <input type="button" onclick="revStart()" value="Start" name="Start" class="contact-but"/> <input type="button" value="End" onclick="revEnd()" name="End" class="contact-but"/>

        <input type="submit" value="Update" name="submit" class="contact-but"/> <input type="reset" class="contact-but">

    </form>

</div>