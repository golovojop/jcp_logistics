package stages;

import transport.Ship;

import java.util.concurrent.TimeUnit;

public class OpenSee extends Stage {

    public OpenSee(int length, String description) {
        super(length, description);
    }

    public void go(Ship ship) {
        try {
            System.out.println(ship.getName() + " начал этап: " + description);
            TimeUnit.MILLISECONDS.sleep(length / ship.getSpeed() * 1000);
            System.out.println(ship.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
