package ws.rest.leasingproject.entities.vehicle.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum MotorType {
    @XmlEnumValue("electric") ELECTRIC("electric"),
    @XmlEnumValue("diesel") DIESEL("diesel"),
    @XmlEnumValue("petrol") PETROL("petrol");

    public final String type;

    @JsonValue
    public String getType() {
        return type;
    }

    private MotorType(String gearBox){
        this.type=gearBox;
    }
}
