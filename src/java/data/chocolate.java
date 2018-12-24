package data;

import db.databaseConnections;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class chocolate implements Serializable{
    
    private int id;
    private String name;
    private String desc;
    private String type; //Dark, Light, Milk
    private String flavour; //Mint, Orange, Bland
    private String weight;
    private String producer;
    private List<Integer> images;
    private String date;
    private float price;
    private List<review> reviews;
    
    public chocolate(){
        //Initialise variables here
        id = 0;
        name = "";
        desc = "";
        type = "";
        flavour = "";
        weight = "";
        producer = "";
        images = new ArrayList<>();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Integer> getImages() {
        return images;
    }
    
    public List<String> getImageStrings(){
        List<String> listStr = new ArrayList<>();
        
        for (int x : images){
            listStr.add(blobToString(x));
        }
        
        return listStr;
    }
    
    public List<String> getImageStringsCara(){
        List<String> listStr = new ArrayList<>();
        
        for (int x = 1; x < images.size(); x++){
            listStr.add(blobToString(x));
        }
        
        return listStr;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }
    
    public String getFirstImage() {
        if (images.isEmpty()){
            return null;
        }else{
            
            return blobToString(images.get(0));
        }
    }
    
    private String blobToString(int blobInt){
         byte [] blobBytes;
            String contentType = "", baseImage = ""; char c; int content = 0;
            try {
                databaseConnections database = new databaseConnections();
                database.setAutoCommit();
                
                Blob blob = database.retrieveImage(blobInt);
                blobBytes = blob.getBytes(1, (int) blob.length());
                for (byte b : blobBytes){
                    c = (char) b;

                    if (c == ';'){
                        content = 1;
                        continue;
                    }else if (c == ','){
                        content = 2;
                        continue;
                    }

                    if (content == 0){
                        contentType += c;
                    }else if (content == 2){
                        baseImage += c;
                    }

                }

                baseImage = "data:" + contentType + ";base64," + baseImage;
            } catch (Exception ex) {
                baseImage = ex.toString();
            }
            
            return baseImage;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<review> getReviews() {
        return reviews;
    }

    public void setReviews(List<review> reviews) {
        this.reviews = reviews;
    }
}