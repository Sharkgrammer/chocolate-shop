package servlets;

import data.review;
import db.databaseConnections;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class reviewServlet extends HttpServlet {

    databaseConnections database;

    public reviewServlet() {
        database = new databaseConnections();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reviewDo(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reviewDo(request, response);
    }

    void reviewDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int mode = Integer.valueOf(request.getParameter("mode"));
        switch (mode) {
            case 1:
                String paramStr;
                List<String> ParamsList = new ArrayList<>();

                Enumeration<String> Params = request.getParameterNames();
                while (Params.hasMoreElements()) {
                    paramStr = Params.nextElement();

                    if (!paramStr.equals("submit") && !paramStr.equals("mode")) {
                        ParamsList.add(request.getParameter(paramStr));
                    }
                }

                Date dateSys = new Date(System.currentTimeMillis());
                java.sql.Date dateSql = new java.sql.Date(dateSys.getTime());
                boolean result = false;

                result = database.createReview(Integer.parseInt(ParamsList.get(0)), Integer.parseInt(ParamsList.get(1)), dateSql.toString(), ParamsList.get(2),
                        ParamsList.get(3), ParamsList.get(4).equals("true"));
                
                try (PrintWriter out = response.getWriter()) {
                    out.println(database.returnErrorMessage());
                    out.println(database.returnLastResult());
                }
                
                break;
            case 2:
                List<review> review = database.retrieveMultiReviews(6);
                request.setAttribute("revList", review);
                
                break;
        }
    }
}
