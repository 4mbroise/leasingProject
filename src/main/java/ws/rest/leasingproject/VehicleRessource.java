package ws.rest.leasingproject;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dom4j.DocumentException;
import org.glassfish.jersey.client.internal.ClientResponseProcessingException;
import ws.rest.leasingproject.entities.vehicle.dao.IVehicleDAO;
import ws.rest.leasingproject.entities.vehicle.dao.MySQLVehicleDAO;
import ws.rest.leasingproject.entities.vehicle.entity.GearBox;
import ws.rest.leasingproject.entities.vehicle.entity.MotorType;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;
import ws.rest.leasingproject.entities.vehicle.entity.VehicleType;
import ws.rest.leasingproject.entities.vehicle.entity.parsers.VehicleJSONParser;
import ws.rest.leasingproject.entities.vehicle.entity.parsers.VehicleXMLParser;

import java.sql.SQLException;

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
        return VehicleXMLParser.toXML(car);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String carJSON() {
        //return "JSON";
        return VehicleJSONParser.toJSON(car);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public String createCarXML(String carAsXMLString) {
        try{
            String xml = VehicleXMLParser.toXML(VehicleXMLParser.fromXML(carAsXMLString));
            return xml;
        } catch (DocumentException e){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
