package ws.rest.leasingproject.entities.vehicle.entity;

import java.lang.annotation.ElementType;

public enum GearBox {
    MANUAL("manual"),
    AUTOMATIC("auto");

    public final String gearBox;

    private GearBox(String gearBox){
        this.gearBox=gearBox;
    }
}
