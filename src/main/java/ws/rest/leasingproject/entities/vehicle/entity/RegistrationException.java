package ws.rest.leasingproject.entities.vehicle.entity;

public class RegistrationException extends Exception{
    RegistrationException(){
        super("Registration doesn't match the 'AA-001-AA' format");
    }
}
