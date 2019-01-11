<div>
    <form action="stockServlet?mode=4" method="POST">

        <input type="text" id="stockID" required name="stockID" readonly="true" placeholder="ID" class="contact-form"/></td>

        <input type="number" id="stockChocoID" required name="stockChocoID" placeholder="Choco ID" class="contact-form"/></td>

        <input type="number" id="stockAmt" required name="stockAmt" placeholder="Amount" class="contact-form"/></td>

        <input type="button" value="Back" onclick="stockBack()" name="Back" class="contact-but"/> <input type="button" onclick="stockNext()" value="Next" name="Next" class="contact-but"/> 

        <input type="button" onclick="stockStart()" value="Start" name="Start" class="contact-but"/> <input type="button" value="End" onclick="stockEnd()" name="End" class="contact-but"/>

        <input type="submit" value="Update" name="submit" class="contact-but"/> <input type="reset" class="contact-but">

    </form>
</div>