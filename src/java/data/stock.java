package data;

public class stock {
    
    private int id;
    private int choco_id;
    private int amount;
    private String bestBefore;
    private String date;
    
    public stock(){
        //Initialise variables here
        id = 0;
        choco_id = 0;
        amount = 0;
        bestBefore = "";
        date = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChoco_id() {
        return choco_id;
    }

    public void setChoco_id(int choco_id) {
        this.choco_id = choco_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(String bestBefore) {
        this.bestBefore = bestBefore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
