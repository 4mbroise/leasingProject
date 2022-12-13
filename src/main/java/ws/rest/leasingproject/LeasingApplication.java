package ws.rest.leasingproject;

import jakarta.inject.Singleton;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@ApplicationPath("/api")
public class LeasingApplication extends Application {
    public  LeasingApplication() throws ClassNotFoundException, SQLException {
        System.out.println("test");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/leasing", "root", "");

        // CREATE TABLE IF NOT EXISTS employees (memberId int PRIMARY KEY AUTO_INCREMENT, name varchar(100), surnname varchar(100), socialSecurityId varchar(100), driverLicenseId varchar(100), adress varchar(100));

        String createVehicleTable = "create table if not exists vehicle" +
                "(" +
                "    registration varchar(100) not null," +
                "    type         varchar(100) not null," +
                "    brand         varchar(100) not null," +
                "    model         varchar(100) not null," +
                "    motorType         varchar(100) not null," +
                "    gearBox         varchar(100) not null," +
                "    description         varchar(500) not null," +
                "    constraint vehicle_pk" +
                "    primary key (registration)" +
                "    );";

        String createEmployeeTable = "create table if not exists employee" +
                "(" +
                "    memberId         int auto_increment," +
                "    name             varchar(100) not null," +
                "    surname          varchar(100) not null," +
                "    socialSecurityId varchar(100) not null," +
                "    driverLicenseId  varchar(100) not null," +
                "    adress           varchar(100) not null," +
                "    constraint employee_pk" +
                "    primary key (memberId)" +
                "    );";

        String createRentTable = "create table if not exists rent" +
                "(" +
                "    rentId        int          not null," +
                "    renter        int          not null," +
                "    rentedVehicle varchar(100) not null," +
                "    rentDate      DATE         not null," +
                "    hourStartRent TIME         not null," +
                "    hoursEndRent  TIME         not null," +
                "    constraint rent_pk" +
                "    primary key (rentId)," +
                "    constraint rentedVehicle" +
                "    foreign key (rentedVehicle) references vehicle (registration)," +
                "    constraint renter" +
                "    foreign key (renter) references employee (memberId)" +
                "    );";

        try(Statement statement = dbConnection.createStatement()){
            statement.execute(createVehicleTable);
            statement.execute(createEmployeeTable);
            statement.execute(createRentTable);
        }
        dbConnection.close();
    }
}