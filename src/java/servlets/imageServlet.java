package servlets;

import db.databaseConnections;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class imageServlet extends HttpServlet {
    
    
    databaseConnections database;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //handle image code here
        database = new databaseConnections();
        int id = Integer.valueOf(request.getParameter("id"));
        try{
            database.setAutoCommit();
            
            Blob blob = database.retrieveImage(id);
            byte [] blobBytes = blob.getBytes(1, (int) blob.length());
            String contentType = "", baseImage = ""; char c; int content = 0;
            for (byte b : blobBytes){
                c = (char) b;
                
                if (c == ';'){
                    content = 1;
                    continue;
                }else if (c == ','){
                    content = 2;
                    continue;
                }
                
                
                if (content == 0){
                    contentType += c;
                }else if (content == 2){
                    baseImage += c;
                }
                
            }
            
            baseImage = "data:" + contentType + ";base64," + baseImage;
            
            try (PrintWriter out = response.getWriter()) {
                 out.print(baseImage);
             }
            
            
            
            /*BufferedImage img = ImageIO.read(new ByteArrayInputStream(baseImage.getBytes()));
            
            //InputStream input = blob.getBinaryStream();
            OutputStream output = response.getOutputStream();
            
            response.setContentType(contentType);
            
            output.write(baseImage.getBytes());*/
            
        } catch (Exception ex) {
             try (PrintWriter out = response.getWriter()) {
                 out.print(ex.toString());
             }
        }
        
    }

}
