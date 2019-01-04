/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.chocolate;
import db.databaseConnections;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gills
 */
public class shopServlet extends HttpServlet {

    databaseConnections database;

    public shopServlet() {
        database = new databaseConnections();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        shopDo(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        shopDo(request, response);
    }

    void shopDo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int mode = Integer.valueOf(request.getParameter("mode"));
        switch (mode) {
            case 1:
                //get single choco data here and return a page w/ the data
                int id = Integer.valueOf(request.getParameter("id")),
                 userid = 0;
                chocolate choco = database.retrieveSingleChocolate(id);

                Cookie cookie = getCookie("id", request);
                if (cookie != null) {
                    userid = Integer.valueOf(cookie.getValue());
                }

                request.setAttribute("chocoShop", choco);
                request.setAttribute("chocoImages", choco.getImageStringsCara());
                request.setAttribute("userid", userid);
                request.getRequestDispatcher("/shop-item.jsp").forward(request, response);
                break;
            case 2:
                //Assume search and filter options here
                //Shop.jsp is self sufficent, normally. Pass filts and other options into chocolate servlet

                String search = request.getParameter("search");
                List<chocolate> chocoList = database.retrieveSearchChocolate(search);
                
                request.setAttribute("listShop", chocoList);
                request.setAttribute("options", 0);
                request.getRequestDispatcher("/shop.jsp").forward(request, response);
                break;
        }
    }

    private Cookie getCookie(String name, HttpServletRequest request) {
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
}
