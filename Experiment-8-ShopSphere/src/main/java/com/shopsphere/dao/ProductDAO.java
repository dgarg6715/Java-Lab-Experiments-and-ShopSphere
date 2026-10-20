package com.shopsphere.dao;
import com.shopsphere.model.Product;
import com.shopsphere.util.DBConnection;
import java.sql.*;
import java.util.*;
public class ProductDAO {
    public List<Product> findAll(String search) throws SQLException {
        List<Product> list=new ArrayList<>();
        String sql="SELECT product_id,name,description,price,stock,image_url FROM products WHERE status=TRUE AND name LIKE ? ORDER BY product_id DESC";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,"%"+(search==null?"":search.trim())+"%");
            try(ResultSet r=p.executeQuery()){while(r.next()) list.add(new Product(r.getInt("product_id"),r.getString("name"),r.getString("description"),r.getBigDecimal("price"),r.getInt("stock"),r.getString("image_url")));}
        } return list;
    }
    public Optional<Product> findById(int id) throws SQLException {
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT product_id,name,description,price,stock,image_url FROM products WHERE product_id=? AND status=TRUE")){
            p.setInt(1,id); try(ResultSet r=p.executeQuery()){if(r.next()) return Optional.of(new Product(r.getInt("product_id"),r.getString("name"),r.getString("description"),r.getBigDecimal("price"),r.getInt("stock"),r.getString("image_url")));}
        } return Optional.empty();
    }
}
