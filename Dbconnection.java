package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    static Connection con = null;

    static Connection get() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection to the MySQL database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab", "root", "");
            return con;
        } catch (ClassNotFoundException e) {
            // Handle ClassNotFoundException
            System.out.println("MySQL JDBC driver not found");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            // Handle SQLException
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
            return null;
        }
    }
}

