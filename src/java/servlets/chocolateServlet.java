package servlets;

import data.chocolate;
import db.databaseConnections;
import java.io.IOException;
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
        
        int mode = Integer.valueOf(request.getParameter("mode"));
        choco = database.retrieveMultiChocolate(4, mode);
        
        request.setAttribute("list", choco);
        
        
        /*String attName = "name", attDesc = "desc", attImg = "img", counter = "";
        
        for (int x = 0; x < 4; x++){
            counter = String.valueOf(x);
            request.setAttribute(attName + x, x);
            //request.setAttribute(attDesc + x, choco.get(x).getDescription());
            //request.setAttribute(attImg + x, choco.get(x).getImage_folder());
        }//*/
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
       
    }		
}
