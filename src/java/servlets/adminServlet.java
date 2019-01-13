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
        List<review> reviews = database.retrieveAllReviews(0);
        List<purchase> purchases;
        List<stock> stocks = database.retrieveAllStocks(1);
        graph graph;
        List<String> dataList = new ArrayList<>();
        List<Integer> dataIntList = new ArrayList<>();

        //Review Date Graph code
        String date;
        int loc;
        int graphID = 0;

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
            map.put("label", toVisualDate(dataList.get(x), " "));
            map.put("y", Integer.parseInt(dataList.get(x + 1)));
            list.add(map);
        }

        graph = new graph(++graphID);
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
            if (rev.isLiked()) {
                dataIntList.set(0, dataIntList.get(0) + 1);
            } else {
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

        graph = new graph(++graphID);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Review - Scores");
        graph.setType("pie");
        output.add(graph);

        //Review per users
        reviews = database.retrieveAllReviews(1);

        String Name;
        dataList = new ArrayList<>();
        list = new ArrayList<>();

        for (review rev : reviews) {
            Name = rev.getUser() + " (ID: " + String.valueOf(rev.getUser_id()) + ")";
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

        graph = new graph(++graphID);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Reviews - Users");
        graph.setType("pie");
        output.add(graph);

        //Purchases per user
        dataList = new ArrayList<>();
        list = new ArrayList<>();
        purchases = database.retrieveAllPurchases(2);

        for (purchase purch : purchases) {
            Name = purch.getUser().getName() + " (ID: " + String.valueOf(purch.getUser().getId()) + ")";
            if (!dataList.contains(Name)) {
                dataList.add(Name);
                dataList.add(String.valueOf(purch.getAmount()));
            } else {
                loc = dataList.indexOf(Name) + 1;
                dataList.set(loc, String.valueOf(Integer.parseInt(dataList.get(loc)) + purch.getAmount()));
            }
        }

        for (int x = 0; x < dataList.size(); x += 2) {
            map = new HashMap<>();
            map.put("label", dataList.get(x));
            map.put("y", Integer.parseInt(dataList.get(x + 1)));
            list.add(map);
        }

        graph = new graph(++graphID);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Purchases - Users");
        graph.setType("pie");
        output.add(graph);

        //Purchases per chocolate
        dataList = new ArrayList<>();
        list = new ArrayList<>();
        purchases = database.retrieveAllPurchases(1);

        for (purchase purch : purchases) {
            Name = purch.getChoco().getName() + " (ID: " + String.valueOf(purch.getChoco().getId()) + ")";
            if (!dataList.contains(Name)) {
                dataList.add(Name);
                dataList.add(String.valueOf(purch.getAmount()));
            } else {
                loc = dataList.indexOf(Name) + 1;
                dataList.set(loc, String.valueOf(Integer.parseInt(dataList.get(loc)) + purch.getAmount()));
            }
        }

        for (int x = 0; x < dataList.size(); x += 2) {
            map = new HashMap<>();
            map.put("label", dataList.get(x));
            map.put("y", Integer.parseInt(dataList.get(x + 1)));
            list.add(map);
        }

        graph = new graph(++graphID);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Purchases - Amounts");
        graph.setType("bar");
        graph.setxLabel("Chocolate");
        graph.setyLabel("Purchases");
        output.add(graph);

        //Stock level amounts
        dataList = new ArrayList<>();
        list = new ArrayList<>();

        for (stock stock : stocks) {
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

        graph = new graph(++graphID);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Stock - Amounts");
        graph.setType("bar");
        graph.setxLabel("Chocolate");
        graph.setyLabel("Amount");
        output.add(graph);

        //Purchases per Date
        dataList = new ArrayList<>();
        list = new ArrayList<>();
        purchases = database.retrieveAllPurchases(3);

        for (purchase purch : purchases) {
            date = purch.getDate();
            if (!dataList.contains(date)) {
                dataList.add(date);
                dataList.add(String.valueOf(purch.getAmount()));
            } else {
                loc = dataList.indexOf(date) + 1;
                dataList.set(loc, String.valueOf(Integer.parseInt(dataList.get(loc)) + purch.getAmount()));
            }
        }

        for (int x = 0; x < dataList.size(); x += 2) {
            map = new HashMap<>();
            map.put("label", toVisualDate(dataList.get(x), "-"));
            map.put("y", Integer.parseInt(dataList.get(x + 1)));
            list.add(map);
        }

        graph = new graph(++graphID);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Purchases - Date");
        graph.setType("column");
        graph.setxLabel("Date");
        graph.setyLabel("Purchases");
        output.add(graph);

        //Stock level amounts with date
        dataList = new ArrayList<>();
        list = new ArrayList<>();

        stocks = database.retrieveAllStocks(0);
        for (stock stock : stocks) {
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
            map.put("label", toVisualDate(dataList.get(x), "-"));
            map.put("y", Integer.parseInt(dataList.get(x + 1)));
            list.add(map);
        }

        graph = new graph(++graphID);
        graph.setData(gsonObj.toJson(list));
        graph.setName("Stock - Date");
        graph.setType("column");
        graph.setxLabel("Date");
        graph.setyLabel("Amount");
        output.add(graph);

        request.setAttribute("output", output);
        request.setAttribute("stockCount", database.retrieveCountStocks());
        request.setAttribute("revCount", database.retrieveCountReviews());
        request.setAttribute("chocCount", database.retrieveCountChocolate());

        /*
        try (PrintWriter out = response.getWriter()) {
            out.println(graph.getData());
            out.println(database.returnErrorMessage());
            out.println(database.returnLastResult());
        }//*/
    }

    private String toVisualDate(String date, String s) {
        String returnDate = "";
        String[] dateArr = date.split(s);
        for (int x = dateArr.length - 1; x >= 0; x--) {
            returnDate += dateArr[x];
            if (x != 0) {
                returnDate += "/";
            }
        }
        return returnDate;
    }

}
