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
    private int user_id;

    public review() {
        //Initialise variables here
        id = 0;
        chocoID = 0;
        user = "";
        date = "";
        date = "";
        title = "";
        liked = false;
        user_id = 0;
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
        this.date = toVisualDate(date);
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private String toVisualDate(String date) {
        String returnDate = "";
        String[] dateArr = date.split("-");
        for (int x = dateArr.length - 1; x >= 0; x--) {
            returnDate += dateArr[x];
            if (x != 0) {
                returnDate += "/";
            }
        }
        return returnDate;
    }
}
