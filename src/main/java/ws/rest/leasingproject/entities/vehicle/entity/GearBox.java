package ws.rest.leasingproject.entities.vehicle.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum GearBox {
    @XmlEnumValue("manual") MANUAL("manual"),
    @XmlEnumValue("auto") AUTOMATIC("auto");

    public final String gearBox;

    @JsonValue
    public String getGearBox() {
        System.out.println(gearBox);
        return gearBox;
    }

    private GearBox(String gearBox){
        this.gearBox=gearBox;
    }
}
