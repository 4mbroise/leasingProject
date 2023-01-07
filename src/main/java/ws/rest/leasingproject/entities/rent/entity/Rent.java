package ws.rest.leasingproject.entities.rent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ws.rest.leasingproject.entities.employee.dao.IEmployeeDAO;
import ws.rest.leasingproject.entities.employee.dao.MySQLEmployeeDAO;
import ws.rest.leasingproject.entities.employee.entity.Employee;
import ws.rest.leasingproject.entities.vehicle.dao.IVehicleDAO;
import ws.rest.leasingproject.entities.vehicle.dao.MySQLVehicleDAO;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;

import java.rmi.server.ExportException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@JacksonXmlRootElement(localName = "rent")
public class Rent {
    private int rentId;

    @JsonIgnore
    private Employee renter;

    @JsonIgnore
    private Vehicle rentedVehicle;
    private Date rentDay;
    private Time hourStartRent;
    private Time hourEndRent;

    public static Rent fromXML(String xml) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        Rent rent;
        rent = xmlMapper.readValue(xml, Rent.class);

        //checkIfValidEmployee(employee);

        return rent;
    }

    public static Rent fromJSON(String xml) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        Rent rent;
        rent = jsonMapper.readValue(xml, Rent.class);

        //checkIfValidEmployee(employee);

        return rent;
    }

    @JsonProperty("renter")
    public int getEmployeeId(){
        return this.getRenter().getMemberId();
    }

    @JsonProperty("rentedVehicle")
    public String getRentedVehicleRegistration(){
        return this.rentedVehicle.getRegistration();
    }

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

    public String toXML() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(this);
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(this);
    }

    @Override
    public String toString() {
        return "Rent{" +
                "rentId=" + rentId +
                ", renter=" + renter +
                ", rentedVehicle=" + rentedVehicle +
                ", rentDay=" + rentDay +
                ", hourStartRent=" + hourStartRent +
                ", hourEndRent=" + hourEndRent +
                '}';
    }

    public static void checkIfRentIsValid(Rent rent) throws Exception {
        try {
            Integer id = new Integer(rent.rentId);
        } catch (NumberFormatException e){
            throw new Exception("not a correct rent Id "+rent.rentId);
        }

        Employee.checkIfValidEmployee(rent.renter);
        Vehicle.checkIfValidVehicule(rent.rentedVehicle);

        if(rent.rentDay == null) throw new Exception("Missing rent Day");
        if(rent.hourStartRent == null) throw new Exception("Missing rent Day");
        if(rent.hourEndRent == null) throw new Exception("Missing rent Day");

        Date today = new Date(System.currentTimeMillis());

        // rentDay > today
        if(today.after(rent.rentDay)) throw new Exception("You can plan a rent only after today, rent day :"+rent.rentDay.toString());

        // hourStartRent < hourEndRent
        if(rent.hourStartRent.after(rent.hourEndRent)) throw new Exception("The start time of a rent should be before its end time. start : "+rent.hourStartRent.toString()+ ". end : "+rent.hourEndRent.toString());

        LocalDate date = rent.rentDay.toLocalDate().minusDays(7);
        Date sqlDateRangeStart = new Date(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        date = rent.rentDay.toLocalDate().plusDays(7);
        Date sqlDateRangeEnd = new Date(date.getYear(), date.getMonthValue(), date.getDayOfMonth());

        String queryNumberOfRent = "SELECT COUNT(*) as rentCount FROM `rent` WHERE rentDate >= ? AND rentDate >= ? AND renter = ?";


    }

    public static Rent defaultRent() {
        Rent rent = new Rent();
        rent.setRentId(-1111);
        rent.setRenter(Employee.defaultEmployee());
        rent.setRentedVehicle(Vehicle.defaultVehicle());

        //  Today
        long date = System.currentTimeMillis();
        rent.setRentDay(new Date(date));
        rent.setHourStartRent(new Time(System.currentTimeMillis()));
        rent.setHourEndRent(new Time(System.currentTimeMillis() + 3600000));

        return rent;
    }

}
