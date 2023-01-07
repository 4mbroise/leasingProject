package ws.rest.leasingproject.entities.employee.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JacksonXmlRootElement(localName = "employee")
public class Employee {
    @JsonProperty("memberID")
    private int memberId;
    private String name;
    private String surname;
    private String socialSecurityId;
    private String driverLicenseId;
    private String adress;

    public static Employee fromXML(String xml) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        Employee employee;
        employee = xmlMapper.readValue(xml, Employee.class);

        checkIfValidEmployee(employee);

        return employee;
    }

    public static Employee fromJSON(String json) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        Employee employee;
        employee = jsonMapper.readValue(json, Employee.class);

        checkIfValidEmployee(employee);

        return employee;
    }

    public static Employee defaultEmployee() {
        Employee employee = new Employee();
        employee.memberId = -1111;
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

    public String toJSON() throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(this);
    }

    public static void checkIfValidEmployee(Employee employee) throws Exception {
        if (employee.name == null) throw new Exception("name missing");
        if (employee.surname == null) throw new Exception("surname missing");
        if (employee.socialSecurityId == null) throw new Exception("socialSecurityId missing");
        if (employee.driverLicenseId == null) throw new Exception("driverLicenseId missing");
        if (employee.adress == null) throw new Exception("adress missing");
        if (!Employee.isSocialSecurityIdValid(employee.socialSecurityId)) throw new Exception("wrong format : socialSecurityId");
        if (!Employee.isDriverLicenseIdValid(employee.driverLicenseId)) throw new Exception("wrong format : driverLicenseId");

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
