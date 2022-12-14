package ws.rest.leasingproject.entities.vehicle.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jakarta.xmlbind.JakartaXmlBindAnnotationModule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vehicle {
    private String registration;
    private VehicleType type;
    private String brand;
    private String model;
    private MotorType motorType;
    private GearBox gearBox;
    private String description;

    public Vehicle(String registration, VehicleType type, String brand, String model, MotorType motorType, GearBox gearBox, String description) throws RegistrationException {
        if(!isRegistrationFormatOK(registration)){
            throw new RegistrationException();
        }
        this.registration = registration;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.motorType = motorType;
        this.gearBox = gearBox;
        this.description = description;
    }

    public Vehicle(){};

    public static Vehicle fromXML(String xml) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JakartaXmlBindAnnotationModule());
        Vehicle vehicle = null;
        vehicle = xmlMapper.readValue(xml, Vehicle.class);

        Vehicle.checkIfValidVehicule(vehicle);

        return vehicle;
    }

    public static Vehicle fromJSON(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Vehicle vehicle = null;
        vehicle = objectMapper.readValue(json, Vehicle.class);

        Vehicle.checkIfValidVehicule(vehicle);

        return vehicle;
    }

    public static Vehicle defaultVehicle(){
        try{
            return new Vehicle("AA-001-AA", VehicleType.CAR,
                    "BMW", "i8", MotorType.PETROL,
                    GearBox.MANUAL, "? description ?");
        } catch (RegistrationException e) {
            return null;
        }
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) throws RegistrationException {
        if(!isRegistrationFormatOK(registration)){
            throw new RegistrationException();
        }
        this.registration = registration;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public MotorType getMotorType() {
        return motorType;
    }

    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
    }

    public GearBox getGearBox() {
        return gearBox;
    }

    public void setGearBox(GearBox gearBox) {
        this.gearBox = gearBox;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toXML() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JakartaXmlBindAnnotationModule());
        return xmlMapper.writeValueAsString(this);
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(this);
    }

    public static void checkIfValidVehicule(Vehicle vehicle) throws Exception {
        if (vehicle.registration == null) throw new Exception("name registration");
        if (vehicle.type == null) throw new Exception("name type");
        if (vehicle.brand == null) throw new Exception("name brand");
        if (vehicle.model == null) throw new Exception("name model");
        if (vehicle.motorType == null) throw new Exception("name motorType");
        if (vehicle.gearBox == null) throw new Exception("name gearBox");
        if (vehicle.description == null) throw new Exception("name description");
        if (!Vehicle.isRegistrationFormatOK(vehicle.registration)) throw new Exception("wrong format : registration");
    }

    public static boolean isRegistrationFormatOK(String toTest){
        final String regex =  "^[A-Z]{2}-[0-9]{3}-[A-Z]{2}$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(toTest);

        return matcher.find();
    }

}
