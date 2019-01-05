package data;

import java.util.HashMap;
import java.util.Map;

public class updateMaps {

    private Map mapAct;
    private Map mapPurch;
    private Map mapChoco;
    private Map mapStock;
    private Map mapUser;
    private Map mapRev;

    public updateMaps() {

        mapAct = new HashMap() {
            {
                put("ACTIVITY_ID", 0);
                put("USER_ID", 0);
                put("ACTIVITY_TYPE", "");
                put("ACTIVITY_DETAILS", "");
            }
        };

        mapRev = new HashMap() {
            {
                put("CHOCO_ID", 0);
                put("USER_ID", 0);
                put("REV_DATE", "");
                put("REV_DATA", "");
                put("REV_TITLE", "");
                put("REV_POSTIVE", "");
            }
        };

        mapPurch = new HashMap() {
            {
                put("PURCHASE_ID", 0);
                put("CHOCO_ID", 0);
                put("USER_ID", 0);
                put("PURCHASE_AMOUNT", 0);
                put("PURCHASE_DATE", "");
            }
        };

        mapChoco = new HashMap() {
            {
                put("CHOCO_ID", 0);
                put("CHOCO_NAME", "");
                put("CHOCO_DESC", "");
                put("CHOCO_TYPE", "");
                put("CHOCO_FLAVOUR", "");
                put("CHOCO_WEIGHT", "");
                put("CHOCO_PRODUCER", "");
                put("CHOHO_IMAGE_FOLDER", "");
                put("CHOCO_DATE_ENTERED", "");
            }
        };

        mapStock = new HashMap() {
            {
                put("STOCK_ID", 0);
                put("CHOCO_ID", 0);
                put("STOCK_AMOUNT", 0);
                put("STOCK_BESTBEFORE", "");
                put("STOCK_DATE_ENTERED", "");
            }
        };

        mapUser = new HashMap() {
            {
                put("USER_ID", 0);
                put("USER_NAME", "");
                put("USER_EMAIL", "");
                put("USER_PASSWORD", "");
                put("USER_TYPE", "");
                put("USER_ADDRESS", "");
            }
        };

    }

}
