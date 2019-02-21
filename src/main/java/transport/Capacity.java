package transport;

public class Capacity {
    private int capacityFuel;
    private int capacityFood;
    private int capacityClothes;
    private int speed;

    public Capacity(int capacityFuel, int capacityFood, int capacityClothes, int speed) {
        this.capacityFuel = capacityFuel;
        this.capacityFood = capacityFood;
        this.capacityClothes = capacityClothes;
        this.speed = speed;
    }

    public int getCapacityFuel() {
        return capacityFuel;
    }

    public int getCapacityFood() {
        return capacityFood;
    }

    public int getCapacityClothes() {
        return capacityClothes;
    }

    public int getSpeed() {
        return speed;
    }
}
