import javax.swing.*;
import java.time.LocalTime;
import java.time.Duration;

public class Exit {

    static float night = 1.15f;
    static double motorcycle = 2500;
    static double car = 4000;
    static double suv = 4500;
    static int minutesLimit = 15;
    static float weekend = 1.10f;
    static float fine = 20000;

    public void exit(Parking p){
        /// Type, CheckIn, dayName, Level
        String plate = JOptionPane.showInputDialog(null, "What is the license plate of the vehicle?");
        Vehicle vehicleOut = p.searchByPlate(plate);
        if (vehicleOut == null){
            JOptionPane.showMessageDialog(null, "That plate isn't in that parking", "Plate error", JOptionPane.WARNING_MESSAGE);
        }else{
            LocalTime checkOut = LocalTime.now();
            vehicleOut.setCheckOut(checkOut);

            Duration duration = Duration.between(vehicleOut.getCheckIn(), checkOut);
            System.out.println(duration);
            vehicleOut.setDuration(duration);

            int hours = (int) duration.toHours();
//            int hours = 2;
            int minutes = (int) duration.toMinutes();
//            int minutes = 16;

            if (vehicleOut.getMembership() != null){
                JOptionPane.showMessageDialog(null, "That plate has a membership "+vehicleOut.getMembership(), "Membership", JOptionPane.INFORMATION_MESSAGE);
            }else{
                String type = vehicleOut.getType();
                payment(hours, minutes, checkOut, vehicleOut, type);

                p.removeVehicle(plate);
            }
        }

    }

    public void payment(int hours, int minutes, LocalTime checkOut, Vehicle vehicleOut, String type){
        double vehicle = switch (type) {
            case "Motorcycle" -> motorcycle;
            case "Car" -> car;
            case "SUV" -> suv;
            default -> 0;
        };
        double payment = (double) (vehicle * hours);
        if (minutes > minutesLimit) {
            payment += vehicle;
        }
        if (isNight(checkOut)) {
            payment *= night;
        }
        if (vehicleOut.getDayName().equals("SATURDAY") || vehicleOut.getDayName().equals("SUNDAY")) {
            payment *= weekend;
        }

        String[] ticket = {"Yes", "No"};
        String response = JOptionPane.showInputDialog(null, "Did the person hand over the ticket?", "Ticket", JOptionPane.QUESTION_MESSAGE, null, ticket, ticket[0]).toString();
        if (response.equals("No")){
            if (hours > 2){
                payment+=fine;
            }else{
                payment = vehicle * 2;
            }
        }
        
        JOptionPane.showMessageDialog(null, "That license plate has to pay: " + payment, "Payment info", JOptionPane.WARNING_MESSAGE);
    }

    public boolean isNight(LocalTime hora) {
        return hora.isAfter(LocalTime.of(21, 0))
                || hora.equals(LocalTime.of(21, 0))
                || hora.isBefore(LocalTime.of(6, 0));
    }

}
