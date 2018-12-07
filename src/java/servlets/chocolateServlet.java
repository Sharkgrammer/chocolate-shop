package servlets;

import data.chocolate;
import db.databaseConnections;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class chocolateServlet extends HttpServlet {
    databaseConnections database;
    List<chocolate> choco;
    
    public chocolateServlet(){
        database = new databaseConnections();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chocolateDo(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        chocolateDo(request,response);
    }	
    
    void chocolateDo(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        int mode = Integer.valueOf(request.getParameter("mode"));
        switch(mode){
            case 1:
                choco = database.retrieveMultiChocolate(4, mode);
                request.setAttribute("list", choco);
                break;
            case 2:
                Enumeration<String> headers = request.getParameterNames();
                try (PrintWriter out = response.getWriter()) {
                    while (headers.hasMoreElements()){
                        String headerStr = headers.nextElement();
                        out.println(headerStr + "  " + request.getParameter(headerStr));
                    }
                }
        
        }
        
    }
    
    
}
