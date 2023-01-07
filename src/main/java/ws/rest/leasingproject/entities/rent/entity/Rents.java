package ws.rest.leasingproject.entities.rent.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;

import java.util.List;

@JacksonXmlRootElement(localName = "rents")
@JsonAutoDetect
public class Rents {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "rent")
    @JsonProperty
    @JsonUnwrapped
    private Rent[] rents;

    public Rents(List<Rent> rents){
        this.rents = new Rent[rents.size()];
        rents.toArray(this.rents);
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
