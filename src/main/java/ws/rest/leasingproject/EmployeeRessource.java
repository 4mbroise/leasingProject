package ws.rest.leasingproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBException;
import org.dom4j.DocumentException;
import org.glassfish.jersey.client.internal.ClientResponseProcessingException;
import ws.rest.leasingproject.entities.employee.dao.IEmployeeDAO;
import ws.rest.leasingproject.entities.employee.dao.MySQLEmployeeDAO;
import ws.rest.leasingproject.entities.employee.entity.Employee;
import ws.rest.leasingproject.entities.employee.entity.Employees;
import ws.rest.leasingproject.entities.vehicle.entity.GearBox;
import ws.rest.leasingproject.entities.vehicle.entity.MotorType;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;
import ws.rest.leasingproject.entities.vehicle.entity.VehicleType;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringWriter;
import java.util.List;

@Path("/employee")
public class EmployeeRessource {

    private IEmployeeDAO employeeDAO = new MySQLEmployeeDAO();


    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String employeeXML() {
        try {
            return new Employees(employeeDAO.findAll()).toXML();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String employeeJSON() {
        try {
            return new Employees(employeeDAO.findAll()).toJSON();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{id}")
    public String employeeXML(@PathParam("id") int id) {
        try {
            return employeeDAO.findByMemberId(id).toXML();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String employeeJSON(@PathParam("id") int id) {
        try {
            return employeeDAO.findByMemberId(id).toJSON();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public String createEmployeeXML(String xmlAsEmployee) {
        Employee employee = null;
        try {
            employee = Employee.fromXML(xmlAsEmployee);
            Employee.checkIfValidEmployee(employee);
            int memberId = employeeDAO.createEmployee(employee);
            return employeeDAO.findByMemberId(memberId).toXML();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String createEmployeeJSON(String jsonAsEmployee) {
        Employee employee = null;
        try {
            employee = Employee.fromJSON(jsonAsEmployee);
            Employee.checkIfValidEmployee(employee);
            int memberId = employeeDAO.createEmployee(employee);
            return employeeDAO.findByMemberId(memberId).toJSON();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{id}")
    public String updateEmployeeXML(@PathParam("id") int id, String xmlAsEmployee) {
        Employee employee = null;
        try {
            employee = Employee.fromXML(xmlAsEmployee);
            employeeDAO.updateEmployeeByMemberId(id, employee);
            return employeeDAO.findByMemberId(id).toXML();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String updateEmployeeJSON(@PathParam("id") int id, String xmlAsEmployee) {
        Employee employee = null;
        try {
            employee = Employee.fromJSON(xmlAsEmployee);
            employeeDAO.updateEmployeeByMemberId(id, employee);
            return employeeDAO.findByMemberId(id).toJSON();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String deleteEmployeeJSON(@PathParam("id") int id) {
        try {
            employeeDAO.removeEmployeeByMemberId(id);
            return "{}";
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }




}
