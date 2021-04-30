package com.example.bus_routing_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    String dbURL = "jdbc:mariadb://localhost:3306/";
    String dbName = "bus_routing_system?";
    String dbUsername = "user=root&password=";
    String dbPassword = "1234";
    Statement statement;
    Connection connection;

    public DatabaseConnection(){
        try {
            tryConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tryConnection() throws Exception{
        Class.forName("org.mariadb.jdbc.Driver");

        try{
            connection = DriverManager.getConnection(dbURL+dbName+dbUsername+dbPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
