package ws.rest.leasingproject;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ws.rest.leasingproject.entities.employee.entity.Employee;
import ws.rest.leasingproject.entities.rent.dao.IRentDao;
import ws.rest.leasingproject.entities.rent.dao.MysSQLRentDAO;
import ws.rest.leasingproject.entities.rent.entity.Rent;
import ws.rest.leasingproject.entities.rent.entity.Rents;
import ws.rest.leasingproject.entities.vehicle.dao.MySQLVehicleDAO;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;


@Path("/rent")
public class RentRessource {


    IRentDao rentDAO;

    public RentRessource() {
        try {
            this.rentDAO = new MysSQLRentDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String employeeXML() {

        try {
            Rents rents = new Rents(rentDAO.findAll());
            return rents.toXML();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String employeeJSON() {

        try {
            Rents rents = new Rents(rentDAO.findAll());
            return rents.toJSON();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public String createEmployeeXML(String xmlAsEmployee) {
        Employee employee = null;
        try {
            employee = Employee.fromXML(xmlAsEmployee);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
        return employee.toString();
    }

}
