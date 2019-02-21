package transport;

import seaport.Dock;
import stages.Route;

import java.util.List;

public class Ship {
    private List<Dock> docks;
    private Capacity capacity;
    private Route route;

    public Ship(List<Dock> docks, Capacity capacity, Route route) {
        this.docks = docks;
        this.capacity = capacity;
        this.route = route;
    }


}
