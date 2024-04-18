package com.ryerson.rentviewfrontendservice.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DatabaseConnection_CRUD {
    protected static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RENTVIEWFRONTENDSERVICE?autoReconnect=true&useSSL=false", "root", "student");
            String connection = System.getenv("DB_URL");
            con = DriverManager.getConnection("jdbc:mysql://" + connection + "/RENTVIEWFRONTENDSERVICE?allowPublicKeyRetrieval=true&useSSL=false", "root", "student");
            System.out.println("Connection Good");
        } catch (Exception e) {
            System.out.println("CONNECTION failed: " + e);
        }
        return con;
    }
}