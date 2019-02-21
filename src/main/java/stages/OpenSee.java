package stages;

import transport.Ship;

import java.util.concurrent.TimeUnit;

public class OpenSee extends Stage {

    public OpenSee(int length, String description) {
        super(length, description);
    }

    public void go(Ship ship) {
        try {
            p(String.format("%s вошел в '%s'", ship.getShipName(), description));
            TimeUnit.MILLISECONDS.sleep(length / ship.getSpeed() * 100);
            p(String.format("%s вышел из '%s'", ship.getShipName(), description));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
