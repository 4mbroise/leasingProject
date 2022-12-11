package ws.rest.leasingproject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dom4j.DocumentException;
import org.glassfish.jersey.client.internal.ClientResponseProcessingException;
import ws.rest.leasingproject.entities.vehicle.entity.GearBox;
import ws.rest.leasingproject.entities.vehicle.entity.MotorType;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;
import ws.rest.leasingproject.entities.vehicle.entity.VehicleType;
import ws.rest.leasingproject.entities.vehicle.entity.parsers.VehicleJSONParser;
import ws.rest.leasingproject.entities.vehicle.entity.parsers.VehicleXMLParser;

@Path("/car")
public class HelloResource {

    private Vehicle car = new Vehicle("immat", VehicleType.CAR,
                                    "BMW", "i8", MotorType.PETROL,
                                    GearBox.MANUAL, "? description ?");


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
