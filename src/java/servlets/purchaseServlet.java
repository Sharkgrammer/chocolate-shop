package servlets;

import data.purchase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class purchaseServlet extends HttpServlet {

    public purchaseServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        purchaseDo(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        purchaseDo(request, response);
    }

    void purchaseDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //do purchase stuff
        int mode = Integer.valueOf(request.getParameter("mode"));
        Cookie[] cookies;
        String cookieStr = "";
        switch (mode) {
            case 1:
                //Add to the cart
                int id = Integer.valueOf(request.getParameter("id"));
                float amt = Integer.valueOf(request.getParameter("amt"));
                
                cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("cart")) {
                            cookieStr = cookie.getValue();
                            break;
                        }
                    }
                }
                
                response.addCookie(new Cookie("cart", cookieStr + "@" + String.valueOf(id) + "-" + String.valueOf(amt)));
                
                break;
            case 2:
                //Retrieve all cart stuff
                
                cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("cart")) {
                            cookieStr = cookie.getValue();
                            break;
                        }
                    }
                }
                
                List<purchase> purchList = new ArrayList<>();
                purchase purch;
                for (String item : cookieStr.split("@")){
                    purch = new purchase();
                    
                }
                
                request.setAttribute("purchList", mode);
                break;
        }
    }

}