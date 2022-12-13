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

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public Employee getRenter() {
        return renter;
    }

    public void setRenter(Employee renter) {
        this.renter = renter;
    }

    public Vehicle getRentedVehicle() {
        return rentedVehicle;
    }

    public void setRentedVehicle(Vehicle rentedVehicle) {
        this.rentedVehicle = rentedVehicle;
    }

    public Date getRentDay() {
        return rentDay;
    }

    public void setRentDay(Date rentDay) {
        this.rentDay = rentDay;
    }

    public Time getHourStartRent() {
        return hourStartRent;
    }

    public void setHourStartRent(Time hourStartRent) {
        this.hourStartRent = hourStartRent;
    }

    public Time getHourEndRent() {
        return hourEndRent;
    }

    public void setHourEndRent(Time hourEndRent) {
        this.hourEndRent = hourEndRent;
    }
}
