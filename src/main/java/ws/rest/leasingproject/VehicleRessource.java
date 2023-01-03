package ws.rest.leasingproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dom4j.DocumentException;
import ws.rest.leasingproject.entities.employee.entity.Employee;
import ws.rest.leasingproject.entities.vehicle.dao.IVehicleDAO;
import ws.rest.leasingproject.entities.vehicle.dao.MySQLVehicleDAO;
import ws.rest.leasingproject.entities.vehicle.entity.MotorType;
import ws.rest.leasingproject.entities.vehicle.entity.RegistrationException;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;
import ws.rest.leasingproject.entities.vehicle.entity.VehicleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Path("/vehicle")
public class VehicleRessource {


    Vehicle car = Vehicle.defaultVehicle();
    IVehicleDAO vehicleDAO;

    public VehicleRessource() {
        System.out.println("Elliot");
        try {
            this.vehicleDAO = new MySQLVehicleDAO();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String carXML() {
        try {
            return car.toXML();
        } catch (JsonProcessingException e) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String carJSON() {
        try {
            return car.toJSON();
        } catch (JsonProcessingException e) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public String createCarXML(String vehicleAsXMLString) {
        try{
            String xml = Vehicle.fromXML(vehicleAsXMLString).toXML();
            return xml;
        } catch (DocumentException e){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createCarJSON(String vehicleAsJSONString) {
        try{
            String xml = Vehicle.fromJSON(vehicleAsJSONString).toJSON();
            return xml;
        } catch (DocumentException e){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
