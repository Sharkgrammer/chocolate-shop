package data;

public class graph {

    private int id;
    private String name;
    private String type;
    private String xLabel;
    private String yLabel;
    private String data;

    public graph(int id) {
        this.id = id;
        name = "";
        type = "";
        xLabel = "";
        yLabel = "";
        data = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getxLabel() {
        return xLabel;
    }

    public void setxLabel(String xLabel) {
        this.xLabel = xLabel;
    }

    public String getyLabel() {
        return yLabel;
    }

    public void setyLabel(String yLabel) {
        this.yLabel = yLabel;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public int getId() {
        return id;
    }

}
