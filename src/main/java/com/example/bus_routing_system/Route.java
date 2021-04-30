package com.example.bus_routing_system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Route {
    DatabaseConnection DBConnection = new DatabaseConnection();
    Statement statement = DBConnection.statement;

    public Vector<RouteObject> getRoutes(){
        Vector<RouteObject> routeObjects = new Vector<RouteObject>();

        return routeObjects;
    }

    public boolean exists(int id){
        boolean exist = false;
        String query = String.format(
                "SELECT * FROM Routes WHERE Route_ID =" + id + ";"
        );
        try {
            ResultSet row = statement.executeQuery(query);
            if(row.next())
                exist = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return exist;
    }

    public boolean Create(String start, String end, String startTime, String endTime, int ac, int nonAC){
        boolean creation = false;
        String query = String.format(
                "INSERT INTO Routes (Start_point, End_point, Start_time, End_time,AC_Cost,Non_AC_Cost) VALUES(\'"
                    + start + "\',\'"
                    + end  + "\',\'"
                    + startTime  + "\',\'"
                    + endTime +"\',"
                    + ac + ","
                    + nonAC + ");"
        );
        try {
            statement.execute(query);
            creation = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return creation;
    }
    public boolean Delete(int id){
        boolean deletion = false;
        if(!exists(id))
            return false;
        String query = String.format(
                "DELETE FROM Routes WHERE Route_ID = "+id+";"
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
        String query = "";
        if(field.equals("Start_time")||field.equals("End_time")) {
            query = String.format(
                    "UPDATE Routes SET "
                            + field + " = \'"
                            + value
                            +"\' WHERE Route_ID = " + id +";"
            );

        }else{
            query = String.format(
                    "UPDATE Routes SET "+ field +" = "
                            + Integer.parseInt(value)
                            +" WHERE Route_ID = " + id +";");
        }
        try {
            statement.execute(query);
            update = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return update;
    }

}


