package ws.rest.leasingproject.entities.vehicle.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vehicle {
    private String immatriculation;
    private VehicleType type;
    private String brand;
    private String model;
    private MotorType motorType;
    private GearBox gearBox;
    private String description;

    public Vehicle(String immatriculation, VehicleType type, String brand, String model, MotorType motorType, GearBox gearBox, String description) throws RegistrationException {
        if(!isRegistrationFormatOK(immatriculation)){
            throw new RegistrationException();
        }
        this.immatriculation = immatriculation;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.motorType = motorType;
        this.gearBox = gearBox;
        this.description = description;
    }

    public Vehicle(){};

    public static Vehicle defaultVehicle(){
        try{
            return new Vehicle("AA-001-AA", VehicleType.CAR,
                    "BMW", "i8", MotorType.PETROL,
                    GearBox.MANUAL, "? description ?");
        } catch (RegistrationException e) {
            return null;
        }
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) throws RegistrationException {
        if(!isRegistrationFormatOK(immatriculation)){
            throw new RegistrationException();
        }
        this.immatriculation = immatriculation;
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

    public static boolean isRegistrationFormatOK(String toTest){
        final String regex =  "^[A-Z]{2}-[0-9]{3}-[A-Z]{2}$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(toTest);

        return matcher.find();
    }

}
