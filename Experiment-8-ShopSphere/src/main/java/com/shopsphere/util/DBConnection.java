package com.shopsphere.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public final class DBConnection {
    private DBConnection() {}
    public static Connection getConnection() throws SQLException {
        String url = System.getenv().getOrDefault("SHOPSPHERE_DB_URL", "jdbc:mysql://localhost:3306/shopsphere?useSSL=false&serverTimezone=UTC");
        String user = System.getenv().getOrDefault("SHOPSPHERE_DB_USER", "root");
        String password = System.getenv().getOrDefault("SHOPSPHERE_DB_PASSWORD", "");
        return DriverManager.getConnection(url, user, password);
    }
}
