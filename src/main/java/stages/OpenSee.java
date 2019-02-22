package stages;

import org.apache.log4j.Logger;
import transport.Ship;

import java.util.concurrent.TimeUnit;

public class OpenSee extends Stage {

    public OpenSee(int length, String description) {
        super(length, description);
    }

    public void go(Ship ship) {
        Logger logbook = ship.getLogbook();

        try {
            logbook.info(String.format("'%s' вошел в '%s'", ship.getShipName(), description));
            TimeUnit.MILLISECONDS.sleep(length / ship.getSpeed() * 100);
            logbook.info(String.format("'%s' вышел из '%s'", ship.getShipName(), description));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
