package ws.rest.leasingproject.entities.vehicle.dao;

import jakarta.inject.Singleton;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Singleton
public class MySQLVehicleDAO implements IVehicleDAO{

    private Connection dbConnection;

    public MySQLVehicleDAO() throws ClassNotFoundException, SQLException {
        System.out.println("Faugier");
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/leasing", "root", "");

        // CREATE TABLE IF NOT EXISTS employees (memberId int PRIMARY KEY AUTO_INCREMENT, name varchar(100), surnname varchar(100), socialSecurityId varchar(100), driverLicenseId varchar(100), adress varchar(100));
        String createVehicleTable = "CREATE TABLE IF NOT EXISTS employees (memberId int PRIMARY KEY AUTO_INCREMENT, name varchar(100), surnname varchar(100), socialSecurityId varchar(100), driverLicenseId varchar(100), adress varchar(100));";
        try(Statement statement = dbConnection.createStatement()){
            statement.execute(createVehicleTable);
        }
    }


    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public Vehicle findByVehicleId(int VehicleId) {
        return null;
    }

    @Override
    public void createVehicle(Vehicle vehicle) {

    }

    @Override
    public void updateRentByVehicleId(int VehicleId) {

    }

    @Override
    public void removeRentByVehicleId(int VehicleId) {

    }
}
