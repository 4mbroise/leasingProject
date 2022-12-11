package ws.rest.leasingproject.entities.vehicle.entity;

public class Vehicle {
    private String immatriculation;
    private VehicleType type;
    private String brand;
    private String model;
    private MotorType motorType;
    private GearBox gearBox;
    private String description;

    public Vehicle(String immatriculation, VehicleType type, String brand, String model, MotorType motorType, GearBox gearBox, String description) {
        this.immatriculation = immatriculation;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.motorType = motorType;
        this.gearBox = gearBox;
        this.description = description;
    }

    public Vehicle(){};

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
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
}
