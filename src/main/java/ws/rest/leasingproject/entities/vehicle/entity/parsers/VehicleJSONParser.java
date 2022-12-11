package ws.rest.leasingproject.entities.vehicle.entity.parsers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import ws.rest.leasingproject.entities.vehicle.entity.Vehicle;

public class VehicleJSONParser {
    public static Vehicle fromJSON(String vehicleAsJSON){
        try{
            Gson gson = new Gson();
            Vehicle vehicle = gson.fromJson(vehicleAsJSON, Vehicle.class);
            return vehicle;
        } catch (JsonSyntaxException e){
            throw e;
        }
    }

    public static String toJSON(Vehicle v){
        return new Gson().toJson(v);
    }
}
