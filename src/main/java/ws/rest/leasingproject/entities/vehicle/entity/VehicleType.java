package ws.rest.leasingproject.entities.vehicle.entity;

public enum VehicleType {
    CAR("car"),
    TRUCK("truck");

    public final String type;

    private VehicleType(String gearBox){
        this.type=gearBox;
    }
}
