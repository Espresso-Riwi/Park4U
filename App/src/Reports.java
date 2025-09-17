package App.src;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.Duration;

public class Reports {

    // This method is the main entry point for the reports menu.
    public static void reportsMenu(Parking p1, Parking p2, Parking p3, Parking p4,
            ArrayList<Vehicle> todayVehiclesList) {

        String[] options = { "Show Occupancy", "Show Average Hours", "Show Top 3 Earners", "Back" };
        String option = JOptionPane.showInputDialog(null, "Select a report:", "Reports Menu",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();

        switch (option) {
            case "Show Occupancy":
                showOccupancy(p1, p2, p3, p4);
                break;
            case "Show Average Hours":
                showAverageHours(todayVehiclesList);
                break;
            case "Show Top 3 Earners":
                showTopEarners(todayVehiclesList);
                break;
            case "Back":
                // Just go back to the main menu.
                break;
        }
    }

    // This report shows how full each parking level is.
    public static void showOccupancy(Parking p1, Parking p2, Parking p3, Parking p4) {

        // These are the total capacities for each level, as specified in the problem.
        int level1Capacity = 30;
        int level2Capacity = 40;
        int level3Capacity = 40;
        int level4Capacity = 50;

        String occupancyReport = "--- Parking Occupancy ---\n\n" +
                "Level 1: " + p1.getVehicles().size() + " / " + level1Capacity + " vehicles\n" +
                "Level 2: " + p2.getVehicles().size() + " / " + level2Capacity + " vehicles\n" +
                "Level 3: " + p3.getVehicles().size() + " / " + level3Capacity + " vehicles\n" +
                "Level 4: " + p4.getVehicles().size() + " / " + level4Capacity + " vehicles\n";

        JOptionPane.showMessageDialog(null, occupancyReport, "Occupancy Report", JOptionPane.INFORMATION_MESSAGE);
    }

    // This report calculates and displays the average hours for each vehicle type.
    public static void showAverageHours(ArrayList<Vehicle> vehicles) {

        long carTotalHours = 0;
        int carCount = 0;
        long motorcycleTotalHours = 0;
        int motorcycleCount = 0;
        long suvTotalHours = 0;
        int suvCount = 0;

        // We go through all the vehicles that have completed their stay.
        for (Vehicle v : vehicles) {
            // We only count vehicles that have a set duration (meaning they have exited).
            if (v.getDuration() != null) {
                // Get the duration in hours.
                long durationInHours = v.getDuration().toHours();

                // Now, we add the hours to the correct vehicle type.
                switch (v.getType()) {
                    case "Car":
                        carTotalHours += durationInHours;
                        carCount++;
                        break;
                    case "Motorcycle":
                        motorcycleTotalHours += durationInHours;
                        motorcycleCount++;
                        break;
                    case "SUV":
                        suvTotalHours += durationInHours;
                        suvCount++;
                        break;
                }
            }
        }

        // Now, we calculate the averages. We must be careful not to divide by zero!
        String report = "--- Average Hours Report ---\n\n";
        report += "Average hours for Cars: " + (carCount > 0 ? (double) carTotalHours / carCount : "No data") + "\n";
        report += "Average hours for Motorcycles: "
                + (motorcycleCount > 0 ? (double) motorcycleTotalHours / motorcycleCount : "No data") + "\n";
        report += "Average hours for SUVs: " + (suvCount > 0 ? (double) suvTotalHours / suvCount : "No data") + "\n";

        JOptionPane.showMessageDialog(null, report, "Average Hours", JOptionPane.INFORMATION_MESSAGE);
    }

    // This report shows the top 3 vehicles that generated the most revenue.
    public static void showTopEarners(ArrayList<Vehicle> vehicles) {

        // IMPORTANT: The 'Vehicle' class needs a 'revenue' field for this to work.
        // You'll need to add 'private double revenue;' and a 'setRevenue' method to
        // 'Vehicle.java'.
        // And update 'Exit.java' to call 'setRevenue' after calculating the payment.

        // We sort the list of vehicles by their revenue, from highest to lowest.
        vehicles.sort(Comparator.comparing(Vehicle::getRevenue).reversed());

        String topEarnersReport = "--- Top 3 Vehicles by Revenue ---\n\n";
        int count = 0;
        for (Vehicle v : vehicles) {
            if (v.getRevenue() > 0 && count < 3) {
                topEarnersReport += "Plate: " + v.getPlate() + " - Revenue: $" + String.format("%.2f", v.getRevenue())
                        + "\n";
                count++;
            }
        }

        if (count == 0) {
            topEarnersReport += "No revenue data available yet.";
        }

        JOptionPane.showMessageDialog(null, topEarnersReport, "Top Earners Report", JOptionPane.INFORMATION_MESSAGE);
    }
}