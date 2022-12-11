package ws.rest.leasingproject.entities.vehicle.entity;

public enum MotorType {
    ELECTRIC("electric"),
    DIESEL("diesel"),
    PETROL("petrol");

    public final String type;

    private MotorType(String gearBox){
        this.type=gearBox;
    }
}
