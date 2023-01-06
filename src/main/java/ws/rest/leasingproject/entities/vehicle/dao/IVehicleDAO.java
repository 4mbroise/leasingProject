package ws.rest.leasingproject.entities.vehicle.dao;

import ws.rest.leasingproject.entities.rent.entity.Rent;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;

import java.util.List;

public interface IVehicleDAO {
    List<Vehicle> findAll() throws Exception;

    Vehicle findByRegistration(String registration) throws Exception;
    void createVehicle(Vehicle vehicle) throws Exception;
    void updateRentByVehicleId(String registration, Vehicle vehicle) throws Exception;

    void updateByRegistration(String registration, Vehicle vehicle) throws Exception;

    void removeVehicleByRegistration(String registration) throws Exception;

}
