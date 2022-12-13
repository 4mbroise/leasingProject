package ws.rest.leasingproject.entities.vehicle.dao;

import ws.rest.leasingproject.entities.rent.entity.Rent;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;

import java.util.List;

public interface IVehicleDAO {
    List<Vehicle> findAll() throws Exception;
    Vehicle findByVehicleId(int VehicleId) throws Exception;
    void createVehicle(Vehicle vehicle) throws Exception;
    void updateRentByVehicleId(int VehicleId) throws Exception;
    void removeRentByVehicleId(int VehicleId) throws Exception;
}
