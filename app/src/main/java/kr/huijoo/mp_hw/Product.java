package kr.huijoo.mp_hw;

public class Product {
    private String image;
    private String title;
    private String price;

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
}
