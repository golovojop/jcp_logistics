package seaport;

public enum DockType {
    FUEL(8500), FOOD(5900), CLOTHES(2700);

    private int capacity;

    DockType(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
