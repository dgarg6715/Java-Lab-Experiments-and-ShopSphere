package com.shopsphere.service;
import com.shopsphere.dao.ProductDAO;
import com.shopsphere.model.Product;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
public class ProductService {
    private final ProductDAO dao=new ProductDAO();
    public List<Product> search(String q) throws SQLException { return dao.findAll(q); }
    public Optional<Product> find(int id) throws SQLException { return dao.findById(id); }
}
