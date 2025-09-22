import javax.swing.JOptionPane;

public class Cashier {
    // Static variables to maintain shift totals
    private static double totalMotorcycles = 0;
    private static double totalCars = 0;
    private static double totalSUVs = 0;
    private static double totalMemberships = 0;
    private static int ticketsMotorcycles = 0;
    private static int ticketsCars = 0;
    private static int ticketsSUVs = 0;
    private static int ticketsMemberships = 0;
    private static double totalFines = 0;
    private static double totalDiscounts = 0;
    private static double totalSurcharges = 0;
    private static double totalShift = 0;
    
    // Individual ticket variables
    private String plate;
    private double basePrice;
    private double totalPrice;
    private double discount;
    private double surcharge;
    private double fine = 0;
    private double extraMinutesCharge = 0;

    public Cashier() {
    }

    // Method to register a payment
    public static void registerPayment(String vehicleType, double amount, double discount, double surcharge, double fine, boolean isMembership) {
        vehicleType = vehicleType.toUpperCase();
        
        if (isMembership) {
            totalMemberships += amount;
            ticketsMemberships++;
        } else {
            switch (vehicleType) {
                case "MOTORCYCLE":
                    totalMotorcycles += amount;
                    ticketsMotorcycles++;
                    break;
                case "CAR":
                    totalCars += amount;
                    ticketsCars++;
                    break;
                case "SUV":
                    totalSUVs += amount;
                    ticketsSUVs++;
                    break;
            }
        }
        
        totalDiscounts += discount;
        totalSurcharges += surcharge;
        totalFines += fine;
        totalShift += amount;
    }

    // Method to show cash register report
    public static void showCashReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== CASH REGISTER REPORT ===\n\n");
        
        report.append("SUBTOTALS BY VEHICLE TYPE:\n");
        report.append(String.format("Motorcycles: $%.2f (%d tickets)\n", totalMotorcycles, ticketsMotorcycles));
        report.append(String.format("Cars: $%.2f (%d tickets)\n", totalCars, ticketsCars));
        report.append(String.format("SUVs: $%.2f (%d tickets)\n", totalSUVs, ticketsSUVs));
        report.append(String.format("Memberships: $%.2f (%d tickets)\n", totalMemberships, ticketsMemberships));
        report.append("\n");
        
        int totalTickets = ticketsMotorcycles + ticketsCars + ticketsSUVs + ticketsMemberships;
        report.append(String.format("TOTAL TICKETS CHARGED: %d\n", totalTickets));
        report.append(String.format("TOTAL FINES: $%.2f\n", totalFines));
        report.append(String.format("TOTAL DISCOUNTS: $%.2f\n", totalDiscounts));
        report.append(String.format("TOTAL SURCHARGES: $%.2f\n", totalSurcharges));
        report.append("\n");
        report.append(String.format("TOTAL SHIFT: $%.2f\n", totalShift));
        
        JOptionPane.showMessageDialog(null, report.toString());
    }

    // Method to close shift
    public static void closeShift() {
        int confirmation = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to close the shift?\n\nThis will reset all totals but keep memberships.", 
            "Close Shift", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmation == JOptionPane.YES_OPTION) {
            // Show final report
            StringBuilder finalReport = new StringBuilder();
            finalReport.append("=== FINAL SHIFT REPORT ===\n\n");
            finalReport.append(String.format("Total collected: $%.2f\n", totalShift));
            finalReport.append(String.format("Tickets processed: %d\n", 
                ticketsMotorcycles + ticketsCars + ticketsSUVs + ticketsMemberships));
            finalReport.append("\nShift closed successfully.");
            
            JOptionPane.showMessageDialog(null, finalReport.toString());
            
            // Reset totals
            totalMotorcycles = 0;
            totalCars = 0;
            totalSUVs = 0;
            totalMemberships = 0;
            ticketsMotorcycles = 0;
            ticketsCars = 0;
            ticketsSUVs = 0;
            ticketsMemberships = 0;
            totalFines = 0;
            totalDiscounts = 0;
            totalSurcharges = 0;
            totalShift = 0;
        }
    }

    // Getters and setters for individual ticket
    public String getPlate() {
        return plate;
    }

    public double getExtraMinutesCharge() {
        return extraMinutesCharge;
    }

    public void setExtraMinutesCharge(double extraMinutesCharge) {
        this.extraMinutesCharge = extraMinutesCharge;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
    }
}
