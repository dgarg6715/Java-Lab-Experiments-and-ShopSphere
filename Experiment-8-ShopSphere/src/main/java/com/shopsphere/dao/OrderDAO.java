package com.shopsphere.dao;
import com.shopsphere.util.DBConnection;
import java.sql.*;
import java.math.BigDecimal;
import java.util.*;
public class OrderDAO {
    public int createOrder(int userId, Map<Integer,Integer> quantities) throws SQLException {
        try(Connection c=DBConnection.getConnection()){
            c.setAutoCommit(false);
            try {
                BigDecimal total=BigDecimal.ZERO;
                Map<Integer,BigDecimal> prices=new LinkedHashMap<>();
                for(var e:quantities.entrySet()){
                    try(PreparedStatement p=c.prepareStatement("SELECT price,stock FROM products WHERE product_id=? AND status=TRUE FOR UPDATE")){
                        p.setInt(1,e.getKey()); try(ResultSet r=p.executeQuery()){
                            if(!r.next() || e.getValue()<1 || r.getInt("stock")<e.getValue()) throw new SQLException("Product unavailable or insufficient stock.");
                            BigDecimal price=r.getBigDecimal("price"); prices.put(e.getKey(),price);
                            total=total.add(price.multiply(BigDecimal.valueOf(e.getValue())));
                        }
                    }
                }
                int orderId;
                try(PreparedStatement p=c.prepareStatement("INSERT INTO orders(user_id,total_amount,order_status) VALUES(?,?,'PLACED')",Statement.RETURN_GENERATED_KEYS)){
                    p.setInt(1,userId);p.setBigDecimal(2,total);p.executeUpdate();
                    try(ResultSet r=p.getGeneratedKeys()){if(!r.next())throw new SQLException("Order ID was not generated.");orderId=r.getInt(1);}
                }
                for(var e:quantities.entrySet()){
                    try(PreparedStatement p=c.prepareStatement("INSERT INTO order_items(order_id,product_id,quantity,price) VALUES(?,?,?,?)")){
                        p.setInt(1,orderId);p.setInt(2,e.getKey());p.setInt(3,e.getValue());p.setBigDecimal(4,prices.get(e.getKey()));p.executeUpdate();
                    }
                    try(PreparedStatement p=c.prepareStatement("UPDATE products SET stock=stock-? WHERE product_id=?")){
                        p.setInt(1,e.getValue());p.setInt(2,e.getKey());p.executeUpdate();
                    }
                }
                c.commit(); return orderId;
            } catch(SQLException ex){c.rollback();throw ex;} finally {c.setAutoCommit(true);}
        }
    }
    public List<Map<String,Object>> findByUser(int userId)throws SQLException{
        List<Map<String,Object>> list=new ArrayList<>();
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("SELECT order_id,total_amount,order_status,created_at FROM orders WHERE user_id=? ORDER BY order_id DESC")){
            p.setInt(1,userId);try(ResultSet r=p.executeQuery()){while(r.next()){
                Map<String,Object> row=new LinkedHashMap<>();row.put("orderId",r.getInt("order_id"));row.put("total",r.getBigDecimal("total_amount"));row.put("status",r.getString("order_status"));row.put("createdAt",r.getTimestamp("created_at"));list.add(row);
            }}
        }return list;
    }
}
