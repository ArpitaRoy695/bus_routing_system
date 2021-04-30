package com.example.bus_routing_system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    String name;
    String address;
    String phoneNumber;
    String email;
    String role;

    DatabaseConnection DBConnection = new DatabaseConnection();
    Statement statement = DBConnection.statement;

    public int exists(String field, String value){
        int userID = -1;

        String query = String.format(
                "SELECT User_ID from Users WHERE " +
                        field +
                        " = \'" +
                        value +
                        "\';");
        try {
            ResultSet row = statement.executeQuery(query);
            if(row.next())
                userID = row.getInt("User_ID");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userID;
    }

    public boolean confirmPassword(int id, String password){
        boolean match = false;
        String query = String.format(
                "SELECT Password FROM Users WHERE User_ID = " + id + " ;"
        );
        try {
            ResultSet row = statement.executeQuery(query);
            row.next();
            if(password.equals(row.getString("Password")))
                match = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return match;
    }




    public int isAdmin(String email){
        int admin = -1;
        String query = String.format(
                "SELECT * FROM Users WHERE Email = \'" + email +"\';"
        );
        try {
           ResultSet row = statement.executeQuery(query);
           if(row.next())
               if(row.getString("User_Role").equals("admin"))
                   admin = row.getInt("User_ID");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admin;
    }

    public boolean Create(String name, String address, String phoneNumber,
                          String email, String role, String password){
        boolean creation = false;

        String query = String.format(
                "INSERT INTO Users (Full_Name, Address, Phone_number, Email,User_Role,Password) VALUES("
                        +"\'"+name +"\',"
                        +"\'"+address + "\',"
                        +"\'"+phoneNumber+ "\',"
                        +"\'"+email+"\',"
                        +"\'"+role+"\',"
                        +"\'"+password+"\');"
        );
        try{
            statement.execute(query);
            creation = true;

        }catch (
                SQLException e){
            throw new RuntimeException("Unable to add User", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creation;
    }
    public boolean Delete(int id){
        boolean deletion = false;

        String query = String.format(
                "DELETE FROM Users WHERE User_ID = "+id+";"
        );
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            deletion = true;
        }

        return  deletion;
    }

    public boolean Update(String field, String value, int id){
        boolean update = false;

            String query = String.format(
                    "UPDATE Users SET " +
                            field + " = \'"
                            + value
                            +"\' WHERE User_ID = " + id +";");
        try {
            statement.execute(query);
            update = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return update;
    }

}
