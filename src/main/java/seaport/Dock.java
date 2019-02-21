package seaport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Dock {

    public static final int BUSSY = -1;

    private final int loadingSpeed = 100;
    private final int capacity;
    private ReentrantLock lock;
    private DockType type;
    private int remain;

    public Dock(DockType type) {
        lock = new ReentrantLock();
        this.capacity = type.getCapacity();
        this.remain = this.capacity;
        this.type = type;
    }

    /**
     * @return тип дока
     */
    public DockType getType() {
        return type;
    }

    /**
     * TODO: Ожидание погрузки товара в количестве qty единиц
     * @param qty количество отгружаемого товара
     */
    private void onLoading(int qty) {
        try {
            TimeUnit.MILLISECONDS.sleep((qty / loadingSpeed) * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO: Метод погрузки на корабль
     * @param qty - сколько может принять корабль
     * @return - сколько фактически отгружено,
     *          0 - если док пустой
     *          BUSSY - если занят погрузкой на другой корабль
     */
    public int loadOnShip(int qty) {
        boolean permit = lock.tryLock();
        int actuallyLoaded = BUSSY;

        if(permit) {
            try {
                if (remain > qty) {
                    actuallyLoaded = qty;
                    remain -= qty;
                } else {
                    actuallyLoaded = remain;
                    remain = 0;
                }
                onLoading(actuallyLoaded);
            } finally {
                lock.unlock();
            }
        }
        return actuallyLoaded;
    }
}
