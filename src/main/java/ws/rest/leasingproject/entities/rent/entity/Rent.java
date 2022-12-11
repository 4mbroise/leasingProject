package ws.rest.leasingproject.entities.rent.entity;

import ws.rest.leasingproject.entities.employee.entity.Employee;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;

import java.sql.Date;
import java.sql.Time;

public class Rent {
    private int rentId;
    private Employee renter;
    private Vehicle rentedVehicle;
    private Date rentDay;
    private Time hourStartRent;
    private Time hourEndRent;
}
