package ws.rest.leasingproject;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ws.rest.leasingproject.entities.employee.entity.Employee;

@Path("/rent")
public class RentRessource {


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
