package ws.rest.leasingproject.entities.vehicle.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "vehicles")
@JsonAutoDetect
public class Vehicles {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "vehicle")
    @JsonProperty
    @JsonUnwrapped
    private Vehicle[] vehicles;

    public Vehicles(List<Vehicle> vehicules){
        this.vehicles = new Vehicle[vehicules.size()];
        vehicules.toArray(this.vehicles);
    }

    public String toXML() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(this);
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(this);
    }
}
