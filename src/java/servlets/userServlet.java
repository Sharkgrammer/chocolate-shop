package servlets;

import data.user;
import db.databaseConnections;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class userServlet extends HttpServlet {

    databaseConnections database;
    List<user> user;

    public userServlet() {
        database = new databaseConnections();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userDo(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userDo(request, response);
    }

    void userDo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int mode = Integer.valueOf(request.getParameter("mode"));
        switch (mode) {
            case 1:
                //Creating Users
                String paramStr;
                List<String> ParamsList = new ArrayList<>();

                Enumeration<String> Params = request.getParameterNames();
                while (Params.hasMoreElements()) {
                    paramStr = Params.nextElement();

                    if (!paramStr.equals("submit") && !paramStr.equals("mode")) {
                        ParamsList.add(request.getParameter(paramStr));
                    }
                }

                boolean result = false;

                result = database.createUser(ParamsList.get(0), ParamsList.get(1), ParamsList.get(2), "USER", ParamsList.get(3));
                
                if (result){
                    request.setAttribute("boop", "Success");
                }
                
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                
                break;
            case 2:
                //Logging in
                
                String email = request.getParameter("emai"), pass = request.getParameter("pass");
                
                int ID = database.login(email, pass);
                
                if (ID != 0){
                    request.setAttribute("boop", ID);
                }
                
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                
                break;
        }
    }
}
