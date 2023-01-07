package ws.rest.leasingproject.entities.rent.dao;

import ws.rest.leasingproject.entities.rent.entity.Rent;

import java.util.List;

public interface IRentDao {
    List<Rent> findAll() throws Exception;
    Rent findByRentId(int rentId) throws Exception;
    void createRent(Rent rent) throws Exception;
    void updateRentByRentId(int rentId) throws Exception;
    void removeRentByRentId(int rentId) throws Exception;
}
