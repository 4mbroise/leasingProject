package ws.rest.leasingproject.entities.vehicle.entity.parsers;

import org.dom4j.*;
import ws.rest.leasingproject.entities.vehicle.entity.GearBox;
import ws.rest.leasingproject.entities.vehicle.entity.MotorType;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;
import ws.rest.leasingproject.entities.vehicle.entity.VehicleType;

public class VehicleXMLParser {
    public static Vehicle fromXML(String xmlAsString) throws DocumentException {

        Vehicle vehicle = new Vehicle();
        Element rootElement = DocumentHelper.parseText(xmlAsString).getRootElement();

        try{
            String registration = rootElement.selectNodes("registration").get(0).getText();
            vehicle.setRegistration(registration);
        } catch (Exception e){
        }
        try{
            String vehicleType = rootElement.selectNodes("type").get(0).getText();
            switch (vehicleType){
                case "car":
                    vehicle.setType(VehicleType.CAR);
                    break;
                case "truck":
                    vehicle.setType(VehicleType.TRUCK);
                    break;
                default:
                    throw new UnknwownAttribute(vehicleType);
            }
        } catch (UnknwownAttribute e){
            System.out.println("ici");
            throw new DocumentException();
        } catch (IndexOutOfBoundsException e){
        }
        try{
            String brand = rootElement.selectNodes("brand").get(0).getText();
            vehicle.setBrand(brand);
        } catch (Exception e){
        }
        try{
            String model = rootElement.selectNodes("model").get(0).getText();
            vehicle.setModel(model);
        } catch (Exception e){
        }
        try{
            String motorType = rootElement.selectNodes("motorType").get(0).getText();
            switch (motorType){
                case "electric":
                    vehicle.setMotorType(MotorType.ELECTRIC);
                    break;
                case "diesel":
                    vehicle.setMotorType(MotorType.DIESEL);
                case "petrol":
                    vehicle.setMotorType(MotorType.PETROL);
                    break;
                default:
                    throw new UnknwownAttribute(motorType);
            }
        } catch (UnknwownAttribute e){
            System.out.println("here");
            throw new DocumentException();
        } catch (IndexOutOfBoundsException e){
        }
        try{
            String gearBox = rootElement.selectNodes("gearBox").get(0).getText();
            switch (gearBox){
                case "manual":
                    vehicle.setGearBox(GearBox.MANUAL);
                    break;
                case "automatic":
                    vehicle.setGearBox(GearBox.AUTOMATIC);
                default:
                    throw new UnknwownAttribute(gearBox);
            }
        } catch (Exception e){
        }
        try{
            String description = rootElement.selectNodes("description").get(0).getText();
            vehicle.setDescription(description);
        } catch (Exception e){
            throw new DocumentException();
        }
        return vehicle;
    }

    public static String toXML(Vehicle vehicle){

        Document document = DocumentHelper.createDocument();
        Element vehicleAsXML = document.addElement("vehicle");

        //--------------------------------------------------------------------------------REGISTRATION
        if(vehicle.getRegistration() != null){
            vehicleAsXML.addElement("registration").addText(vehicle.getRegistration());
        }
        //--------------------------------------------------------------------------------VEHICLE TYPE
        if(vehicle.getRegistration() != null){
            vehicleAsXML.addElement("type").addText(vehicle.getType().type);
        }
        //--------------------------------------------------------------------------------BRAND
        if(vehicle.getRegistration() != null){
            vehicleAsXML.addElement("brand").addText(vehicle.getBrand());
        }
        //--------------------------------------------------------------------------------MODEL
        if(vehicle.getRegistration() != null){
            vehicleAsXML.addElement("model").addText(vehicle.getModel());
        }
        //--------------------------------------------------------------------------------MOTOT TYPE
        if(vehicle.getRegistration() != null){
            vehicleAsXML.addElement("motorType").addText(vehicle.getMotorType().type);
        }
        //--------------------------------------------------------------------------------GEAR BOX
        if(vehicle.getRegistration() != null){
            vehicleAsXML.addElement("gearBox").addText(vehicle.getGearBox().gearBox);
        }
        //--------------------------------------------------------------------------------DESCRIPTION
        if(vehicle.getRegistration() != null){
            vehicleAsXML.addElement("description").addText(vehicle.getDescription());
        }
        return vehicleAsXML.asXML();
    }
}
