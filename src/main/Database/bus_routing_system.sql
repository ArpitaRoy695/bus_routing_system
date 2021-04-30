CREATE DATABASE bus_routing_system;

USE bus_routing_system;

CREATE TABLE Users
(
    User_ID int NOT NULL AUTO_INCREMENT,
    User_pic text,
    Full_Name varchar (30),
    Address varchar (100),
    Phone_number varchar (15),
    Email varchar (30) NOT NULL,
    User_Role varchar (9),
    Password varchar (30),
    PRIMARY KEY (User_ID)
);


CREATE TABLE Routes
(
    Route_ID int(4) NOT NULL AUTO_INCREMENT,
    Start_point varchar (20),
    End_point varchar (20),
    AC_Cost int (4),
    Non_AC_Cost int(4),
    Start_time time ,
    End_time time,
    PRIMARY KEY (Route_ID)
);

CREATE TABLE Buses
(
    Bus_ID int(4) NOT NULL AUTO_INCREMENT,
    Bus_route int(4),
    Bus_Type varchar (6),
    No_Seats int (2),
    Available_seats varchar(108),
    Taken_seats varchar (108),
    PRIMARY KEY (Bus_ID),
    FOREIGN KEY (Bus_route) REFERENCES Routes(Route_ID)
);


CREATE TABLE Tickets
(
    Ticket_no int NOT NULL AUTO_INCREMENT,
    Seat_no char(2),
    Bus_no int(4),
    Passenger_ID int,
    Route_ID int,
    Price int(4),
    Journey_date date NOT NULL,
    PRIMARY KEY (Ticket_no),
    FOREIGN KEY (Passenger_ID) REFERENCES  Users(User_ID),
    FOREIGN KEY (Route_ID) REFERENCES Routes(Route_ID)
);

CREATE TABLE Payments
(
    Payment_ID int (9) AUTO_INCREMENT,
    Payment_method char (6),
    Amount int (4),
    Ticket_number int,
    Refund char(3) DEFAULT 'NO',
    Passenger_ID int,
    PRIMARY KEY (Payment_ID),
    FOREIGN KEY (Ticket_number) REFERENCES Tickets(Ticket_no),
    FOREIGN KEY (Passenger_ID) REFERENCES Users(User_ID)
);

CREATE TABLE Complaints
(
    Complaint_ID int AUTO_INCREMENT,
    Body TEXT,
    User_ID int,
    PRIMARY KEY (Complaint_ID),
    FOREIGN KEY (User_ID) REFERENCES Users(User_ID)
);