package data;

import java.io.Serializable;

public class chocolate implements Serializable{
    
    private int id;
    private String name;
    private String desc;
    private String type; //Dark, Light, Milk
    private String flavour; //Mint, Orange, Bland
    private String weight;
    private String producer;
    private String image_folder;
    private String date;
    
    public chocolate(){
        //Initialise variables here
        id = 0;
        name = "";
        desc = "";
        type = "";
        flavour = "";
        weight = "";
        producer = "";
        image_folder = "";
        date = "";
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getImage_folder() {
        return image_folder;
    }
    
    public void setImage_folder(String image_folder) {
        this.image_folder = image_folder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}