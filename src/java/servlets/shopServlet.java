package servlets;

import data.chocolate;
import db.databaseConnections;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String search;
        List<chocolate> chocoList;
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
                //Search stuff
                search = request.getParameter("search");
                chocoList = database.retrieveSearchChocolate(search);

                request.setAttribute("listShop", chocoList);
                request.setAttribute("options", 0);
                request.getRequestDispatcher("/shop.jsp").forward(request, response);
                break;

            case 3:
                //search return for textbox
                search = request.getParameter("search");

                if (search.equals("")) {
                    chocoList = null;
                } else {
                    chocoList = database.retrieveSearchChocolate(search, 10);
                }

                try (PrintWriter out = response.getWriter()) {
                    for (chocolate chocoLoop : chocoList) {
                        out.print(chocoLoop.getName() + ",");
                    }
                }
                break;
            case 4:
                //Populate the filters here pls
                List<List<String>> List = database.retrieveChocolateFilters();

                request.setAttribute("listType", List.get(0));
                request.setAttribute("listProd", List.get(1));
                request.setAttribute("listFlav", List.get(2));

                if (request.getParameter("search") == null) {
                    request.setAttribute("resultType", 0);
                    request.setAttribute("resultProd", 0);
                    request.setAttribute("resultFlav", 0);
                }

                break;
            case 5:
                //return based on types
                search = request.getParameter("search");
                chocoList = database.retrieveTypeChocolate(search);

                request.setAttribute("listShop", chocoList);
                request.setAttribute("options", 0);
                request.setAttribute("resultType", search);
                request.setAttribute("resultProd", 0);
                request.setAttribute("resultFlav", 0);
                request.getRequestDispatcher("/shop.jsp").forward(request, response);
                break;
            case 6:
                //return based on prod
                search = request.getParameter("search");
                chocoList = database.retrieveProducerChocolate(search);

                request.setAttribute("listShop", chocoList);
                request.setAttribute("options", 0);
                request.setAttribute("resultType", 0);
                request.setAttribute("resultProd", search);
                request.setAttribute("resultFlav", 0);
                request.getRequestDispatcher("/shop.jsp").forward(request, response);
                break;
            case 7:
                //return based on flavour
                search = request.getParameter("search");
                chocoList = database.retrieveFlavourChocolate(search);

                request.setAttribute("listShop", chocoList);
                request.setAttribute("options", 0);
                request.setAttribute("resultType", 0);
                request.setAttribute("resultProd", 0);
                request.setAttribute("resultFlav", search);
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
