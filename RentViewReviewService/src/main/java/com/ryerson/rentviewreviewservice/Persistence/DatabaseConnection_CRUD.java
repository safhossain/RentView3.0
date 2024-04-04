package com.ryerson.rentviewreviewservice.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DatabaseConnection_CRUD {
    protected static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RENTVIEWREVIEWSERVICE?autoReconnect=true&useSSL=false", "root", "student");
        } catch (Exception e) {
            System.out.println("CONNECTION failed: " + e);
        }
        return con;
    }
}