import seaport.Dock;
import seaport.DockType;
import seaport.Port;
import stages.Channel;
import stages.OpenSee;
import stages.Route;
import transport.Capacity;
import transport.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.*;


public class MainClass {

    public static void main(String[] args) {

        // Порт разгрузки
        Port port = new Port();

        // Список доков с товаром
        List<Dock> docks = Arrays.asList(
                new Dock(DockType.FUEL),
                new Dock(DockType.FOOD),
                new Dock(DockType.CLOTHES)
        );

        // Маршрут
        Route route = new Route(
                new OpenSee(300, "Акватория порта"),
                new Channel(200, "Пролив"),
                new OpenSee(200, "Акватория доков")
        );

        // Технические характеристики кораблей
        Capacity capFuelTanker      = new Capacity(4000, 2500, 1500, 70);
        Capacity capFoodCargo       = new Capacity(1300, 2100, 1700, 90);
        Capacity capClothesCargo    = new Capacity(800, 1300, 1800, 100);

        // Корабли
        List<Ship> ships = Arrays.asList(
                new Ship("Tanker1", capFuelTanker, port, docks, new Route(route.getStages()), Logger.getLogger("ship1")),
                new Ship("FoodCargo", capFoodCargo, port, docks, new Route(route.getStages()), Logger.getLogger("ship2")),
                new Ship("Tanker2", capFuelTanker, port, docks, new Route(route.getStages()), Logger.getLogger("ship3")),
                new Ship("ClothesCargo", capClothesCargo, port, docks, new Route(route.getStages()), Logger.getLogger("ship4"))
        );

        // Поехали
        for(Ship s : ships) s.start();

        try {
            for(Ship s : ships) s.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
