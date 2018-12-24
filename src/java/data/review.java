package data;

import java.io.Serializable;

public class review implements Serializable {

    private int id;
    private int chocoID;
    private String user;
    private String date; 
    private String data;
    private String title;
    private boolean liked;

    public review() {
        //Initialise variables here
        id = 0;
        chocoID = 0;
        user = "";
        date = "";
        date = "";
        title = "";
        liked = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChocoID() {
        return chocoID;
    }

    public void setChocoID(int chocoID) {
        this.chocoID = chocoID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
