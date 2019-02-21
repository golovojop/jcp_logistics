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
            // Вход в пролив
            semaphore.acquire();

            p(String.format("%s вошел в '%s'", ship.getShipName(), description));
            TimeUnit.MILLISECONDS.sleep(length / ship.getSpeed() * 100);
            p(String.format("%s вышел из '%s'", ship.getShipName(), description));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

}
