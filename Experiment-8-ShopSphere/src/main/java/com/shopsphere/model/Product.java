package com.shopsphere.model;
import java.math.BigDecimal;
public class Product {
    private int productId, stock; private String name, description, imageUrl; private BigDecimal price;
    public Product() {}
    public Product(int id,String name,String description,BigDecimal price,int stock,String imageUrl) {
        this.productId=id; this.name=name; this.description=description; this.price=price; this.stock=stock; this.imageUrl=imageUrl;
    }
    public int getProductId(){return productId;} public void setProductId(int v){productId=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;}
    public int getStock(){return stock;} public void setStock(int v){stock=v;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;}
}
