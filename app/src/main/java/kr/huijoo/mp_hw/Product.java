package kr.huijoo.mp_hw;

import java.io.Serializable;

public class Product implements Serializable {
    private String image;
    private String title;
    private String price;
    private boolean isSelected;
    private String count;

    public Product(){}

    public String getImage(){
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
