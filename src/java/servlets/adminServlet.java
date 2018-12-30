package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import data.*;
import db.databaseConnections;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class adminServlet extends HttpServlet {

    public adminServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        adminDo(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        adminDo(request, response);
    }

    void adminDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //output all data for charts pls

        databaseConnections database = new databaseConnections();
        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<>();
        List<graph> output = new ArrayList<>();

        List<review> reviews = database.retrieveAllReviews();
        graph graph;
        List<String> dataList = new ArrayList<>();
        String date;
        
        int loc = 0;
        for (review rev : reviews) {
            date = rev.getDate().replace("-", " ");
            if (!dataList.contains(date)) {
                dataList.add(date);
                dataList.add("1");
            } else {
                loc = dataList.indexOf(date) + 1;
                dataList.set(loc, String.valueOf(Integer.parseInt(dataList.get(loc)) + 1));
                continue;
            }
        }
        
        for (int x = 0; x < dataList.size(); x += 2) {
            map = new HashMap<>();
            map.put("x", dataList.get(x));
            map.put("y", dataList.get(x + 1));
            list.add(map);
        }

        graph = new graph(1);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Review Date");
        graph.setType("column");
        graph.setxLabel("Date");
        graph.setyLabel("Count");

        output.add(graph);

        /* map = new HashMap<Object, Object>();
        map.put("x", 10);
        map.put("y", 31);
        list.add(map);

        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("x", 40);
        map.put("y", 84);
        map.put("indexLabel", "Highest");*/
        request.setAttribute("output", output);

        //try (PrintWriter out = response.getWriter()) {
            //out.println(graph.getData());
        //}
        
    }

}
