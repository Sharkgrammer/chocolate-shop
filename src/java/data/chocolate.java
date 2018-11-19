package data;

import java.io.Serializable;

public class chocolate implements Serializable{
    
    private String name;
    private String desc;
    private String type; //Dark, Light, Milk, Mint, Orange
    private String bestBefore;
    private String weight;
    private String producer;
    
    public chocolate(){
        //Initialise variables here
        name = "";
        desc = "";
        type = "";
        bestBefore = "";
        weight = "";
        producer = "";
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

    public String getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(String bestBefore) {
        this.bestBefore = bestBefore;
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
}
