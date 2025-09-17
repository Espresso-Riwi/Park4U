public class Parking {

    private String level;
    private int capacity;
    private int actualCapacity;

    public Parking() {

    }

    public Parking(String level, int capacity, int actualCapacity) {
        this.level = level;
        this.capacity = capacity;
        this.actualCapacity = actualCapacity;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getActualCapacity() {
        return actualCapacity;
    }

    public void setActualCapacity(int actualCapacity) {
        this.actualCapacity = actualCapacity;
    }
}
