package db;

import java.sql.*;
import data.*;
import java.util.ArrayList;
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
        try{
           Class.forName(DRIVER);
           conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch(ClassNotFoundException | SQLException e){
          errorMessage = e.toString();
          System.out.println(e.toString());
        }    
   }
   
   //Misc operations related to the databse go here
   public String returnErrorMessage(){
       return errorMessage;
   }
   
   public String returnLastResult(){
       return sql + " | " + resultMessage + " | " + sqlSuccess;
   }
   
   private void sqlSuccessHandler(String fuctName){
       if (sqlSuccess){
           resultMessage = fuctName + ":Complete";
       }else{
           resultMessage = fuctName + ":Failed";
           errorMessage = resultMessage;
       }
   }
   
   private void sqlSuccessHandler(String fuctName, SQLException ex){
       resultMessage = fuctName + ":Failed";
       sqlSuccess = false;
       errorMessage = ex.toString();
       System.out.println(errorMessage);
   }
   
   //Create operations
   public boolean createUser(String Name, String Email, String Password, String Type, String Address){
       String funtName = "Create User";
       sql = "insert into users(user_name,user_email,user_password,user_type,user_address) values (" +
               Name + "," + Email + "," + Password + "," + Type + "," + Address + ");";
       try {
           Statement query = conn.createStatement();
           sqlSuccess = query.execute(sql);
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
      
       return sqlSuccess;
   }
   
   public boolean createChocolate(String Name, String Desc, String Type, String Flavour, String Weight, String Producer, String Image_Loc){
       String funtName = "Create Chocolate";
       sql = "insert into chocolate(CHOCO_NAME,CHOCO_DESC,CHOCO_TYPE,CGOCO_FLAVOUR,CHOCO_WEIGHT,CHOCO_PRODUCER,CHOHO_IMAGE_FOLDER,CHOCO_DATE_ENTERED) values (" +
               Name + "," + Desc + "," + Type + "," + Flavour + "," + Weight + "," + Producer + "," + Image_Loc + ",NOW());";
       try {
           Statement query = conn.createStatement();
           sqlSuccess = query.execute(sql);
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
      
       return sqlSuccess;
   }
   
   public boolean createPurchase(String ChocoID, String UserID, int Amt){
       String funtName = "Create Purchase";
       sql = "insert into purchase(CHOCO_ID,USER_ID,PURCHASE_AMOUNT,PURCHASE_DATE) values (" +
               ChocoID + "," + UserID + "," + Amt + ",NOW());";
       try {
           Statement query = conn.createStatement();
           sqlSuccess = query.execute(sql);
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
      
       return sqlSuccess;
   }
   
   public boolean createActivity(String UserID, String Type, String Details){
       String funtName = "Create Activity";
       sql = "insert into activity(USER_ID,ACTIVITY_TYPE,ACTIVITY_DETAILS) values (" +
               UserID + "," + Type + "," + Details + ");";
       try {
           Statement query = conn.createStatement();
           sqlSuccess = query.execute(sql);
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
      
       return sqlSuccess;
   }
   
   public boolean createStock(String ChocoID, int Amt, String BestBefore){
       String funtName = "Create Stock";
       sql = "insert into stocks(CHOCO_ID,STOCK_AMOUNT,STOCK_BESTBEFORE,STOCK_DATE_ENTERED) values (" +
               ChocoID + "," + Amt + "," + BestBefore + ",NOW());";
       try {
           Statement query = conn.createStatement();
           sqlSuccess = query.execute(sql);
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
      
       return sqlSuccess;
   }
   
   //Retrieve operations
   public List<user> retrieveAllUsers(){
       return retrieveUsersInternal(0);
   }
   
   
   public List<user> retrieveSingleUser(int id){
       return retrieveUsersInternal(id);
   }
   
   private List<user> retrieveUsersInternal(int id){
       String funtName = "Retrieve All Users";
       List <user> List = new ArrayList<>();
       
       if (id == 0){
           sql = "select * from users";
       }else{
           sql = "select * from users where user_id = " + id;
       }
      
       try {
           Statement query = conn.createStatement();
           ResultSet rs = query.executeQuery(sql);
           
           user user;
           while(rs.next()){
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
   
   public List<chocolate> retrieveAllChocolate(){
       return retrieveChocolateInternal(0,0, "", 0);
   }
   
   public List<chocolate> retrieveSingleChocolate(int id){
       return retrieveChocolateInternal(id, 0, "", 0);
   }
   
   public List<chocolate> retrieveMultiChocolate(int amt, int mode){
       return retrieveChocolateInternal(0, amt, "", mode);
   }
   
   public List<chocolate> retrieveSearchChocolate(String name){
       return retrieveChocolateInternal(0, 0, name, 0);
   }
   
   public List<chocolate> retrieveSearchChocolate(String name, int amt){
       return retrieveChocolateInternal(0, amt, name, 0);
   }
   
   public List<chocolate> retrieveSearchChocolate(String name, int amt, int mode){
       return retrieveChocolateInternal(0, amt, name, mode);
   }
   
   private List<chocolate> retrieveChocolateInternal(int id, int amt, String name, int mode){
       String funtName = "Retrieve Chocolate";
       List <chocolate> List = new ArrayList<>();
       
       if (id == 0){
           sql = "select * from chocolate";
       }else{
           sql = "select * from chocolate where choco_id = " + id;
       }
       
       if (!name.equals("")){
           sql += " where CHOCO_NAME like %" + name + "%";
       }
       
       if (mode != 0){
           switch(mode){
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
           }
       }
       
       if (amt != 0){
           sql += " fetch first " + amt + " rows only";
       }
       
       try {
           Statement query = conn.createStatement();
           ResultSet rs = query.executeQuery(sql);
           
           chocolate choco;
           while(rs.next()){
               choco = new chocolate();
               choco.setId(rs.getInt("CHOCO_ID"));
               choco.setName(rs.getString("CHOCO_NAME"));
               choco.setDescription(rs.getString("CHOCO_DESC"));
               choco.setType(rs.getString("CHOCO_TYPE"));
               choco.setFlavour(rs.getString("CHOCO_FLAVOUR"));
               choco.setWeight(rs.getString("CHOCO_WEIGHT"));
               choco.setProducer(rs.getString("CHOCO_PRODUCER"));
               choco.setImage_folder(rs.getString("CHOHO_IMAGE_FOLDER"));
               choco.setDate(rs.getString("CHOCO_DATE_ENTERED"));
               List.add(choco);
           }
           
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
       
       sqlSuccess = !List.isEmpty();
       sqlSuccessHandler(funtName);
       
       return List;
   }
   
   public List<purchase> retrieveAllPurchases(){
       return retrievePurchaseInternal(0);
   }
   
   public List<purchase> retrieveSinglePurchase(int id){
       return retrievePurchaseInternal(id);
   }
   
   private List<purchase> retrievePurchaseInternal(int id){
       String funtName = "Retrieve Purchase";
       List <purchase> List = new ArrayList<>();
       
       if (id == 0){
           sql = "select * from purchase";
       }else{
           sql = "select * from purchase where purchase_id = " + id;
       }
      
       try {
           Statement query = conn.createStatement();
           ResultSet rs = query.executeQuery(sql);
           
           purchase purch;
           while(rs.next()){
               purch = new purchase();
               purch.setId(rs.getInt("PURCHASE_ID"));
               purch.setChoco_id(rs.getInt("CHOCO_ID"));
               purch.setUser_id(rs.getInt("USER_ID"));
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
   
   public List<activity> retrieveAllActivitys(){
       return retrieveActivityInternal(0);
   }
   
   public List<activity> retrieveSingleActivity(int id){
       return retrieveActivityInternal(id);
   }
   
   private List<activity> retrieveActivityInternal(int id){
       String funtName = "Retrieve Activity";
       List <activity> List = new ArrayList<>();
       
       if (id == 0){
           sql = "select * from activity";
       }else{
           sql = "select * from activity where activity_id = " + id;
       }
      
       try {
           Statement query = conn.createStatement();
           ResultSet rs = query.executeQuery(sql);
           
           activity act;
           while(rs.next()){
               act = new activity();
               act.setId(rs.getInt("ACTIVITY_ID"));
               act.setUser_id(rs.getInt("USER_ID"));
               act.setType(rs.getString("ACTIVITY_TYPE"));
               act.setDetails(rs.getString("ACTIVITY_DETAILS"));
               List.add(act);
           }
           
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
       
       sqlSuccess = !List.isEmpty();
       sqlSuccessHandler(funtName);
       
       return List;
   }
   
   public List<stock> retrieveAllStocks(){
       return retrieveStockInternal(0);
   }
   
   public List<stock> retrieveSingleStock(int id){
       return retrieveStockInternal(id);
   }
   
   private List<stock> retrieveStockInternal(int id){
       String funtName = "Retrieve Stock";
       List <stock> List = new ArrayList<>();
       
       if (id == 0){
           sql = "select * from stocks";
       }else{
           sql = "select * from stocks where stock_id = " + id;
       }
      
       try {
           Statement query = conn.createStatement();
           ResultSet rs = query.executeQuery(sql);
           
           stock stock;
           while(rs.next()){
               stock = new stock();
               stock.setId(rs.getInt("STOCK_ID"));
               stock.setChoco_id(rs.getInt("CHOCO_ID"));
               stock.setAmount(rs.getInt("STOCK_AMOUNT"));
               stock.setBestBefore(rs.getString("STOCK_BESTBEFORE"));
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
   public boolean updateUser(Map mapUser, int id){
       String funtName = "Update Users";
       updateControl.clear();
       
       if (mapUser.isEmpty()){
           sqlSuccess = false;
           return sqlSuccess;
       }
       
       mapUser.forEach((k,v) -> updateControl.add(k.toString() + ":::" + v.toString()));
       //Using updateControl, the update is created here
       
       sql = "update users set ";
       for (int x = 0; x < updateControl.size(); x++){
           sql += updateControl.get(x).split(":::")[0] + " = ?";
           if (x != updateControl.size() - 1){
               sql += ",";
           }
       }
       sql += "where user_id = ?";
       
       try {
           PreparedStatement stmnt = conn.prepareStatement(sql);
           
           for (int x = 0; x < updateControl.size(); x++){
               if (updateControl.get(x).split(":::")[0].contains("id") || updateControl.get(x).split(":::")[0].contains("amount")){
                   stmnt.setInt(x, Integer.parseInt(updateControl.get(x).split(":::")[1]));
               }else{
                   stmnt.setString(x, updateControl.get(x).split(":::")[1]);
               }
           }
           stmnt.setInt(updateControl.size(), id);
           
           sqlSuccess = stmnt.executeUpdate() != 0;
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
       
       return sqlSuccess;
   }
   
   public boolean updateChocolate(Map mapChoco, int id){
       String funtName = "Update Chocolate";
       updateControl.clear();
       
       if (mapChoco.isEmpty()){
           sqlSuccess = false;
           return sqlSuccess;
       }
       
       mapChoco.forEach((k,v) -> updateControl.add(k.toString() + ":::" + v.toString()));
       //Using updateControl, the update is created here
       
       sql = "update chocolate set ";
       for (int x = 0; x < updateControl.size(); x++){
           sql += updateControl.get(x).split(":::")[0] + " = ?";
           if (x != updateControl.size() - 1){
               sql += ",";
           }
       }
       sql += "where chocolate_id = ?";
       
       try {
           PreparedStatement stmnt = conn.prepareStatement(sql);
           
           for (int x = 0; x < updateControl.size(); x++){
               if (updateControl.get(x).split(":::")[0].contains("id") || updateControl.get(x).split(":::")[0].contains("amount")){
                   stmnt.setInt(x, Integer.parseInt(updateControl.get(x).split(":::")[1]));
               }else{
                   stmnt.setString(x, updateControl.get(x).split(":::")[1]);
               }
           }
           stmnt.setInt(updateControl.size(), id);
           
           sqlSuccess = stmnt.executeUpdate() != 0;
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
       
       return sqlSuccess;
   }
   
   public boolean updatePurchase(Map mapPurch, int id){
       String funtName = "Update Purchases";
       updateControl.clear();
       
       if (mapPurch.isEmpty()){
           sqlSuccess = false;
           return sqlSuccess;
       }
       
       mapPurch.forEach((k,v) -> updateControl.add(k.toString() + ":::" + v.toString()));
       //Using updateControl, the update is created here
       
       sql = "update purchase set ";
       for (int x = 0; x < updateControl.size(); x++){
           sql += updateControl.get(x).split(":::")[0] + " = ?";
           if (x != updateControl.size() - 1){
               sql += ",";
           }
       }
       sql += "where purchase_id = ?";
       
       try {
           PreparedStatement stmnt = conn.prepareStatement(sql);
           
           for (int x = 0; x < updateControl.size(); x++){
               if (updateControl.get(x).split(":::")[0].contains("id") || updateControl.get(x).split(":::")[0].contains("amount")){
                   stmnt.setInt(x, Integer.parseInt(updateControl.get(x).split(":::")[1]));
               }else{
                   stmnt.setString(x, updateControl.get(x).split(":::")[1]);
               }
           }
           stmnt.setInt(updateControl.size(), id);
           
           sqlSuccess = stmnt.executeUpdate() != 0;
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
       
       return sqlSuccess;
   }
   
public boolean updateactivity(Map mapAct, int id){
       String funtName = "Update Activitys";
       updateControl.clear();
       
       if (mapAct.isEmpty()){
           sqlSuccess = false;
           return sqlSuccess;
       }
       
       mapAct.forEach((k,v) -> updateControl.add(k.toString() + ":::" + v.toString()));
       //Using updateControl, the update is created here
       
       sql = "update activity set ";
       for (int x = 0; x < updateControl.size(); x++){
           sql += updateControl.get(x).split(":::")[0] + " = ?";
           if (x != updateControl.size() - 1){
               sql += ",";
           }
       }
       sql += "where activity_id = ?";
       
       try {
           PreparedStatement stmnt = conn.prepareStatement(sql);
           
           for (int x = 0; x < updateControl.size(); x++){
               if (updateControl.get(x).split(":::")[0].contains("id") || updateControl.get(x).split(":::")[0].contains("amount")){
                   stmnt.setInt(x, Integer.parseInt(updateControl.get(x).split(":::")[1]));
               }else{
                   stmnt.setString(x, updateControl.get(x).split(":::")[1]);
               }
           }
           stmnt.setInt(updateControl.size(), id);
           
           sqlSuccess = stmnt.executeUpdate() != 0;
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
       
       return sqlSuccess;
   }
   
  public boolean updatestocks(Map mapStock, int id){
       String funtName = "Update Stocks";
       updateControl.clear();
       
       if (mapStock.isEmpty()){
           sqlSuccess = false;
           return sqlSuccess;
       }
       
       mapStock.forEach((k,v) -> updateControl.add(k.toString() + ":::" + v.toString()));
       //Using updateControl, the update is created here
       
       sql = "update stocks set ";
       for (int x = 0; x < updateControl.size(); x++){
           sql += updateControl.get(x).split(":::")[0] + " = ?";
           if (x != updateControl.size() - 1){
               sql += ",";
           }
       }
       sql += "where stock_id = ?";
       
       try {
           PreparedStatement stmnt = conn.prepareStatement(sql);
           
           for (int x = 0; x < updateControl.size(); x++){
               if (updateControl.get(x).split(":::")[0].contains("id") || updateControl.get(x).split(":::")[0].contains("amount")){
                   stmnt.setInt(x, Integer.parseInt(updateControl.get(x).split(":::")[1]));
               }else{
                   stmnt.setString(x, updateControl.get(x).split(":::")[1]);
               }
           }
           stmnt.setInt(updateControl.size(), id);
           
           sqlSuccess = stmnt.executeUpdate() != 0;
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
       
       return sqlSuccess;
   }
   
   //Delete operations
   public boolean deleteUser(int id){
       String funtName = "Delete User";
       sql = "delete * from users where user_id = id";
       try {
           Statement query = conn.createStatement();
           sqlSuccess = query.execute(sql);
           sqlSuccessHandler(funtName);
       } catch (SQLException ex) {
           sqlSuccessHandler(funtName, ex);
       }
      
       return sqlSuccess;
   }
   
   public boolean deleteChocolate(int id){
      String funtName = "Delete Chocolate";
      sql = "delete * from chocolates where chocolate_id = id";
      try {
          Statement query = conn.createStatement();
          sqlSuccess = query.execute(sql);
          sqlSuccessHandler(funtName);
      } catch (SQLException ex) {
          sqlSuccessHandler(funtName, ex);
      }
      
       return sqlSuccess;
   }
   
   public boolean deletePurchase(int id){
      String funtName = "Delete Purchase";
      sql = "delete * from purchases where purchase_id = id";
      try {
          Statement query = conn.createStatement();
          sqlSuccess = query.execute(sql);
          sqlSuccessHandler(funtName);
      } catch (SQLException ex) {
          sqlSuccessHandler(funtName, ex);
      }
      
       return sqlSuccess;
   }
   
   public boolean deleteActivity(int id){
      String funtName = "Delete Activity";
      sql = "delete * from activity where activity_id = id";
      try {
          Statement query = conn.createStatement();
          sqlSuccess = query.execute(sql);
          sqlSuccessHandler(funtName);
      } catch (SQLException ex) {
          sqlSuccessHandler(funtName, ex);
      }
      
       return sqlSuccess;
   }
   
   public boolean deleteStock(int id){
      String funtName = "Delete Stock";
      sql = "delete * from stock where stock_id = id";
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
