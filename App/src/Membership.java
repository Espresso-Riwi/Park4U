package App.src;    

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Membership {

    public static void membershipsMenu(ArrayList<Vehicle> list) {
        String opt = JOptionPane.showInputDialog("Memberships\n1. Add\n2. Query\n3. Cancel\n4. Back");
        if (opt == null) return;

        switch (opt) {
            case "1": addMembership(list); break;
            case "2": queryMembership(list); break;
            case "3": cancelMembership(list); break;
            default: break;
        }
    }

    public static void addMembership(ArrayList<Vehicle> list) {
        String plate = JOptionPane.showInputDialog("Enter plate for membership:");
        if (plate == null) return;
        plate = plate.toUpperCase();

        Vehicle v = findVehicle(list, plate);
        if (v == null) {
            JOptionPane.showMessageDialog(null, "Vehicle not found");
            return;
        }

        if (v.getMembership() != null) {
            JOptionPane.showMessageDialog(null, "Vehicle already has a membership");
            return;
        }

        String plan = JOptionPane.showInputDialog("Enter plan (BASIC, PLUS, PREMIUM):");
        if (plan == null) return;

        v.setMembership(plan.toUpperCase());
        JOptionPane.showMessageDialog(null, "Membership added");
    }

    public static void queryMembership(ArrayList<Vehicle> list) {
        String plate = JOptionPane.showInputDialog("Enter plate to query:");
        if (plate == null) return;
        plate = plate.toUpperCase();

        Vehicle v = findVehicle(list, plate);
        if (v == null) {
            JOptionPane.showMessageDialog(null, "Vehicle not found");
            return;
        }

        String plan = v.getMembership();
        if (plan == null)
            JOptionPane.showMessageDialog(null, "No membership");
        else
            JOptionPane.showMessageDialog(null, "Plan: " + plan);
    }

    public static void cancelMembership(ArrayList<Vehicle> list) {
        String plate = JOptionPane.showInputDialog("Enter plate to cancel:");
        if (plate == null) return;
        plate = plate.toUpperCase();

        Vehicle v = findVehicle(list, plate);
        if (v == null) {
            JOptionPane.showMessageDialog(null, "Vehicle not found");
            return;
        }

        if (v.getMembership() == null) {
            JOptionPane.showMessageDialog(null, "Vehicle has no membership");
            return;
        }

        v.setMembership(null);
        JOptionPane.showMessageDialog(null, "Membership cancelled");
    }

    // 🔹 Busca un vehículo en la lista por placa
    private static Vehicle findVehicle(ArrayList<Vehicle> list, String plate) {
        for (Vehicle v : list) {
            if (v.getPlate().equalsIgnoreCase(plate)) {
                return v;
            }
        }
        return null;
    }
}
