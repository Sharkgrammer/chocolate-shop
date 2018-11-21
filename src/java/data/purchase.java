package data;

public class purchase {
    
    private int id;
    private int choco_id;
    private int user_id;
    private int amount;
    private String date;
    
    public purchase(){
        //Initialise variables here
        id = 0;
        choco_id = 0;
        user_id = 0;
        amount = 0;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
