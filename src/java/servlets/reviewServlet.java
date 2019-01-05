package servlets;

import data.review;
import db.databaseConnections;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    void reviewDo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int mode = Integer.valueOf(request.getParameter("mode"));
        List<review> reviews;
        Enumeration<String> Params;
        List<String> ParamsList = new ArrayList<>();
        int id;

        switch (mode) {
            case 1:
                String paramStr;

                Params = request.getParameterNames();
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
                reviews = database.retrieveMultiReviews(6);
                request.setAttribute("revList", reviews);
                break;
            case 3:
                //return for admin
                id = Integer.valueOf(request.getParameter("id"));
                review rev = database.retrieveSingleReview(id);

                try (PrintWriter out = response.getWriter()) {
                    out.print(rev.getId() + ",");
                    out.print(rev.getChocoID() + ",");
                    out.print(rev.getUser_id() + ",");
                    out.print(rev.getData() + ",");
                    out.print(rev.getTitle() + ",");
                    out.print(rev.isLiked());
                }

                break;
            case 4:
                //update pls

                Params = request.getParameterNames();
                while (Params.hasMoreElements()) {
                    paramStr = Params.nextElement();

                    if (!paramStr.equals("submit") && !paramStr.equals("mode")) {
                        ParamsList.add(request.getParameter(paramStr));
                    }
                }

                Map mapRev = new HashMap() {
                    {
                        put("CHOCO_ID", Integer.parseInt(ParamsList.get(1)));
                        put("USER_ID", Integer.parseInt(ParamsList.get(2)));
                        put("REV_DATA", ParamsList.get(3));
                        put("REV_TITLE", ParamsList.get(4));
                        put("REV_POSTIVE", ParamsList.get(5).equals("true"));
                    }
                };

                database.updateReview(mapRev, Integer.parseInt(ParamsList.get(0).replace("ID: ", "")));
                request.getRequestDispatcher("/admin.jsp").forward(request, response);
                break;
            case 5:
                id = Integer.valueOf(request.getParameter("id"));
                database.deleteReview(id);
                request.getRequestDispatcher("/admin.jsp").forward(request, response);
                break;
        }
    }
}
