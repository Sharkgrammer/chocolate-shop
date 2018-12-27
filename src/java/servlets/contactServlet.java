package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class contactServlet extends HttpServlet {

    public contactServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        contactDo(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        contactDo(request, response);
    }

    void contactDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //do contact code here pls
        int mode = Integer.valueOf(request.getParameter("mode"));
        switch (mode) {
            case 1:
                //handle contact form
        
                break;
            case 2:
                //handle newsletter form

                break;
        }
    }

}
