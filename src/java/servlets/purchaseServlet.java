package servlets;

import data.purchase;
import db.databaseConnections;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class purchaseServlet extends HttpServlet {

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

    void purchaseDo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //do purchase stuff
        int mode = Integer.valueOf(request.getParameter("mode"));
        String cookieStr;
        databaseConnections database;
        switch (mode) {
            case 1:
                //Add to the cart
                int id = Integer.valueOf(request.getParameter("id"));
                int amt = Integer.valueOf(request.getParameter("amt"));
                
                cookieStr = getCartCookie(request);

                response.addCookie(new Cookie("cart", cookieStr + "@" + String.valueOf(id) + "-" + String.valueOf(amt)));
                request.setAttribute("result", "Items added to cart");
                request.getRequestDispatcher("/shopServlet").forward(request, response);
                //solution to thing needs to go here
                break;
            case 2:
                //Retrieve all cart stuff
                database = new databaseConnections();
                cookieStr = getCartCookie(request);

                List<purchase> purchList = new ArrayList<>();
                String item;
                purchase purch;
                for (int x = 1; x < cookieStr.split("@").length; x++) {
                    item = cookieStr.split("@")[x];
                    purch = new purchase();
                    purch.setChoco(database.retrieveSingleChocolate(Integer.parseInt(item.split("-")[0])));
                    purch.setAmount(Integer.parseInt(item.split("-")[1]));
                    purch.setCart(x);
                    purchList.add(purch);
                }

                request.setAttribute("purchList", purchList);
                request.setAttribute("purchCount", purchList.size());
                break;
            case 3:
                cookieStr = getCartCookie(request);
                database = new databaseConnections();
                boolean pass = true;
                for (int x = 1; x < cookieStr.split("@").length; x++) {
                    item = cookieStr.split("@")[x];
                    if (!database.createPurchase(Integer.parseInt(item.split("-")[0]), Integer.parseInt(getIdCookie(request)), item.split("-")[1])){
                        pass = false;
                    }
                }
                if (pass){
                    Cookie cookie = getCookie("cart", request);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                
                request.setAttribute("result", "Items Bought!");
                request.getRequestDispatcher("/purchase.jsp").forward(request, response);
                
                break;
        }
    }
    
    private Cookie getCookie(String name, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    
    private String getCartCookie(HttpServletRequest request){
        try{
            return getCookie("cart",request).getValue();
        }catch(Exception e){
            //only runs at the start, if blank
            return "";
        }
    }
    
    private String getIdCookie(HttpServletRequest request){
        return getCookie("id",request).getValue();
    }

}