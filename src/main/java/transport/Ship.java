package transport;

import seaport.Dock;
import seaport.DockType;
import seaport.Port;
import stages.Route;
import stages.Stage;

import java.util.List;

import static seaport.DockType.*;

public class Ship extends Thread {
    private List<Dock> docks;
    private Capacity capacity;
    private Route route;
    private String name;
    private Port port;

    public Ship(String name, Capacity capacity, Port port, List<Dock> docks, Route route) {
        this.name = name;
        this.docks = docks;
        this.capacity = capacity;
        this.route = route;
        this.port = port;
    }

    @Override
    public void run() {
        p(String.format("%s вышел из порта", name));

        boolean docksNotEmpty = true;

        // Челночим пока все доки не опустеют
        while (docksNotEmpty) {
            int receivedQty = 0;

            // Передвижение в сторону доков
            for (Stage s : route.getStages()) {
                s.go(this);
            }

            p(String.format("%s подошел к докам", name));

            // Получить груз
            receivedQty = getCargo();
            if(receivedQty == 0) docksNotEmpty = false;

            // Инверсия маршрута
            route.routeBack();

            p(String.format("%s возвращается в порт", name));

            // В сторону порта
            for (Stage s : route.getStages()) {
                s.go(this);
            }

            if(receivedQty != 0) {
                p(String.format("\t%s разгружается", name));
                // Разгрузиться
                port.unloadShip(receivedQty);

                // Инверсия маршрута. Снова в доки за грузом
                route.routeBack();
            }

            if(!docksNotEmpty) {
                p(String.format("\t%s закончил навигацию", name));
            }
        }
    }

    /**
     * TODO: Получить груз
     * @return количество груза
     */
    private int getCargo() {
        int emptyDocks = 0;
        int receivedQty = 0;

        while (receivedQty <= 0) {

            // Попытка загрузиться в любом доке.
            for (Dock dock : docks) {
                // Сколько груза данного типа я могу принять
                int qty = chooseShipCapacity(dock.getType());
                // Получить груз
                receivedQty = dock.loadOnShip(qty);
                // Груз получен
                if (receivedQty > 0) {
                    p(String.format("\t%s получил %d груза в доке %s", name, receivedQty, dock.getType()));
                    return receivedQty;
                }
                // В доке не осталось грузов
                if (receivedQty == 0) emptyDocks++;
            }

            // Все доки пустые. Выход из цикла
            if (emptyDocks == docks.size()) {
                break;
            } else {
                emptyDocks = 0;
            }
        }
        return receivedQty;
    }

    /**
     * @return скорость
     */
    public int getSpeed() {
        return capacity.getSpeed();
    }

    /**
     * @param dt тип дока
     * @return количество груза данного типа, которое можно принять на борт
     */
    private int chooseShipCapacity(DockType dt) {
        int cap = 0;

        switch (dt) {
            case FUEL:
                cap = capacity.getCapacityFuel();
                break;
            case FOOD:
                cap = capacity.getCapacityFood();
                break;
            case CLOTHES:
                cap = capacity.getCapacityClothes();
                break;
        }
        return cap;
    }


    /**
     * @return имя корабля
     */
    public String getShipName() {
        return name;
    }

    /**
     * Вывод в консоль
     */
    private void p(String s) {
        System.out.println(s);
    }

}
