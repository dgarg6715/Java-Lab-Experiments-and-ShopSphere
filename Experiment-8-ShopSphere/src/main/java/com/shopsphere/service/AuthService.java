package com.shopsphere.service;
import com.shopsphere.dao.UserDAO;
import com.shopsphere.model.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;
import java.util.Optional;
public class AuthService {
    private final UserDAO users=new UserDAO();
    public boolean register(String name,String email,String password) throws SQLException {
        if(name==null||name.isBlank()||email==null||!email.contains("@")||password==null||password.length()<8) return false;
        if(users.findByEmail(email.trim().toLowerCase()).isPresent()) return false;
        return users.create(name.trim(),email.trim().toLowerCase(),BCrypt.hashpw(password,BCrypt.gensalt()));
    }
    public Optional<User> login(String email,String password) throws SQLException {
        if(email==null||password==null) return Optional.empty();
        Optional<User> user=users.findByEmail(email.trim().toLowerCase());
        if(user.isPresent() && BCrypt.checkpw(password,user.get().getPasswordHash())) return user;
        return Optional.empty();
    }
}
