package ws.rest.leasingproject.entities.employee.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
    private int memberId;
    private String name;
    private String surname;
    private String socialSecurityId;
    private String driverLicenseId;
    private String adress;

    public static Employee fromXML(String xml) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        Employee employee = null;
        employee = xmlMapper.readValue(xml, Employee.class);

        if (employee.name == null) throw new Exception("name missing");
        if (employee.surname == null) throw new Exception("name missing");
        if (employee.socialSecurityId == null) throw new Exception("name missing");
        if (employee.driverLicenseId == null) throw new Exception("name missing");
        if (employee.adress == null) throw new Exception("name missing");
        if (!Employee.isSocialSecurityIdValid(employee.socialSecurityId)) throw new Exception("wrong format : socialSecurityId");
        if (!Employee.isDriverLicenseIdValid(employee.driverLicenseId)) throw new Exception("wrong format : driverLicenseId");

        return employee;
    }

    public static Employee defaultEmployee() {
        Employee employee = new Employee();
        employee.memberId = -1;
        employee.name = "Ambroise";
        employee.surname = "FAUGIER";
        employee.socialSecurityId = "000000000000011";
        employee.driverLicenseId = "0123456789abcde";
        employee.adress = "Mon adress";
        return employee;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSocialSecurityId() {
        return socialSecurityId;
    }

    public void setSocialSecurityId(String socialSecurityId) {
        this.socialSecurityId = socialSecurityId;
    }

    public String getDriverLicenseId() {
        return driverLicenseId;
    }

    public void setDriverLicenseId(String driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", socialSecurityId='" + socialSecurityId + '\'' +
                ", driverLicenseId='" + driverLicenseId + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }

    public String toXML() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(this);
    }

    public static boolean isDriverLicenseIdValid(String toTest){
        if(toTest == null){
            return false;
        }
        return toTest.length() <= 15;
    }

    public static boolean isSocialSecurityIdValid(String toTest){

        if(toTest == null){
            return false;
        }

        final String regex =  "^[0-9]{15}$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(toTest);

        return matcher.find();
    }
}
