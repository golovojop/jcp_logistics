import seaport.Dock;
import seaport.DockType;
import stages.Channel;
import stages.OpenSee;
import stages.Route;
import transport.Capacity;
import transport.Ship;

import java.util.Arrays;

public class MainClass {


    public static void main(String[] args) {
        Dock dockFuel = new Dock(DockType.FUEL);
        Dock dockFood = new Dock(DockType.FOOD);
        Dock dockClothes = new Dock(DockType.CLOTHES);

        Ship tanker = new Ship(Arrays.asList(dockFuel, dockClothes, dockFood),
                new Capacity(700, 500, 300, 70),
                new Route(new OpenSee(300, "sea"), new Channel(200, "channel"), new OpenSee(200, "sea")));

    }
}
