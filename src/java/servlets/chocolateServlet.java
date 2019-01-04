package servlets;

import data.chocolate;
import db.databaseConnections;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

public class chocolateServlet extends HttpServlet {

    databaseConnections database;
    List<chocolate> choco;

    public chocolateServlet() {
        database = new databaseConnections();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chocolateDo(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chocolateDo(request, response);
    }

    void chocolateDo(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ///*
        int mode = Integer.valueOf(request.getParameter("mode")), filter = Integer.valueOf(request.getParameter("filt"));
        switch (mode) {
            case 1:
                choco = database.retrieveMultiChocolate(4, filter);
                request.setAttribute("listNew", choco);
                break;
            case 2:
                String paramStr;
                List<Blob> imageList = new ArrayList<>();
                List<String> ParamsList = new ArrayList<>();

                Enumeration<String> Params = request.getParameterNames();
                while (Params.hasMoreElements()) {
                    paramStr = Params.nextElement();

                    if (!(paramStr.equals("img") || paramStr.equals("submit") || paramStr.equals("mode") || paramStr.equals("filt"))) {
                        ParamsList.add(request.getParameter(paramStr));
                    } else if (paramStr.equals("img")) {
                        Blob blob = null;
                        for (int x = 1; x < request.getParameter(paramStr).split("data:").length; x++){
                            String imageStr = request.getParameter(paramStr).split("data:")[x];
                            try {
                                imageStr = "data:" + imageStr; 
                                blob = new SerialBlob(imageStr.getBytes());
                            } catch (SQLException ex) {
                                try (PrintWriter out = response.getWriter()) {
                                    out.print(ex.toString());
                                }
                            }
                            imageList.add(blob);
                        }
                    }
                }

                Date dateSys = new Date(System.currentTimeMillis());
                java.sql.Date dateSql = new java.sql.Date(dateSys.getTime());

                boolean result = false;

                result = database.createChocolate(ParamsList.get(0), ParamsList.get(1), ParamsList.get(2),
                        ParamsList.get(5), ParamsList.get(3), ParamsList.get(4), dateSql.toString(), ParamsList.get(6), imageList);

                try (PrintWriter out = response.getWriter()) {
                    out.println(imageList.size());
                    out.println(database.returnErrorMessage());
                    out.println(database.returnLastResult());
                }
                
                break;
            case 3:
                choco = database.retrieveMultiChocolate(5, filter);
                List<chocolate> choco2 = new ArrayList<>();
                for (int x = 1; x < choco.size();  x++){
                    choco2.add(choco.get(x));
                }
                
                request.setAttribute("firstChoco", choco.get(0));
                request.setAttribute("listCara", choco2);
                break;
            case 4:
                choco = database.retrieveAllChocolate();
                //choco = database.retrieveMultiChocolate(5, filter);
                request.setAttribute("listShop", choco);
                break;

        }//*/

    }
}
