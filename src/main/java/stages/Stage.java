package stages;

import transport.Ship;

import java.util.concurrent.TimeUnit;

public abstract class Stage {
    protected int length;
    protected String description;

    public Stage(int length, String description) {
        this.length = length;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public abstract void go(Ship ship);
}
