package seaport;

import java.util.concurrent.TimeUnit;

public class Dock {
    private final int loadingSpeed = 100;
    private final int capacity;

    private DockType type;
    private int remain;

    public Dock(DockType type) {
        this.capacity = type.getCapacity();
        this.type = type;
    }

    /**
     * Ожидание погрузки товара в количестве qty единиц
     *
     * @param qty количество отгружаемого товара
     */
    private void waitForLoad(int qty) {
        try {
            TimeUnit.MILLISECONDS.sleep((qty / loadingSpeed) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод погрузки на корабль
     *
     * @param max - сколько может принять корабль
     * @return - сколько фактически отгружено
     */

    synchronized int loadToShip(int max) {
        int loaded = 0;

        if (remain > max) {
            loaded = max;
            remain -= max;
        } else {
            loaded = remain;
            remain = 0;
        }

        waitForLoad(loaded);

        return loaded;
    }


}
