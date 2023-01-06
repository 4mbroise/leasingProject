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
import ws.rest.leasingproject.entities.vehicle.entity.*;

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
            return new Vehicles(vehicleDAO.findAll()).toXML();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String carJSON() {
        try {
            return new Vehicles(vehicleDAO.findAll()).toJSON();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{registration}")
    public String vehicleXML(@PathParam("registration") String registration) {
        try {
            return vehicleDAO.findByRegistration(registration).toXML();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{registration}")
    public String vehicleJSON(@PathParam("registration") String registration) {
        try {
            return vehicleDAO.findByRegistration(registration).toJSON();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public String createEmployeeXML(String xmlAsVehicle) {
        Vehicle employee = null;
        try {
            employee = Vehicle.fromXML(xmlAsVehicle);
            return vehicleDAO.findByRegistration(employee.getRegistration()).toXML();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String createEmployeeJSON(String jsonAsVehicle) {
        Vehicle employee = null;
        try {
            employee = Vehicle.fromJSON(jsonAsVehicle);
            return vehicleDAO.findByRegistration(employee.getRegistration()).toJSON();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{registration}")
    public String updateEmployeeXML(@PathParam("registration") String registration, String xmlAsVehicule) {
        Vehicle vehicule = null;
        try {
            vehicule = Vehicle.fromXML(xmlAsVehicule);
            vehicleDAO.updateRentByVehicleId(registration, vehicule);
            return vehicleDAO.findByRegistration(registration).toXML();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{registration}")
    public String updateEmployeeJSON(@PathParam("registration") String registration, String jsonAsVehicule) {
        Vehicle vehicule = null;
        try {
            vehicule = Vehicle.fromXML(jsonAsVehicule);
            vehicleDAO.updateRentByVehicleId(registration, vehicule);
            return vehicleDAO.findByRegistration(registration).toXML();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String deleteEmployeeJSON(@PathParam("id") String registration) {
        try {
            vehicleDAO.removeVehicleByRegistration(registration);
            return "{}";
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
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
