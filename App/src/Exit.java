package App.src;

import javax.swing.*;
import java.time.LocalTime;
import java.time.Duration;

public class Exit {

    static final float NIGHT_SURCHARGE = 1.15f;
    static final double MOTORCYCLE_RATE = 2500;
    static final double CAR_RATE = 4000;
    static final double SUV_RATE = 5500; // Corrected from 4500 to 5500 as per chagenlle.md
    static final int ROUNDING_LIMIT = 15;
    static final float WEEKEND_SURCHARGE = 1.10f;
    static final double TICKET_FINE = 20000;
    static final int MINIMUM_FINE_HOURS = 2;

    public void exit(Parking p) {
        // We use a simple loop with a counter to limit the attempts.
        for (int i = 0; i < 3; i++) {
            String plate = JOptionPane.showInputDialog(null, "What is the license plate of the vehicle?");

            if (plate == null) return; // Exit if the user clicks Cancel.

            Vehicle vehicleOut = p.searchByPlate(plate);
            
            if (vehicleOut == null) {
                JOptionPane.showMessageDialog(null, "That plate is not in this parking level.", "Plate Error", JOptionPane.WARNING_MESSAGE);
            } else {
                LocalTime checkOut = LocalTime.now();
                vehicleOut.setCheckOut(checkOut);
                
                // Calculate the duration of the stay.
                Duration duration = Duration.between(vehicleOut.getCheckIn(), checkOut);
                vehicleOut.setDuration(duration);

                if (vehicleOut.getMembership() != null) {
                    JOptionPane.showMessageDialog(null, "This vehicle has a membership: " + vehicleOut.getMembership(), "Membership", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Start the payment calculation.
                    payment(vehicleOut, p);
                }
                // The loop breaks here because a valid plate was found.
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Too many failed attempts. Operation cancelled.", "Cancelled", JOptionPane.ERROR_MESSAGE);
    }

    public void payment(Vehicle vehicleOut, Parking p) {
        
        // --- 1. Calculate Base Fare ---
        double hourlyRate = getHourlyRate(vehicleOut.getType());
        long totalMinutes = vehicleOut.getDuration().toMinutes();
        
        // This is the number of hours, rounded up if the minutes exceed the limit.
        long totalHours = totalMinutes / 60;
        if (totalMinutes % 60 >= ROUNDING_LIMIT) {
            totalHours++;
        }
        
        double baseFare = hourlyRate * totalHours;

        // --- 2. Apply Surcharges ---
        double finalPayment = baseFare;
        boolean isWeekend = vehicleOut.getDayName().equals("SATURDAY") || vehicleOut.getDayName().equals("SUNDAY");
        boolean isNight = isNight(vehicleOut.getCheckOut());

        if (isNight) {
            finalPayment *= NIGHT_SURCHARGE;
        }
        if (isWeekend) {
            finalPayment *= WEEKEND_SURCHARGE;
        }

        // --- 3. Handle Lost Ticket Fine ---
        String[] ticketOptions = {"Yes", "No"};
        String ticketResponse = JOptionPane.showInputDialog(null, "Did the person hand over the ticket?", "Ticket", JOptionPane.QUESTION_MESSAGE, null, ticketOptions, ticketOptions[0]).toString();
        
        if (ticketResponse.equals("No")) {
            // Apply the fine: a fixed amount + an estimated 2 hours minimum.
            finalPayment = TICKET_FINE + (hourlyRate * MINIMUM_FINE_HOURS);
        }

        // --- 4. Apply Discounts (Not implemented in your provided code, but an important step) ---
        // Your logic for discounts would go here.

        // --- 5. Display Receipt and Finalize ---
        // Set the revenue on the vehicle object for the reports.
        // This line assumes you added the 'revenue' field and setter to your Vehicle class.
        // vehicleOut.setRevenue(finalPayment);

        JOptionPane.showMessageDialog(null, "The total to pay is: $" + String.format("%.2f", finalPayment), "Payment Info", JOptionPane.INFORMATION_MESSAGE);

        // This removes the vehicle from the parking level.
        p.removeVehicle(vehicleOut.getPlate());
    }

    // A helper method to get the hourly rate based on vehicle type.
    private double getHourlyRate(String type) {
        return switch (type) {
            case "Motorcycle" -> MOTORCYCLE_RATE;
            case "Car" -> CAR_RATE;
            case "SUV" -> SUV_RATE;
            default -> 0;
        };
    }

    // A helper method to check if the time is considered "night".
    public boolean isNight(LocalTime hora) {
        return hora.isAfter(LocalTime.of(21, 0))
                || hora.isBefore(LocalTime.of(6, 0));
    }
}