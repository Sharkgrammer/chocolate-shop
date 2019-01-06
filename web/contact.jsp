<div class="container-fluid" id='contact'>

    <div class="row">
        <div class="col-sm-1" ></div>

        <div class="col-sm-10 content border border-dark rounded center text-center">
            <header class="text-center">
                <h3> Contact Us </h3>
                <h3>${resultContact}</h3>
            </header>
            <div class="container-fluid">

                <div class="row">
                    <div class="col-sm-3" ></div>

                    <div class="col-sm-6">
                        <form name="create" action="contactServlet?mode=1" method="POST">
                            <input type="text" required name="emai" placeholder="Email" class="contact-form"/>
                            <input type="text" required name="subj" placeholder="Subject" class="contact-form" />
                            <textarea rows="2" required cols="25" name="deta" placeholder="Details" class="contact-form"></textarea>

                            <input type="submit" onclick="contactLoading();" value="Submit" name="submit" class="contact-but"/> <input type="reset" class="contact-but"/>
                        </form>
                    </div>

                    <div class="col-sm-3"></div>

                </div>
            </div>

        </div>

        <div class="col-sm-1"></div>

    </div>
</div>