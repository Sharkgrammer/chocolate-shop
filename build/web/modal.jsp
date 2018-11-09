<div id="gdprmodal" class="modal">

  <div class="modal-gdpr">
    <div class="modal-edge">
        <h2>Sharkolate uses Cookies and other tracking technologies</h2>
    </div>
    <div style=" padding: 2px 16px;">
      </br> 
      
      <h5>In order to use this site you must accept our cookies and other tracking technologies</h5>
      <h5>If you do not accept these, you cannot use this site. Filthy EU casual </h5>
      <a href="terms.html"><h5>Learn more here</h5></a>
      <div class="center text-center">
          <button type="button" class="btn btn-success" id="close">Accept</button>
          <a type="button" class="btn btn-danger" href="reject.html">Reject</a>
      </div>
      
      </br>
   </div>
    <div class="modal-edge"></br></div>
  </div>

</div>

<div id="newsmodal" class="modal">
  <div class="modal-news-padding center text-center">
    <h2 id="newstitle">Hi there! Interested in some high quality deals and news?</h2>
    <div class="modal-news">
        <h3>Do you want the latest news in chocolate?</h3>
        <h3>The newest tasty goods hot off the shelf?</h3>
        <h3>The greatest deals on the best chocolate on the Internet?</h3>
        <h3>Sign up to our newsletter!</h3>
        <form action="email.jsp">
          <input type="text" name="email" placeholder="Email.."  size="35" class="border border-dark rounded" class="bar">
          </br></br>
          <button type="submit" value="Submit" class="btn btn-success modal-news-btn">Sure! I love deals!</button>
          <button type="button" class="btn btn-danger modal-news-btn" id="closenews">No i hate saving money</button>
        </form>
    </div>
  </div>

</div>
   
<script>
    var modal = document.getElementById('gdprmodal');
    var span = document.getElementById("close");
    
    //Check cookies here for GDPR compliance pls before showing it
    modal.style.display = "block";
    span.onclick = function() { modal.style.display = "none"; };
    
    //Check cookies here if email has been given to us, if not do other things.
    var modalnews = document.getElementById('newsmodal');
    var spannews = document.getElementById("closenews");
    var titlenews = document.getElementById("newstitle");
    modalnews.style.display = "none";
    spannews.onclick = function() { modalnews.style.display = "none"; };
    
    var newsletter = setInterval(newsletterCheck, 1000);
    var newsCounter = 0;

    function newsletterCheck() {
        //Dont show newsletter request if the gdpr notice is up
        if (modal.style.display === "none" && modalnews.style.display === "none"){
            switch(newsCounter){
                case 1:
                    titlenews.innerHTML = "Are you sure you're not interested in some high quality deals and news?";
                    break;
                case 2:
                    titlenews.innerHTML = "Are you super positive?";
                    break;
                case 3:
                    titlenews.innerHTML = "You may miss out on some great deals if you do not sign up!";
                    break;
                case 4:
                    titlenews.innerHTML = "Final chance to get some of these amazing deals";
                    break;
                case 5:
                    clearInterval(newsletter);
                    return;
            }
            newsCounter++;
            modalnews.style.display = "block";
        }
    }
    
</script>