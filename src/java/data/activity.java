package data;

public class activity {
    
    private int id;
    private int user_id;
    private String type;
    private String details;
    
    public activity(){
        //Initialise variables here
        id = 0;
        user_id = 0;
        type = "";
        details = "";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
}
