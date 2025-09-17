package App.src;    

import java.util.HashMap;
import java.util.Map;

public class Parking {

    private String level;
    private int capacity;
    private int actualCapacity;
    private Map<String, Vehicle> vehicles;

    public Parking() {

    }

    public Parking(String level, int capacity) {
        this.level = level;
        this.capacity = capacity;
        this.actualCapacity = 0;
        this.vehicles = new HashMap<>();
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (actualCapacity < capacity && !vehicles.containsKey(vehicle.getPlate())) {
            vehicles.put(vehicle.getPlate(), vehicle);
            actualCapacity++;
            return true;
        }
        return false;
    }

    public Vehicle removeVehicle(String plate) {
        Vehicle removed = vehicles.remove(plate);
        if (removed != null) actualCapacity--;
        return removed;
    }

    public Vehicle searchByPlate(String plate) {
        return vehicles.get(plate);
    }

    public Map<String, Vehicle> getVehicles() {
        return vehicles;
    }
}
