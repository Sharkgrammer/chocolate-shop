package data;

public class purchase {
    
    private int id;
    private chocolate choco;
    private user user;
    private int amount;
    private String date;
    private int cart;
    
    public purchase(){
        //Initialise variables here
        id = 0;
        choco = null;
        user = null;
        amount = 0;
        date = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
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
