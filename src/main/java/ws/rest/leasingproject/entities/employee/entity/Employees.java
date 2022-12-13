package ws.rest.leasingproject.entities.employee.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JacksonXmlRootElement(localName = "employees")
@JsonAutoDetect
public class Employees {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "employee")
    @JsonProperty
    @JsonUnwrapped
    private Employee[] employees;

    public Employees(List employees) {
        this.employees = new Employee[employees.size()];
        employees.toArray(this.employees);
        System.out.println(this.employees[0]);
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
