import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Membership {
    static ArrayList<String> membershipsCars = new ArrayList<>();

    public static void membershipsMenu(ArrayList<Vehicle> list) {
        String opt = JOptionPane.showInputDialog("Memberships\n1. Add\n2. Query\n3. Cancel\n4. Back");
        if (opt == null)
            return;

        switch (opt) {
            case "1":
                addMembership(list);
                break;
            case "2":
                queryMembership(list);
                break;
            case "3":
                cancelMembership(list);
                break;
            default:
                break;
        }
    }

    public static void addMembership(ArrayList<Vehicle> list) {
        String plate = askAny("Enter plate for membership:");
        if (plate == null)
            return;
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

        String plan = askValidated("Enter plan (BASICO, PLUS, PREMIUM):", "(?i)BASICO|PLUS|PREMIUM");
        if (plan == null)
            return;

        v.setMembership(plan.toUpperCase());
        membershipsCars.add(plate.toUpperCase());
        System.out.println(plate);
        JOptionPane.showMessageDialog(null, "Membership added");
    }

    public static void queryMembership(ArrayList<Vehicle> list) {
        String plate = askAny("Enter plate to query:");
        if (plate == null)
            return;
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
        String plate = askAny("Enter plate to cancel:");
        if (plate == null)
            return;
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
        membershipsCars.remove(plate);
        JOptionPane.showMessageDialog(null, "Membership cancelled");
    }

    static String askValidated(String prompt, String regex) {
        int attempts = 0;
        while (attempts < 3) {
            String v = JOptionPane.showInputDialog(prompt);
            if (v == null)
                return null;
            if (v.matches(regex))
                return v;
            attempts++;
            JOptionPane.showMessageDialog(null, "Invalid input. Attempts left: " + (3 - attempts));
        }
        JOptionPane.showMessageDialog(null, "Operation cancelled after 3 failed attempts.");
        return null;
    }

    static String askAny(String prompt) {
        int attempts = 0;
        while (attempts < 3) {
            String v = JOptionPane.showInputDialog(prompt);
            if (v == null)
                return null;
            if (!v.trim().isEmpty())
                return v.trim();
            attempts++;
            JOptionPane.showMessageDialog(null, "Empty input. Attempts left: " + (3 - attempts));
        }
        JOptionPane.showMessageDialog(null, "Operation cancelled after 3 failed attempts.");
        return null;
    }

    private static Vehicle findVehicle(ArrayList<Vehicle> list, String plate) {
        for (Vehicle v : list) {
            if (v.getPlate().equalsIgnoreCase(plate)) {
                return v;
            }
        }
        return null;
    }
}
