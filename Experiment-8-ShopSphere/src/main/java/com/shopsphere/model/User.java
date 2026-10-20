package com.shopsphere.model;
public class User {
    private int userId; private String name, email, passwordHash, role;
    public User() {}
    public User(int userId, String name, String email, String passwordHash, String role) {
        this.userId=userId; this.name=name; this.email=email; this.passwordHash=passwordHash; this.role=role;
    }
    public int getUserId(){return userId;} public void setUserId(int v){userId=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String v){passwordHash=v;}
    public String getRole(){return role;} public void setRole(String v){role=v;}
}
