/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.chocolate;
import db.databaseConnections;
import java.io.IOException;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get single choco data here and return a page w/ the data
        int id = Integer.valueOf(request.getParameter("id")), userid = 0;
        chocolate choco = database.retrieveSingleChocolate(id);
        
        Cookie cookie = getCookie("id", request);
        if (cookie != null){
            userid = Integer.valueOf(cookie.getValue());
        }
        
        request.setAttribute("chocoShop", choco);
        request.setAttribute("chocoImages", choco.getImageStringsCara());
        request.setAttribute("userid", userid);
        request.getRequestDispatcher("/shop-item.jsp").forward(request, response);
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
}