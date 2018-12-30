package data;

public class purchase {
    
    private int id;
    private chocolate choco;
    private int user_id;
    private int amount;
    private String date;
    private int cart;
    
    public purchase(){
        //Initialise variables here
        id = 0;
        choco = null;
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

    public chocolate getChoco() {
        return choco;
    }

    public void setChoco(chocolate choco) {
        this.choco = choco;
    }

    public int getCart() {
        return cart;
    }

    public void setCart(int cart) {
        this.cart = cart;
    }
    
}
