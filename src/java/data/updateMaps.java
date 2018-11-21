package data;

import java.util.HashMap;
import java.util.Map;

public class updateMaps {
    
    private Map mapAct;
    private Map mapPurch;
    private Map mapChoco;
    private Map mapStock;
    private Map mapUser;
    
    public updateMaps(){
        
        mapAct = new HashMap(){{
            put("ACTIVITY_ID", 0);
            put("USER_ID", 0);
            put("ACTIVITY_TYPE", "");
            put("ACTIVITY_DETAILS", "");
        }};
        
        mapPurch = new HashMap(){{
            put("PURCHASE_ID", 0);
            put("CHOCO_ID", 0);
            put("USER_ID", 0);
            put("PURCHASE_AMOUNT", 0);
            put("PURCHASE_DATE", "");
        }};
        
        mapChoco = new HashMap(){{
            put("CHOCO_ID", 0);
            put("CHOCO_NAME", "");
            put("CHOCO_DESC", "");
            put("CHOCO_TYPE", "");
            put("CHOCO_FLAVOUR", "");
            put("CHOCO_WEIGHT", "");
            put("CHOCO_PRODUCER", "");
            put("CHOHO_IMAGE_FOLDER", "");
            put("CHOCO_DATE_ENTERED", "");
        }};
        
        mapStock = new HashMap(){{
            put("STOCK_ID", 0);
            put("CHOCO_ID", 0);
            put("STOCK_AMOUNT", 0);
            put("STOCK_BESTBEFORE", "");
            put("STOCK_DATE_ENTERED", "");
        }};
        
        mapUser = new HashMap(){{
            put("USER_ID", 0);
            put("USER_NAME", "");
            put("USER_EMAIL", "");
            put("USER_PASSWORD", "");
            put("USER_TYPE", "");
            put("USER_ADDRESS", "");
        }};
        
    }

    public void setMapActValue(String Key, Object Value) {
        if (mapAct.containsKey(Key)){
            mapAct.put(Key, Value);
        }
    }
    
    public boolean checkMapAct(Object Key, Object Value){
        return mapAct.get(Key).equals(Value);
    }

    public void setMapPurchValue(String Key, Object Value) {
        if (mapPurch.containsKey(Key)){
            mapPurch.put(Key, Value);
        }
    }
    
    public boolean checkMapPurch(Object Key, Object Value){
        return mapPurch.get(Key).equals(Value);
    }

    public void setMapChocoValue(String Key, Object Value) {
        if (mapChoco.containsKey(Key)){
            mapChoco.put(Key, Value);
        }
    }
    
    public boolean checkMapChoco(Object Key, Object Value){
        return mapChoco.get(Key).equals(Value);
    }

    public void setMapStockValue(String Key, Object Value) {
        if (mapStock.containsKey(Key)){
            mapStock.put(Key, Value);
        }
    }
    
    public boolean checkMapStock(Object Key, Object Value){
        return mapStock.get(Key).equals(Value);
    }

    public void setMapUserValue(String Key, Object Value) {
        if (mapUser.containsKey(Key)){
            mapUser.put(Key, Value);
        }
    }
    
    public boolean checkMapUser(Object Key, Object Value){
        return mapUser.get(Key).equals(Value);
    }
}
