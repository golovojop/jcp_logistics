import seaport.Dock;
import seaport.DockType;
import stages.Channel;
import stages.OpenSee;
import stages.Route;
import transport.Capacity;
import transport.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClass {


    public static void main(String[] args) {

        // Список доков с товаром
        List<Dock> docks = Arrays.asList(
                new Dock(DockType.FUEL),
                new Dock(DockType.FOOD),
                new Dock(DockType.CLOTHES)
        );

        // Маршрут
        Route route = new Route(
                new OpenSee(300, "sea1"),
                new Channel(200, "channel"),
                new OpenSee(200, "sea2")
        );

        // Технические характеристики кораблей
        Capacity capFuelTanker      = new Capacity(700, 500, 300, 70);
        Capacity capFoodCargo       = new Capacity(300, 700, 300, 90);
        Capacity capClothesCargo    = new Capacity(300, 300, 800, 100);

        // Корабли
        List<Ship> ships = Arrays.asList(
                new Ship("Tanker1", capFuelTanker, docks, new Route(route.getStages())),
                new Ship("FoodCargo", capFoodCargo, docks, new Route(route.getStages())),
                new Ship("Tanker2", capFuelTanker, docks, new Route(route.getStages())),
                new Ship("ClothesCargo", capClothesCargo, docks, new Route(route.getStages()))
        );

        // Поехали
        for(Ship s : ships) s.start();

        try {
            for(Ship s : ships) s.join();
        } catch (InterruptedException e) {e.printStackTrace();}
    }
}
