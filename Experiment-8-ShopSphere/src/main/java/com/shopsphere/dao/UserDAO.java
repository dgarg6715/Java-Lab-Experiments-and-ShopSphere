package com.shopsphere.dao;
import com.shopsphere.model.User;
import com.shopsphere.util.DBConnection;
import java.sql.*;
import java.util.Optional;
public class UserDAO {
    public Optional<User> findByEmail(String email) throws SQLException {
        String sql="SELECT user_id,name,email,password_hash,role FROM users WHERE email=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,email);
            try(ResultSet r=p.executeQuery()){ if(r.next()) return Optional.of(new User(r.getInt("user_id"),r.getString("name"),r.getString("email"),r.getString("password_hash"),r.getString("role"))); }
        } return Optional.empty();
    }
    public boolean create(String name,String email,String hash) throws SQLException {
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("INSERT INTO users(name,email,password_hash) VALUES(?,?,?)")){
            p.setString(1,name); p.setString(2,email); p.setString(3,hash); return p.executeUpdate()==1;
        }
    }
}
