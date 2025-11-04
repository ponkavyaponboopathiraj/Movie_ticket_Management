package src.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

    private static final String URL = "jdbc:postgresql://localhost:5432/movie_ticket_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static DBConfig instance;
    private Connection connection;

    // 🧩 Private constructor (Singleton)
    private DBConfig() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to PostgreSQL database!");
        } catch (Exception e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
        }
    }

    // 🧠 Static synchronized getInstance() for thread-safe singleton
    public static synchronized DBConfig getInstance() {
        if (instance == null) {
            instance = new DBConfig();
        }
        return instance;
    }

    // 🎯 Method to get connection (auto-reconnect if closed)
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.out.println("❌ Reconnection failed: " + e.getMessage());
        }
        return connection;
    }
}
