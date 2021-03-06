package db;

import java.sql.*;
import data.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class databaseConnections {

    //database details
    static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String DB_URL = "jdbc:derby://localhost:1527/chocolateshop";
    static final String USER = "chocolate";
    static final String PASS = "chocolate";
    private Connection conn;
    private String errorMessage;
    private String resultMessage;
    private String sql;
    private boolean sqlSuccess;
    private List<String> updateControl;

    //Make the database connetion here
    public databaseConnections() {
        //set vars here, in case
        conn = null;
        errorMessage = "";
        resultMessage = "";
        sql = "";
        sqlSuccess = false;
        updateControl = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            errorMessage = e.toString();
            System.out.println(e.toString());
        }
    }

    public void setAutoCommit() throws SQLException {
        conn.setAutoCommit(false);
    }

    //Misc operations related to the databse go here
    public String returnErrorMessage() {
        return errorMessage;
    }

    public String returnLastResult() {
        return sql + " | " + resultMessage + " | " + sqlSuccess;
    }

    private void sqlSuccessHandler(String fuctName) {
        if (sqlSuccess) {
            resultMessage = fuctName + ":Complete";
        } else {
            resultMessage = fuctName + ":Failed";
            errorMessage = resultMessage;
        }
    }

    private void sqlSuccessHandler(String fuctName, SQLException ex) {
        resultMessage = fuctName + ":Failed";
        sqlSuccess = false;
        errorMessage = ex.toString();
        System.out.println(errorMessage);
    }

    public int login(String email, String pass) {
        List<user> userList = retrieveAllUsers();
        int result = 0;

        for (user x : userList) {
            if (x.getEmail().equals(email) && x.getPassword().equals(pass)) {
                result = x.getId();
                break;
            }
        }

        return result;
    }

    public boolean isAdmin(int ID) {
        String type = "", funtName = "Check Admin";
        sql = "select user_type from users where user_id = " + ID;
        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while (rs.next()) {
                type = rs.getString("USER_TYPE");
            }

        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return type.equals("ADMIN");
    }

    public String getAuthKey(int ID) {
        String key = "", funtName = "Retrieve Auth Key";
        sql = "select user_key from users where user_id = " + ID;
        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while (rs.next()) {
                key = rs.getString("USER_KEY");
            }

        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return key;
    }

    public boolean setAuthKey(String key, int ID) {
        Map map = new HashMap() {
            {
                put("USER_KEY", key);
            }
        };
        return updateUser(map, ID);
    }

    public boolean checkAuthKey(String key, int ID) {
        return key.equals(getAuthKey(ID));
    }

    //Create operations
    public boolean createUser(String Name, String Email, String Password, String Type, String Address) {
        String funtName = "Create User";
        sql = "insert into users(user_name,user_email,user_password,user_type,user_address) values (?,?,?,?,?)";
        try {
            PreparedStatement query = conn.prepareStatement(sql);

            query.setString(1, Name);
            query.setString(2, Email);
            query.setString(3, Password);
            query.setString(4, Type);
            query.setString(5, Address);

            sqlSuccess = query.executeUpdate() == 1;
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean createChocolate(String Name, String Desc, String Type, String Flavour, String Weight, String Producer, String Date, String Price, List<Blob> imageList) {
        String funtName = "Create Chocolate";

        sql = "insert into chocolate(CHOCO_NAME,CHOCO_DESC,CHOCO_TYPE,CHOCO_FLAVOUR,CHOCO_WEIGHT,CHOCO_PRODUCER,CHOCO_DATE_ENTERED, CHOCO_PRICE) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement query = conn.prepareStatement(sql, new String[]{"CHOCO_ID"});

            query.setString(1, Name);
            query.setString(2, Desc);
            query.setString(3, Type);
            query.setString(4, Flavour);
            query.setString(5, Weight);
            query.setString(6, Producer);
            query.setString(7, Date);
            query.setFloat(8, Float.parseFloat(Price));

            sqlSuccess = query.executeUpdate() == 1;

            ResultSet key = query.getGeneratedKeys();

            int keyInt = 0, x = 1;

            while (key.next()) {
                keyInt += key.getInt(x);
            }

            for (Blob image : imageList) {
                try {
                    sql = "insert into image(choco_id, img_img) values (?,?)";
                    query = conn.prepareStatement(sql);
                    query.setInt(1, keyInt);
                    query.setBlob(2, image);
                    sqlSuccess = !query.execute();

                } catch (SQLException ex) {
                    sqlSuccessHandler("Add Image", ex);
                }
            }

            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean createImage(int chocoID, String image) {
        String funtName = "Create Image";
        sql = "insert into image(choco_id, img_img) values (" + chocoID + "," + image + ")";
        try {
            Statement query = conn.createStatement();
            sqlSuccess = query.execute(sql);
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean createReview(int chocoID, int userID, String date, String data, String title, boolean pos) {
        String funtName = "Create Review";
        sql = "insert into review(choco_id, user_id, rev_date, rev_data, rev_title, rev_postive) values (?,?,?,?,?,?)";
        try {
            PreparedStatement query = conn.prepareStatement(sql);

            query.setInt(1, chocoID);
            query.setInt(2, userID);
            query.setString(3, date);
            query.setString(4, data);
            query.setString(5, title);
            query.setBoolean(6, pos);

            sqlSuccess = query.executeUpdate() == 1;
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean createPurchase(int chocoID, int userID, String amt) {
        String funtName = "Create Purchase";
        sql = "insert into purchases(CHOCO_ID,USER_ID,PURCHASE_AMOUNT,PURCHASE_DATE) values (?,?,?,?)";

        java.util.Date dateSys = new java.util.Date(System.currentTimeMillis());
        java.sql.Date dateSql = new java.sql.Date(dateSys.getTime());

        try {

            PreparedStatement query = conn.prepareStatement(sql);

            query.setInt(1, chocoID);
            query.setInt(2, userID);
            query.setString(3, amt);
            query.setString(4, dateSql.toString());

            sqlSuccess = query.executeUpdate() == 1;
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean createStock(String ChocoID, int Amt, String Date) {
        String funtName = "Create Stock";
        sql = "insert into stocks(CHOCO_ID,STOCK_AMOUNT,STOCK_DATE_ENTERED) values (?,?,?)";
        try {
            PreparedStatement query = conn.prepareStatement(sql);

            query.setString(1, ChocoID);
            query.setInt(2, Amt);
            query.setString(3, Date);

            sqlSuccess = query.executeUpdate() == 1;
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    //Retrieve operations
    public List<user> retrieveAllUsers() {
        return retrieveUsersInternal(0);
    }

    public user retrieveSingleUser(int id) {
        return retrieveUsersInternal(id).get(0);
    }

    private List<user> retrieveUsersInternal(int id) {
        String funtName = "Retrieve All Users";
        List<user> List = new ArrayList<>();

        if (id == 0) {
            sql = "select * from users";
        } else {
            sql = "select * from users where user_id = " + id;
        }

        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            user user;
            while (rs.next()) {
                user = new user();
                user.setId(rs.getInt("USER_ID"));
                user.setName(rs.getString("USER_NAME"));
                user.setEmail(rs.getString("USER_EMAIL"));
                user.setPassword(rs.getString("USER_PASSWORD"));
                user.setType(rs.getString("USER_TYPE"));
                user.setAddress(rs.getString("USER_ADDRESS"));
                List.add(user);
            }

        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        sqlSuccess = !List.isEmpty();
        sqlSuccessHandler(funtName);

        return List;
    }

    public List<chocolate> retrieveAllChocolate(int mode) {
        return retrieveChocolateInternal(0, 0, "", mode, "", "", "");
    }

    public chocolate retrieveSingleChocolate(int id) {
        try {
            return retrieveChocolateInternal(id, 0, "", 0, "", "", "").get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public List<chocolate> retrieveMultiChocolate(int amt, int mode) {
        return retrieveChocolateInternal(0, amt, "", mode, "", "", "");
    }

    public List<chocolate> retrieveSearchChocolate(String name) {
        return retrieveChocolateInternal(0, 0, name, 0, "", "", "");
    }

    public List<chocolate> retrieveSearchChocolate(String name, int amt) {
        return retrieveChocolateInternal(0, amt, name, 0, "", "", "");
    }

    public List<chocolate> retrieveSearchChocolate(String name, int amt, int mode) {
        return retrieveChocolateInternal(0, amt, name, mode, "", "", "");
    }

    public List<chocolate> retrieveTypeChocolate(String Type) {
        return retrieveChocolateInternal(0, 0, "", 0, Type, "", "");
    }

    public List<chocolate> retrieveFlavourChocolate(String Flavour) {
        return retrieveChocolateInternal(0, 0, "", 0, "", Flavour, "");
    }

    public List<chocolate> retrieveProducerChocolate(String Producer) {
        return retrieveChocolateInternal(0, 0, "", 0, "", "", Producer);
    }

    public int retrieveCountChocolate() {
        String funtName = "Retrieve Chocolate Count";
        sql = "select CHOCO_ID from chocolate ORDER BY CHOCO_ID DESC FETCH FIRST 1 ROWS ONLY";
        int result = 0;
        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while (rs.next()) {
                result = rs.getInt("CHOCO_ID");
            }
            sqlSuccess = result != 0;

            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }
        return result;
    }

    private List<chocolate> retrieveChocolateInternal(int id, int amt, String name, int mode, String type, String flavour, String producer) {
        String funtName = "Retrieve Chocolate";
        List<chocolate> List = new ArrayList<>();

        if (id == 0) {
            sql = "select * from chocolate";
        } else {
            sql = "select * from chocolate where choco_id = " + id;
        }

        if (!name.equals("")) {
            sql += " where UPPER(CHOCO_NAME) like UPPER('%" + name + "%')";
        }

        if (!type.equals("")) {
            sql += " where UPPER(CHOCO_TYPE) like UPPER('%" + type + "%')";
        }

        if (!flavour.equals("")) {
            sql += " where UPPER(CHOCO_FLAVOUR) like UPPER('%" + flavour + "%')";
        }

        if (!producer.equals("")) {
            sql += " where UPPER(CHOCO_PRODUCER) like UPPER('%" + producer + "%')";
        }

        if (mode != 0) {
            switch (mode) {
                case 1:
                    sql += " order by CHOCO_DATE_ENTERED asc";
                    break;
                case 2:
                    sql += " order by CHOCO_DATE_ENTERED desc";
                    break;
                case 3:
                    sql += " order by CHOCO_TYPE";
                    break;
                case 4:
                    sql += " order by CHOCO_WEIGHT";
                    break;
                case 5:
                    sql += " order by CHOCO_PRODUCER";
                    break;
                case 6:
                    sql += " order by CHOCO_FLAVOURT";
                    break;
                case 7:
                    sql += " order by CHOCO_ID asc";
                    break;
                case 8:
                    sql += " order by CHOCO_ID desc";
                    break;
            }
        }

        if (amt != 0) {
            sql += " fetch first " + amt + " rows only";
        }

        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql), rsImg, rsRev;
            chocolate choco;
            review review;
            List<Integer> imageList;
            List<review> reviewList;

            while (rs.next()) {
                choco = new chocolate();
                choco.setId(rs.getInt("CHOCO_ID"));
                choco.setName(rs.getString("CHOCO_NAME"));
                choco.setDescription(rs.getString("CHOCO_DESC"));
                choco.setType(rs.getString("CHOCO_TYPE"));
                choco.setFlavour(rs.getString("CHOCO_FLAVOUR"));
                choco.setWeight(rs.getString("CHOCO_WEIGHT"));
                choco.setProducer(rs.getString("CHOCO_PRODUCER"));
                choco.setPrice(rs.getFloat("CHOCO_PRICE"));
                choco.setDate(rs.getString("CHOCO_DATE_ENTERED"));

                imageList = new ArrayList<>();
                query = conn.createStatement();
                rsImg = query.executeQuery("select * from image where choco_id = " + choco.getId());
                while (rsImg.next()) {
                    imageList.add(rsImg.getInt("IMG_ID"));
                }
                choco.setImages(imageList); //*/
                choco.setReviews(retrieveChocoReviews(choco.getId(), 6));

                List.add(choco);
            }

        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        sqlSuccess = !List.isEmpty();
        sqlSuccessHandler(funtName);

        return List;
    }

    public List<List<String>> retrieveChocolateFilters() {
        String funtName = "Retrieve Chocolate Filters";

        sql = "select DISTINCT CHOCO_TYPE, CHOCO_PRODUCER, CHOCO_FLAVOUR, CHOCO_WEIGHT from chocolate";
        List<String> ListType = new ArrayList<>(), ListProducer = new ArrayList<>(), ListFlavour = new ArrayList<>(), ListWeight = new ArrayList<>();
        List<List<String>> returnList = new ArrayList<>();

        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);
            while (rs.next()) {
                if (!rs.getString("CHOCO_TYPE").equals("") && !ListType.contains(rs.getString("CHOCO_TYPE"))) {
                    ListType.add(rs.getString("CHOCO_TYPE"));
                }

                if (!rs.getString("CHOCO_PRODUCER").equals("") && !ListProducer.contains(rs.getString("CHOCO_PRODUCER"))) {
                    ListProducer.add(rs.getString("CHOCO_PRODUCER"));
                }

                if (!rs.getString("CHOCO_FLAVOUR").equals("") && !ListFlavour.contains(rs.getString("CHOCO_FLAVOUR"))) {
                    ListFlavour.add(rs.getString("CHOCO_FLAVOUR"));
                }

                if (!rs.getString("CHOCO_WEIGHT").equals("") && !ListWeight.contains(rs.getString("CHOCO_WEIGHT"))) {
                    ListWeight.add(rs.getString("CHOCO_WEIGHT"));
                }
            }

            returnList.add(ListType);
            returnList.add(ListProducer);
            returnList.add(ListFlavour);
            returnList.add(ListWeight);

        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        sqlSuccess = !returnList.get(0).isEmpty();
        sqlSuccessHandler(funtName);
        return returnList;
    }

    public List<review> retrieveAllReviews(int mode) {
        return retrieveReviewInternal(0, 0, mode, 0);
    }

    public int retrieveCountReviews() {
        String funtName = "Retrieve Reviews Count";
        sql = "select REV_ID from review ORDER BY REV_ID DESC FETCH FIRST 1 ROWS ONLY";
        int result = 0;
        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while (rs.next()) {
                result = rs.getInt("REV_ID");
            }
            sqlSuccess = result != 0;

            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }
        return result;
    }

    public List<review> retrieveChocoReviews(int id) {
        return retrieveReviewInternal(id, 0, 0, 0);
    }

    public List<review> retrieveChocoReviews(int id, int amt) {
        return retrieveReviewInternal(id, amt, 0, 0);
    }

    public review retrieveSingleReview(int id) {
        try {
            return retrieveReviewInternal(0, 0, 0, id).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public List<review> retrieveMultiReviews(int amt) {
        return retrieveReviewInternal(0, amt, 0, 0);
    }

    private List<review> retrieveReviewInternal(int id, int amt, int mode, int revid) {
        String funtName = "Retrieve Review";
        List<review> List = new ArrayList<>();

        if (id == 0) {
            sql = "select * from review";
        } else {
            sql = "select * from review where choco_id = " + id;
        }

        if (revid != 0) {
            sql = "select * from review where rev_id = " + revid;
        }

        if (mode == 0) {
            sql += " order by REV_ID desc";
        } else if (mode == 1) {
            sql += " order by USER_ID desc";
        } else {
            sql += " order by REV_ID asc";
        }

        if (amt != 0) {
            sql += " fetch first " + amt + " rows only";
        }

        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);
            review review;
            while (rs.next()) {
                review = new review();
                review.setId(rs.getInt("REV_ID"));
                review.setChocoID(rs.getInt("CHOCO_ID"));
                review.setUser(retrieveSingleUser(rs.getInt("USER_ID")).getName());
                review.setUser_id(rs.getInt("USER_ID"));
                review.setDate(rs.getString("REV_DATE"));
                review.setData(rs.getString("REV_DATA"));
                review.setTitle(rs.getString("REV_TITLE"));
                review.setLiked(rs.getBoolean("REV_POSTIVE"));
                List.add(review);
            }
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }
        return List;
    }

    public Blob retrieveImage(int id) {
        String funtName = "Retrieve Image";
        sql = "select * from image where img_id = " + id;
        Blob blob = null;
        try {
            Statement query = conn.createStatement();
            ResultSet rsImg = query.executeQuery(sql);

            while (rsImg.next()) {
                blob = rsImg.getBlob("IMG_IMG");
            }
            sqlSuccess = blob != null;

            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return blob;
    }

    public List<purchase> retrieveAllPurchases(int mode) {
        return retrievePurchaseInternal(0, mode);
    }

    public List<purchase> retrieveSinglePurchase(int id) {
        return retrievePurchaseInternal(id, 0);
    }

    private List<purchase> retrievePurchaseInternal(int id, int mode) {
        String funtName = "Retrieve Purchase";
        List<purchase> List = new ArrayList<>();

        if (id == 0) {
            sql = "select * from purchases";
        } else {
            sql = "select * from purchases where purchase_id = " + id;
        }

        if (mode == 1) {
            sql += " order by CHOCO_ID desc";
        } else if (mode == 2) {
            sql += " order by USER_ID desc";
        }else{
             sql += " order by purchase_id desc";
        }

        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            purchase purch;
            while (rs.next()) {
                purch = new purchase();
                purch.setId(rs.getInt("PURCHASE_ID"));
                purch.setChoco(retrieveSingleChocolate(rs.getInt("CHOCO_ID")));
                purch.setUser(retrieveSingleUser(rs.getInt("USER_ID")));
                purch.setAmount(rs.getInt("PURCHASE_AMOUNT"));
                purch.setDate(rs.getString("PURCHASE_DATE"));
                List.add(purch);
            }

        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        sqlSuccess = !List.isEmpty();
        sqlSuccessHandler(funtName);

        return List;
    }

    public List<stock> retrieveAllStocks(int mode) {
        return retrieveStockInternal(0, mode);
    }

    public int retrieveCountStocks() {
        String funtName = "Retrieve Stocks Count";
        sql = "select STOCK_ID from stocks ORDER BY STOCK_ID DESC FETCH FIRST 1 ROWS ONLY";
        int result = 0;
        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while (rs.next()) {
                result = rs.getInt("STOCK_ID");
            }
            sqlSuccess = result != 0;

            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }
        return result;
    }

    public stock retrieveSingleStock(int id) {
        try {
            return retrieveStockInternal(id, 0).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    private List<stock> retrieveStockInternal(int id, int mode) {
        String funtName = "Retrieve Stock";
        List<stock> List = new ArrayList<>();

        if (id == 0) {
            sql = "select * from stocks";
        } else {
            sql = "select * from stocks where stock_id = " + id;
        }

        if (mode != 0) {
            sql += " order by CHOCO_ID desc";
        }else{
            sql += " order by STOCK_ID desc";
        }

        try {
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            stock stock;
            while (rs.next()) {
                stock = new stock();
                stock.setId(rs.getInt("STOCK_ID"));
                stock.setChoco(retrieveSingleChocolate(rs.getInt("CHOCO_ID")));
                stock.setAmount(rs.getInt("STOCK_AMOUNT"));
                stock.setDate(rs.getString("STOCK_DATE_ENTERED"));
                List.add(stock);
            }

        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        sqlSuccess = !List.isEmpty();
        sqlSuccessHandler(funtName);

        return List;
    }

    //Update operations
    public boolean updateUser(Map mapUser, int id) {
        String funtName = "Update Users";
        updateControl.clear();

        if (mapUser.isEmpty()) {
            sqlSuccess = false;
            return sqlSuccess;
        }

        mapUser.forEach((k, v) -> updateControl.add(k.toString() + ":::" + v.toString()));
        //Using updateControl, the update is created here

        sql = "update users set ";
        for (int x = 0; x < updateControl.size(); x++) {
            sql += updateControl.get(x).split(":::")[0] + " = ?";
            if (x != updateControl.size() - 1) {
                sql += ",";
            }
        }
        sql += " where user_id = ? ";

        try {
            PreparedStatement stmnt = conn.prepareStatement(sql);

            for (int x = 0; x < updateControl.size(); x++) {
                if (updateControl.get(x).split(":::")[0].toLowerCase().contains("id")) {
                    stmnt.setInt(x + 1, Integer.parseInt(updateControl.get(x).split(":::")[1]));
                } else {
                    stmnt.setString(x + 1, updateControl.get(x).split(":::")[1]);
                }
            }
            stmnt.setInt(updateControl.size() + 1, id);

            sqlSuccess = stmnt.executeUpdate() != 0;
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean updateReview(Map mapreview, int id) {
        String funtName = "Update Reviews";
        updateControl.clear();

        if (mapreview.isEmpty()) {
            sqlSuccess = false;
            return sqlSuccess;
        }

        mapreview.forEach((k, v) -> updateControl.add(k.toString() + ":::" + v.toString()));
        //Using updateControl, the update is created here

        sql = "update review set ";
        for (int x = 0; x < updateControl.size(); x++) {
            sql += updateControl.get(x).split(":::")[0] + " = ?";
            if (x != updateControl.size() - 1) {
                sql += ",";
            }
        }
        sql += " where rev_id = ? ";

        try {
            PreparedStatement stmnt = conn.prepareStatement(sql);

            for (int x = 0; x < updateControl.size(); x++) {
                if (updateControl.get(x).split(":::")[0].toLowerCase().contains("id")) {
                    stmnt.setInt(x + 1, Integer.parseInt(updateControl.get(x).split(":::")[1]));
                } else {
                    stmnt.setString(x + 1, updateControl.get(x).split(":::")[1]);
                }
            }
            stmnt.setInt(updateControl.size() + 1, id);

            sqlSuccess = stmnt.executeUpdate() != 0;
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean updateChocolate(Map mapChoco, int id) {
        String funtName = "Update Chocolate";
        updateControl.clear();

        System.out.println(id);

        if (mapChoco.isEmpty()) {
            sqlSuccess = false;
            return sqlSuccess;
        }

        mapChoco.forEach((k, v) -> updateControl.add(k.toString() + ":::" + v.toString()));
        //Using updateControl, the update is created here

        sql = "update chocolate set ";
        for (int x = 0; x < updateControl.size(); x++) {
            sql += updateControl.get(x).split(":::")[0] + " = ?";
            if (x != updateControl.size() - 1) {
                sql += ",";
            }
            System.out.println(updateControl.get(x));
        }
        sql += " where CHOCO_ID = ?";
        try {
            PreparedStatement stmnt = conn.prepareStatement(sql);

            for (int x = 0; x < updateControl.size(); x++) {
                if (updateControl.get(x).split(":::")[0].contains("ID") || updateControl.get(x).split(":::")[0].contains("WEIGHT")) {
                    if (updateControl.get(x).split(":::")[0].contains("PRICE")) {
                        stmnt.setFloat(x + 1, Integer.parseInt(updateControl.get(x).split(":::")[1]));
                    } else {
                        stmnt.setInt(x + 1, Integer.parseInt(updateControl.get(x).split(":::")[1]));
                    }
                } else {
                    stmnt.setString(x + 1, updateControl.get(x).split(":::")[1]);
                }
            }
            stmnt.setInt(updateControl.size() + 1, id);

            sqlSuccess = stmnt.execute();
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return sqlSuccess;
    }

    public boolean updatePurchase(Map mapPurch, int id) {
        String funtName = "Update Purchases";
        updateControl.clear();

        if (mapPurch.isEmpty()) {
            sqlSuccess = false;
            return sqlSuccess;
        }

        mapPurch.forEach((k, v) -> updateControl.add(k.toString() + ":::" + v.toString()));
        //Using updateControl, the update is created here

        sql = "update purchase set ";
        for (int x = 0; x < updateControl.size(); x++) {
            sql += updateControl.get(x).split(":::")[0] + " = ?";
            if (x != updateControl.size() - 1) {
                sql += ",";
            }
        }
        sql += " where purchase_id = ? ";

        try {
            PreparedStatement stmnt = conn.prepareStatement(sql);

            for (int x = 0; x < updateControl.size(); x++) {
                if (updateControl.get(x).split(":::")[0].contains("id") || updateControl.get(x).split(":::")[0].contains("amount")) {
                    stmnt.setInt(x + 1, Integer.parseInt(updateControl.get(x).split(":::")[1]));
                } else {
                    stmnt.setString(x + 1, updateControl.get(x).split(":::")[1]);
                }
            }
            stmnt.setInt(updateControl.size() + 1, id);

            sqlSuccess = stmnt.executeUpdate() != 0;
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean updateStocks(Map mapStock, int id) {
        String funtName = "Update Stocks";
        updateControl.clear();

        if (mapStock.isEmpty()) {
            sqlSuccess = false;
            return sqlSuccess;
        }

        mapStock.forEach((k, v) -> updateControl.add(k.toString() + ":::" + v.toString()));
        //Using updateControl, the update is created here

        sql = "update stocks set ";
        for (int x = 0; x < updateControl.size(); x++) {
            sql += updateControl.get(x).split(":::")[0] + " = ?";
            if (x != updateControl.size() - 1) {
                sql += ",";
            }
        }
        sql += " where stock_id = ? ";

        try {
            PreparedStatement stmnt = conn.prepareStatement(sql);

            for (int x = 0; x < updateControl.size(); x++) {
                if (updateControl.get(x).split(":::")[0].contains("id") || updateControl.get(x).split(":::")[0].contains("amount")) {
                    stmnt.setInt(x + 1, Integer.parseInt(updateControl.get(x).split(":::")[1]));
                } else {
                    stmnt.setString(x + 1, updateControl.get(x).split(":::")[1]);
                }
            }
            stmnt.setInt(updateControl.size() + 1, id);

            sqlSuccess = stmnt.executeUpdate() != 0;
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    //Delete operations
    public boolean deleteUser(int id) {
        String funtName = "Delete User";
        sql = "delete from users where user_id = " + id;
        try {
            Statement query = conn.createStatement();
            sqlSuccess = query.execute(sql);
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean deleteReview(int id) {
        String funtName = "Delete Review";
        sql = "delete from review where rev_id = " + id;
        try {
            Statement query = conn.createStatement();
            sqlSuccess = query.execute(sql);
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean deleteChocolate(int id) {
        String funtName = "Delete Chocolate";
        sql = "delete from chocolate where choco_id = " + id;
        try {
            Statement query = conn.createStatement();
            query.execute("delete from image where choco_id = " + id);
            query.execute("delete from review where choco_id = " + id);
            query.execute("delete from stocks where choco_id = " + id);
            sqlSuccess = query.execute(sql);
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean deleteImage(int id) {
        String funtName = "Delete Image";
        sql = "delete from image where image_id = " + id;
        try {
            Statement query = conn.createStatement();
            sqlSuccess = query.execute(sql);
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean deletePurchase(int id) {
        String funtName = "Delete Purchase";
        sql = "delete from purchases where purchase_id = " + id;
        try {
            Statement query = conn.createStatement();
            sqlSuccess = query.execute(sql);
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }

    public boolean deleteStock(int id) {
        String funtName = "Delete Stock";
        sql = "delete from stocks where stock_id = " + id;
        try {
            Statement query = conn.createStatement();
            sqlSuccess = query.execute(sql);
            sqlSuccessHandler(funtName);
        } catch (SQLException ex) {
            sqlSuccessHandler(funtName, ex);
        }

        return sqlSuccess;
    }
}
