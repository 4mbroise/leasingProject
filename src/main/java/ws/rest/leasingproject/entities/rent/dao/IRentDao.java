package ws.rest.leasingproject.entities.rent.dao;

import ws.rest.leasingproject.entities.rent.entity.Rent;

import java.util.List;

public interface IRentDao {
    List<Rent> findAll();
    Rent findByRentId(int rentId);
    void createRent(Rent rent);
    void updateRentByRentId(int rentId);
    void removeRentByRentId(int rentId);
}
