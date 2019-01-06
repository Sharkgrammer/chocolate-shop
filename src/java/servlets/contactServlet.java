package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class contactServlet extends HttpServlet {

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

    void contactDo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //do contact code here pls
        int mode = Integer.valueOf(request.getParameter("mode"));
        Enumeration<String> Params;
        String paramStr;
        List<String> ParamsList = new ArrayList<>();

        switch (mode) {
            case 1:
                //handle contact form 
                Params = request.getParameterNames();
                while (Params.hasMoreElements()) {
                    paramStr = Params.nextElement();

                    if (!paramStr.equals("submit") && !paramStr.equals("mode")) {
                        ParamsList.add(request.getParameter(paramStr));
                    }
                }

                String Email = ParamsList.get(0),
                 QuerySub = ParamsList.get(1),
                 QueryMsg = ParamsList.get(2);

                boolean res = SendEmail(Email, "Thank you for your inquiry: " + QuerySub,
                        "A Sharkolate representative will return to you soon on your query. Your message:</br> <q>'" + QueryMsg + "'</q>");

                if (res) {
                    request.setAttribute("resultContact", "Thank you for your query");
                } else {
                    request.setAttribute("resultContact", "Error: Please try again");
                }
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case 2:
                //handle newsletter form
                Params = request.getParameterNames();
                while (Params.hasMoreElements()) {
                    paramStr = Params.nextElement();

                    if (!paramStr.equals("submit") && !paramStr.equals("mode")) {
                        ParamsList.add(request.getParameter(paramStr));
                    }
                }

                String EmailTo = ParamsList.get(0);
                SendEmail(EmailTo, "Thank you for joining our newsletter", "Did you know sharks enjoy chocolate?");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
        }
    }

    boolean SendEmail(String toAdd, String Subject, String MessageData) {
        String result = "", fromAdd = "sharkolatechocolates@gmail.com", pass = "Sharks11/1/98";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", fromAdd);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);

        Session session = Session.getInstance(props, null);
        MimeMessage message = new MimeMessage(session);

        try {
            InternetAddress from = new InternetAddress(fromAdd);
            message.setSubject(Subject);
            message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(toAdd));
            Multipart multipart = new MimeMultipart("alternative");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(MessageData);
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String htmlMessage = MessageData;
            messageBodyPart.setContent(htmlMessage, "text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", fromAdd, pass);
            transport.sendMessage(message, message.getAllRecipients());

        } catch (Exception e) {
            result = e.toString();
        }

        return result.equals("");
    }

}
