package data;

public class stock {
    
    private int id;
    private chocolate choco;
    private int amount;
    private String date;
    
    public stock(){
        //Initialise variables here
        id = 0;
        choco = null;
        amount = 0;
        date = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public chocolate getChoco() {
        return choco;
    }

    public void setChoco(chocolate choco) {
        this.choco = choco;
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
