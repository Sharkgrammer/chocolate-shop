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
        List<purchase> purchases = database.retrieveAllPurchases();
        List<stock> stocks = database.retrieveAllStocks();
        graph graph;
        List<String> dataList = new ArrayList<>();
        List<Integer> dataIntList = new ArrayList<>();
        
        //Review Date Graph code
        String date;
        int loc;
        
        for (review rev : reviews) {
            date = rev.getDate().replace("-", " ");
            if (!dataList.contains(date)) {
                dataList.add(date);
                dataList.add("1");
            } else {
                loc = dataList.indexOf(date) + 1;
                dataList.set(loc, String.valueOf(Integer.parseInt(dataList.get(loc)) + 1));
            }
        }
        
        for (int x = 0; x < dataList.size(); x += 2) {
            map = new HashMap<>();
            map.put("label", toVisualDate(dataList.get(x)));
            map.put("y", Integer.parseInt(dataList.get(x + 1)));
            list.add(map);
        }

        graph = new graph(1);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Review - Dates");
        graph.setType("column");
        graph.setxLabel("Date");
        graph.setyLabel("Count");
        output.add(graph);
        
        //Review Date Graph code
        list = new ArrayList<>();
        dataIntList.add(0);
        dataIntList.add(0);
        
        for (review rev : reviews) {
            if (rev.isLiked()){
                dataIntList.set(0, dataIntList.get(0) + 1);
            }else{
                dataIntList.set(1, dataIntList.get(1) + 1);
            }
        }
 
        map = new HashMap<>();
        map.put("label", "Positive");
        map.put("y", dataIntList.get(0));
        list.add(map);
        
        map = new HashMap<>();
        map.put("label", "Negative");
        map.put("y", dataIntList.get(1));
        list.add(map);
        
        graph = new graph(2);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Review - Scores");
        graph.setType("pie");
        output.add(graph);
        
        //Purchases per chocolate
        dataList = new ArrayList<>();
        list = new ArrayList<>();
        String Name;
        
        for (purchase purch : purchases){
            Name = purch.getChoco().getName() + " (ID: " + String.valueOf(purch.getChoco().getId()) + ")";
            if (!dataList.contains(Name)) {
                dataList.add(Name);
                dataList.add("1");
            } else {
                loc = dataList.indexOf(Name) + 1;
                dataList.set(loc, String.valueOf(Integer.parseInt(dataList.get(loc)) + 1));
            }
        }
        
        for (int x = 0; x < dataList.size(); x += 2) {
            map = new HashMap<>();
            map.put("label", dataList.get(x));
            map.put("y", Integer.parseInt(dataList.get(x + 1)));
            list.add(map);
        }
        
        graph = new graph(3);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Purchases - Chocolate");
        graph.setType("bar");
        graph.setxLabel("Chocolate");
        graph.setyLabel("Purchases");
        output.add(graph);
        
        //Purchases per user
        dataList = new ArrayList<>();
        list = new ArrayList<>();
        
        for (purchase purch : purchases){
            Name = purch.getUser().getName() + " (ID: " + String.valueOf(purch.getUser().getId()) + ")";
            if (!dataList.contains(Name)) {
                dataList.add(Name);
                dataList.add("1");
            } else {
                loc = dataList.indexOf(Name) + 1;
                dataList.set(loc, String.valueOf(Integer.parseInt(dataList.get(loc)) + 1));
            }
        }
        
        for (int x = 0; x < dataList.size(); x += 2) {
            map = new HashMap<>();
            map.put("label", dataList.get(x));
            map.put("y", Integer.parseInt(dataList.get(x + 1)));
            list.add(map);
        }
        
        graph = new graph(4);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Purchases - Users");
        graph.setType("bar");
        graph.setxLabel("Users");
        graph.setyLabel("Purchases");
        output.add(graph);
        
        //Stock level amounts
        dataList = new ArrayList<>();
        list = new ArrayList<>();
        
        for (stock stock : stocks){
            Name = stock.getChoco().getName() + " (ID: " + String.valueOf(stock.getChoco().getId()) + ")";
            if (!dataList.contains(Name)) {
                dataList.add(Name);
                dataList.add(String.valueOf(stock.getAmount()));
            } else {
                loc = dataList.indexOf(Name) + 1;
                dataList.set(loc, String.valueOf(Integer.parseInt(dataList.get(loc)) + stock.getAmount()));
            }
        }
        
        for (int x = 0; x < dataList.size(); x += 2) {
            map = new HashMap<>();
            map.put("label", dataList.get(x));
            map.put("y", Integer.parseInt(dataList.get(x + 1)));
            list.add(map);
        }
        
        graph = new graph(5);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Stock - Amounts");
        graph.setType("column");
        graph.setxLabel("Chocolate");
        graph.setyLabel("Amount");
        output.add(graph);
        
        //Stock level amounts
        dataList = new ArrayList<>();
        list = new ArrayList<>();
        
        for (stock stock : stocks){
            date = stock.getDate();
            if (!dataList.contains(date)) {
                dataList.add(date);
                dataList.add(String.valueOf(stock.getAmount()));
            } else {
                loc = dataList.indexOf(date) + 1;
                dataList.set(loc, String.valueOf(Integer.parseInt(dataList.get(loc)) + stock.getAmount()));
            }
        }
        
        for (int x = 0; x < dataList.size(); x += 2) {
            map = new HashMap<>();
            map.put("label", toVisualDate(dataList.get(x)));
            map.put("y", Integer.parseInt(dataList.get(x + 1)));
            list.add(map);
        }
        
        graph = new graph(6);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Stock - Date");
        graph.setType("column");
        graph.setxLabel("Date");
        graph.setyLabel("Amount");
        output.add(graph);
        
        request.setAttribute("output", output);
        
      /*
        try (PrintWriter out = response.getWriter()) {
            out.println(graph.getData());
            out.println(database.returnErrorMessage());
            out.println(database.returnLastResult());
        }//*/
    }
    
    private String toVisualDate(String date){
        String returnDate = "";
        String[] dateArr = date.split(" ");
        for (int x = dateArr.length - 1 ; x >= 0; x--){
            returnDate += dateArr[x];
            if (x != 0){
                returnDate += "/";
            }
        }
        return returnDate;
    }

}
