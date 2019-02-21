package seaport;

import java.util.concurrent.TimeUnit;

public class Port {

    private final int loadingSpeed = 100;

    /**
     * TODO: Ожидание погрузки товара в количестве qty единиц
     * @param qty количество отгружаемого товара
     */
    private void onUnloading(int qty) {
        try {
            TimeUnit.MILLISECONDS.sleep((qty / loadingSpeed) * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO: Разгрузить корабль
     * @param qty количество груза
     */
    public void unloadShip(int qty) {
        onUnloading(qty);
    }
}
