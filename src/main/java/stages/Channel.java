package stages;

import transport.Ship;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Channel extends Stage {

    Semaphore semaphore = new Semaphore(2);

    public Channel(int length, String description) {
        super(length, description);
    }

    public void go(Ship ship) {
        try {
            semaphore.acquire();
            System.out.println(ship.getName() + " начал этап: " + description);
            TimeUnit.MILLISECONDS.sleep(length / ship.getSpeed() * 1000);
            System.out.println(ship.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

}
