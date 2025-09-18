public class Cashier {
    private String plate;
    private double basePrice;
    private double totalPrice;
    private double discount;
    private double surcharge;
    private double fine = 0;
    private double extraMinutesCharge = 0;

    public Cashier() {
    }

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
