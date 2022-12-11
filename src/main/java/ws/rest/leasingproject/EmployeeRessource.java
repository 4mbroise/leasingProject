package ws.rest.leasingproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBException;
import org.dom4j.DocumentException;
import org.glassfish.jersey.client.internal.ClientResponseProcessingException;
import ws.rest.leasingproject.entities.employee.entity.Employee;
import ws.rest.leasingproject.entities.vehicle.entity.GearBox;
import ws.rest.leasingproject.entities.vehicle.entity.MotorType;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;
import ws.rest.leasingproject.entities.vehicle.entity.VehicleType;
import ws.rest.leasingproject.entities.vehicle.entity.parsers.VehicleJSONParser;
import ws.rest.leasingproject.entities.vehicle.entity.parsers.VehicleXMLParser;

@Path("/employee")
public class EmployeeRessource {


    private Employee car = Employee.defaultEmployee();


    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String employeeXML() {
        try {
            System.out.println("Elliot");
            return Employee.defaultEmployee().toXML();
        } catch (JsonProcessingException e) {
            return e.getMessage();
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
