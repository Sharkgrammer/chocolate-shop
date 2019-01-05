package servlets;

import data.stock;
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

public class stockServlet extends HttpServlet {
    
    databaseConnections database;
    
    public stockServlet() {
        database = new databaseConnections();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        stockDo(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        stockDo(request, response);
    }

    void stockDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int mode = Integer.valueOf(request.getParameter("mode"));
        int id;
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

                result = database.createStock(ParamsList.get(0), Integer.parseInt(ParamsList.get(1)), dateSql.toString());
                
                try (PrintWriter out = response.getWriter()) {
                    out.println(database.returnErrorMessage());
                    out.println(database.returnLastResult());
                }
                
                break;
            case 2:
                
                id = Integer.valueOf(request.getParameter("id"));
                stock stock = database.retrieveSingleStock(id);
                
                try (PrintWriter out = response.getWriter()) {
                    out.print(stock.getId() + ",");
                    out.print(stock.getChoco().getId() + ",");
                    out.print(stock.getAmount());
                    
                }
                
                break;
            case 6:
                id = Integer.valueOf(request.getParameter("id"));
                database.deleteStock(id);
                break;
        }
    }

}
