package com.example.bus_routing_system;

import java.sql.SQLException;
import java.sql.Statement;

public class Payments {
    DatabaseConnection DBConnection = new DatabaseConnection();
    Statement statement = DBConnection.statement;


    public boolean addPayment(String method, int amount, int ticketNumber, int passengerID){
        boolean added = false;
        String query = String.format(
                "INSERT INTO Payments (Payment_method,Amount,Ticket_number,Passenger_ID) VALUES(" +
                        "\'" + method + "\',"+
                        amount + ","+
                        ticketNumber+ ","+
                        passengerID+ ");"
        );
        try {
            statement.execute(query);
            added = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  added;
    }

    public boolean refund(int ticketNumber){
        boolean refund = false;
        String query = String.format(
                "UPDATE Payment SET Refund = \'YES\' WHERE Ticket_number = "
                + ticketNumber + ";"
        );
        try {
            statement.execute(query);
            refund = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return refund;
    }

}
