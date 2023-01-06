package ws.rest.leasingproject.entities.vehicle.dao;

import jakarta.inject.Singleton;
import ws.rest.leasingproject.entities.vehicle.entity.MotorType;
import ws.rest.leasingproject.entities.vehicle.entity.RegistrationException;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;
import ws.rest.leasingproject.entities.vehicle.entity.VehicleType;

import java.sql.*;
import java.util.ArrayList;
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
    public List<Vehicle> findAll() throws Exception {
        Connection connection = initConnection();

        String queryALlVehicle = "SELECT * from leasing.vehicle";
        PreparedStatement statement = connection.prepareStatement(queryALlVehicle);
        ResultSet rs = statement.executeQuery();
        List<Vehicle> result = new ArrayList<>();

        while(rs.next()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setRegistration(rs.getString("registration"));

            //Gestion des type de véhicules
            String type = rs.getString("type");
            switch (type) {
                case "car":
                    vehicle.setType(VehicleType.CAR);
                    break;
                case "truck":
                    vehicle.setType(VehicleType.TRUCK);
                    break;
                default:
                    throw new Exception();
            }

            //Gestion des type de moteur
            String motorType = rs.getString("motorType");
            switch (motorType){
                case "electric":
                    vehicle.setMotorType(MotorType.ELECTRIC);
                    break;
                case "diesel":
                    vehicle.setMotorType(MotorType.DIESEL);
                    break;
                case "petrol":
                    vehicle.setMotorType(MotorType.PETROL);
                    break;
                default:
                    throw new Exception();
            }

            //Gestion des type de boîtes de vitesses
            String gearBox = rs.getString("gearBox");
            switch (gearBox) {
                case "manual":
                    vehicle.setType(VehicleType.CAR);
                    break;
                case "auto":
                    vehicle.setType(VehicleType.TRUCK);
                    break;
                default:
                    throw new Exception();
            }

            String brand = rs.getString("brand");
            vehicle.setBrand(brand);

            String model = rs.getString("model");
            vehicle.setModel(model);

            String description = rs.getString("description");
            vehicle.setDescription(description);

            result.add(vehicle);
        }
        connection.close();
        return result;
    }

    @Override
    public Vehicle findByRegistration(String registration) throws Exception {
        Connection connection = initConnection();
        String queryCehicle = "SELECT * from leasing.employee WHERE registration = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(queryCehicle);
        preparedStatement.setString(1, registration);

        ResultSet rs = preparedStatement.executeQuery();
        List<Vehicle> result = new ArrayList<>();

        while (rs.next()){
            Vehicle vehicle = new Vehicle();
            vehicle.setRegistration(rs.getString("registration"));

            //Gestion des type de véhicules
            String type = rs.getString("type");
            switch (type) {
                case "car":
                    vehicle.setType(VehicleType.CAR);
                    break;
                case "truck":
                    vehicle.setType(VehicleType.TRUCK);
                    break;
                default:
                    throw new Exception();
            }

            //Gestion des type de moteur
            String motorType = rs.getString("motorType");
            switch (motorType){
                case "electric":
                    vehicle.setMotorType(MotorType.ELECTRIC);
                    break;
                case "diesel":
                    vehicle.setMotorType(MotorType.DIESEL);
                    break;
                case "petrol":
                    vehicle.setMotorType(MotorType.PETROL);
                    break;
                default:
                    throw new Exception();
            }

            //Gestion des type de boîtes de vitesses
            String gearBox = rs.getString("gearBox");
            switch (gearBox) {
                case "manual":
                    vehicle.setType(VehicleType.CAR);
                    break;
                case "auto":
                    vehicle.setType(VehicleType.TRUCK);
                    break;
                default:
                    throw new Exception();
            }

            String brand = rs.getString("brand");
            vehicle.setBrand(brand);

            String model = rs.getString("model");
            vehicle.setModel(model);

            String description = rs.getString("description");
            vehicle.setDescription(description);

            result.add(vehicle);
        }
        connection.close();

        if(result.size() == 0){
            throw new Exception("No vehicle registred with the following registration : "+registration);
        } else {
            return result.get(0);
        }
    }


    @Override
    public void createVehicle(Vehicle vehicle) throws Exception {
        Vehicle.checkIfValidVehicule(vehicle);
        Connection connection = initConnection();

        String queryCreateVehicle = "INSERT INTO leasing.vehicle (registration, type, brand, model, motorType, gearBox, description) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(queryCreateVehicle);

        preparedStatement.setString(1, vehicle.getRegistration());

        // Gestion Type
        switch(vehicle.getType()){
            case CAR:
                preparedStatement.setString(2,"car");
                break;
            case TRUCK:
                preparedStatement.setString(2,"truck");
                break;
            default:
                throw new Exception();
        }

        preparedStatement.setString(3, vehicle.getBrand());
        preparedStatement.setString(4, vehicle.getModel());

        // Gestion Motor Type
        switch (vehicle.getMotorType()){
            case DIESEL:
                preparedStatement.setString(5, "diesel");
                break;
            case PETROL:
                preparedStatement.setString(5, "petrol");
                break;
            case ELECTRIC:
                preparedStatement.setString(5, "electric");
                break;
            default:
                throw new Exception();
        }

        // Gestion Gear Box
        switch (vehicle.getGearBox()){
            case MANUAL:
                preparedStatement.setString(6, "manual");
                break;
            case AUTOMATIC:
                preparedStatement.setString(6, "automatic");
                break;
            default:
                throw new Exception();
        }
        preparedStatement.setString(7, vehicle.getDescription());
    }

    @Override
    public void updateRentByVehicleId(String registration, Vehicle vehicle) throws Exception {
        Connection connection = initConnection();

        // Update Type
        if(vehicle.getType() != null){
            String update = "UPDATE leasing.employee SET type=? WHERE registraton=?;";
            PreparedStatement statement = connection.prepareStatement(update);

            switch(vehicle.getType()){
                case CAR:
                    statement.setString(1,"car");
                    break;
                case TRUCK:
                    statement.setString(1,"truck");
                    break;
                default:
                    throw new Exception();
            }

            statement.setString(2, registration);
            statement.execute();
        }

        // Update Brand
        if(vehicle.getType() != null){
            String update = "UPDATE leasing.employee SET brand=? WHERE registraton=?;";
            PreparedStatement statement = connection.prepareStatement(update);

            statement.setString(1, vehicle.getBrand());
            statement.setString(2, registration);

            statement.execute();
        }

        // Update Model
        if(vehicle.getType() != null){
            String update = "UPDATE leasing.employee SET model=? WHERE registraton=?;";
            PreparedStatement statement = connection.prepareStatement(update);

            statement.setString(1, vehicle.getModel());
            statement.setString(2, registration);

            statement.execute();
        }

        // Update MotorType
        if(vehicle.getType() != null){
            String update = "UPDATE leasing.employee SET motorType=? WHERE registraton=?;";
            PreparedStatement statement = connection.prepareStatement(update);

            switch (vehicle.getMotorType()){
                case DIESEL:
                    statement.setString(1, "diesel");
                    break;
                case PETROL:
                    statement.setString(1, "petrol");
                    break;
                case ELECTRIC:
                    statement.setString(1, "electric");
                    break;
                default:
                    throw new Exception();
            }

            statement.setString(2, registration);
            statement.execute();
        }

        // Update Gear Box
        if(vehicle.getType() != null){
            String update = "UPDATE leasing.employee SET gearBox=? WHERE registraton=?;";
            PreparedStatement statement = connection.prepareStatement(update);

            switch (vehicle.getGearBox()){
                case MANUAL:
                    statement.setString(1, "manual");
                    break;
                case AUTOMATIC:
                    statement.setString(1, "automatic");
                    break;
                default:
                    throw new Exception();
            }

            statement.setString(2, registration);
            statement.execute();
        }

        // Update Description
        if(vehicle.getType() != null){
            String update = "UPDATE leasing.employee SET description=? WHERE registraton=?;";
            PreparedStatement statement = connection.prepareStatement(update);

            statement.setString(1, vehicle.getDescription());
            statement.setString(2, registration);

            statement.execute();
        }

    }


    @Override
    public void updateByRegistration(String registration, Vehicle vehicle) throws SQLException, ClassNotFoundException {
        Connection connection = initConnection();

        if(vehicle.getBrand() != null){
            String update = "UPDATE leasing.vehicle SET brand=? WHERE registration=?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, vehicle.getBrand());
            statement.setString(2, registration);
            statement.execute();
        }

        if(vehicle.getDescription() != null){
            String update = "UPDATE leasing.vehicle SET description=? WHERE registration=?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, vehicle.getDescription());
            statement.setString(2, registration);
            statement.execute();
        }

        if(vehicle.getType() != null){
            String update = "UPDATE leasing.vehicle SET type=? WHERE registration=?;";
            PreparedStatement statement = connection.prepareStatement(update);

            switch (vehicle.getType()){
                case CAR:
                    statement.setString(1, "car");
                    break;
                case TRUCK:
                    statement.setString(1, "truck");
                    break;
            }
            statement.setString(2, registration);
            statement.execute();
        }

        if(vehicle.getModel() != null){
            String update = "UPDATE leasing.vehicle SET model=? WHERE registration=?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, vehicle.getModel());
            statement.setString(2, registration);
            statement.execute();
        }

        if(vehicle.getMotorType() != null){
            String update = "UPDATE leasing.vehicle SET motorType=? WHERE registration=?;";
            PreparedStatement statement = connection.prepareStatement(update);

            switch (vehicle.getMotorType()){
                case PETROL:
                    statement.setString(1, "petrol");
                    break;
                case DIESEL:
                    statement.setString(1, "diesel");
                    break;
                case ELECTRIC:
                    statement.setString(1, "electric");
                    break;
            }
            statement.setString(2, registration);
            statement.execute();
        }

        if(vehicle.getGearBox() != null){
            String update = "UPDATE leasing.vehicle SET type=? WHERE registration=?;";
            PreparedStatement statement = connection.prepareStatement(update);

            switch (vehicle.getGearBox()){
                case MANUAL:
                    statement.setString(1, "manual");
                    break;
                case AUTOMATIC:
                    statement.setString(1, "automatic");
                    break;
            }
            statement.setString(2, registration);
            statement.execute();
        }
        connection.close();
    }

    @Override
    public void removeVehicleByRegistration(String registration) throws Exception {
        Connection connection = initConnection();
        String delete = "DELETE FROM leasing.employee WHERE registration=?;";
        PreparedStatement statement = connection.prepareStatement(delete);

        statement.setString(1, registration);

        statement.execute(delete);
    }

    private Connection initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/leasing", "root", "");
    }
}
