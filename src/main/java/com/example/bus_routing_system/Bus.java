package com.example.bus_routing_system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bus {
    DatabaseConnection DBConnection = new DatabaseConnection();
    Statement statement = DBConnection.statement;

    public boolean update(int route, int id){
        boolean updated = false;
        String query = String.format(
                "UPDATE Buses SET Bus_route = "
                + route + "WHERE Bus_ID = " +id
                + ";"
        );
        try {
            statement.execute(query);
            updated = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updated;
    }

    public boolean exists(int id){
        boolean exist = false;
        String query = String.format(
                "SELECT * FROM Buses WHERE Bus_ID =" + id + ";"
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

    public boolean delete(int id){
        if(!exists(id))
            return false;
        String query = String.format(
                "DELETE FROM Buses WHERE Bus_ID =" + id + ";"
        );
        try {
            ResultSet row = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean create(int route, String type){
        boolean creation = false;
        String query = String.format(
                "INSERT INTO Buses (Bus_route, Bus_type,No_Seats, Available_seats) VALUES(" +
                        route + ",\'" +
                        type + "\',"
        );
        if(type.equals("ac"))
            query+= String.format(27 +
                    "," +
                    "\'A1,A2,A3,B1,B2,B3,C1,C2,C3,D1,D2,D3,E1,E2,E3,F1,F2,F3,G1,G2,G3,H1,H2,H3,I1,I2,I3\');");
        else
            query+= String.format(36 +
                    "," +
                    "\'A1,A2,A3,A4,B1,B2,B3,B4,C1,C2,C3,C4,D1,D2,D3,D4,E1,E2,E3,E4,F1,F2,F3,F4,G1,G2,G3,G4,H1,H2,H3,H4,I1,I2,I3,I4\');");

        try {
            statement.execute(query);
            creation = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return creation;
    }
}
