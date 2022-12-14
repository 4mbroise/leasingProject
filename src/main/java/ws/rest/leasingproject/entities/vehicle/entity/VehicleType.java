package ws.rest.leasingproject.entities.vehicle.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum VehicleType {
    @XmlEnumValue("car") CAR("car"),
    @XmlEnumValue("truck") TRUCK("truck");

    public final String type;

    @JsonValue
    public String getType() {
        return type;
    }

    private VehicleType(String gearBox){
        this.type=gearBox;
    }
}
