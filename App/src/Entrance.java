import javax.swing.*;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;

public class Entrance {

    public String chooseParking(Parking p1, Parking p2, Parking p3, Parking p4){
        String[] levels = {"Level 1", "Level 2", "Level 3", "Level 4"};
        String level = JOptionPane.showInputDialog(null, "What level is the vehicle at?", "Level", JOptionPane.QUESTION_MESSAGE, null, levels, levels[0]).toString();
        
        switch (level){
            case "Level 1":
                p1.setActualCapacity(p1.getActualCapacity() + 1);
            case "Level 2":
                p1.setActualCapacity(p1.getActualCapacity() + 1);
            case "Level 3":
                p1.setActualCapacity(p1.getActualCapacity() + 1);
            case "Level 4":
                p1.setActualCapacity(p1.getActualCapacity() + 1);
        }
        
        return level;
    }

    public void entrance(String level, Parking p, ArrayList<Vehicle> list){
        Vehicle v = new Vehicle();
        String[] types = {"Motorcycle", "Car", "SUV"};
        String plate = JOptionPane.showInputDialog(null, "What is the license plate of the vehicle?");
        while (plate.length() > 6){
            JOptionPane.showMessageDialog(null, "The plate is to large", "Plate error", JOptionPane.WARNING_MESSAGE);
            plate = JOptionPane.showInputDialog(null, "What is the license plate of the vehicle?");
        }

        String type = JOptionPane.showInputDialog(null, "Select the type of your vehicle: ", "Vehicle type",
                JOptionPane.QUESTION_MESSAGE, null, types, types[0]).toString();

        LocalTime checkIn = LocalTime.now();

        LocalDate today = LocalDate.now();
        String dayName = today.getDayOfWeek().toString();

        v.setPlate(plate);
        v.setType(type);
        v.setCheckIn(checkIn);
        v.setDayName(dayName);
        v.setLevel(level);

        list.add(v);
        /// Type, CheckIn, level
    }
}

