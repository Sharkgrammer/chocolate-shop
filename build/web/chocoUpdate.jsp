<div>
    <form name="create" action="chocolateServlet?mode=7&filt=0" method="POST">
        <input type="text" id="chocoID" name="choc" readonly="true" class="contact-form"/>

        <input type="text" id="chocoName" required name="name" placeholder="Name" class="contact-form"/>

        <textarea rows="2" id="chocoDesc" required cols="25" name="desc" placeholder="Description" class="contact-form"></textarea>

        <input type="text" id="chocoType" required name="type" placeholder="Type" class="contact-form"/>

        <input type="number" id="chocoWeig" min="0" required name="weig" placeholder="Weight" class="contact-form"/>

        <input type="text" id="chocoProd" required name="prod" placeholder="Producer" class="contact-form"/>

        <input type="text" id="chocoFlav" required name="flav" placeholder="Flavour" class="contact-form"/>

        <input type="text" id="chocoPric" required name="pric" placeholder="Price (0.00)" class="contact-form"/>

        <input type="button" value="Back" onclick="chocBack()" name="Back" class="contact-but"/> <input type="button" onclick="chocNext()" value="Next" name="Next" class="contact-but"/> 

        <input type="button" onclick="chocStart()" value="Start" name="Start" class="contact-but"/> <input type="button" value="End" onclick="chocEnd()" name="End" class="contact-but"/>

        <input type="submit" value="Update" name="submit" class="contact-but"/> <input type="reset" class="contact-but">
    </form>
</div>