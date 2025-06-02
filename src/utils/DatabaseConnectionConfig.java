package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionConfig {
    private static final String databaseUrl  ="jdbc:postgresql://localhost:5432/morning_ecommerce_db";
    private static final String userName = "postgres";
    private static final String password = "123";
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(databaseUrl, userName, password);
        }catch (Exception exception){
            System.err.println("[!] ERROR during get database connection: " + exception.getMessage());
        }
        return null;
    }
}
