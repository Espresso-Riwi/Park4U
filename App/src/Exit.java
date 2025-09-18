import javax.swing.*;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;

public class Exit {
    static double night = 0.15f;
    static double motorcycle = 2500;
    static double car = 4000;
    static double suv = 4500;
    static int minutesLimit = 15;
    static double weekend = 0.10f;
    static double fine = 20000;

    public void exit(Parking p, ArrayList<Cashier> payList){
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

//            int hours = (int) duration.toHours();
            int hours = 1;
//            int minutes = (int) duration.toMinutes();
            int minutes = 14;

            if (vehicleOut.getMembership() != null){
                JOptionPane.showMessageDialog(null, "That plate has a membership "+vehicleOut.getMembership(), "Membership", JOptionPane.INFORMATION_MESSAGE);
            }else{
                String type = vehicleOut.getType();
                payment(hours, minutes, checkOut, vehicleOut, type, payList);

                p.removeVehicle(plate);
            }
        }
    }

    public void payment(int hours, int minutes, LocalTime checkOut, Vehicle vehicleOut, String type, ArrayList<Cashier> payList){
        double vehicle = switch (type) {
            case "Motorcycle" -> motorcycle;
            case "Car" -> car;
            case "SUV" -> suv;
            default -> 0;
        };

        double basePrice = vehicle * hours;
        double payment = basePrice;
        Cashier c = new Cashier();

        c.setBasePrice(basePrice);

        if (minutes > minutesLimit) {
            payment = payment + vehicle;
            c.setExtraMinutesCharge(vehicle);
        }

        if (isNight(checkOut)) {
            payment *= (1 + night);
            c.setSurcharge(night);
        }

        if (vehicleOut.getDayName().equals("SATURDAY") || vehicleOut.getDayName().equals("SUNDAY")) {
            payment *= (1 + weekend);
            c.setSurcharge(c.getSurcharge() + weekend);
        }

        String[] ticket = {"Yes", "No"};
        String response = JOptionPane.showInputDialog(null, "Did the person hand over the ticket?", "Ticket", JOptionPane.QUESTION_MESSAGE, null, ticket, ticket[0]).toString();
        if (response.equals(ticket[1])){
            if (hours > 2){
                payment+=fine;
                c.setFine(fine);
            }else{
                payment+=fine;
                payment += vehicle * 2;
                c.setFine(fine+(vehicle * 2));
            }
        }
        c.setTotalPrice(payment);
        c.setPlate(vehicleOut.getPlate());

        payList.add(c);
        JOptionPane.showMessageDialog(null, "Licence plate: "+c.getPlate()+"\nBase price: "+c.getBasePrice()+
                "\nSurcharge percentage: "+(c.getDiscount()*1)+
                "\nDiscount percentage: "+c.getDiscount()+
                "\nFine: "+c.getFine()+ "\nExtra minutes charge: "+c.getExtraMinutesCharge()+
                "\nFull payment:  "+c.getTotalPrice()+
                "\n", "Payment info", JOptionPane.INFORMATION_MESSAGE);

    }

    public boolean isNight(LocalTime hora) {
        return hora.isAfter(LocalTime.of(21, 0))
                || hora.equals(LocalTime.of(21, 0))
                || hora.isBefore(LocalTime.of(6, 0));
    }
}
