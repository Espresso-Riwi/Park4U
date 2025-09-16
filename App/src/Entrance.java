import javax.swing.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Entrance {
    public void entrance(HashMap<String, HashMap<String, String>> parkingLevel1){
        HashMap<String, String> vehicleData = new HashMap<>();

        String[] types = {"Motorcycle", "Car", "Truck"};
        String plate = JOptionPane.showInputDialog(null, "What is the license plate of the vehicle?");
        while (plate.length() > 6){
            JOptionPane.showMessageDialog(null, "The plate is to large", "Plate error", JOptionPane.WARNING_MESSAGE);
            plate = JOptionPane.showInputDialog(null, "What is the license plate of the vehicle?");
        }

        String type = JOptionPane.showInputDialog(null, "Select the type of your vehicle: ", "Vehicle type", JOptionPane.QUESTION_MESSAGE, null, types, types[0]).toString();
        String level = "A"+(parkingLevel1.size()+1);

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter hour = DateTimeFormatter.ofPattern("hh:mm");
        LocalDate day =  LocalDate.now();
        String dayString = day.toString();
        String formattedTime = currentTime.format(hour);

        System.out.println(formattedTime+" "+day);
        vehicleData.put("Type", type);
        vehicleData.put("Level", level);
        vehicleData.put("EntryHour", formattedTime);
        vehicleData.put("Day", dayString);

        parkingLevel1.put(plate, vehicleData);

        for (String plates:  parkingLevel1.keySet()){
            System.out.println(parkingLevel1.get(plates));
        }

        /// Type, CheckIn, level
    }
}

