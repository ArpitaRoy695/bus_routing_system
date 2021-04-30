package com.example.bus_routing_system;

import java.sql.SQLException;
import java.sql.Statement;

public class Ticket {
    DatabaseConnection DBConnection = new DatabaseConnection();
    Statement statement = DBConnection.statement;

    boolean buyTicket(String seat, int bus, int passenger, int route, int price, String date){
        boolean added = false;
        String query = String.format(
                "INSERT INTO Tickets (Seat_no, Bus_no, Passenger_ID, Route_ID, Price, Journey date) VALUES(\'"+
                        seat+"\'," +
                        bus+","+
                        passenger +","+
                        route +","+
                        price +",\'"+
                        date + "\');"
        );
        try {
            statement.execute(query);
            added = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return added;
    }
}
